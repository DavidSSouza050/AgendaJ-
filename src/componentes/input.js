import React, { Component } from 'react';
import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";

// import { Container } from './styles';

export default class Input extends Component {
  render() {
    return (
        <div className={this.props.styleContainerInput}>
            <label htmlFor={this.props.inputId}>{this.props.labelText}</label>
            <input type={this.props.type} maxLength={this.props.maxLength}  className={this.props.styleInput} id={this.props.inputId} value={this.props.value}/>
        </div>
        
    );
  }
}

export class InputReadOnly extends Component {
  render() {
    return (
        <div className={this.props.styleContainerInput}>
            <label htmlFor={this.props.inputId}>{this.props.labelText}</label>
            <input type={this.props.type} className={this.props.styleInput} value={this.props.value} id={this.props.inputId} ref={this.props.ref} readOnly/>
        </div>
        
    );
  }
}

export class InputWithPlaceholder extends Component {
  render() {
    return (
        <div className={this.props.styleContainerInput}>
            <label htmlFor={this.props.inputId}>{this.props.labelText}</label>
            <input type={this.props.type} autoComplete="off" maxLength={this.props.maxLength} className={this.props.styleInput} id={this.props.inputId} value={this.props.value} onChange={this.props.onKeyUp} placeholder={this.props.placeholder}/>
        </div>
        
    );
  }
}
 