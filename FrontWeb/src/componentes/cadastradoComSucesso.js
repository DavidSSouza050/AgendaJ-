import React, { Component } from 'react';
import "../css/style.css";
import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Checked from "../icones/checked.png";
import { Link} from 'react-router-dom'; 
import Button from './botao';


export default class CadastradoComSucesso extends Component {

    componentWillMount(){
        document.body.className = "body_cadastro back overflow-hidden center-full";
        document.title = "AgendaJá - Cadastrado com Sucesso";
    }

    render() {
        const container_cadastrado_com_sucesso = {
            width: "900px",
            height: "450px",
            marginTop: "80px"
        }

        const texto = {
            lineHeight: "1.6"
        }
        return (
            <div className="container bg-light align-middle rounded px-4 py-3 shadow-lg" style={container_cadastrado_com_sucesso}>
                <div className="w-100 h-auto pt-3">
                    <span className="h3 mb-4 text-dark">Cadastro realizado com Sucesso!</span>
                    <hr className="hr w-100"/>
                </div>
                <div className="w-100 h-50 d-flex flex-row">
                    <div className="p2 h-100 w-25">
                        <img className="img-fluid mt-4 ml-4 h-75 w-75" src={Checked} alt="ok"/>  
                    </div>
                    <div className="p2 h-100 w-75 px-4">
                        <span className="mx-5 my-5">
                            <p style={texto} className="text-justify text-black-50 font-weight-normal texto_cadastrado_com_sucesso">
                                Seu cadastro foi realizado com sucesso, apartir de agora você poderá gerenciar os agendamentos dos seus serviços por meio da nossa plataforma. Se desejar realizar o Login pela primeira vez basta clicar no botão abaixo que o redirecionará para a página de Login.
                            </p>
                        </span>
                    </div>  
                </div> 

                <div className="w-100 d-flex flex-row-reverse mt-5">
                    <Link to="/">
                        <Button type="button" classButton="bg-success btn-lg shadow btn text-light" id="btn_login" nameButton="Fazer Login"/>
                    </Link>
                </div>
            </div>
        );
    }
}
