import React, { Component } from 'react';
import "../css/style.css";
import "../css/reset.css";
import "../css/bootstrap.css"
import logoCompleto from "../imagens/versaoFinalLogoAgendaJa.png";
import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";
import {InputWithPlaceholder} from './input';
import MensagemErro from './mensagemErro';
import axios from 'axios';
import PropTypes from "prop-types";
import {Link} from 'react-router-dom';
// import sha1 from 'sha1';
import { setItem, removeItem } from '../utils/sessionStorage';
import api from "../utils/http";
const ip = api();

export default class Login extends Component {

    constructor(props){
        super(props);
        this.state = {
            email:"",
            senha:"",
            showErroLogin:false,
            erroLogin:"Usuário não encontrado! Verifique se o email e senha estão corretos",
            showErroEmail:false,
            erroEmail:"O Email não pode estar vazio",
            showErroSenha:false,
            erroSenha:"A senha não pode estar vazio"
        };

        this.setEmail = this.setEmail.bind(this);
        this.setSenha = this.setSenha.bind(this);
        this.getLoginToken = this.getLoginToken.bind(this);
        this.fazerLogin = this.fazerLogin.bind(this);
        this.renderizarErroLogin = this.renderizarErroLogin.bind(this);
        this.renderizarErroEmail = this.renderizarErroEmail.bind(this);
        this.renderizarErroSenha = this.renderizarErroSenha.bind(this);
        this.validarCaixa = this.validarCaixa.bind(this);
    }

    //Propriedades do WithRouter
    static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    };

    setEmail(evento){
        this.setState({email:evento.target.value})
    }

    setSenha(evento){
        this.setState({senha: evento.target.value});
    }

    validarCaixa(){
        let txtEmail = document.querySelector("#txt-email");
        let txtSenha = document.querySelector("#txt-senha");
        let wasValidated = true;

        if(txtEmail.value === ""){        
            this.setState({showErroEmail:true})
            wasValidated = false;
        }else{
            this.setState({showErroEmail:false})
        }

        if(txtSenha.value === ""){
            this.setState({showErroSenha:true})
            wasValidated = false;
        }else{
            this.setState({showErroSenha:false})
        }

        return wasValidated;
    }

    getLoginToken(evento){
        evento.preventDefault();

        if(this.validarCaixa()){
            this.setState({showErroLogin:false});
            const urlGetTolken = "http://"+ip+"/login/estabelecimento"
                
            let login = {
                email: this.state.email,
                // password: sha1(this.state.senha)
                password: this.state.senha
            }
            
            axios.post(urlGetTolken, login)
                .then((response) =>{
                    let token = response.data.token;
                    setItem("token", token);
                    this.fazerLogin(token);
                })
                .catch(()=>{
                    removeItem("token");
                })

        }    

    }

    fazerLogin(token){
        const urlLogin = "http://"+ip+"/estabelecimentos/login";

        let config = {
            'token': token
        }

        let login = {
            email: this.state.email,
            senha: this.state.senha
            // senha: sha1(this.state.senha)
        }

        axios({ 
            method: 'post', 
            url: urlLogin, 
            data:login,
            headers: config })
            .then((response)=>{
                let estabelecimento = JSON.stringify(response.data);
                setItem("estabelecimento", estabelecimento);
                this.props.history.push("/estabelecimento/primeiros-passos");
            })
            .catch(()=>{
                this.setState({showErroLogin:true});
            })
        
    }

    componentDidMount(){
        window.document.body.className = "background_login back";
        document.title = "AgendaJá - Login";
    }

    renderizarErroEmail(){
        if(this.state.showErroEmail === true ){
            return (
                <MensagemErro
                className="form-group col-sm-12 text-danger"
                id="erroEmailMessage"
                mensagemErro={this.state.erroEmail}/>
            );
        }
    } 

    renderizarErroSenha(){
        if(this.state.showErroSenha === true ){
            return (
                <MensagemErro
                className="form-group col-sm-11 text-danger"
                id="errorSenhaMessage"
                mensagemErro={this.state.erroSenha}/>
            );
        }
    } 

    renderizarErroLogin(){
        if(this.state.showErroLogin === true ){
            return (
                <MensagemErro
                className="form-group col-sm-11 text-danger"
                id="errorLoginMessage"
                mensagemErro={this.state.erroLogin}/>
            );
        }
    } 

    render() {
        const login = {
            marginTop: "125px"
        }

        const link = {
            textDecoration: "none",
            color:"#2C3650"
        }

        return(
            <div id="container_all_login" style={login}>
                <div className="center align-middle shadow-lg rounded" id="container_form_login">
                    <div id="container_titulo_form">
                        <span className="col-2 h-100 align-middle h2 text-center text-dark">Entre já em Nossa Plataforma</span>
                    </div>
                    <div id="container_logotipo">
                        <img className="center" id="logotipo" alt="" src={logoCompleto}/>
                    </div>
                    <div id="form_login">
                        <form method="post" onSubmit={this.getLoginToken}>
                            <InputWithPlaceholder
                                styleContainerInput="form-group"
                                inputId="txt-email"
                                labelText="Email:"
                                styleInput="form-control caixa_de_texto sombra_input"
                                placeholder="seu_email@email.com"
                                onChange={this.setEmail}
                                value={this.state.email}
                                maxLength="50"
                                labelClass="label mb-2"
                                type="email">    
                            </InputWithPlaceholder>
                            {this.renderizarErroEmail()}
                            <InputWithPlaceholder
                                styleContainerInput="form-group"
                                inputId="txt-senha"
                                labelText="Senha:"
                                styleInput="form-control caixa_de_texto sombra_input"
                                onChange={this.setSenha}
                                value={this.state.senha}
                                placeholder="senha"
                                maxLength="30"
                                labelClass="label mb-2"
                                type="password">    
                            </InputWithPlaceholder>
                            {this.renderizarErroSenha()}
                            {this.renderizarErroLogin()}
                            <div className="align_button">
                                <input type="submit" id="btn_entrar" className="btn btn-lg btn-block " value="Entrar"/>
                                    Não tem uma Conta?
                                    <Link to="/cadastroEstabelecimento" style={link} className="dark esqueceu_senha">
                                        <span id="esqueceu_senha" className="esqueceu_senha form-text">Cadastre-se Já!</span>
                                    </Link>    
                            </div>    
                        </form>
                    </div>
                </div>
            </div>   
        );
  }
}
