const socketio = require('socket.io');
const request = require('request');

module.exports.listen = (app) => {
    const io = socketio.listen(app);
    
    io.on('connection', function(socket) {
        
        socket.on('join', function(data) {
            socket.join('estabelecimento_'+data.idEstabelecimento);
        });

        socket.on('agendamento', function(data) {
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
                uri: "http://54.146.225.104:8080/agendamentos",
                headers: {
                    "Content-Type": "application/json",
                    token: data.token
                },
                body: jsonAgendamento,
                json: true
            }, function(error, response, body) {
                if(!error && response.statusCode === 201) {
                    io.sockets.in('estabelecimento_'+data.idEstabelecimento).emit('agendamento', body);
                    request.get({
                        uri: "http://54.146.225.104:8080/agendamentos/total/estabelecimento/"+data.idEstabelecimento,
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



        });

    });
};