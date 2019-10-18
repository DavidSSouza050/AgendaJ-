package br.senai.sp.agendaja;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.Services.CadastroFoto;
import br.senai.sp.agendaja.modal.Cliente;
import br.senai.sp.agendaja.modal.Endereco;
import br.senai.sp.agendaja.modal.Informacao;
import br.senai.sp.agendaja.tasks.TaskCadastrarCliente;
import br.senai.sp.agendaja.tasks.TaskCadastrarEndereco;
import br.senai.sp.agendaja.tasks.TaskGetToken;
import br.senai.sp.agendaja.tasks.TaskLoginClienteToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContatoActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText celular;
    private EditText email;
    private EditText confirmarEmail;
    private EditText senha;
    private EditText confirmarSenha;
    private Button btnFinalizar;
    private Button btnVoltar;
    private Endereco enderecoFinal;
    private Cliente clienteFinal;
    private Informacao infomacoesCliente;
    private String token;
    private ProgressBar progressCarregando;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        Intent intentDadosCliente = getIntent();

        enderecoFinal = (Endereco) intentDadosCliente.getSerializableExtra("Endereco");
        clienteFinal = (Cliente) intentDadosCliente.getSerializableExtra("clienteComDados");

        celular = findViewById(R.id.txt_celular_contato);
        email =  findViewById(R.id.txt_email_contato);
        confirmarEmail = findViewById(R.id.txt_confirmar_email_contato);
        senha = findViewById(R.id.txt_senha_contato);
        confirmarSenha = findViewById(R.id.txt_confirmar_senha_contato);
        btnFinalizar = findViewById(R.id.btn_finalizar_contato);
        btnVoltar = findViewById(R.id.btn_voltar_contato);
        progressCarregando = findViewById(R.id.pb_carregando_contato);

        btnFinalizar.setOnClickListener(this);
        btnVoltar.setOnClickListener(this);


        //Adicionando mascaras nos campos

        SimpleMaskFormatter maskCelular = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher textCelular = new MaskTextWatcher(celular,maskCelular);
        celular.addTextChangedListener(textCelular);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_finalizar_contato:
                //comparando senhas e emails para verificar se são iguais aos campos de confirmação

                //if(email.getText().toString() == confirmarEmail.getText().toString()
                     //&& senha.getText().toString() == confirmarSenha.getText().toString() ){

//                    infomacoesCliente = new Informacao();
                    clienteFinal.setCelular(celular.getText().toString());
                    clienteFinal.setEmail(email.getText().toString());
                    clienteFinal.setSenha(senha.getText().toString());


                    //Cadastrando endereco
                    TaskCadastrarEndereco taskCadastrarEndereco = new TaskCadastrarEndereco(enderecoFinal);
                    taskCadastrarEndereco.execute();

                    try {
                        int respostaCadastroEndereco = (Integer) taskCadastrarEndereco.get();

                        if(respostaCadastroEndereco != 0){
                            //cadastrando cliente
                            TaskCadastrarCliente taskCadastrarCliente = new TaskCadastrarCliente(clienteFinal,respostaCadastroEndereco);
                            taskCadastrarCliente.execute();

                            final Cliente respostaCadastroCliente = (Cliente) taskCadastrarCliente.get();
                            if(respostaCadastroCliente.getIdCliente()!=null){

                                TaskGetToken getToken = new TaskGetToken(respostaCadastroCliente.getEmail(),respostaCadastroCliente.getSenha());
                                getToken.execute();

                                if(getToken.get()!=null){
                                    token = (String) getToken.get();
                                }else{
                                    token = null;
                                }

                                //EM FASE DE MELHORAS.
                                //cadastrando a foto do cliente
                                CadastroFoto cadastroFoto = new CadastroFoto();
                                final Call<Cliente> verificacao = cadastroFoto.CadastrarFoto(clienteFinal.getFoto(),respostaCadastroCliente.getIdCliente());

                                exibirProgress(true);

                                Handler handler = new Handler();

                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        verificacao.enqueue(new Callback<Cliente>() {
                                            @Override
                                            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                                                if(response.isSuccessful()){

                                                    TaskLoginClienteToken loginClienteToken = new TaskLoginClienteToken(respostaCadastroCliente.getEmail(),respostaCadastroCliente.getSenha(),token);
                                                    loginClienteToken.execute();

                                                    try {

                                                        Cliente clienteLogado = (Cliente) loginClienteToken.get();
                                                        Intent intent = new Intent(ContatoActivity.this,MainActivity.class);
                                                        intent.putExtra("clienteLogado",clienteLogado);
                                                        intent.putExtra("token",token);
                                                        startActivity(intent);
                                                        finish();
                                                        exibirProgress(false);

                                                    } catch (ExecutionException e) {
                                                        e.printStackTrace();
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }

                                                }

                                            }

                                            @Override
                                            public void onFailure(Call<Cliente> call, Throwable t) {
                                                Toast.makeText(ContatoActivity.this,"Erro ao cadastrar foto",Toast.LENGTH_LONG).show();
                                                Log.d("ERRO CADASTRO FOTO",t.getMessage());
                                            }
                                        });

                                    }
                                },3000);


                            }

                        }else{
                            Toast.makeText(ContatoActivity.this,"Falha no cadastro",Toast.LENGTH_LONG).show();
                        }


                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                break;
            case R.id.btn_voltar_contato:
                Intent intentVoltar = new Intent(ContatoActivity.this, EnderecoActivity.class);
                if(enderecoFinal!=null ){
                    intentVoltar.putExtra("EnderecoVoltado",enderecoFinal);
                    intentVoltar.putExtra("ClienteVoltado",clienteFinal);
                }
                startActivity(intentVoltar);
        }
    }

    public void exibirProgress(Boolean resposta){
        progressCarregando.setVisibility(resposta ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Toast.makeText(ContatoActivity.this,endereco.getCep(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed(){
        Intent intentVoltar = new Intent(ContatoActivity.this,EnderecoActivity.class);
        startActivity(intentVoltar);
        finish();
    }
}
