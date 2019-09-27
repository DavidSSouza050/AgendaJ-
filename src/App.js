import React, { Component } from 'react';
//import EscolhaConta from './componentes/escolhaConta';
import "./css/style.css";
import "./css/styleFont.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import CadastroUsuario from "./componentes/cadastroUsuario";
//import LandingPage from "./componentes/landingPage";

class App extends Component {

    componentDidMount(){
        window.document.body.className = "body_cadastro back";
        document.title = "AgendaJá - Cadastro de Estabelecimento";
    }

    render(){
        return (
        
            <CadastroUsuario></CadastroUsuario>            
        );
    };
}

export default App;