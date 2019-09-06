package br.senai.sp.agendaja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.senai.sp.agendaja.modal.Cliente;
import br.senai.sp.agendaja.modal.Endereco;

public class ContatoActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText celular;
    private EditText email;
    private EditText confirmarEmail;
    private EditText senha;
    private EditText confirmarSenha;
    private Button btnFinalizar;
    private Button btnVoltar;
    private Endereco endereco;
    private Cliente clienteComDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        Intent intentDadosCliente = getIntent();

        endereco = (Endereco) intentDadosCliente.getSerializableExtra("Endereco");
        clienteComDados = (Cliente) intentDadosCliente.getSerializableExtra("clienteComDados");

        celular = findViewById(R.id.txt_celular_contato);
        email =  findViewById(R.id.txt_email_contato);
        confirmarEmail = findViewById(R.id.txt_confirmar_email_contato);
        senha = findViewById(R.id.txt_senha_contato);
        confirmarSenha = findViewById(R.id.txt_confirmar_senha_contato);
        btnFinalizar = findViewById(R.id.btn_finalizar_contato);
        btnVoltar = findViewById(R.id.btn_voltar_contato);

        btnFinalizar.setOnClickListener(this);
        btnVoltar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_finalizar_contato:
                Toast.makeText(ContatoActivity.this,"Nada por enquanto",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_voltar_contato:
                Intent intentVoltar = new Intent(ContatoActivity.this, EnderecoActivity.class);
                if(endereco!=null ){
                    intentVoltar.putExtra("EnderecoVoltado",endereco);
                    intentVoltar.putExtra("ClienteVoltado",clienteComDados);
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
