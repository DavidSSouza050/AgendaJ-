import React, { Component } from 'react';
import "../css/style.css";
import "../css/bootstrap.css";
import logout from "../icones/logout.png";
import home from "../icones/home.png"
import servico from "../icones/servicos.png"
import funcionario from "../icones/funcionario.png"
import portifolio from "../icones/portifolio.png"
import agendamentos from "../icones/agendamentos.png"
import dados from "../icones/dados.png"
import historico from "../icones/historico.png"
import avaliacoes from "../icones/avaliacoes.png"
import { Link } from 'react-router-dom';
import getItem from '../utils/sessionStorage';
import {serverImagem} from "../utils/http";
const ip = serverImagem();

export default class MenuLateral extends Component {

    constructor(){
        super();
        this.state = { 
            nomeEstabelecimento:"",
            urlImagem:""
        }
    }

    componentDidMount(){
        let estabelecimento = JSON.parse(getItem("estabelecimento"));
        this.setState({nomeEstabelecimento:estabelecimento.nomeEstabelecimento});
        this.setState({urlImagem:estabelecimento.foto})
    }

    render() {
        return(
            <div className="menu-sistema float-left h-100 bg-white">
                <InfoUsuario nomeEstabelecimento={this.state.nomeEstabelecimento} urlImagem={this.state.urlImagem} agendamentos="20"></InfoUsuario>
                <Menu></Menu>
            </div>    
        );
    }
}

export class InfoUsuario extends Component{


    render(){
        const imagem = ip + this.props.urlImagem;
        return(
            <div className="w-100" id="dados-usuario">
                <div className="text-white pt-2 h5 pl-4">
                    Informações
                </div>
                <div className="w-100 dados">
                    <div className="h-100 center-full caixa float-left mr-4">
                        <div className="circulo align-self-center shadow rounded-circle bg-light">
                            <figure>
                                <img className="circulo rounded-circle" src={imagem} alt={this.props.nomeEstabelecimento} />
                            </figure>    
                        </div>
                    </div>
                    <div className="h-100 w-50 d-block float-left">
                        <div className="h-75 w-100 mt-2 caixa2 pt-3 text-white">
                            <p className="mt-1">
                                <span className="mr-3">Nome:</span>{this.props.nomeEstabelecimento}</p>
                            <p className="mt-3">
                                <span className="mr-3">Nº Agendamentos:</span>{this.props.agendamentos}
                            </p>
                        </div>
                    </div>
                </div>
                <div className="h-25 d-flex flex-row-reverse w-100">
                    <Link to="/Logout">
                        <div className="w-75 h-50 mr-5">
                            <div className="w-25 h-100 mr-2 pb-2 float-left"> 
                                <figure>
                                    <img className="img-logout" src={logout} alt="logout"/>
                                </figure>
                            </div>
                            <div className="w-50 h-100 float-left font-weight-bold text-white"> 
                                Sair
                            </div>
                        </div>
                    </Link>
                </div>
            </div>
        );
    }
}

export class Menu extends Component {
    
    render(){
        const corItemMenu = {
            "backgroundColor":"#462e72"
        }

        return(
            <nav className="w-100 h-100">
                <div className="text-white h5 pt-2 pl-4 mb-3">
                    <h5> Menu</h5>
                </div>
                <div className="container-menu" id="menu">
                    <Link to="/estabelecimento/primeiros-passos">
                        <div className="card mb-1 border-0">
                            <div className="card-header-size card-header" id="headingOne" style={corItemMenu}>
                                <h5 className="mb-0">
                                    <button className="btn text-white" type="button-link">
                                        <img className="icone float-left mt-1" src={home} alt="icone-home"/>
                                        <span className="float-left ">Home</span>
                                    </button>
                                </h5>
                            </div>
                        </div>
                    </Link>
                    <Link to="/estabelecimento/agendamentos-pendentes">
                        <div className="card mb-1 border-0">
                            <div className="card-header-size card-header" id="headingOne" style={corItemMenu}>
                                <h5 className="mb-0">
                                    <button className="btn text-white" type="button-link">
                                        <img className="icone float-left mt-1" src={agendamentos} alt="icone-agendamentos"/>
                                        <span className="float-left ">Agendamentos Pendentes</span>
                                    </button>
                                </h5>
                            </div>
                        </div>
                    </Link>
                    <Link to="/estabelecimento/servicos-cadastrados">
                        <div className="card mb-1 border-0">
                            <div className="card-header-size card-header" id="headingOne" style={corItemMenu}>
                                <h5 className="mb-0">
                                    <button className="btn text-white" type="button-link">
                                        <img className="icone float-left mt-1" src={servico} alt="icone-agendamentos"/>
                                        <span className="float-left ">Serviços</span>
                                    </button>
                                </h5>
                            </div>
                        </div>
                    </Link>
                    <Link to="/estabelecimento/funcionario">
                        <div className="card mb-1 border-0">
                            <div className="card-header-size card-header" id="headingOne" style={corItemMenu}>
                                <h5 className="mb-0">
                                    <button className="btn text-white" type="button-link">
                                        <img className="icone float-left mt-1" src={funcionario} alt="icone-agendamentos"/>
                                        <span className="float-left ">Funcionário</span>
                                    </button>
                                </h5>
                            </div>
                        </div>
                    </Link>
                    <div className="card mb-1 border-0">
                        <div className="card-header-size card-header" id="headingOne" style={corItemMenu}>
                            <h5 className="mb-0">
                                <button className="btn text-white" type="button-link">
                                    <img className="icone float-left mt-1" src={portifolio} alt="icone-portifolio"/>
                                    <span className="float-left ">Portifólio</span>
                                </button>
                            </h5>
                        </div>
                    </div>
                    <div className="card mb-1 border-0">
                        <div className="card-header-size card-header" id="headingOne" style={corItemMenu}>
                            <h5 className="mb-0">
                                <button className="btn text-white" type="button-link">
                                    <img className="icone float-left mt-1" src={dados} alt="icone-home"/>
                                    <span className="float-left ">Dados do Estabelecimento</span>
                                </button>
                            </h5>
                        </div>
                    </div>
                    <div className="card mb-1 border-0">
                        <div className="card-header-size card-header" id="headingOne" style={corItemMenu}>
                            <h5 className="mb-0">
                                <button className="btn text-white" type="button-link">
                                    <img className="icone float-left mt-1" src={avaliacoes} alt="icone-avaliações"/>
                                    <span className="float-left ">Avaliações</span>
                                </button>
                            </h5>
                        </div>
                    </div>
                    <div className="card mb-1 border-0">
                        <div className="card-header-size card-header" id="headingOne" style={corItemMenu}>
                            <h5 className="mb-0">
                                <button className="btn text-white" type="button-link">
                                    <img className="icone float-left mt-1" src={historico} alt="icone-historico"/>
                                    <span className="float-left ">Histórico</span>
                                </button>                               
                            </h5>
                        </div>
                    </div>
                </div>
            </nav>
        );
    }
}

