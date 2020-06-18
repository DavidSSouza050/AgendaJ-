import React,{Component} from 'react';
import Tabs, { Tab } from 'react-bootstrap/Tabs'
import {CardFuncionario} from './cards';
import "../css/reset.css";
import "../css/style.css";
import "../css/bootstrap.css";
import getItem from "../utils/sessionStorage";
import axios from "axios"
import api from "../utils/http";

// Constantes Globais
const ip = api();
const token = getItem('token');
const config = {
    'token': token
}

export default class Funcionario extends Component{

    constructor(){
        super()
        this.state = {
            listaFuncionario:[]
        }

        this.buscarFuncionarios = this.buscarFuncionarios.bind(this);
    }

    componentDidMount(){
        this.buscarFuncionarios()
    }

    buscarFuncionarios(){
        const estabelecimento = JSON.parse(getItem('estabelecimento'));
        const idEstabelecimento = estabelecimento.idEstabelecimento;
        const url = "http://"+ip+"/funcionariosEstabelecimentos/estabelecimento/"+idEstabelecimento

        console.log("teste")
        axios({
            method:"get",
            url:url,
            headers:config,
        })
        .then((response)=>{
            console.log(response.data);
            this.setState({listaFuncionario:response.data});
        })

    }

    render(){        
        return(
            <>   
                <div className="card mx-auto mt-1 mb-4">
                    <div className="card-header"> 
                        <h1>Funcionário</h1>
                    </div>
                </div>
                <div className="container padding mt-5 ml-4" id="container-tab">
                    <Tabs defaultActiveKey="listaDeFuncionário" id="tab" className="bg-light w-100">
                            <Tab eventKey="listaDeFuncionário" title="Lista de Funcionários">
                                <div className="mt-3">
                                    <div className="card-deck mt-4 float-left">
                                       {this.state.listaFuncionario.map(
                                           funcionario=>  <CardFuncionario
                                                            nome={funcionario.nome}
                                                            email={funcionario.nome+"@gmail.com"}
                                                            foto={funcionario.foto}
                                                            cargo="Cabelereiro e Barberiro"
                                                           />
                                        )}
                                       
                                    </div>
                                </div>    
                            </Tab>
                            <Tab eventKey="cadastroFuncionario" title="Cadastro de Funcionário">
                                <div className="mt-5">    
                            
                                </div>
                            </Tab>
                        </Tabs>
                </div>                        
            
        </>    
        );
    }
}
