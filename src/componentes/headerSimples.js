import React, { Component } from 'react';
import "../css/style.css";
import "../css/bootstrap.css";
import logo from "../imagens/logo_mobile_sem_fundo.png";

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