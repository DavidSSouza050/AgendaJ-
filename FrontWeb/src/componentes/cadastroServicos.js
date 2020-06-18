import React,{Component} from 'react';
import "../css/reset.css";
import "../css/style.css";
import "../css/bootstrap.css";
import getItem from "../utils/sessionStorage";
import api from "../utils/http";
import axios from 'axios';

// Constantes Globais
const ip = api();
const token = getItem('token');
const config = {
    'token': token
}

export default class CadastroServicos extends Component{
    
    constructor(){
        super();
        this.state = {
            listaCategoria:[]
        }

        this.buscarCategoria = this.buscarCategoria.bind(this);
    }

    componentDidMount(){
        this.buscarCategoria();
    }

    buscarCategoria(){

        const url = "http://"+ip+"/categoriaServicos";

        axios({
            method:"get",
            url:url,
            headers:config,
        })
        .then((response)=>{
            console.log(response.data);
            this.setState({listaCategoria:response.data});
        })
    }

    render(){
        return(     
            <div className="container w-75 h-50 mx-auto mt-4 border p-4">
                <form>
                
                <div className="form-group">
                    <label htmlFor="inputAddress">Nome do Serviço</label>
                    <input type="text" className="form-control" id="inputServico"/>
                </div>
                <div className="form-group">
                    <label htmlFor="inputAddress2">Categoria do Serviço</label>
                    <select id="inputCategoria" className="form-control">

                        <option selected>Selecione uma Categoria de Serviço</option>
                        {this.state.listaCategoria.map(
                            categoria=><option value={categoria.idCategoriaServico}>{categoria.categoriaServico}</option>
                        )}
                        
                    </select>
                </div>
                <div className="form-row">
                    <div className="form-group col-md-6">
                        <label htmlFor="inputEmail4">Preço</label>
                        <input type="text" className="form-control" id="inputPreco"/>
                    </div>
                    <div className="form-group col-md-6">
                        <label htmlFor="inputPassword4">Duração</label>
                        <input type="text" className="form-control" id="inputDuracao"/>
                    </div>
                    
                </div>
                <div className="form-row mt-2">
                    <div className="form-group d-flex flex-row-reverse col-12">
                        <button className="btn text-white text-center bg-success" type="submit">Enviar</button>
                    </div>
                </div>
                    
                </form>
            </div>
        );
    }
}
