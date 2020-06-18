import React,{Component} from 'react';
import "../css/reset.css";
import "../css/style.css";
import "../css/bootstrap.css";

export default class Exemplo extends Component{
    render(){
        return(
            
            <div className="tab-pane ml-3 fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                            
                <div className="card-deck   mt-4  float-left">

                    <div className="card border-secondary mb-4 mr-5 " style="width: 285px;">
                    <figure className="pt-3 "><img src="imagens/naruto.png" alt="teste"  className="rounded-circle circulo2 mx-auto d-block"/> </figure>
                    <div className="card-body pt-1 text-secondary">
                        <p className="card-text h4 text-center">Funcionário 01</p>
                        <p className="card-text font-weight-bold text-center">fun1@email.com</p>
                        <p className="card-text font-weight-bold">Cargo: </p>
                        <p className="card-text font-weight-bold">Agendamento: </p>
                        <p className="card-text font-weight-bold">Receita Total: </p>
                    </div>
                        <div className="card-footer row-12">
                            <div className="float-left col-6">
                                <img className="img-logout mx-auto d-block " src="icones/ativado.png" alt="ativado"/>
                                <p className="card-text text-center text-dark" >Ativo</p>
                                
                            </div>
                            
                            <div className="float-left col-6">
                                
                                <img className="img-logout mx-auto d-block " src="icones/editar.png" alt="editar"/>
                                <p className="card-text text-center text-dark" >Editar</p>
                            </div>
                        </div>
                    </div>
                </div>
                    
                <div className="card-deck   mt-4 float-left">

                    <div className="card border-secondary mb-4 mr-5 " style="width: 285px;">
                    <figure className="pt-3 "><img src="imagens/naruto.png" alt="teste"  className="rounded-circle circulo2 mx-auto d-block"/></figure>
                    <div className="card-body pt-1 text-secondary">
                        <p className="card-text h4 text-center">Funcionário 02</p>
                        <p className="card-text font-weight-bold text-center">fun1@email.com</p>
                        <p className="card-text font-weight-bold">Cargo: </p>
                        <p className="card-text font-weight-bold">Agendamento: </p>
                        <p className="card-text font-weight-bold">Receita Total: </p>
                    </div>
                        <div className="card-footer row-12">
                            <div className="float-left col-6">
                                <img className="img-logout mx-auto d-block " src="icones/ativado.png" alt="ativado"/>
                                <p className="card-text text-center text-dark" >Ativo</p>
                                
                            </div>
                            
                            <div className="float-left col-6">
                                
                                <img className="img-logout mx-auto d-block " src="icones/editar.png" alt="editar"/>
                                <p className="card-text text-center text-dark" >Editar</p>
                            </div>
                        </div>
                    </div>
                </div> 
                
                <div className="card-deck mt-4  float-left">

                    <div className="card border-secondary mb-4 mr-5 " style="width: 285px;">
                    <figure className="pt-3 "><img src="imagens/naruto.png" alt="teste"  className="rounded-circle circulo2 mx-auto d-block"/></figure>
                    <div className="card-body pt-1 text-secondary">
                        <p className="card-text h4 text-center">Funcionário 03</p>
                        <p className="card-text font-weight-bold text-center">fun1@email.com</p>
                        <p className="card-text font-weight-bold">Cargo: </p>
                        <p className="card-text font-weight-bold">Agendamento: </p>
                        <p className="card-text font-weight-bold">Receita Total: </p>
                    </div>
                        <div className="card-footer row-12">
                            <div className="float-left col-6">
                                <img className="img-logout mx-auto d-block " src="icones/ativado.png" alt="ativado"/>
                                <p className="card-text text-center text-dark" >Ativo</p>
                                
                            </div>
                            
                            <div className="float-left col-6">
                                
                                <img className="img-logout mx-auto d-block " src="icones/editar.png" alt="editar"/>
                                <p className="card-text text-center text-dark">Editar</p>
                            </div>
                        </div>
                    </div>
                </div>
                    
                <div className="card-deck mt-4 float-left">

                    <div className="card border-secondary mb-4 mr-5 " style="width: 285px;">
                    <figure className="pt-3 "><img src="imagens/naruto.png" alt="teste"  className="rounded-circle circulo2 mx-auto d-block"/></figure>
                    <div className="card-body pt-1 text-secondary">
                        <p className="card-text h4 text-center">Funcionário 04</p>
                        <p className="card-text font-weight-bold text-center">fun1@email.com</p>
                        <p className="card-text font-weight-bold">Cargo: </p>
                        <p className="card-text font-weight-bold">Agendamento: </p>
                        <p className="card-text font-weight-bold">Receita Total: </p>
                    </div>
                        <div className="card-footer row-12">
                            <div className="float-left col-6">
                                <img className="img-logout mx-auto d-block " src="icones/ativado.png" alt="ativado"/>
                                <p className="card-text text-center text-dark" >Ativo</p>
                                
                            </div>
                            
                            <div className="float-left col-6">
                                
                                <img className="img-logout mx-auto d-block " src="icones/editar.png" alt="editar"/>
                                <p className="card-text text-center text-dark" >Editar</p>
                            </div>
                        </div>
                    </div>
                </div>
                    
            </div>
            
            
        
        
        
    

        );
    }
}
