import React, { Component } from 'react';
import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../css/style.css";
// import { Container } from './styles';

export default class Button extends Component {
  render() {
    return (
      <input 
        type="Button" 
        className={this.props.classButton} 
        id={this.props.idButton}
        onClick={this.props.onClick} 
        value={this.props.nameButton}>
      </input>
    ); 
  }
}

export class ButtonWithDiv extends Component {
  render() {
    return(
      <div className={this.props.classDiv}> 
        <input 
          type="Button" 
          className={this.props.classButton} 
          id={this.props.idButton}
          onClick={this.props.onClick} 
          value={this.props.nameButton}>
        </input>
      </div>
    )
  }
}

