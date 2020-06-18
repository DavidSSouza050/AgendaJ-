import React, { Component } from 'react';
import MenuLateral from './menuLateral';
import {HeaderSistema} from './headerSimples';

export default class HeaderMenuSistema extends Component {

  componentDidMount(){
    document.body.className = "body-sistema back";
  }

  
  render() {
    const overflow = {
      overflow:"hidden"
    } 
  
    return(
        <div className="body-sistema">
            <HeaderSistema></HeaderSistema>
              <div className="align"></div>
              <div className="container-all d-inline container-fluid bg-light">
                  <MenuLateral></MenuLateral>
                  <div className="container-conteudo float-left">
                    <div className="container shadow rounded box-conteudo bg-white" style={overflow}>
                        {this.props.children}
                    </div>
                  </div>
              </div>    
        </div>
    );
  }
}
