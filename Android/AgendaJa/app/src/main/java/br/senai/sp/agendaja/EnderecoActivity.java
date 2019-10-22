package br.senai.sp.agendaja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.model.Cliente;
import br.senai.sp.agendaja.model.Endereco;
import br.senai.sp.agendaja.tasks.TaskCarregarLocalizacao;

public class EnderecoActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher{

    private EditText cep;
    private TextView cidade;
    private TextView logradouro;
    private TextView bairro;
    private TextView uf;
    private Button proximo;
    private Button voltar;
    private ProgressBar pbCarregando;
    private Endereco endereco;
    private Cliente clienteComDados;
    private Endereco enderecoVoltado;
    private Cliente clienteVoltado;
    private Endereco cepRetornado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco);

        try{
            //pegando o cliente criado pela primeira vez na DadosPessoasActivity  para EnderecoActivity
            Intent dadosCliente = getIntent();
            clienteComDados = (Cliente) dadosCliente.getSerializableExtra("novoCliente");
            cepRetornado = (Endereco) dadosCliente.getSerializableExtra("retornandoCep");


            // Pegando endereco e cliente voltados da activity ContatoActivity
            Intent intentVoltaEndereco = getIntent();
            enderecoVoltado = (Endereco) intentVoltaEndereco.getSerializableExtra("EnderecoVoltado");
            clienteVoltado = (Cliente) intentVoltaEndereco .getSerializableExtra("ClienteVoltado");
        }catch(Exception e){
            e.getMessage();
        }



        cep = findViewById(R.id.txt_cep_endereco);
        cidade = findViewById(R.id.txt_cidade_endereco);
        logradouro = findViewById(R.id.txt_logradouro_endereco);
        bairro = findViewById(R.id.txt_bairro_endereco);
        uf = findViewById(R.id.txt_uf_endereco);
        proximo = findViewById(R.id.btn_proximo__endereco);
        voltar = findViewById(R.id.btn_voltar_endereco);
        pbCarregando = findViewById(R.id.pb_carregando);



        cep.addTextChangedListener(this);
        proximo.setOnClickListener(this);
        voltar.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(enderecoVoltado!=null){
            cep.setText(enderecoVoltado.getCep());
        }

        if(cepRetornado!=null){
            cep.setText(cepRetornado.getCep());
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        int tam = cep.length();

        if(tam==8){
            TaskCarregarLocalizacao localizacao = new TaskCarregarLocalizacao(cep.getText().toString(),EnderecoActivity.this,pbCarregando);
            localizacao.execute();

            try {
                if(localizacao.get()!=null){
                    endereco = (Endereco) localizacao.get();
                    endereco.setCep(cep.getText().toString());
                    cidade.setText(endereco.getCidade());
                    logradouro.setText(endereco.getLogradouro());
                    bairro.setText(endereco.getBairro());
                    uf.setText(endereco.getEstado());
                }else{
                    Toast.makeText(EnderecoActivity.this,"verifique o cep ou a conexao com a internet",Toast.LENGTH_LONG).show();
                }

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public Boolean validar(){
        Boolean validacao = true;

        if(cep.length()==0){
            validacao = false;
        }

        return validacao;

    }

    @Override
    public void onClick(View v) {

        switch ( v.getId()){
            case R.id.btn_proximo__endereco:

                Intent intentProximoEndereco = new Intent(EnderecoActivity.this,ContatoActivity.class);
                intentProximoEndereco.putExtra("Endereco",endereco);
                intentProximoEndereco.putExtra("clienteComDados",clienteComDados);
                if(cep.length()>0 && cidade.length()>0){
                    startActivity(intentProximoEndereco);
                    finish();
                }else{
                    Toast.makeText(EnderecoActivity.this,"Digite o cep",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_voltar_endereco:
                Intent intentVoltarEndereco = new Intent(EnderecoActivity.this,DadosPessoaisActivity.class);
                if(clienteComDados !=null){
                    intentVoltarEndereco.putExtra("voltantoDados",clienteComDados);
                }
                if(cep.length()==8){
                    Endereco enderecoParaRetornar = new Endereco();
                    enderecoParaRetornar.setCep(cep.getText().toString());
                    intentVoltarEndereco.putExtra("voltandoCep",enderecoParaRetornar);
                }
                if(clienteVoltado!=null){
                    intentVoltarEndereco.putExtra("clienteVoltado",clienteVoltado);
                }
                startActivity(intentVoltarEndereco);
                finish();
                break;
        }

    }

    @Override
    public void onBackPressed(){
        Intent intentVoltar = new Intent(EnderecoActivity.this,DadosPessoaisActivity.class);
        startActivity(intentVoltar);
        finish();
    }
}
