package br.senai.sp.agendaja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.Model.Endereco;
import br.senai.sp.agendaja.Tasks.TaskGetEnderecoCliente;

public class EditarEnderecoActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText txtCep;
    private EditText txtCidade;
    private EditText txtLogradouro;
    private EditText txtBairro;
    private EditText txtEstado;
    private Button btnAtualizar;
    private Endereco enderecoCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_endereco);

        //instanciando as views da activity
        txtCep = findViewById(R.id.text_cep_editar_endereco);
        TaskGetEnderecoCliente getEnderecoCliente = new TaskGetEnderecoCliente(MainActivity.CLIENTELOGADO.getIdCliente());
        getEnderecoCliente.execute();

        try {
            if(getEnderecoCliente.get()!=null){
                enderecoCliente = (Endereco) getEnderecoCliente.get();
            }



            //instanciando as views da activity
            txtCep = findViewById(R.id.text_cep_editar_endereco);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        txtCidade = findViewById(R.id.text_cidade_editar_endereco);
        txtLogradouro = findViewById(R.id.text_logradouro_editar_endereco);
        txtBairro = findViewById(R.id.text_bairro_editar_endereco);
        txtEstado = findViewById(R.id.text_uf_editar_endereco);
        btnAtualizar = findViewById(R.id.btn_salvar_editar_endereco);
        //setando valores
//      txtCidade.setText(enderecoCliente.getCidade());
//      txtBairro.setText(enderecoCliente.getBairro());
//      txtCep.setText(enderecoCliente.getCep());
//      txtEstado.setText(enderecoCliente.getEstado());
//      txtLogradouro.setText(enderecoCliente.getLogradouro());

        //setando os listenners
        btnAtualizar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

    }
}
