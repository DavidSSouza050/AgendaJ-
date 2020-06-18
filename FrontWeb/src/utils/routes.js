import React, {Fragment} from 'react';
import '../index.css';
import { BrowserRouter, Switch, Route, Redirect} from 'react-router-dom';
import EscolhaConta from '../componentes/escolhaConta';
import CadastroEstabelecimento from '../componentes/cadastroUsuario';
import CadastradoComSucesso from '../componentes/cadastradoComSucesso'
import LandingPage from "../componentes/landingPage";
import PrimeirosPassos from '../componentes/primeirosPassos'
import Logout from '../componentes/logout';
import Login from '../componentes/login';
import HeaderMenuSistema from '../componentes/headerMenuSistema'
import getItem from './sessionStorage';
import AgendamentosPendentes from '../componentes/agendamentosPendentes';
import ServicosCadastrados from '../componentes/servicosCadastrados';
import Funcionario from '../componentes/funcionarios'

const isAuthenticated = () =>{
    if(getItem("token") !== null || getItem("token") !== ""){
        return true;
    }else{
        return false;
    }
}

const PrivateRoute = ({ component: Component, ...rest }) => (
    <Route
      {...rest}
      render={props =>
        isAuthenticated() ? (
          <Component {...props} />
        ) : (
          <Redirect to={{ pathname: "/", state: { from: props.location } }} />
        )
      }
    />
  );

export default function Routes(){
    return(
        <BrowserRouter>
            <Switch>
                <Route path="/" exact={true} component={Login}></Route>
                <Route path="/cadastroEstabelecimento" component={CadastroEstabelecimento}></Route>
                <Route path="/escolhaConta" component={EscolhaConta}></Route>
                <Route path="/site" component={LandingPage}></Route>
                <Route path="/cadastradoComSucesso" component={CadastradoComSucesso}></Route>

                <Route path="/estabelecimento" render={({match:{url}})=>(
                    <Fragment>
                      <HeaderMenuSistema>
                        <PrivateRoute path={`${url}/primeiros-passos`} component={PrimeirosPassos} exact></PrivateRoute>
                        <PrivateRoute path={`${url}/agendamentos-pendentes`} component={AgendamentosPendentes} exact></PrivateRoute>
                        <PrivateRoute path={`${url}/servicos-cadastrados`} component={ServicosCadastrados} exact></PrivateRoute>
                        <PrivateRoute path={`${url}/funcionario`} component={Funcionario} exact></PrivateRoute>
                      </HeaderMenuSistema>
                    </Fragment>  
                  )}
                />

                <Route path="/Logout" component={Logout}></Route>
            </Switch>
        </BrowserRouter>
    );
}
