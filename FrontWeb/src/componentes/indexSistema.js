import React, { Component } from 'react';
import PrimeirosPassos from "./primeirosPassos"
import "../css/style.css";
import "../css/bootstrap.css";


export default class IndexSistema extends Component {

    componentDidMount(){
        window.document.body.className = "body-sistema"; 
    }

    render(){
        return (  
            <PrimeirosPassos/>
        );
    }
}
