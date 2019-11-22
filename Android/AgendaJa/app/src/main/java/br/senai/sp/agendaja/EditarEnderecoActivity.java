package br.senai.sp.agendaja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditarEnderecoActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText txtCep;
    private EditText txtCidade;
    private EditText txtLogradouro;
    private EditText txtBairro;
    private EditText txtEstado;
    private Button btnAtualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_endereco);

        //instanciando as views da activity
        txtCep = findViewById(R.id.text_cep_editar_endereco);
        txtCidade = findViewById(R.id.text_cidade_editar_endereco);
        txtLogradouro = findViewById(R.id.text_logradouro_editar_endereco);
        txtBairro = findViewById(R.id.text_bairro_editar_endereco);
        txtEstado = findViewById(R.id.text_uf_editar_endereco);
        btnAtualizar = findViewById(R.id.btn_salvar_editar_endereco);


        //setando os listenners
        btnAtualizar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

    }
}
