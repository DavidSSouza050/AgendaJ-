import React, { Component } from 'react';
import "../css/style.css";
import "../css/styleFont.css";
import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";

// import { Container } from './styles';

export default class componentes extends Component {
  render() {
    return(
        <div id="container_all_login">
            <div className="center vertical_center" id="container_form_login">
                <div id="container_titulo_form">
                    <h3 className="vertical_center titulo">Entre já em Nossa Plataforma</h3>
                </div>
                <div id="container_logotipo">
                    <img className="center" id="logotipo"  src="imagens/teste.png"/>
                </div>
                <div id="form_login">
                    <form>
                        <div className="form-group">
                            <label className="label" for="exampleInputEmail1">Email:</label>
                            <input type="email" className="form-control form-control caixa_de_texto" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Seu email"/>
                        </div>
                        <div id="div_senha" className="form-group">
                            <label className="label" for="exampleInputPassword1">Senha:</label>
                            <input type="password" className="form-control form-control caixa_de_texto" id="exampleInputPassword1" placeholder="Senha" maxlength="30"/>
                            <span id="esqueceu_senha" className="esqueceu_senha form-text">Esqueceu sua Senha</span>
                        </div>
                        <div className="align_button">
                            <input type="submit" id="btn_entrar" className="btn btn-lg btn-block " value="Entrar"/>
                            Não tem uma Conta?<span id="esqueceu_senha" className="esqueceu_senha form-text">Cadastre-se Já!</span>
                        </div>    
                    </form>
                </div>
            </div>
        </div>
    );
  }
}
