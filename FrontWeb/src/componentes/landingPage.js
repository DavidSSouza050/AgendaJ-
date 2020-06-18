import React from 'react';
import "../css/style.css";
import "../css/styleLanding.css";
import "../css/bootstrap.css"
import Input from "./input";
import TextArea from "./textArea";
import logo from "../imagens/logo_mobile_sem_fundo.png";
import logoCompleto from "../imagens/versaoFinalLogoAgendaJa.png";
import telaMobile from "../imagens/telaMobile.png";
import telaWeb from "../imagens/telaWeb.png";
import missao from "../imagens/missao.png";
import visao from "../imagens/visao.png";
import valores from "../imagens/valores.png" ;
import iconFacebook from "../icones/facebook.png";
import iconInstagram from "../icones/instagram.png" ;
import iconLinkedin from "../icones/linkedin.png";
import Select from "./select";
import { Link } from 'react-router-dom';
import  AnchorLink from 'react-anchor-link-smooth-scroll';

function Menu(){

    const link = {
        color:"white",
        textDecoration:'none',
    }


    return(
        <nav className="conteiner_menu" >
            <ul id="ul_menu" className="center">
                <li id="botao_entrar_menu">
                    <Link to="/" style={link}>
                        <button type="button" id="botao_entrar" data-toggle="modal" data-target="#exampleModalCenter">                    
                            Entrar
                        </button>
                    </Link> 
                </li>
                <li id="margin_item" className="item_menu sumir">
                    <Link to="/escolhaConta" style={link}>
                        Crie sua conta
                    </Link>    
                </li>                
                <li id="img_menu" >
                    <img src={logo} className="img" alt="AgendaJá" />
                </li>
                <li id="img_menu_mobile" >
                    <img src={logo} className="img" alt="AgendaJá" />
                </li>
                <li className="item_menu">
                    <AnchorLink href="#caixa_aplicacao" style={link}>
                        <span id="linhar_item">Aplicação</span>
                    </AnchorLink>
                </li>
                <li className="item_menu">
                    <AnchorLink href="#conteiner_quem_somos" style={link}>Quem Somos</AnchorLink> 
                </li>
                <li className="item_menu">
                    <AnchorLink href="#conteiner_fale_conosco" style={link}>Fale Conosco</AnchorLink>
                </li>
            </ul>
        </nav>
    );
}

function Header(){
    return(
        <>
            <div id="espaco_landing"></div>
            <header id="caixa_header" className="back">
                <div id="headerlanding_page" className="center">
                    <div id="titulo_header">
                        <p><span>Tenha os serviços de Beleza em suas mãos</span></p>
                    </div>
                </div>
            </header>
        </>
    );
}

function Aplicacao() {

    return(

        <div id="caixa_aplicacao">
            <div id="aplicacao" className="center">
                <h2 className="sumirdagrande">Aplicação</h2>  
                <div id="imagem_logo_aplicacao" className="vertical_center" >
                    <img src={logoCompleto} className="img" alt="AgendaJá" />
                </div>

                <div id="texto_aplicacao" className="texto_landing">
                    <h2 className="sumir">Aplicação</h2>

                    <p>
                        O sistema AgendaJá é uma aplicação voltada para o mercado de estética, e visa ligar os clientes aos salões e barbearias, agilizando o processo de agendamento dos serviços oferecidos por estes estabelecimentos. 
                    </p>
                    <p>
                        Além de acelerar o agendamento a aplicação funciona como uma forma de divulgação das atividades realizadas pelos estabelecimentos.
                    </p>
                </div>
            </div>
        </div>

    );
    
}

function Plataforma() {
    return(
        <div id="container_plataforma" className="back text-dark">
            <div id="caixa_plataforma" className="center">
                <div id="plataforma_mobile" className="text-center pt-5 shadow fonte_plataforma texto_landing"> 
                    <span className="h2 mt-5 text-center">
                        Plataforma Mobile
                    </span>
                    <p className="mt-5">
                        O aplicativo AgendaJá é uma aplicação Android direcionada para pessoas que desejam garantir seu horário no salão/barbearia de sua preferência e ainda realizar o pagamento pela própria plataforma.
                    </p>
                    <div id="imagem_plataforma_mobile">
                        <img src={telaMobile} className="img" alt="Mobile" />
                    </div>
                </div>
                
                <div id="plataforma_web" className="text-center shadow pt-5 fonte_plataforma texto_landing">
                    <span className="h2 text-center">
                        Plataforma Web
                    </span>
                    <p className="mt-5">
                        O Sistema Web é voltado para os Establecimentos do ramo da estética, onde será necessário gerenciar os serviços oferecidos, os funcionários que oferecem esse serviços e suas agenda para assim ser possível os agendamentos. 
                    </p>
                    <div id="imagem_plataforma_web">
                        <img src={telaWeb} className="img mt-5" alt="Estabelecimento" />
                    </div>
                </div>
            </div>
        </div>
    );
    
}


function Fucionalidade() {
    return(
        <div id="conteiner_funcionalidades">
            <div id="caixa_funcionalidades" className="center">
                <div className="funcionalidades">   
                    <h2 className="h2">Funcionalidades</h2>
                    <ul>
                        <li>
                            Agendamentos simplificados dos serviços com a possibilidade de pagamento pelo aplicativo;
                        </li>
                        <li>
                            Divulgação dos estabelecimentos e dos serviços oferecidos;
                        </li>
                        
                        <li>
                            Filtragem dos serviços por localização, valor e serviço;
                        </li>
                        <li>
                           Sistema de Avaliação tanto para os clientes quanto para os estabelecimentos;
                        </li>
                    </ul>
                </div>
                <div className="funcionalidades">
                    <div id="video_funcionalidades" className="shadow-lg">
                        <iframe className="img" src="https://www.youtube.com/embed/h4QTJjvPEbo" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" title="Apresentação" />
                    </div>
                </div>
            </div>
        </div>
    );
    
}

function BaixarAplicacao(){
    return(

        <div id="conteiner_baixar_aplicacao" className="back">
            <div className="caixa_escura">
                <div id="caixa_baixar_aplicacao" className="center">
                    <p>Quer fazer seus agendamentos? Baixe já!!</p>
                    <a href="/" className="shadow" download>
                        AgendaJá Mobile
                    </a>
                </div>
            </div>
        </div>

    );
}

function QuemSomos() {
    return(

        <div id="conteiner_quem_somos">
            <div id="caixa_quem_somos" className="center text-dark">
                <div id="caixa_texto_quem_somos">
                    <h1>Quem somos ?</h1>
                    <p>
                        O Sistema AgendaJá é oferecido pela empresa [LumiCode] que atua na área de desenvolvimento de soluções com o uso de tecnologia. A [LumiCode] se destaca exatamente por entender o mercado de atuação e buscar trazer aos seus usuários a melhor experiência possível dentro de nossas plataformas. Além disso estamos sempre buscando realizar melhorias significativas que estejam de acordo com o perfil dos nossos clientes.
                    </p>
                </div>
                <div id="caixa_missao_visao_valores">
                    <div className="missao_visao_valores">
                        <div className="missao_visao_valores_imagem_titulo">
                            <div className="missao_visao_valores_imagem">
                                <img src={missao} className="img" alt="Missão" />
                            </div>
                            <div className="missao_visao_valores_titulo">
                                <h2>Missão</h2>
                            </div>
                        </div>
                        <div className="missao_visao_valores_texto">
                            <p>
                                Criar soluções com o uso da tecnologia e torna o uso delas acessíveis a todos.
                            </p>
                        </div>
                    </div>
                    <div className="missao_visao_valores">
                        <div className="missao_visao_valores_imagem_titulo">
                            <div className="missao_visao_valores_imagem">
                                <img src={visao} className="img" alt="Visão" />
                            </div>
                            <div className="missao_visao_valores_titulo">
                                <h2>Visão</h2>
                            </div>
                        </div>
                        <div className="missao_visao_valores_texto">
                            <p>
                                Ter o máximo de usuários possíveis utilizando nossas plataformas e ser reconhecida como uma das maiores empresas de Desenvolvimento de Softwares do Brasil.
                            </p>
                        </div>
                    </div>
                    <div className="missao_visao_valores">
                        <div className="missao_visao_valores_imagem_titulo">
                            <div className="missao_visao_valores_imagem">
                                <img src={valores} className="img" alt="Valores" />
                            </div>
                            <div className="missao_visao_valores_titulo">
                                <h2>Valores</h2>
                            </div>
                        </div>
                        <div className="missao_visao_valores_texto">
                            <p>
                                Responsabilidade, Proximidade com o usuário, Praticidade, Cooperatividade, Estar Aberto a Melhorias e Sugestões.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    );
    
}

function FaleConosco() {
    return(
        <div id="conteiner_fale_conosco">
            <div id="caixa_fale_conosco" className="center">
                <div id="imagem_fale_conosco">

                </div>
                <div id="fale_conosco_box">
                    <p>
                        Deixe um Comentário, Sugestão ou Dúvida por meio do Formulário ao Lado.
                    </p>
                    <div id="fale_conosco">
                        <div id="titulo_fale_conosco">
                            Entre em Contato Conosco
                        </div>
                        <div id="form_fale_conosco">
                            <div className="form-row">

                                <Input
                                    styleContainerInput="form-group col-sm-11"
                                    inputId="nome_fale_conosco"
                                    labelText="Nome:"
                                    styleInput="form-control sombra_input"/>
                            
                                
                                <Input
                                    styleContainerInput="form-group col-sm-11"
                                    inputId="email_fale_conosco"
                                    labelText="Email:"
                                    styleInput="form-control sombra_input"/>

                                <Select/>
                                
                                <TextArea
                                    styleContainer="form-group col-sm-11"
                                    labelText="Comentário:"
                                    textareaId="comentario_fale_conosco"
                                    styleTextArea="form-control  textArea_fale_conosco sombra_input"
                                />

                            </div>
                        </div>
                        <div id="caixa_botao_fale_conosco">
                            <input type="button" id="botao_fale_conosco" value="Enviar Formulário" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}


function PreFooter() {
    return(
        <div id="pre_footer">
            <div className="footer center">
                <div id="imagem_logo_footer">
                    <img src={logoCompleto} className="img" alt="Agendajá" />
                </div>
                <p>
                    &copy; 2019 LumiCode
                </p>
            </div>
        </div>  
    );
    
}

function FooterLanding(params) {
    return(
        <footer id="footer_landing_page">
            <div className="footericon">
                <div className="rede_social">
                    <img src={iconFacebook} className="img" alt="Visite nosso facebook" /> 
                </div>
                <div className="rede_social">
                    <img src={iconInstagram} className="img" alt="visite nosso instagram" />
                </div>
                <div className="rede_social">
                    <img src={iconLinkedin} className="img" alt="Visite nosso linkedin" />
                </div>
            </div>
        </footer>
    );
}


export default function LandingPage() {

    

    return(
        <>
            <Menu></Menu>
            <Header></Header>
            <Aplicacao></Aplicacao>
            <Plataforma></Plataforma>
            <Fucionalidade></Fucionalidade>
            <BaixarAplicacao></BaixarAplicacao>
            <QuemSomos></QuemSomos>
            <FaleConosco></FaleConosco>
            <PreFooter></PreFooter>
            <FooterLanding></FooterLanding>
        </>
    );
}

