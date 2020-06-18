import React, { Component } from 'react';
import HeaderSimples from './headerSimples';
import Input, { InputReadOnly, InputWithPlaceholder} from './input';
import Button, {ButtonWithDiv} from "./botao";
import axios from "axios";
import MensagemErro from "./mensagemErro";
import MaskCEP, {MaskCNPJ, MaskCelular, MaskTelefone} from "../utils/maskValidator";
import { Link, withRouter } from 'react-router-dom'; 
import PropTypes from "prop-types";
import {validate} from 'cnpj';
import sha1 from 'sha1';
import api from "../utils/http";
const ip = api();


class CadastroEstabelecimento extends Component {

  //código que modifica o body
  componentDidMount(){
    window.document.body.className = "body_cadastro back";
    document.title = "AgendaJá - Cadastro de Estabelecimento";
  }

  //Propriedades do WithRouter
  static propTypes = {
    match: PropTypes.object.isRequired,
    location: PropTypes.object.isRequired,
    history: PropTypes.object.isRequired
  };

  constructor(){
    super();

    //Declaração dos States
    this.state = {
      nomeEstabelecimento: '',
      razaoSocial:'',
      cnpj:'',
      telefone:'',
      celular:'',
      email:'',
      senha:'',
      confirmarSenha:'',
      cep:'',
      numero:'',
      endereco:{}, 
      erroNomeEstabelecimento:'',
      showErroNomeEstabelecimento: false,
      erroRazaoSocial:'',
      showErroRazaoSocial: false,
      erroCNPJ:'',
      showErroCPNJ : false,
      erroTelefone:'',
      showErroTelefone: false,
      erroCEP:"",
      showErroCEP: false,
      erroNumero: "",
      showErroNumero: false,
      erroEmail: "",
      showErroEmail: false,
      erroSenha: "",
      showErroSenha: false,
      erroConfirmarSenha: "",
      showErroConfirmarSenha: false,
      erroApi: {},
      showErroApi: false,
      usuarioIdResponse:"",
      enderecoIdResponse:""
    };

    //Declaração das Funções para evitar o uso do .bind(this)
    this.pesquisarCEP = this.pesquisarCEP.bind(this);
    this.setNomeEstabelecimento = this.setNomeEstabelecimento.bind(this);
    this.setRazaoSocial = this.setRazaoSocial.bind(this);
    this.setCNPJ = this.setCNPJ.bind(this);
    this.setTelefone = this.setTelefone.bind(this);
    this.setCelular = this.setCelular.bind(this);
    this.setEmail = this.setEmail.bind(this);
    this.setSenha = this.setSenha.bind(this);
    this.setNumero = this.setNumero.bind(this);
    this.setCEP = this.setCEP.bind(this);
    this.setConfirmarSenha = this.setConfirmarSenha.bind(this);
    this.enviarFormulario = this.enviarFormulario.bind(this);
    this.validarCaixas = this.validarCaixas.bind(this);
    this.renderizarErroCep = this.renderizarErroCep.bind(this);
    this.renderizarErroNomeEstabelecimento = this.renderizarErroNomeEstabelecimento.bind(this);
    this.renderizarErroRazaoSocial = this.renderizarErroRazaoSocial.bind(this);
    this.renderizarErroCNPJ = this.renderizarErroCNPJ.bind(this);
    this.renderizarErroTelefone = this.renderizarErroTelefone.bind(this);
    this.renderizarErroNumero = this.renderizarErroNumero.bind(this);
    this.renderizarErroEmail = this.renderizarErroEmail.bind(this);
    this.renderizarErroSenha = this.renderizarErroSenha.bind(this);
    this.renderizarErroConfirmarSenha = this.renderizarErroConfirmarSenha.bind(this);
    this.renderizarErroApi = this.renderizarErroApi.bind(this);
    this.relacionarEnderecoEstabelecimento = this.relacionarEnderecoEstabelecimento.bind(this);
  }

  //Métodos Set
  setNomeEstabelecimento(evento){
    this.setState({nomeEstabelecimento:evento.target.value});
  }

  setRazaoSocial(evento){
    this.setState({razaoSocial:evento.target.value});
  }

  setCNPJ(){
    let caixaCNPJ = document.querySelector('#cnpj_estabelecimento');
    let cnpjValue = caixaCNPJ.value; 
    this.setState({cnpj: MaskCNPJ(cnpjValue)});
  }

  setTelefone(){
    let caixaTelefone = document.querySelector('#telefone_estabelecimento');
    let telefoneValue = caixaTelefone.value; 
    this.setState({telefone:MaskTelefone(telefoneValue)});
  }

  setCelular(){
    let caixaCelular = document.querySelector('#celular_estabelecimento');
    let celularValue = caixaCelular.value; 
    this.setState({celular:MaskCelular(celularValue)});
  }

  setEmail(evento){
    this.setState({email:evento.target.value});
  }

  setSenha(evento){
    this.setState({senha:evento.target.value});
  }

  setConfirmarSenha(evento){
    this.setState({confirmarSenha:evento.target.value});
  }

  setNumero(evento){
    this.setState({numero:evento.target.value});
  }

  setCEP() {
    let caixaCEP = document.querySelector('#cep_estabelecimento');
    let cepValue = caixaCEP.value; 
    this.setState({cep: MaskCEP(cepValue)});
  }


  //Método que Pesquisa os CEP digitado no Formulário e retorna o Endereço
  pesquisarCEP(){
    let cep = this.state.cep;
    if(cep.length === 9){
      const url = `https://viacep.com.br/ws/${cep}/json/`;
      axios.get(url)
        .then((response) => {
          if(response.data.erro === true){
            this.setState({
              showErroCEP:true, 
              erroCEP:"O CEP digitado não existe", 
              endereco:{
                logradouro:"", 
                bairro:"", 
                localidade:"",
                uf:""
              }
            });
            this.renderizarErroCep();        
          }else{
            this.setState({endereco:response.data, showErroCEP:false});
          }
        })
    }
  }

  //Método que envia para a API os campos digitados nas caixas de Texto do formulário
  enviarFormulario(evento){
    //Anula o evento de Submção da Página 
    evento.preventDefault();

    if(this.validarCaixas() === true){
     this.enviarUsuario();
    }
  }


  relacionarEnderecoEstabelecimento(){
    const urlRelacaoEnderecoEstabelecimento = 'http://'+ip+'/enderecosEstabelecimentos';

    let relacionamento = {
      endereco:{
        idEndereco: this.state.enderecoIdResponse
      },
      estabelecimento: {
        idEstabelecimento: this.state.usuarioIdResponse
      }
    }

    axios.post(urlRelacaoEnderecoEstabelecimento, relacionamento)
      .then(()=>{
        this.props.history.push("/cadastradoComSucesso");
      })
      .catch(()=>{
        this.setState({erroApi:"Não foi possível realizar o cadastro!", showErroApi: true});
      })

  }

  enviarEndereco(){
    //Url da API
    const urlEndereco = 'http://'+ip+'/enderecos';
     
    //Remove a Mascara do CEP
    let cepNoMask = this.state.cep.replace(/[-]+/g, '');

    //Constrói o objeto Endereço
    let endereco = 
      {
        logradouro: this.state.endereco.logradouro,
        bairro: this.state.endereco.bairro,
        cep: cepNoMask,
        numero: this.state.numero,
        idCidade: {
          idCidade: this.state.endereco.ibge
        }
      };

    //Requisição POST que irá salvar o Endereço
    axios.post(urlEndereco, endereco)
      //Após salvar o Endereço o Usuário é cadastrado
      .then((response)=>{
        this.setState({enderecoIdResponse:response.data.idEndereco})
        this.relacionarEnderecoEstabelecimento();
      })
      //Caso a requisição de erro a mensagem retornada é armazenada no state
      .catch((erro)=>{
        this.setState({erroApi:(erro.response.data.mensage), showErroApi: true});
      })

  }

  //Método que envia para API o Estabelecimento a ser Cadastrado
  enviarUsuario(){
    const urlEstabelecimento = 'http://'+ip+'/estabelecimentos';

    //Contrói o objeto Estabelecimento
    let estabelecimento = 
      {
        cnpj:this.state.cnpj,
        razaoSocial:this.state.razaoSocial,
        nomeEstabelecimento:this.state.nomeEstabelecimento,
        telefone:this.state.telefone,
        celular:this.state.celular,
        email:this.state.email,
        //Criptografia da Senha
        senha:sha1(this.state.senha),
      };

    //Requisição que envia os dados do Estabelecimento
     axios.post(urlEstabelecimento, estabelecimento)
        //Caso a Requisição funcione, é aberto a Página de Cadastrado com Sucesso
        .then((response)=>{
          
          this.setState({usuarioIdResponse:response.data.idEstabelecimento});
          this.enviarEndereco();

        })
        //CAso a Requisição de Errado a mensagem de erro é Armazenada
        .catch((erro)=>{
          this.setState({erroApi:(erro.response.data.mensage), showErroApi: true});
        })

  }

  //Validação dos Input. As Requisições só são realizadas após a validação
  validarCaixas(){
    let isValidated = true;
    let caixaNomeEstabelecimento = document.querySelector('#nome_estabeleciemento');
    let caixaRazaoSocial = document.querySelector('#razao_social_estabelecimento');
    let caixaCNPJ = document.querySelector('#cnpj_estabelecimento');
    let caixaTelefone = document.querySelector('#telefone_estabelecimento');
    let caixaCEP = document.querySelector('#cep_estabelecimento');
    let caixaNumero = document.querySelector('#numero_estabeleciemnto');
    let caixaEmail = document.querySelector('#email_estabelecimento');
    let caixaSenha = document.querySelector('#senha_estabelecimento');
    let caixaConfirmarSenha = document.querySelector('#confirmar_senha_estabelecimento');

    if(caixaNomeEstabelecimento.value === ""){
      this.setState({erroNomeEstabelecimento:"Nome do Estabelecimento é um campo obrigatório", showErroNomeEstabelecimento: true});
      isValidated = false;
    }if(caixaNomeEstabelecimento.value.length < 5 ){
      this.setState({erroNomeEstabelecimento:"Nome do Estabelecimento deve conter mais de 5 caracteres", showErroNomeEstabelecimento: true});
      isValidated = false;
    }else{
      this.setState({showErroNomeEstabelecimento: false});
    }

    if(caixaRazaoSocial.value === ""){
      this.setState({erroRazaoSocial:"Razão Social é um campo obrigatório", showErroRazaoSocial: true});
      isValidated = false;
    }if(caixaRazaoSocial.value.length < 5 ){
      this.setState({erroRazaoSocial:"Razão Social deve conter mais de 5 caracteres", showErroRazaoSocial: true});
      isValidated = false;
    }else{
      this.setState({showErroRazaoSocial: false});
    }
    
    if(caixaCNPJ.value === ""){
      this.setState({erroCNPJ:"CNPJ é um campo obrigatório", showErroCPNJ: true});
      isValidated = false;
    }
    if(validate(caixaCNPJ.value) === false){
      this.setState({erroCNPJ:"CNPJ invalido!", showErroCPNJ: true});
      isValidated = false;
    }else{
      this.setState({showErroCPNJ: false});
    }

    if(caixaTelefone.value === ""){
      this.setState({erroTelefone:"Telefone é um campo obrigatório", showErroTelefone: true});
      isValidated = false;
    }else{
      this.setState({showErroTelefone: false});
    }

    if(caixaCEP.value === ""){
      this.setState({erroCEP:"CEP é um campo obrigatório", showErroCEP: true});
      isValidated = false;
    }else{
      this.setState({showErroCEP: false});
    }

    if(caixaNumero.value === ""){
      this.setState({erroNumero:"Número é um campo obrigatório", showErroNumero: true});
      isValidated = false;
    }else{
      this.setState({showErroNumero: false});
    }

    if(caixaEmail.value === ""){
      this.setState({erroEmail:"Email é um campo obrigatório", showErroEmail: true});
      isValidated = false; 
    }if(caixaEmail.value.length < 10){
      this.setState({erroEmail:"O Email deve conter mais de 5 caracteres", showErroEmail: true});
      isValidated = false;
    }else{
      this.setState({showErroEmail: false});
    }

    if(caixaSenha.value.length < 8){
      this.setState({erroSenha:"Senha deve conter no mínimo 8 caracteres", showErroSenha: true});
      isValidated = false; 
    }else{
      this.setState({showErroSenha: false});
    }

    if(caixaConfirmarSenha.value !== caixaSenha.value){
      this.setState({erroConfirmarSenha:"As senhas não correspodem", showErroConfirmarSenha: true});
      isValidated = false; 
    }else{
      this.setState({showErroConfirmarSenha: false});
    }

    return isValidated;
  }


  //Métodos que Renderizam abaixo da tela seus Erros
  renderizarErroNomeEstabelecimento(){
    if(this.state.showErroNomeEstabelecimento === true ){
      return (
        <MensagemErro
          className="form-group col-sm-11 text-danger"
          id="errorNomeEstabelecimentoMessage"
          mensagemErro={this.state.erroNomeEstabelecimento}/>
      );
    }
  } 

  renderizarErroRazaoSocial(){
    if(this.state.showErroRazaoSocial === true ){
      return (
        <MensagemErro
          className="form-group col-sm-11 text-danger"
          id="errorRazaoSocialMessage"
          mensagemErro={this.state.erroRazaoSocial}/>
      );
    }
  } 

  renderizarErroCNPJ(){
    if(this.state.showErroCPNJ === true ){
      return (
        <MensagemErro
          className="form-group col-sm-11 text-danger"
          id="errorCNPJMessage"
          mensagemErro={this.state.erroCNPJ}/>
      );
    }
  } 

  renderizarErroTelefone(){
    if(this.state.showErroCPNJ === true ){
      return (
        <MensagemErro
          className="form-group col-sm-11 text-danger"
          id="errorTelefoneMessage"
          mensagemErro={this.state.erroTelefone}/>
      );
    }
  }  

  renderizarErroCep(){
    if(this.state.showErroCEP === true){
      return (
        <MensagemErro
          className="form-group col-sm-11 text-danger"
          id="errorCEPMessage"
          mensagemErro={this.state.erroCEP}/>
      );
    }
  }

  renderizarErroNumero(){
    if(this.state.showErroNumero === true){
      return (
        <MensagemErro
          className="form-group col-sm-11 text-danger"
          id="erroNumeroMessage"
          mensagemErro={this.state.erroNumero}/>
      );
    }
  }

  renderizarErroEmail(){
    if(this.state.showErroEmail === true){
      return (
        <MensagemErro
          className="form-group col-sm-11 text-danger"
          id="errorEmailMessage"
          mensagemErro={this.state.erroEmail}/>
      );
    }
  }

  renderizarErroSenha(){
    if(this.state.showErroSenha === true){
      return (
         <MensagemErro
          className="form-group col-sm-11 text-danger"
          id="erroSenhaMessage"
          mensagemErro={this.state.erroSenha}/>
      );
    }
  }

  renderizarErroConfirmarSenha(){
    if(this.state.showErroConfirmarSenha === true){
      return (
        <MensagemErro
          className="form-group col-sm-11 text-danger"
          id="erroConfimarSenhaMessage"
          mensagemErro={this.state.erroConfirmarSenha}/>
      );
    }
  }

  renderizarErroApi(){
    if(this.state.showErroApi === true){
      return (
        <div className="form-group col-sm-11 text-danger font-weight-bold error-message-size" id="erroAPIMessage">
          <br/>
          <p>{this.state.erroApi}</p>
       </div>
      );
    }
  }


  render() {
    return (
      <div id="container_all_cadastro">
        <HeaderSimples></HeaderSimples>
        <div id="espaco"/>
        <div id="conteudo_cadastro" className="rounded shadow-lg center">
          <div id="titulo_cadastro" className="text-dark h4 mt-3 center">
            Cadastro de Estabelecimento
          </div>        
          <hr className="hr" />
          <form method="post" onSubmit={this.enviarFormulario}>
            <div className="card_informacoes_conta">
              {/*card que vai aparecer o titulo da conta*/}
              <div className="informacoes_conta_esquerda"> 
                <span className="text-dark h5">Informações da Conta</span>
              </div>
              <div className="informacoes_conta_direita">
                <div className="form-row">
                  <Input
                    styleContainerInput="form-group col-sm-11"
                    inputId="nome_estabeleciemento"
                    labelText="Nome do Estabelecimento:*"
                    styleInput="form-control sombra_input"
                    onChange={this.setNomeEstabelecimento}
                    value={this.state.nomeEstabelecimento}
                    maxLength="100"
                    labelClass="mb-2"
                    type="text"
                    >
                  </Input>
                  {this.renderizarErroNomeEstabelecimento()}

                  <Input
                    styleContainerInput="form-group col-sm-11"
                    inputId="razao_social_estabelecimento"
                    labelText="Razão Social:*"
                    styleInput="form-control sombra_input"
                    onChange={this.setRazaoSocial}
                    value={this.state.razaoSocial}
                    maxLength="150"
                    labelClass="mb-2"
                    type="text">
                  </Input>
                  {this.renderizarErroRazaoSocial()}

                  <InputWithPlaceholder
                    styleContainerInput="form-group col-sm-11"
                    inputId="cnpj_estabelecimento"
                    labelText="CNPJ:*"
                    styleInput="form-control sombra_input"
                    placeholder="00.000.000/0000-00"
                    maxLength="18"
                    onChange={this.setCNPJ}
                    value={this.state.cnpj}
                    labelClass="mb-2"
                    type="text"> 
                  </InputWithPlaceholder>
                  {this.renderizarErroCNPJ()}

                  <InputWithPlaceholder
                    styleContainerInput="form-group col-md-6"
                    inputId="telefone_estabelecimento"
                    labelText="Telefone:*"
                    styleInput="form-control sombra_input"
                    placeholder="(00) 0000-0000"
                    maxLength="14"
                    onChange={this.setTelefone}
                    value={this.state.telefone}
                    labelClass="mb-2"
                    type="text">       
                  </InputWithPlaceholder>
                 
                  <InputWithPlaceholder
                    styleContainerInput="form-group col-md-5"
                    inputId="celular_estabelecimento"
                    labelText="Celular:"
                    styleInput="form-control sombra_input"
                    placeholder="(00) 90000-0000"
                    maxLength="15"
                    onChange={this.setCelular}
                    value={this.state.celular}
                    labelClass="mb-2"
                    type="text">
                  </InputWithPlaceholder>
                  {this.renderizarErroTelefone()}
                </div>
              </div>
            </div>
            <hr className="hr" />

            <div className="card_informacoes_conta">
              <div className="informacoes_conta_esquerda"> 
                <span className="text-dark h5">Endereço</span>
              </div>
             
              <div className="informacoes_conta_direita">
                <div className="form-row">
          
                <InputWithPlaceholder
                  styleContainerInput="form-group mt-2 col-sm-9"
                  inputId="cep_estabelecimento"
                  labelText="CEP:*"
                  styleInput="form-control sombra_input"
                  placeholder="00000-000"
                  onChange={this.setCEP}
                  value={this.state.cep}
                  maxLength="9"
                  labelClass="mb-2"
                  type="text">    
                </InputWithPlaceholder>
                
                <ButtonWithDiv
                  onClick={this.pesquisarCEP}
                  classButton="btn btn-outline-primary"
                  id="cadastrar_usuario"
                  nameButton="Buscar CEP"
                  classDiv="form-group col-sm-2 align_cep_button">        
                </ButtonWithDiv>

                {this.renderizarErroCep()}

                <InputReadOnly
                  styleContainerInput="form-group col-sm-11"
                  inputId="logradouro_estabeleciemento"
                  labelText="Logradouro:"
                  styleInput="form-control sombra_input"
                  value={this.state.endereco.logradouro}
                  labelClass="mb-2"
                  onChange={this.setLogradouro}
                  type="text"
                  >
                </InputReadOnly>

                <InputReadOnly
                  styleContainerInput="form-group col-md-9"
                  inputId="bairro_estabeleciemento"
                  labelText="Bairro:"
                  styleInput="form-control sombra_input"
                  value={this.state.endereco.bairro}
                  labelClass="mb-2"
                  onChange={this.setBairro}
                  type="text"
                  >
                </InputReadOnly>

                <InputWithPlaceholder
                  styleContainerInput="form-group col-md-2"
                  inputId="numero_estabeleciemnto"
                  labelText="Numero:*"
                  styleInput="form-control sombra_input"
                  placeholder="000"
                  value={this.state.numero}
                  onChange={this.setNumero}
                  labelClass="mb-2"
                  type="text"
                  >
                </InputWithPlaceholder>
                {this.renderizarErroNumero()}
                <InputReadOnly
                  styleContainerInput="form-group col-md-9"
                  inputId="cidade_estabeleciemento"
                  labelText="Cidade:"
                  styleInput="form-control sombra_input"
                  value={this.state.endereco.localidade}
                  onChange={this.setCidade}
                  labelClass="mb-2"
                  type="text"
                  >
                </InputReadOnly>

                <InputReadOnly
                  styleContainerInput="form-group col-md-2"
                  inputId="uf_estabeleciemento"
                  labelText="UF:"
                  styleInput="form-control sombra_input"
                  value={this.state.endereco.uf}
                  onChange={this.setUF}
                  labelClass="mb-2"
                  type="text"
                  >
                </InputReadOnly>
                </div>
              </div>
            </div>
            <hr className="hr" />
            <div className="card_informacoes_conta">
              <div className="informacoes_conta_esquerda"> 
                <span className="text-dark h5">Informações Finais</span>
                <br/>
                {this.renderizarErroApi()}
              </div>
              <div className="informacoes_conta_direita"> 
                <div className="form-row">
                  <InputWithPlaceholder
                      styleContainerInput="form-group col-sm-11"
                      inputId="email_estabelecimento"
                      labelText="E-mail:*"
                      styleInput="form-control sombra_input"
                      value={this.state.email}
                      onChange={this.setEmail}
                      labelClass="mb-2"
                      type="email">
                  </InputWithPlaceholder>
                  {this.renderizarErroEmail()}
                  <Input
                    styleContainerInput="form-group col-sm-11"
                    inputId="senha_estabelecimento"
                    labelText="Senha:*"
                    styleInput="form-control sombra_input"
                    value={this.state.senha}
                    onChange={this.setSenha}
                    labelClass="mb-2"
                    type="password">
                  </Input>
                  {this.renderizarErroSenha()}
                  <Input
                    styleContainerInput="form-group col-sm-11"
                    inputId="confirmar_senha_estabelecimento"
                    labelText="Confirmar Senha:*"
                    value={this.state.confirmacaoSenha}
                    onChange={this.setConfirmarSenha}
                    styleInput="form-control sombra_input"
                    labelClass="mb-2" 
                    type="password"> 
                  </Input>
                  {this.renderizarErroConfirmarSenha()}
                </div>
              </div>
            </div>
            <hr className="hr" />
              <div id="caixa_botao_cadastro_estabeleciemento">
                <Link to="/">
                  <Button
                    classButton="botao_cancelar_cadastro"
                    id="cancelar_cadastro"
                    nameButton="Cancelar"
                    type="button">
                  </Button>
                </Link>
                <Button
                  classButton="botao_cadastro"
                  id="cadastrar_usuario"
                  nameButton="Criar Conta"
                  type="submit">
                </Button>
            </div>
          </form>
        </div>
      </div>
      
    );
  }
}

export default withRouter(CadastroEstabelecimento);
