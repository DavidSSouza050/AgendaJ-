import React, { Component } from 'react';
//import EscolhaConta from './componentes/escolhaConta';
import Modal from "../node_modules/react-bootstrap/Modal";
import "./css/style.css";
import "./css/styleFont.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import ButtonToolbar from "../node_modules/react-bootstrap/ButtonToolbar";
import logo from "./imagens/logo_mobile_sem_fundo.png";
import logoCompleto from "./imagens/versaoFinalLogoAgendaJa.png";
import telaMobile from "./imagens/telaMobile.png";
import telaWeb from "./imagens/telaWeb.png";
import missao from "./imagens/missao.png";
import visao from "./imagens/visao.png";
import valores from "./imagens/valores.png" ;
import iconFacebook from "./icones/facebook.png";
import iconInstagram from "./icones/instagram.png" ;
import iconLinkedin from "./icones/linkedin.png";
import barberShop from "./icones/barber-shop.png";
import hairCut from "./icones/hair-cut.png";


function ModalLindaDeBonita(props) {
    return (
      <Modal
        {...props}
        size="md"
        aria-labelledby="contained-modal-title-vcenter"
        centered
      >
        <Modal.Header closeButton>
          <Modal.Title id="contained-modal-title-vcenter">
            Entrar como
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <div className="card_modal_escolha">
                <div className="imagem_modal_escolha">
                    <img src={barberShop} className="img" alt="Estabelecimento" />
                </div>
                <h4>Estabelecimento</h4>
            </div>
            <div className="card_modal_escolha">
                <div className="imagem_modal_escolha">
                    <img src={hairCut} className="img" alt="Cliente" />
                </div>
                <h4>Cliente</h4>
            </div>
        </Modal.Body>
      </Modal>
    );
}
  

function App() {

    const [modalShow, setModalShow] = React.useState(false);
 
    return (
        <div>

            <nav className="conteiner_menu" >
              <ul id="ul_menu" className="center">
                <li id="botao_entrar_menu">
                    <ButtonToolbar>
                        <button  onClick={() => setModalShow(true)} type="button" id="botao_entrar" data-toggle="modal" data-target="#exampleModalCenter">                    
                        Entrar
                        </button>
                        <ModalLindaDeBonita
                         show={modalShow}
                         onHide={() => setModalShow(false)}>
                        </ModalLindaDeBonita>
                    </ButtonToolbar>
                </li>
                <li id="margin_item" className="item_menu">
                    Crie sua conta
                </li>
                <li id="img_menu" >
                    <img src={logo} className="img" alt="AjendaJá" />
                </li>
                <li className="item_menu">
                    <span id="linhar_item">Aplicação</span>
                </li>
                <li className="item_menu">
                    Quem Somos 
                </li>
                <li className="item_menu">
                    Fale Conosco
                </li>
              </ul>
          </nav>

          <div id="espaco_landing"></div>

          
          <header id="caixa_header" className="back">
              <div id="headerlanding_page" className="center">
                  <div id="titulo_header">
                      <p><span>Tenha os serviços de Beleza em suas mãos</span></p>
                  </div>
              </div>
          </header>



          <div id="caixa_aplicacao">
            <div id="aplicacao" className="center">
                
              <div id="imagem_logo_aplicacao" className="vertical_center" >
                  <img src={logoCompleto} className="img" alt="AgendaJá" />
              </div>

              <div id="texto_aplicacao">
                  <h2>Aplicação</h2>

                  <p>
                      O sistema AgendaJá é uma aplicação voltada para o mercado de estética, e visa ligar os clientes aos salões e barbearias, agilizando o processo de agendamento dos serviços oferecidos por estes estabelecimentos. 
                  </p>
                  <p>
                      Além de acelerar o agendamento a aplicação funciona como uma forma de divulgação das atividades realizadas pelos estabelecimentos.
                  </p>
              </div>
            </div>
        </div>


        <div id="container_plataforma" className="back">
          <div id="caixa_plataforma" className="center">
            <div id="plataforma_mobile" className="fonte_plataforma"> 
                <h2>
                    Plataforma Mobile
                </h2>
                <p>
                    O aplicativo AgendaJá funciona no sistema Android e é direcionado as pessoas de desejam garantir seu horário no salão/barbearia que desejar e ainda realizar o agendamento com rapidez e praticidade.
                </p>
                <div id="imagem_plataforma_mobile">
                    <img src={telaMobile} className="img" alt="Mobile" />
                </div>
            </div>
            
            <div id="plataforma_web" className="fonte_plataforma">
                <h2>
                    Plataforma Web
                </h2>
                <p>
                    
                </p>
                <div id="imagem_plataforma_web">
                    <img src={telaWeb} className="img" alt="Estabelecimento" />
                </div>
            </div>

          </div>

        </div>


        <div id="conteiner_funcionalidades">
          <div id="caixa_funcionalidades" className="center">
            <div className="funcionalidades">
                <h2>Funcionalidades</h2>
                <ul>
                  <li>
                    Lorem ipsum dolor fddfsdf asfas sit amet, consectetur;
                  </li>
                  <li>
                    Lorem ipsum dolor sit amet, f afasegfdf sd fconsectetur;
                  </li>
                  <li>
                    Lorem ipsum dolor sit amet, consecteturf asw etfwEFRWEFREWSF;
                  </li>
                  <li>
                    Lorem ipsum dolor sit amet, consectetu;
                  </li>
                </ul>
            </div>
            <div className="funcionalidades">
              <div id="video_funcionalidades">
                <iframe className="img" src="https://www.youtube.com/embed/9n-yiNx8sbY" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
              </div>
            </div>
          </div>
        </div>





        <div id="conteiner_quem_somos">
            <div id="caixa_quem_somos" className="center">
              <div id="caixa_texto_quem_somos">
                  <h1>Quem somos ?</h1>
                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fringilla consequat turpis, in mollis est faucibus ut. Donec vestibulum auctor imperdiet. Phasellus a convallis enim, ac suscipit neque. Curabitur magna nulla, laoreet eu lectus id, sodales fringilla diam. Fusce quis porta massa. Suspendisse egestas ante et tortor eleifend, ut ultrices neque imperdiet.
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


        <div id="conteiner_fale_conosco">
            <div id="caixa_fale_conosco" className="center">
                <div id="imagem_fale_conosco">

                </div>
                <div id="fale_conosco_box">
                    <p>
                        Deixe um Comentário, Sugestão ou Duvida por meio do Formulário ao Lado.
                    </p>
                    <div id="fale_conosco">
                        <div id="titulo_fale_conosco">
                            Entre em Contato Conosco
                        </div>
                        <div id="form_fale_conosco">
                            <div className="form-row">
                                <div className="form-group col-sm-11">
                                    <label htmlFor="nome_fale_conosco">Nome:</label>
                                    
                                    <input type="text" className="form-control sombra_input" id="nome_fale_conosco" />
                                </div>
                                <div className="form-group col-sm-11">
                                    <label htmlFor="email_fale_conosco">Email:</label>
                                    <input type="text" className="form-control sombra_input" id="email_fale_conosco" />
                                </div>
                                <div className="form-group col-sm-11">
                                    <label htmlFor="assunto_fale_conosco">Assunto:</label>
                                    
                                    <select className="form-control sombra_input" id="assunto_fale_conosco" >
                                        <option>Assunto</option>
                                        <option>Opa</option>
                                        <option>Opa</option>
                                    </select>
                                </div>
                                <div className="form-group col-sm-11">
                                    <label htmlFor="comentario_fale_conosco">Comentário:</label>
                                    <textarea className="form-control  textArea_fale_conosco sombra_input" id="comentario_fale_conosco" ></textarea>
                                </div>
                            </div>
                        </div>
                        <div id="caixa_botao_fale_conosco">
                            <input type="button" id="botao_fale_conosco" value="Enviar Formulário" />
                        </div>
                    </div>
                </div>
            </div>
        </div>

        
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

        <footer id="footer_landing_page">
            <div className="footer center">
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

       
      

      </div>
    );
 
}

export default App;