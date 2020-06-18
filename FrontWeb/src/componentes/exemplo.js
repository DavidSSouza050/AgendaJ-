import React,{Component} from 'react';

export default class Exemplo extends Component{
    render(){
        return(
            <div className="container bg-light align-middle rounded px-4 py-3 shadow" style="width: 40%; height:42%; margin-top: 80px;">
                <div className="w-100 h-auto pt-3">
                    <h3 className="mb-4 text-dark">Cadastro realizado com Sucesso!</h3>
                    <hr className="hr w-100"/>
                </div>
                <div className="w-100 h-50 d-flex flex-row">
                    <div className="p2 h-100 w-25">
                        <img className="img-fluid mt-4 ml-4 h-75 w-75" src="icones/checked.png" alt="ok"/>  
                    </div>
                    <div className="p2 h-100 w-75 px-4">
                        <span className="mx-5 my-5">
                            <p className="text-justify text-black-50 font-weight-normal texto_cadastrado_com_sucesso">
                                Seu cadastro foi realizado com sucesso, apartir de agora você poderá gerenciar os agendamentos dos seus serviços por meio da nossa plataforma. Se desejar realizar o Login pela primeira vez basta clicar no botão abaixo que o redirecionará para a página de Login.
                            </p>
                        </span>
                    </div>  
                </div> 
                <div className="w-100 d-flex flex-row-reverse mt-5">
                    <button type="button" className="bg-success btn btn-lg shadow-sm text-light" id="btn_login">Fazer Login</button>
                </div>
            </div> 
        );
    }
}
