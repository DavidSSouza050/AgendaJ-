import React, { Component } from 'react';
import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";

export default class MensagemErro extends Component {
    render() {
        return(
            <div className={this.props.className} id={this.props.id}>
                <small>{this.props.mensagemErro}</small>
            </div>
        );
    }
}
