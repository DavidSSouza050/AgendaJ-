import React, { Component } from 'react';
import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";

// import { Container } from './styles';

export default class Input extends Component {
  render() {
    return (
        <div className={this.props.styleContainerInput}>
            <label className={this.props.labelClass} htmlFor={this.props.inputId}>{this.props.labelText}</label>
            <input type={this.props.type} maxLength={this.props.maxLength} onChange={this.props.onChange} className={this.props.styleInput} id={this.props.inputId} value={this.props.value}/>
        </div>
        
    );
  }
}

export class InputReadOnly extends Component {
  constructor(props){
    super(props);
    this.state = {readOnly: true, value:""};
   
  }

  render() {
    return (
        <div className={this.props.styleContainerInput}>
          <label className={this.props.labelClass} htmlFor={this.props.inputId}>{this.props.labelText}</label>
          <input type={this.props.type} className={this.props.styleInput} value={this.props.value} id={this.props.inputId} readOnly={this.state.readOnly} />
        </div>
        
    );
  }
}

export class InputWithPlaceholder extends Component {
  render() {
    return (
        <div className={this.props.styleContainerInput}>
            <label className={this.props.labelClass} htmlFor={this.props.inputId}>{this.props.labelText}</label>
            <input type={this.props.type} autoComplete="off" maxLength={this.props.maxLength} className={this.props.styleInput} id={this.props.inputId} value={this.props.value} onChange={this.props.onChange} placeholder={this.props.placeholder}/>
        </div>
        
    );
  }
}
 