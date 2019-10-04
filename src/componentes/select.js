import React, { Component } from 'react';

export default class Select extends Component {
  render() {
    return(
        <div className="form-group col-sm-11">
            <label htmlFor="assunto_fale_conosco">Assunto:</label>
            
            <select className="form-control sombra_input" id="assunto_fale_conosco" >
                <option>Tipo do Assunto</option>
                <option>Comentário</option>
                <option>Sugestão</option>
                <option>Dúvida</option>
            </select>
        </div>
    );
  }
}
