const socketio = require('socket.io');
const request = require('request');

module.exports.listen = (app) => {
    const io = socketio.listen(app);
    
    io.on('connection', function(socket) {
        
        socket.on('join', function(data) {
            socket.join('estabelecimento_'+data.idEstabelecimento);
        });

        socket.on('agendamento', function(data) {
            // json para cadastro de agendamento
            const jsonAgendamento = {
                cliente: {
                    idCliente: data.idCliente
                },
                funcionario: {
                    idFuncionario: data.idFuncionario
                },
                estabelecimento: {
                    idEstabelecimento: data.idEstabelecimento
                },
                dataHorarioAgendado: data.dataHorarioAgendado
            };


            request.post({
                uri: "http://localhost:8080/agendamentos",
                headers: {
                    "Content-Type": "application/json",
                    token: data.token
                },
                body: jsonAgendamento,
                json: true
            }, function(error, response, bodyA) {
                if(!error && response.statusCode === 201) {

                    if(typeof bodyA == 'string'){
                        bodyA = JSON.parse(bodyA);
                    }

                    //constrindo json para facilitar logica
                    const mBody = {
                        idAgendamento: bodyA.idAgendamento, 
                        idCliente: bodyA.cliente.idCliente,
                        nomeCliente: bodyA.cliente.nome +" "+ bodyA.cliente.sobrenome,
                        idEstabelecimento: bodyA.estabelecimento.idEstabelecimento,
                        nomeEstabelecimento: bodyA.estabelecimento.nomeEstabelecimento,
                        idFuncionario: bodyA.funcionario.idFuncionario,
                        nomeFuncionario: bodyA.funcionario.nome,
                        dataHorarioAgendado: bodyA.dataHorarioAgendado,
                        status: bodyA.status
                    };

                    // json para cadastro relacao
                    const jsonRelacaoservico = {
                        servico: {
                            idServico: data.idServico
                        },
                        agendamento: {
                            idAgendamento: mBody.idAgendamento
                        }
                    };

                    //cadastrando relação
                    request.post({
                        uri: "http://localhost:8080/agendamentoServicos",
                        headers: {
                            "Content-Type": "application/json",
                            token: data.token
                        },
                        body: jsonRelacaoservico,
                        json: true
                    }, function(error, response, bodyR) {
                        if(!error && response.statusCode === 201) {
                            //data hora para conparação 
                            let datahora = mBody.dataHorarioAgendado.split(" ");
                            let dataAno = datahora[0].split("-");
                            
                            const date =  new Date();
                            let dia = date.getDate();
                            //acresentado o 0 para a comparação
                            if(dia < 10){
                                dia = "0"+dia;
                            }

                            console.log(dia);
                            if(dia === dataAno[2]){
                                request.get({
                                    uri: `http://localhost:8080/agendamentos/estabelecimento/${mBody.idEstabelecimento}/dia/servicosPendente`,
                                    headers: {
                                        token: data.token
                                    }
                                }, function(error2, response2, bodyDia) {

                                    console.log(bodyDia);
                                    if(!error2 && response2.statusCode === 200) {

                                        const mBodyDia =  JSON.parse(bodyDia);
                                        for(let i=0; i < mBodyDia.length ;i++){
                                            
                                            if(mBodyDia[i].idAgendamento === mBody.idAgendamento){
                                            
                                                io.sockets.in('estabelecimento_'+data.idEstabelecimento).emit('agendamentoDia', mBodyDia[i]);
                                                console.log(mBodyDia[i]);
                                            }
                                        }
                                    }

                                    
                                });
                            }

                            //pegando o total de agendamentos
                            request.get({
                                uri: "http://localhost:8080/agendamentos/totalDia/estabelecimento/"+data.idEstabelecimento,
                                headers: {
                                    token: data.token
                                }
                            }, function(error2, response2, body2) {
                                console.log(body2)
                                if(!error2 && response2.statusCode === 200) {
                                    io.sockets.in('estabelecimento_'+data.idEstabelecimento).emit('qtdAgendamento', body2);
                                }
                            });
                        }
                    });
                }
            });
        });
    });
};
