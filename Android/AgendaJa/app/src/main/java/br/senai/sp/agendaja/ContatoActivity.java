package br.senai.sp.agendaja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.Services.CadastroFoto;
import br.senai.sp.agendaja.modal.Cliente;
import br.senai.sp.agendaja.modal.Endereco;
import br.senai.sp.agendaja.modal.Informacao;
import br.senai.sp.agendaja.tasks.CadastrarCliente;
import br.senai.sp.agendaja.tasks.CadastrarEndereco;
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

                    CadastrarEndereco cadastrarEndereco = new CadastrarEndereco(enderecoFinal);
                    cadastrarEndereco.execute();

                    try {
                        int respostaCadastroEndereco = (Integer) cadastrarEndereco.get();

                        if(respostaCadastroEndereco != 0){

                            CadastrarCliente cadastrarCliente = new CadastrarCliente(clienteFinal,respostaCadastroEndereco);
                            cadastrarCliente.execute();

                            int respostaCadastroCliente = (Integer) cadastrarCliente.get();
                            if(respostaCadastroCliente!=0){

                              //Toast.makeText(ContatoActivity.this,clienteFinal.getFoto(),Toast.LENGTH_LONG).show();
                                CadastroFoto cadastroFoto = new CadastroFoto();
                                Call<Cliente> verificacao = cadastroFoto.CadastrarFoto(clienteFinal.getFoto(),respostaCadastroCliente);

                                verificacao.enqueue(new Callback<Cliente>() {
                                    @Override
                                    public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                                        if(response.isSuccessful()){
                                           Intent intent = new Intent(ContatoActivity.this,MainActivity.class);
                                           startActivity(intent);
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<Cliente> call, Throwable t) {
                                        Toast.makeText(ContatoActivity.this,"Erro ao cadastrar foto",Toast.LENGTH_LONG).show();
                                        Log.d("ERRO CADASTRO FOTO",t.getMessage());
                                    }
                                });

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
