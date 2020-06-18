import { Component } from 'react';
import PropTypes from "prop-types";
import {removeItem} from "../utils/sessionStorage";

export default class Logout extends Component {

     //Propriedades do WithRouter
     static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    };

    componentWillMount(){
        removeItem("token");
        removeItem("estabelecimento");
        this.props.history.push("/");
    }

    render() {
        return null;
    }
}

