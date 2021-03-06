package br.senai.sp.agendaja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.CalculoHorario.CalculoHorario;
import br.senai.sp.agendaja.Model.Agendamento;
import br.senai.sp.agendaja.Model.EmServico;
import br.senai.sp.agendaja.Model.Estabelecimento;
import br.senai.sp.agendaja.Model.Funcionario;
import br.senai.sp.agendaja.Model.Servico;
import br.senai.sp.agendaja.Services.CadastarEmServico;
import br.senai.sp.agendaja.Tasks.TaskCadastrarAgendamento;
import br.senai.sp.agendaja.Tasks.TaskCadastrarServicoAgendamento;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmarReservaActivity extends AppCompatActivity  implements View.OnClickListener{
    private Estabelecimento estabelecimentoEscolhido;
    private Funcionario funcionarioEscolhido;
    private String horarioEscolhido;
    private Servico servicoEscolhido;
    private String horaEscolhida;
    private String dataEscolhida;
    private TextView txtNomeEstabelecimento;
    private TextView txtEnderecoEstabelecimento;
    private TextView txtPrecoServico;
    private TextView txtDuracaoServico;
    private TextView txtNomeServico;
    private TextView txtNomeFuncionario;
    private Button btnConfirmarReserva;
    private Agendamento agendamentoRetornado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_reserva);

        Intent intentGetObjects = getIntent();


        //tratando os objetos e instanciando eles
        if(intentGetObjects.getSerializableExtra("estabelecimentoEscolhido")!=null){
            estabelecimentoEscolhido = (Estabelecimento) intentGetObjects.getSerializableExtra("estabelecimentoEscolhido");
        }

        if(intentGetObjects.getSerializableExtra("servicoEscolhido")!=null){
            servicoEscolhido = (Servico) intentGetObjects.getSerializableExtra("servicoEscolhido");
        }

        if(intentGetObjects.getStringExtra("horaEscolhido")!=null){
            horaEscolhida = (String) intentGetObjects.getStringExtra("horaEscolhido");
        }

        if(intentGetObjects.getSerializableExtra("dataEscolhida")!=null){
            dataEscolhida = (String) intentGetObjects.getStringExtra("dataEscolhida");
        }

        if(intentGetObjects.getSerializableExtra("funcionarioEscolhido")!=null){
            funcionarioEscolhido = (Funcionario) intentGetObjects.getSerializableExtra("funcionarioEscolhido");
        }

        //instanciando as views da activity

        txtNomeEstabelecimento = findViewById(R.id.txet_nome_estabelecimento_do_confirmar_servico);
        txtEnderecoEstabelecimento = findViewById(R.id.text_endereco_do_confirmar_servico);
        txtDuracaoServico = findViewById(R.id.text_tempo_do_confirmar_servico);
        txtPrecoServico = findViewById(R.id.text_preco_do_confirmar_servico);
        txtNomeFuncionario = findViewById(R.id.text_funcionario_do_confirmar_servico);
        txtNomeServico = findViewById(R.id.text_servico_do_confirmar_servico);
        btnConfirmarReserva = findViewById(R.id.btn_confirmar_reserva);


//        String[] horario = horarioEscolhido.split(":");
//        int horas = Integer.parseInt(horario[0]);
//        int minutos = Integer.parseInt(horario[1]);
//
//        int soma = minutos + servicoEscolhido.getDuracao();
//
//        String horaFinal = horas + ":"  + soma;

        //colocando conteudo nas views

        txtNomeEstabelecimento.setText(estabelecimentoEscolhido.getNomeEstabelecimento());
        txtEnderecoEstabelecimento.setText(
                estabelecimentoEscolhido.getBairro() + " "  + estabelecimentoEscolhido.getCidade() + "-" + estabelecimentoEscolhido.getEstado() + ","
                + estabelecimentoEscolhido.getCep()
        );

        txtPrecoServico.setText("R$" + servicoEscolhido.getPrecoServico());
        //txtDuracaoServico.setText(horaEscolhida + " - " + CalculoHorario.calcularHorarioFinal(horaFinal));
        txtDuracaoServico.setText(horaEscolhida);
        txtNomeServico.setText(servicoEscolhido.getNomeServico());
        txtNomeFuncionario.setText(funcionarioEscolhido.getNomeFuncionario());


        //setando os listenner nos views
        btnConfirmarReserva.setOnClickListener(this);

    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_confirmar_reserva:
                horarioEscolhido = dataEscolhida + " " + horaEscolhida+":00";

                Log.d("data e horario",horarioEscolhido);
                Log.d("idClienteLogado",String.valueOf(MainActivity.CLIENTELOGADO.getIdCliente()));
                Log.d("estabelecimentoEscolhido", String.valueOf(estabelecimentoEscolhido.getIdEstabelecimento()));
                Log.d("funcioanrioEscolhido", String.valueOf(funcionarioEscolhido.getIdFuncionario()));
                Log.d("ServicoEscolhido", String.valueOf(servicoEscolhido.getIdServico()));

                TaskCadastrarAgendamento cadastrarAgendamento  = new TaskCadastrarAgendamento(MainActivity.CLIENTELOGADO.getIdCliente(),
                     estabelecimentoEscolhido.getIdEstabelecimento(),funcionarioEscolhido.getIdFuncionario(),horarioEscolhido
                );

                cadastrarAgendamento.execute();

                try {

                    if(cadastrarAgendamento.get()!=null){
                         agendamentoRetornado = (Agendamento) cadastrarAgendamento.get();

                        TaskCadastrarServicoAgendamento cadastrarServicoAgendamento = new TaskCadastrarServicoAgendamento(servicoEscolhido.getIdServico(),agendamentoRetornado.getIdAgendamento());
                        cadastrarServicoAgendamento.execute();


                        if(cadastrarServicoAgendamento.get()!=null){
                             Boolean retornoServicoAgendaemnto= (Boolean) cadastrarServicoAgendamento.get();
                            if(retornoServicoAgendaemnto){

                                Integer idAgendamento = agendamentoRetornado.getIdAgendamento();

                                CadastarEmServico cadastarEmServico = new CadastarEmServico();
                                Call<EmServico> postEmServico = cadastarEmServico.postEmServico(idAgendamento);

                                postEmServico.enqueue(new Callback<EmServico>() {
                                    @Override
                                    public void onResponse(Call<EmServico> call, Response<EmServico> response) {
                                        if(response.isSuccessful()){

                                            Log.d("sucesso em servico","sucesso");
                                            Intent intentMain = new Intent(ConfirmarReservaActivity.this,MainActivity.class);
                                            intentMain.putExtra("CLIENTELOGADO",MainActivity.CLIENTELOGADO);
                                            intentMain.putExtra("token",MainActivity.TOKEN);
                                            startActivity(intentMain);
                                        }else{
                                            Log.d("~fracasso","fracasso");
                                            Log.d("mensagem fracasso", String.valueOf(response.code()));
                                            Log.d("mensagem fracasso2", String.valueOf(response.body()));
                                            try {
                                                Log.d("mensagem fracasso3", String.valueOf(response.errorBody().string()));
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<EmServico> call, Throwable t) {

                                        Toast.makeText(ConfirmarReservaActivity.this,"Erro ao cadastrar",Toast.LENGTH_LONG).show();
                                        Log.d("problema do agendamento",t.getMessage());

                                    }
                                });
                            }


                        }else{
                            Log.d("erro no cadastro ServicoAgendamento","deu erro sinto muito");
                        }


                    }else{
                        Log.d("Erro ao agendar","Retorno nulo");
                    }


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


        }

    }
}
