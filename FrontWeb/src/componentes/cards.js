import React, { Component } from 'react';
import ativado from "../icones/ativado.png";
import editar from "../icones/editar.png";
import '../css/bootstrap.css';
import {serverImagem} from "../utils/http";
const ip = serverImagem();

export default class CardAgendamentoDoDia extends Component { 

    render() {
        return(
            <div className="card card-border mt-2 mb-4 mr-5 float-left">
                <div className="card-header font-weight-bold">
                   {this.props.info}
                </div>
                <div className="card-body text-secondary">
                    <div className="card-text text-dark">
                        Cliente:   
                        <span className="ml-2">{this.props.cliente}</span>
                    </div>
                    <div className="card-text text-dark mt-3">
                        Funcionário:   
                        <span className="ml-2">{this.props.funcionario}</span>
                    </div>
                    <div className="card-text text-dark mt-3">
                        Serviço:   
                        <span className="ml-2">{this.props.servico}</span>
                    </div>
                    <div className="card-text text-dark mt-3">
                        Valor:   
                        <span className="ml-2">{this.props.valor}</span>
                    </div>
                    <div className="card-text text-dark mt-3">
                        Pagamento: 
                        <span className="ml-2">{this.props.pagamento}</span>
                    </div>
                </div>
                <div className="card-footer d-flex flex-row-reverse">
                    <div className="mt-1">
                        <button className="btn btn-success" value={this.props.idAgendamento} type="button" onClick={this.props.onClick}>
                            Finalizar
                        </button>
                    </div>
                </div>
            </div>
        )
    }
}

export class CardAgendamentoDoMes extends Component {

    render() {
        return(
            <div className="card card-border mt-2 mb-4 mr-5 float-left">
                <div className="card-header font-weight-bold">
                   {this.props.info}
                </div>
                <div className="card-body text-secondary">
                    <div className="card-text text-dark">
                        Cliente:   
                        <span className="ml-2">{this.props.cliente}</span>
                    </div>
                    <div className="card-text text-dark mt-3">
                        Funcionário:   
                        <span className="ml-2">{this.props.funcionario}</span>
                    </div>
                    <div className="card-text text-dark mt-3">
                        Serviço:   
                        <span className="ml-2">{this.props.servico}</span>
                    </div>
                    <div className="card-text text-dark mt-3">
                        Valor:   
                        <span className="ml-2">{this.props.valor}</span>
                    </div>
                    <div className="card-text text-dark mt-3">
                        Pagamento: 
                        <span className="ml-2">{this.props.pagamento}</span>
                    </div>
                </div>
                <div className="card-footer d-flex flex-row-reverse">
                    <div className="mt-1">
                        <button className="btn btn-danger" value={this.props.idAgendamento} type="button" onClick={this.props.onClick}>
                           Cancelar
                        </button>
                    </div>
                </div>
            </div>
        )
    }
}

export class CardFuncionario extends Component{
    render(){
        const width = {
            width:"285px"
        }

        const url = ip+this.props.foto

        return(
            <div className="card card-border mb-4 mr-5 " style={width}>
                <figure className="pt-3 ">
                    <div className="rounded-circle circulo mx-auto bg-white shadow-sm d-block">
                        <img src={url} alt="teste"  className="circulo rounded-circle circulo2 mx-auto "/> 
                    </div>
                </figure>
                <div className="card-body pt-1 text-secondary">
                    <p className="card-text h4 text-center">{this.props.nome}</p>
                    <p className="card-text font-weight-bold text-center">{this.props.email}</p>
                    <p className="card-text font-weight-bold">Cargo: {this.props.cargo} </p>
                </div>
                <div className="card-footer row-12">
                    <div className="float-left col-6">
                        <img className="img-logout mx-auto d-block " src={ativado} alt="ativado"/>
                        <p className="card-text text-center text-dark" >Ativo</p>
                    </div>
                    <div className="float-left col-6">
                        <img className="img-logout mx-auto d-block " src={editar} alt="editar"/>
                        <p className="card-text text-center text-dark" >Editar</p>
                    </div>
                </div>
            </div>
        )
    }
}

