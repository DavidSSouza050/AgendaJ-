package br.senai.sp.agendaja;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.Model.Avaliacao;
import br.senai.sp.agendaja.Tasks.TaskCadastrarAvaliacao;

public class CadastrarAvaliacaoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNota;
    private EditText txtComentario;
    private Button btnEnviar;
    private int idEstabelecimento;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_avaliacao);

        Intent intent = getIntent();
        idEstabelecimento = Integer.parseInt(intent.getStringExtra("idEstabelecimento"));

        txtComentario = findViewById(R.id.txt_comentario_avaliacao);
        txtNota = findViewById(R.id.txt_nota_avaliacao);
        btnEnviar = findViewById(R.id.btn_enviar_avaliacao);


        btnEnviar.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_enviar_avaliacao:
                final TaskCadastrarAvaliacao cadastrarAvaliacao = new TaskCadastrarAvaliacao(idEstabelecimento,MainActivity.CLIENTELOGADO.getIdCliente(),Integer.parseInt(txtNota.getText().toString()),txtComentario.getText().toString());

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("CADASTRAR AVALIACAO");
                builder.setMessage("CONFIRMA SUA AVALIACAO?");

                builder.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cadastrarAvaliacao.execute();

                        try {

                            if(cadastrarAvaliacao.get()!=null){

                                Toast.makeText(CadastrarAvaliacaoActivity.this,"AVALICAO CADASTRADA",Toast.LENGTH_LONG).show();

                                Intent intentMain = new Intent(CadastrarAvaliacaoActivity.this,MainActivity.class);
                                intentMain.putExtra("CLIENTELOGADO",MainActivity.CLIENTELOGADO);
                                intentMain.putExtra("token",MainActivity.TOKEN);
                                startActivity(intentMain);

                            }


                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                });


                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(CadastrarAvaliacaoActivity.this,"AVALIAÇÃO CANCELADA",Toast.LENGTH_LONG).show();
                    }
                });

                alertDialog = builder.create();
                alertDialog.show();
        }

    }
}
