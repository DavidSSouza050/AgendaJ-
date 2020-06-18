import React, { Component } from 'react';
import "../css/style.css";
import CardAgendamentoDoDia, {CardAgendamentoDoMes} from "./cards";
import axios from 'axios';
import "../css/bootstrap.css";
import getItem from "../utils/sessionStorage";
import Tabs, { Tab } from 'react-bootstrap/Tabs'
import api from "../utils/http";
import dateFormat from "dateformat";

// Constantes Globais
const ip = api();
let token = getItem('token');
let config = {
    'token': token
}


export default class AgendamentosPendentes extends Component {

    constructor(){
        super();

        this.state = {
            listaAgendamentosDoDia:[],
            listaAgendamentosDoMes:[]
        }

        this.buscarAgendamentosDoDia = this.buscarAgendamentosDoDia.bind(this);
        this.buscarAgendamentosDoMes = this.buscarAgendamentosDoMes.bind(this);
        this.cancelarAgendamento = this.cancelarAgendamento.bind(this);
        this.finalizarAgendamento = this.finalizarAgendamento.bind(this);
    
    }

    componentDidMount(){
        this.buscarAgendamentosDoDia();
        this.buscarAgendamentosDoMes();
    }


    buscarAgendamentosDoDia(){
       
        const estabelecimento = JSON.parse(getItem('estabelecimento'));
        const idEstabelecimento = estabelecimento.idEstabelecimento;
        const url = "http://"+ip+"/agendamentos/estabelecimento/"+idEstabelecimento+"/dia/servicosPendente";

        axios({
            method:"get",
            url: url,
            headers: config        
        })
        .then((response)=>{
            let listaAgendamentos = response.data;         
            this.setState({listaAgendamentosDoDia:listaAgendamentos})                    
        })
        .catch((error)=>{
            console.log(error);
        })
    }

    buscarAgendamentosDoMes(){
        
        const estabelecimento = JSON.parse(getItem('estabelecimento'));
        const idEstabelecimento = estabelecimento.idEstabelecimento;
        const url = "http://"+ip+"/agendamentos/estabelecimento/"+idEstabelecimento+"/mes/servicosPendente";

        axios({
            method:"get",
            url: url,
            headers: config        
        })
        .then((response)=>{
            let listaAgendamentos = response.data;   
            this.setState({listaAgendamentosDoMes:listaAgendamentos})                           
        })

    }

    finalizarAgendamento(evento){
        let idAgendamento = evento.target.value;
        const url = "http://"+ip+"/agendamentos/finalizar/"+idAgendamento;

        axios({
            method:"put",
            url: url,
            headers: config        
        })
        .then(()=>{
          this.buscarAgendamentosDoDia();                         
        })
               
        
    }

    cancelarAgendamento(evento){
        let idAgendamento = evento.target.value;
        const url = "http://"+ip+"/agendamentos/cancelar/"+idAgendamento;

        axios({
            method:"put",
            url: url,
            headers: config        
        })
        .then(()=>{
          this.buscarAgendamentosDoMes();                         
        })
    }

    render() {
        return (
            <div className="text-dark">
                <div className="card mx-auto mt-1 mb-4">
                    <div className="card-header"> 
                        <h1>Agendamentos Pendentes</h1>
                    </div>
                </div>
                <div className="container padding mt-5 ml-4" id="container-tab">
                    <Tabs defaultActiveKey="agendamentosDoDia" id="tab" className="bg-light w-100">
                        <Tab eventKey="agendamentosDoDia" title="Agendamentos do Dia">
                            <div className="mt-5">
                                {
                                    this.state.listaAgendamentosDoDia.map(
                                        agendamento =>
                                      

                                        <CardAgendamentoDoDia
                                            info={dateFormat(agendamento.dataHora,"dd/mm/yyyy HH:MM")}
                                            funcionario={agendamento.nomeFuncionario}
                                            cliente={agendamento.nomeCliente}
                                            servico={agendamento.servico}
                                            valor={"R$ "+ parseFloat(agendamento.preco).toFixed(2).replace(".",",")}
                                            pagamento="Dinheiro"
                                            onClick={this.finalizarAgendamento}
                                            idAgendamento={agendamento.idAgendamento}
                                        />
                                            
                                        
                                    )
                                }
                               
                            </div>    
                        </Tab>
                        <Tab eventKey="agendamentosDoMes" title="Agendamentos do Mês">
                            <div className="mt-5">    
                                {
                                    this.state.listaAgendamentosDoMes.map(
                                        agendamento =>
                                        
                                             <CardAgendamentoDoMes
                                                info={dateFormat(agendamento.dataHora,"dd/mm/yyyy HH:MM")}
                                                funcionario={agendamento.nomeFuncionario}
                                                cliente={agendamento.nomeCliente}
                                                servico={agendamento.servico}
                                                valor={"R$ "+ parseFloat(agendamento.preco).toFixed(2).replace(".",",")}
                                                pagamento="Dinheiro"
                                                onClick={this.cancelarAgendamento}
                                                idAgendamento={agendamento.idAgendamento}
                                            />
                                    )
                                }
                            </div>
                        </Tab>
                    </Tabs>
                </div>    
            </div>
        );
    }
}