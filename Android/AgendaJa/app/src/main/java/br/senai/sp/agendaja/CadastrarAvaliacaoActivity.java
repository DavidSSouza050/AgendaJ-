package br.senai.sp.agendaja;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastrarAvaliacaoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNota;
    private EditText txtComentario;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_avaliacao);

        txtComentario = findViewById(R.id.txt_comentario_avaliacao);
        txtNota = findViewById(R.id.txt_nota_avaliacao);
        btnEnviar = findViewById(R.id.btn_enviar_avaliacao);


        btnEnviar.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

    }
}
