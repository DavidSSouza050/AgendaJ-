import React, { Component } from 'react';
import HeaderSimples from './headerSimples';
import Input, { InputReadOnly, InputWithPlaceholder} from './input';
import Button, {ButtonWithDiv} from "./botao";
import axios from "axios";
import MaskCEP, {MaskCNPJ, MaskCelular, MaskTelefone} from "../utils/maskValidator";
import { Link }from 'react-router-dom';


export default class CadastroEstabelecimento extends Component {

  componentDidMount(){
    window.document.body.className = "body_cadastro back";
    document.title = "AgendaJá - Cadastro de Estabelecimento";
  }

  constructor(){
    super();
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
      endereco:[], 
      erroShow: false,
      idEndereco:''
    };

    this.pesquisarCEP = this.pesquisarCEP.bind(this);
    this.verificarCEP = this.verificarCEP.bind(this);
    this.renderizarErro = this.renderizarErro.bind(this);
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
    this.connfirmarSenha = this.confirmarSenha.bind(this);
    this.enviarFormulario = this.enviarFormulario.bind(this);

  }

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

  confirmarSenha(){
    let ok;

    if(this.state.confirmarSenha === this.state.senha){
      ok = true
      
    }else{
      ok = false
    }

    return ok;
  }

  verificarCEP(){
    let caixaCEP = document.querySelector('#cep_estabelecimento');
    let cep = caixaCEP.value; 

    if(cep.length === 0){

    }else if(cep.length === 9) {
      this.pesquisarCEP(cep)
    }
  }

  pesquisarCEP(cep){
    const url = `https://viacep.com.br/ws/${cep}/json/`;
    axios.get(url)
      .then((response) => {
        if(response.data.erro === true){
          this.setState({erroShow:true});
          console.log(this.state.erroShow);
          this.renderizarErro();        
        }else{
          this.setState({endereco:response.data, erroShow:false});
        }
      })
  }

  enviarEndereco(){
    const urlEndereco = 'http://54.167.124.56:8080/endereco'
    let cepNoMask = this.state.cep.replace(/[-]+/g, '');

    let endereco = 
      {
        logradouro: this.state.endereco.logradouro,
        bairro: this.state.endereco.bairro,
        cep: cepNoMask,
        numero: this.state.numero,
        idTipoEndereco:{
          idTipoEndereco: 2
        },
        idCidade: {
          idCidade: this.state.endereco.ibge
        }
      };

    axios.post(urlEndereco, endereco)
      .then((response)=>{
        this.setState({idEndereco:response.data.idEndereco});
        console.log(this.state.idEndereco);
      })  
  
      return true;
  }

  enviarUsuario(){
    const urlEstabelecimento = 'http://54.167.124.56:8080/estabelecimento'

    let estabelecimento = 
      {
        cnpj:this.state.cnpj,
        razaoSocial:this.state.razaoSocial,
        nomeEstabelecimento:this.state.nomeEstabelecimento,
        telefone:this.state.telefone,
        celular:this.state.celular,
        endereco:{
          idEndereco:this.state.idEndereco
        },
        email:this.state.email,
        senha:this.state.senha,
      };

      if(this.confirmarSenha){
        axios.post(urlEstabelecimento, estabelecimento)
          .then((response)=>{
            console.log(response);
            alert("Cadastrou!")
          })
          .catch((response)=>{
            console.log(response);
          })
      }else{
        alert("As senhas não correspondem!");
      }

    

  }

  enviarFormulario(evento){
    evento.preventDefault();
    const url = 'http://54.167.124.56:8080/estabelecimento';

    axios.get(url)
      .then(()=>{
        this.enviarEndereco();
      })
      .then(()=>{
        this.enviarUsuario();
      })
  }

  renderizarErro(){
    if(this.state.erroShow === true ){
      return (
        <div className="form-group col-sm-11 text-danger" id="errorCEPMessage">
          <small>O CEP digitado não existe</small>
        </div>
      );
    }
  }


  render() {
    return (
      <div id="container_all_cadastro">
        <HeaderSimples></HeaderSimples>
        <div id="espaco"/>
        <div id="conteudo_cadastro" className="center">
          <div id="titulo_cadastro" className="center">
            Cadastro de Estabelecimento
          </div>
        
          <hr className="hr" />
          <form method="post" onSubmit={this.enviarFormulario}>
            <div className="card_informacoes_conta">
              {/*card que vai aparecer o titulo da conta*/}
              <div className="informacoes_conta_esquerda"> 
                <span>Informações da Conta</span>
              </div>
              <div className="informacoes_conta_direita">
                <div className="form-row">
                  <Input
                    styleContainerInput="form-group col-sm-11"
                    inputId="nome_estabeleciemnto"
                    labelText="Nome do Estabelecimento:*"
                    styleInput="form-control sombra_input"
                    onChange={this.setNomeEstabelecimento}
                    value={this.state.nomeEstabelecimento}
                    maxLength="100"
                    type="text"
                    >
                  </Input>

                  <Input
                    styleContainerInput="form-group col-sm-11"
                    inputId="razao_social_estabelecimento"
                    labelText="Razão Social:*"
                    styleInput="form-control sombra_input"
                    onChange={this.setRazaoSocial}
                    value={this.state.razaoSocial}
                    maxLength="150"
                    type="text">
                  </Input>

                  <InputWithPlaceholder
                    styleContainerInput="form-group col-sm-11"
                    inputId="cnpj_estabelecimento"
                    labelText="CNPJ:*"
                    styleInput="form-control sombra_input"
                    placeholder="00.000.000/0000-00"
                    maxLength="18"
                    onChange={this.setCNPJ}
                    value={this.state.cnpj}
                    type="text"> 
                  </InputWithPlaceholder>

                  <InputWithPlaceholder
                    styleContainerInput="form-group col-md-6"
                    inputId="telefone_estabelecimento"
                    labelText="Telefone:*"
                    styleInput="form-control sombra_input"
                    placeholder="(00) 0000-0000"
                    maxLength="14"
                    onChange={this.setTelefone}
                    value={this.state.telefone}
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
                    type="text">
                  </InputWithPlaceholder>
                </div>
              </div>
            </div>
            <hr className="hr" />

            <div className="card_informacoes_conta">
              <div className="informacoes_conta_esquerda"> 
                <span>Endereço</span>
              </div>
              <div className="informacoes_conta_direita">
                <div className="form-row">
          
                <InputWithPlaceholder
                  styleContainerInput="form-group col-sm-9"
                  inputId="cep_estabelecimento"
                  labelText="CEP:*"
                  styleInput="form-control sombra_input"
                  placeholder="00000-000"
                  onChange={this.setCEP}
                  value={this.state.cep}
                  maxLength="9"
                  type="text">    
                </InputWithPlaceholder>
                
                <ButtonWithDiv
                  onClick={this.verificarCEP}
                  classButton="btn btn-outline-primary"
                  id="cadastrar_usuario"
                  nameButton="Buscar CEP"
                  classDiv="form-group col-sm-2 align_cep_button">        
                </ButtonWithDiv>

                {this.renderizarErro()}

                <InputReadOnly
                  styleContainerInput="form-group col-sm-11"
                  inputId="logradouro_estabeleciemento"
                  labelText="Logradouro:"
                  styleInput="form-control sombra_input"
                  value={this.state.endereco.logradouro}
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
                  type="text"
                  >
                </InputWithPlaceholder>

                <InputReadOnly
                  styleContainerInput="form-group col-md-9"
                  inputId="cidade_estabeleciemento"
                  labelText="Cidade:"
                  styleInput="form-control sombra_input"
                  value={this.state.endereco.localidade}
                  onChange={this.setCidade}
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
                  type="text"
                  >
                </InputReadOnly>
                </div>
              </div>
            </div>
            <hr className="hr" />
            <div className="card_informacoes_conta">
              <div className="informacoes_conta_esquerda"> 
                <span>Informações Finais</span>
                <p><small>A senha deve conter mais de 8 Caracteres</small></p>
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
                      type="email">
                    </InputWithPlaceholder>

                  <Input
                    styleContainerInput="form-group col-sm-11"
                    inputId="senha_estabelecimento"
                    labelText="Senha:*"
                    styleInput="form-control sombra_input"
                    value={this.state.senha}
                    onChange={this.setSenha}
                    type="password">
                  </Input>

                  <Input
                    styleContainerInput="form-group col-sm-11"
                    inputId="confirmar_senha_estabelecimento"
                    labelText="Confirmar Senha:*"
                    value={this.state.confirmacaoSenha}
                    onChange={this.setConfirmarSenha}
                    styleInput="form-control sombra_input" 
                    type="password"> 
                  </Input>
                </div>
              </div>
            </div>
            <hr className="hr" />
              <div id="caixa_botao_cadastro_estabeleciemento">
                <Link to="/escolhaConta">
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
