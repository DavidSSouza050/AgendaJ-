import React,{Component} from 'react';
import "../css/reset.css";
import "../css/style.css";
import "../css/bootstrap.css";
import Tabs, {Tab} from 'react-bootstrap/Tabs'
import ativado from "../icones/ativado.png";
import editar from "../icones/editar.png";
import api from "../utils/http";
import axios from 'axios';
import getItem from "../utils/sessionStorage";
import CadastroServicos from './cadastroServicos';

// Constantes Globais
const ip = api();
const token = getItem('token');
const config = {
    'token': token
}


export default class ServicosCadastrados extends Component{

    constructor(){
        super();
        this.state = {
            listaServicos:[],
        }

        this.buscarServicos = this.buscarServicos.bind(this);
    }

    componentDidMount(){
        this.buscarServicos();
    }

    buscarServicos(){
        const estabelecimento = JSON.parse(getItem("estabelecimento"));
        const idEstabelecimento = estabelecimento.idEstabelecimento;
        const url = "http://"+ip+"/servicos/estabelecimento/"+idEstabelecimento;

        axios({
            method:"get",
            url:url,
            headers:config,
        })
        .then((response)=>{
            console.log(response.data);
            this.setState({listaServicos:response.data});
        })

    }
    
    render(){
        return(
            <>
                <div className="card mb-4">
                    <div className="card-header text-dark"> 
                        <h1>Serviços Cadastrados</h1>
                    </div>
                </div>
                
                <div className="container ml-4">
                    
                    <h4 className="mb-4 text-dark" >Aqui você terá acesso aos dados de todos os serviços cadastrados</h4>
                    
                          
                    <Tabs defaultActiveKey="listaDeServicos" id="tab" className="bg-light w-100">
                        <Tab eventKey="listaDeServicos" title="Lista De Servicos">
                            <div className="mt-5">
                                <table className="table">
                                    <thead className="thead-light">
                                        <th className="align-middle h5 font-weight-bold text-center">Serviço</th>
                                        <th className="align-middle h5 font-weight-bold text-center">Valor</th>
                                        <th className="align-middle h5 font-weight-bold text-center">Duração (Minutos)</th>
                                        <th className="align-middle h5 font-weight-bold text-center">Operações</th>
                                    </thead>
                                    <tbody>
                                       
                                            {this.state.listaServicos.map(
                                                servicos =>
                                                    <tr>
                                                        <td className="align-middle text-center" >{servicos.servico}</td>
                                                        <td className="align-middle text-center">{"R$ "+ parseFloat(servicos.preco).toFixed(2).replace(".",",")}</td>
                                                        <td className="align-middle text-center">{servicos.duracaoServico + " min"}</td>
                                                        <td className="align-middle text-center">
                                                            <div className="h-100 w-50 mx-auto">
                                                                <div className="float-left col-6">
                                                                    <img className="img-logout mx-auto d-block " src={ativado} alt="ativado"/>
                                                                    <p className="card-text text-center text-dark" >Ativo</p>
                                                                </div>
                                                                <div className="float-left col-6">
                                                                    <img className="img-logout mx-auto d-block" src={editar} alt="editar"/>
                                                                    <p className="card-text text-center text-dark" >Editar</p>
                                                                </div> 
                                                            </div>  
                                                        </td>
                                                    </tr>    
                                            )}
                                    </tbody>
                                </table>
                               
                            </div>    
                        </Tab>
                        <Tab eventKey="cadastroDeServicos" title="Cadastro de Servicos">
                            <div className="mt-5">
                                <CadastroServicos/>                               
                            </div>    
                        </Tab>
                            
                    </Tabs>
                </div>
            </>

        );
    }
}
