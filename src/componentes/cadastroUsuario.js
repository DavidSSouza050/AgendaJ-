import React, { Component } from 'react';
import HeaderSimples from './headerSimples';
import Input, { InputReadOnly, InputWithPlaceholder} from './input';
import Button, {ButtonWithDiv} from "./botao";
import axios from "axios";
import {MaskCEP} from "../utils/cepValidator";

export default class CadastroEstabelecimento extends Component {

  constructor(){
    super();
    this.state = {cep:'', endereco:[]};
    this.setCep = this.setCep.bind(this);
    this.pesquisarCep = this.pesquisarCep.bind(this);
    this.verificarCEP = this.verificarCEP.bind(this);
    // this.input = React.createRef;
  }



  setCep(evento) {
    let caixaCEP = document.querySelector('#cep_estabelecimento');
    let cepValue = caixaCEP.value; 
    this.setState({cep: MaskCEP(cepValue)});
  }

  verificarCEP(){
    let caixaCEP = document.querySelector('#cep_estabelecimento');
    let cep = caixaCEP.value; 

    if(cep.length === 0){

    } else if(cep.length === 9) {
      console.log(cep);
      this.pesquisarCep(cep)
    }
  }

  pesquisarCep(cep){
    const url = `https://viacep.com.br/ws/${cep}/json/`;
    axios.get(url)
      .then(response => response.data)
      .then((data) => {
      this.setState({endereco:data});
    });
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
          <form action="">
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
                    labelText="Nome Do Estabelecimento:"
                    styleInput="form-control sombra_input"
                    type="text"
                    >
                  </Input>

                  <InputWithPlaceholder
                    styleContainerInput="form-group col-sm-11"
                    inputId="razao_social_estabelecimento"
                    labelText="Razão Social:"
                    styleInput="form-control sombra_input"
                    placeholder="Obrigatório"
                    type="text">
                  </InputWithPlaceholder>

                  <InputWithPlaceholder
                    styleContainerInput="form-group col-sm-11"
                    inputId="cnpj_estabelecimento"
                    labelText="CNPJ:"
                    styleInput="form-control sombra_input"
                    placeholder="Obrigatório"
                    type="text"> 
                  </InputWithPlaceholder>

                  <InputWithPlaceholder
                    styleContainerInput="form-group col-md-6"
                    inputId="telefone_estabelecimento"
                    labelText="Telefone:"
                    styleInput="form-control sombra_input"
                    placeholder="Obrigatório"
                    type="text">       
                  </InputWithPlaceholder>

                  <Input
                    styleContainerInput="form-group col-md-5"
                    inputId="celular_estabelecimento"
                    labelText="Celular:"
                    styleInput="form-control sombra_input"
                    type="text">
                  </Input>
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
                  labelText="CEP:"
                  styleInput="form-control sombra_input"
                  placeholder="Obrigatório"
                  onKeyUp={this.setCep}
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
            
                <InputReadOnly
                  styleContainerInput="form-group col-sm-11"
                  inputId="logradouro_estabeleciemento"
                  labelText="Logradouro:"
                  styleInput="form-control sombra_input"
                  value={this.state.endereco.logradouro}
                  type="text"
                  >
                </InputReadOnly>

                <InputReadOnly
                  styleContainerInput="form-group col-md-9"
                  inputId="bairro_estabeleciemento"
                  labelText="Bairro:"
                  styleInput="form-control sombra_input"
                  value={this.state.endereco.bairro}
                  type="text"
                  >
                </InputReadOnly>

                <InputWithPlaceholder
                  styleContainerInput="form-group col-md-2"
                  inputId="numero_estabeleciemnto"
                  labelText="Numero:"
                  styleInput="form-control sombra_input"
                  placeholder="Obrigatório"
                  type="text"
                  >
                </InputWithPlaceholder>

                <InputReadOnly
                  styleContainerInput="form-group col-md-9"
                  inputId="cidade_estabeleciemento"
                  labelText="Cidade:"
                  styleInput="form-control sombra_input"
                  value={this.state.endereco.localidade}
                  type="text"
                  >
                </InputReadOnly>

                <InputReadOnly
                  styleContainerInput="form-group col-md-2"
                  inputId="uf_estabeleciemento"
                  labelText="UF:"
                  styleInput="form-control sombra_input"
                  value={this.state.endereco.uf}
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
                      labelText="E-mail:"
                      styleInput="form-control sombra_input"
                      placeholder="Obrigatório"
                      type="email">
                    </InputWithPlaceholder>

                  <Input
                    styleContainerInput="form-group col-sm-11"
                    inputId="senha_estabelecimento"
                    labelText="Senha:"
                    styleInput="form-control sombra_input"
                    type="password">
                  </Input>

                  <Input
                    styleContainerInput="form-group col-sm-11"
                    inputId="confirmar_senha_estabelecimento"
                    labelText="Confirmar Senha:"
                    styleInput="form-control sombra_input" type="password"> 
                  </Input>
                </div>
              </div>
            </div>
            <hr className="hr" />
              <div id="caixa_botao_cadastro_estabeleciemento">
                <Button
                  classButton="botao_cancelar_cadastro"
                  id="cancelar_cadastro"
                  nameButton="Cancelar">
                </Button>

                <Button
                  classButton="botao_cadastro"
                  id="cadastrar_usuario"
                  nameButton="Criar Conta">
                </Button>
            </div>
          </form>
        </div>
      </div>
      
    );
  }
}
