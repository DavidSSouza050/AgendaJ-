import React, { Component } from 'react';
import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";

export default class TextArea extends Component {

  render() {
    return (
        <div className={this.props.styleContainer}>
            <label htmlFor={this.props.textareaId}>{this.props.labelText}</label>
            <textarea className={this.props.styleTextArea} id={this.props.textareaId}></textarea>
        </div>
    );
  }
}
