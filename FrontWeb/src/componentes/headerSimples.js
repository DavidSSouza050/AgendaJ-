import React, { Component } from 'react';
import "../css/style.css";
import "../css/bootstrap.css";
import logo from "../imagens/logo_mobile_sem_fundo.png";
import logo2 from "../imagens/versaoFinalLogoAgendaJa.png"

export default class HeaderSimples extends Component {
    render() {
        return (
            <header id='header_cadastro'>
                <div className="center vertical_center" id="icone_logo">
                    <img id="logo" src={logo} alt="Logotipo AgendaJá"/> 
                </div>
            </header>
        );
    }
}

export class HeaderSistema extends Component{


    render(){
        
        const altura = {
            'height':'6vh' 
        }
        
        return(
            <header className="header-sistema container-fluid shadow ">
                <div className="w-25 h-75 ml-4 pt-2">
                    <figure>
                        <img className="img-logo-sistema img-fluid" src={logo2} alt="logocompleto" style={altura}/>
                    </figure>
                </div>
            </header>
        );
    }
}