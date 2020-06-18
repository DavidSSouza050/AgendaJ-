import React, { Component } from 'react';

export default class PrimeirosPassos extends Component {
    render() {
        return(
            <div>  
                <div className="row-12 center-full" id="titulo-seja-bem-vindo">
                    <div className="col-6 text-center text-dark h-75">
                        <h1>Seja Bem Vindo ao Sistema AgendaJá!</h1>
                    </div>
                </div>
                <div className="row-12 padding center-full" id="caixa-info">
                    <div className="row-5 bg-light shadow-sm rounded-sm w-50 h-75 border float-left mr-5">
                        <div className="col-12 center-full h-25">
                            <div className="row-5 text-dark text-center w-100 h-100 pt-3">
                                <h4>Os primeiros passos para começar a gerenciar seus agendamentos são:</h4>
                            </div>
                        </div>
                        <div className="col-12 h-75">
                            <div className="row-6 pl-3 h-100">
                                <ul className="lead mt-2">
                                    <li className="font-lista mt-2">Informar os serviços realizados no seu estabelecimento;</li>
                                    <li className="font-lista mt-2">Cadastrar sua equipe de funcionários;</li>
                                    <li className="font-lista mt-2">Realizar o Upload das fotos do portifólio do seu negócio;</li>
                                    <li className="font-lista mt-2">Agardar os agendamentos;</li>
                                </ul>   
                            </div>
                        </div>
                    </div>
                    <div className="row-5 bg-light shadow-sm rounded-sm w-25 h-75 border float-left ml-5">
                        <div className="col-12 h-25 text-dark text-center pt-1">
                            <h4>
                                Link para as principais ferramentas
                            </h4>
                        </div>
                        <div className="col-12 h-75">
                            <div className="row-6 pl-3 h-100">
                                <ul className="lead mt-2">
                                    <li className="font-lista mt-2">Serviços</li>
                                    <li className="font-lista mt-2">Funcionário</li>
                                    <li className="font-lista mt-2">Portifólio</li>
                                    <li className="font-lista mt-2">Estabelecimento</li>
                                </ul>   
                            </div>
                        </div>
                    </div>
                </div>
            </div>    
        );
    }
}
