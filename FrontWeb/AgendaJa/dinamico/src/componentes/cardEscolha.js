import React, { Component } from 'react';
import "../css/style.css";
import "../css/bootstrap.css";

export default class CardEscolha extends Component {
  render() {
    return (
        <div className="item_card card bg-light" id="espaço_card">
            <div className="card-body">
                <div className="header_card">
                    <div className="icone_header_card">
                        <img src={this.props.cardHeaderIcon} className="icones" alt="Icone de Estabelecimento"/>
                    </div>
                    <div className="title_header_card">
                        <h5 className="card-title formatacao_titulo_card">{this.props.cardHeaderTitle}</h5>
                    </div>
                </div> 
                <hr className="line"/>
                <div className="card-text texto_card">{this.props.cardText}</div>
                <div id={this.props.arrowDirection}>
                    <img src={this.props.cardArrowIcon} className="icone_seta" alt="Cadastrar Estabelecimento"/>
                </div>
            </div>
        </div>
    );
  }
}
