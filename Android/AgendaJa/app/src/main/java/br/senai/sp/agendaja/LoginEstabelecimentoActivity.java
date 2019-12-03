package br.senai.sp.agendaja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.Model.Funcionario;
import br.senai.sp.agendaja.Tasks.TaskGetTokenFuncionario;
import br.senai.sp.agendaja.Tasks.TaskLoginFuncionarioToken;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class LoginEstabelecimentoActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText txtEmail;
    private EditText txtSenha;
    private String token;
    private Funcionario funcionario;
    private Button btnLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_estabelecimento);

        txtEmail = findViewById(R.id.txt_email_login_estabelecimento);
        txtSenha = findViewById(R.id.txt_senha_login_estabelecimento);
        btnLogar = findViewById(R.id.btn_entrar_login_estabelecimento);

        btnLogar.setOnClickListener(this);


    }

    @Override
    public void onBackPressed()
    {
        Intent intentVoltar = new Intent(LoginEstabelecimentoActivity.this,TipoLoginActivity.class);
        startActivity(intentVoltar);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_entrar_login_estabelecimento:
                TaskGetTokenFuncionario taskGetTokenFuncionario = new TaskGetTokenFuncionario(txtEmail.getText().toString(),txtSenha.getText().toString());
                taskGetTokenFuncionario.execute();

                try {
                    if(taskGetTokenFuncionario.get()!=null){
                        token = (String) taskGetTokenFuncionario.get();
                        TaskLoginFuncionarioToken taskLoginFuncionarioToken = new TaskLoginFuncionarioToken(txtEmail.getText().toString(),txtSenha.getText().toString(),token);
                        taskLoginFuncionarioToken.execute();

                        if(taskLoginFuncionarioToken.get()!=null){
                            funcionario = (Funcionario) taskLoginFuncionarioToken.get();

                            Intent intent = new Intent(this,MainActivityFuncionario.class);
                            intent.putExtra("token",token);
                            intent.putExtra("FUNCLOGADO",funcionario);
                            startActivity(intent);

                        }


                    }else{
                        Toast.makeText(this,"Email ou senha incorretos",Toast.LENGTH_LONG).show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}
