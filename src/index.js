import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import EscolhaConta from './componentes/escolhaConta';
import CadastroEstabelecimento from './componentes/cadastroUsuario';

ReactDOM.render(
    <BrowserRouter>
        <Switch>
            <Route path="/" exact={true} component={App}></Route>
            <Route path="/cadastroEstabelecimento" component={CadastroEstabelecimento}></Route>
            <Route path="/escolhaConta" component={EscolhaConta}></Route>
            {/* <Route path="/estabelecimento/login" component={Login}></Route>
            <Route path="/cliente/login" component={LoginCliente}></Route> */}
        </Switch>
    </BrowserRouter>, 
    document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
