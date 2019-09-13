import React, { Component } from 'react';
import "../css/bootstrap.css";
import "../css/style.css";
import barbershop from "../icones/barbershop.png";
import setaEsquerda from "../icones/seta_esquerda.png";
import user from "../icones/user.png";
import setaDireita from '../icones/seta_direita.png';
import HeaderSimples from './headerSimples';
import CardEscolha from './cardEscolha';

export default class EscolhaConta extends Component {

    componentWillMount(){
        document.body.className = "background_escolha_cadastro back";
        document.title = "AgendaJá - Escolha o Tipo do Cadastro";
    }

    render() {
        return(
            // Essa é a div que segura todos os componentes da tela
            <div id="container_all_tipo_cadastro" class="font">
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
                        <CardEscolha 
                            cardHeaderIcon={barbershop} 
                            cardHeaderTitle="Criar Conta como Estabelecimento" 
                            cardText="Ao criar uma conta como estabelecimento você poderá realizar a Gestão da sua Agenda de Cliente e divulgar o seu négocio assim podendo realizar os Agendamentos para os serviços Online."
                            cardArrowIcon={setaEsquerda}
                            arrowDirection="icone_esquerdo_footer">
                        </CardEscolha> 

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
                        {/* <!-- Botão Voltar --> */}
                        <input type="button" id="button" value="Voltar"/>
                    </div>
                </div>  
            </div>
        );
    }
}
