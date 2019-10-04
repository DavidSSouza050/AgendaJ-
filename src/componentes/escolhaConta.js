import React, { Component } from 'react';
import "../css/style.css";
import barbershop from "../icones/barbershop.png";
import setaEsquerda from "../icones/seta_esquerda.png";
import user from "../icones/user.png";
import setaDireita from '../icones/seta_direita.png';
import HeaderSimples from './headerSimples';
import CardEscolha from './cardEscolha';
import { Link } from 'react-router-dom';

class EscolhaConta extends Component {

    componentWillMount(){
        document.body.className = "background_escolha_cadastro back";
        document.title = "AgendaJá - Escolha o Tipo do Cadastro";
    }

    render() {

        const link = {
            color:'black'
        }

        return(
            // Essa é a div que segura todos os componentes da tela
            <div id="container_all_tipo_cadastro" className="font">
                {/* Header */}
                <HeaderSimples></HeaderSimples>
                <div id="espaco">
                </div>
                {/* Div que Segura todos os Cards */}
                <div id="conteudo_tipo_cadastro" className="center">
                    {/* <!-- Titulo --> */}
                    <div className="caixa_titulo">
                        <h1 id="titulo">Escolha o Tipo da Conta:</h1>
                    </div>
                    
                    <div className="caixa_cards">
                        {/* <!-- Card 1 --> */}
                        <Link to="/cadastroEstabelecimento" style={link}>
                            <CardEscolha 
                                cardHeaderIcon={barbershop} 
                                cardHeaderTitle="Criar Conta como Estabelecimento" 
                                cardText="Ao criar uma conta como estabelecimento você poderá realizar a gestão da sua Agenda de Clientes e divulgar o seu négocio e assim  Agendamentos Online."
                                cardArrowIcon={setaEsquerda}
                                arrowDirection="icone_esquerdo_footer">
                            </CardEscolha>
                         </Link>
                        {/* <!-- Card 2 --> */}
                        <CardEscolha 
                            cardHeaderIcon={user} 
                            cardHeaderTitle="Criar Conta como Cliente" 
                            cardText="Ao criar uma conta como cliente você poderá agendar os serviços de seu interesse com alguns cliques e ainda realizar o pagamento pelo sistema."
                            cardArrowIcon={setaDireita}
                            arrowDirection="icone_direito_footer">
                        </CardEscolha>                  
                        
                    </div>
                    <div id="caixa_botao">
                        <Link to="/">
                        {/* <!-- Botão Voltar --> */}
                        <input type="button" id="button" value="Voltar"/>
                        </Link>
                    </div>
                </div>  
            </div>
        );
    }
}


export default EscolhaConta;