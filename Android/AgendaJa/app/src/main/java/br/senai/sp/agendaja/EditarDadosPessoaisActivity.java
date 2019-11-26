package br.senai.sp.agendaja;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.Model.Cliente;
import br.senai.sp.agendaja.Tasks.TaskEditarDadosPessoais;

public class EditarDadosPessoaisActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

  private Cliente clienteLogado;
  private Cliente clienteSegundaEdicao;
  private Cliente clienteParaEditar;
  private EditText editarNome;
  private EditText editarSobrenome;
  private EditText editarDataNascimento;
  private EditText editarCpf;
  private Spinner editarSexo;
  private ArrayAdapter adapterSpinner;
  private String sexo;
  private Button btnSalvarDados;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_editar_dados_pessoais);

    ActionBar bar = getSupportActionBar();
    bar.setDisplayHomeAsUpEnabled(true);




    //Pegando Cliente enviado da fragment
    Intent intentCliente = getIntent();
    clienteLogado = (Cliente) intentCliente.getSerializableExtra("clientePrimeiraEdicao");

    //Pegando Cliente para segunda edicao
    clienteSegundaEdicao = (Cliente) intentCliente.getSerializableExtra("clienteSegundaEdicao");

//    sexo = CLIENTELOGADO.getSexo();

    //instanciando os campos
    editarNome = findViewById(R.id.text_contato_perfil);
    editarSobrenome = findViewById(R.id.text_sobre_a_empresa_perfil);
    editarDataNascimento = findViewById(R.id.text_data_nascimento);
    editarCpf = findViewById(R.id.text_cpf_dados_pessoais);
    btnSalvarDados = findViewById(R.id.btn_salvar_dados_pessoais);

    //Colacando a opção do sexo no spinner
    editarSexo = findViewById(R.id.spinner_sexo_dados_pessoais);
    adapterSpinner = ArrayAdapter.createFromResource(EditarDadosPessoaisActivity.this,R.array.sexos,android.R.layout.simple_spinner_item);
    adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    editarSexo.setAdapter(adapterSpinner);

    //setando o listenner no spinner
    editarSexo.setOnItemSelectedListener(this);

    //setando onClickListenner no botao de salvar
    btnSalvarDados.setOnClickListener(this);

    //Formatando os campos da data de nascimento e cpf
//    SimpleMaskFormatter simpleMaskData = new SimpleMaskFormatter("NN/NN/NNNN");
//    MaskTextWatcher watcherDataNasciemnto = new MaskTextWatcher(editarDataNascimento,simpleMaskData);
//    editarDataNascimento.addTextChangedListener(watcherDataNasciemnto);

    SimpleMaskFormatter simpleMaskCpf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
    MaskTextWatcher watcherCpf = new MaskTextWatcher(editarCpf,simpleMaskCpf);
    editarCpf.addTextChangedListener(watcherCpf);


    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

  }

  @Override
  protected void onResume() {
    super.onResume();

    if(clienteLogado!=null){

      if(clienteLogado.getNome() != null){

        editarNome.setText(clienteLogado.getNome());
        editarSobrenome.setText(clienteLogado.getSobrenome());
        editarDataNascimento.setText(clienteLogado.getDataNascimento());
        editarCpf.setText(clienteLogado.getCpf());
        if(clienteLogado.getSexo().equals("F")){
          editarSexo.setSelection(1);

        }else if(clienteLogado.getSexo().equals("M")){
          editarSexo.setSelection(2);

        }else if(clienteLogado.getSexo().equals("0")){
          editarSexo.setSelection(3);
        }
      }

    }

    else if(clienteSegundaEdicao!=null){

      if(clienteSegundaEdicao.getIdCliente()!=null){

        editarNome.setText(clienteSegundaEdicao.getNome());
        editarSobrenome.setText(clienteSegundaEdicao.getSobrenome());
        editarDataNascimento.setText(clienteSegundaEdicao.getDataNascimento());
        editarCpf.setText(clienteSegundaEdicao.getCpf());

        if(clienteSegundaEdicao.getSexo().equals("F")){
          editarSexo.setSelection(1);

        }else if(clienteSegundaEdicao.getSexo().equals("M")){
          editarSexo.setSelection(2);

        }else if(clienteSegundaEdicao.getSexo().equals("0")){
          editarSexo.setSelection(3);

        }
      }
    }

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()){
      case R.id.relativeLayout:
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
    return super.onOptionsItemSelected(item);

  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    String sexoSelecionado = parent.getItemAtPosition(position).toString();

        if(sexoSelecionado.equals("Feminino")){
          sexo = "F";
        }else if(sexoSelecionado.equals("Masculino")){
          sexo = "M";
        }else if(sexoSelecionado.equals("Outros")){
          sexo = "O";
        }

  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {
    Toast.makeText(this,"Sem sexo",Toast.LENGTH_LONG).show();
  }

  @Override
  public void onClick(View v) {

    switch (v.getId()){
      case R.id.btn_salvar_dados_pessoais:

        if(clienteLogado!=null){

            if(clienteLogado.getIdCliente()!=null){

              clienteParaEditar = new Cliente();
              clienteParaEditar.setIdCliente(clienteLogado.getIdCliente());
              clienteParaEditar.setNome(editarNome.getText().toString());
              clienteParaEditar.setSobrenome(editarSobrenome.getText().toString());
              clienteParaEditar.setDataNascimento(editarDataNascimento.getText().toString());
              clienteParaEditar.setCpf(editarCpf.getText().toString());
              clienteParaEditar.setSexo(sexo);
              clienteParaEditar.setSenha(clienteLogado.getSenha());
              clienteParaEditar.setEmail(clienteLogado.getEmail());
              clienteParaEditar.setIdEndereco(clienteLogado.getIdEndereco());
              clienteParaEditar.setCelular(clienteLogado.getCelular());
              clienteParaEditar.setFoto(clienteLogado.getFoto());
            }
        }

        else if(clienteSegundaEdicao!=null){

            if(clienteSegundaEdicao.getIdCliente()!=null){
              clienteParaEditar = new Cliente();
              clienteParaEditar.setIdCliente(clienteSegundaEdicao.getIdCliente());
              clienteParaEditar.setNome(editarNome.getText().toString());
              clienteParaEditar.setSobrenome(editarSobrenome.getText().toString());
              clienteParaEditar.setDataNascimento(editarDataNascimento.getText().toString());
              clienteParaEditar.setCpf(editarCpf.getText().toString());
              clienteParaEditar.setSexo(sexo);
              clienteParaEditar.setSenha(clienteSegundaEdicao.getSenha());
              clienteParaEditar.setEmail(clienteSegundaEdicao.getEmail());
              clienteParaEditar.setIdEndereco(clienteSegundaEdicao.getIdEndereco());
              clienteParaEditar.setCelular(clienteSegundaEdicao.getCelular());

            }
        }



//        Toast.makeText(EditarDadosPessoaisActivity.this,"o sexo é" + clienteParaEditar.getSexo(),Toast.LENGTH_LONG).show();

        TaskEditarDadosPessoais taskEditarDadosPessoais = new TaskEditarDadosPessoais(clienteParaEditar,MainActivity.TOKEN);
        taskEditarDadosPessoais.execute();

        try {
          Cliente clienteEditadoComSucesso = (Cliente) taskEditarDadosPessoais.get();

          if(clienteEditadoComSucesso.getIdCliente()!=null){
            Intent intentMain = new Intent(EditarDadosPessoaisActivity.this,MainActivity.class);
            intentMain.putExtra("clienteEditado",clienteEditadoComSucesso);
            intentMain.putExtra("CLIENTELOGADO",clienteEditadoComSucesso);
            intentMain.putExtra("token",MainActivity.TOKEN);
            startActivity(intentMain);
            finish();
          }else{
            Toast.makeText(EditarDadosPessoaisActivity.this,"Erro ao editar",Toast.LENGTH_LONG).show();
          }


        } catch (ExecutionException e) {
          e.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
    }

  }
}
