package br.senai.sp.agendaja;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.modal.Cliente;
import br.senai.sp.agendaja.tasks.TaskLoginCliente;
import br.senai.sp.agendaja.tasks.TaskLoginClienteToken;


//LoaderCallbacks<Cursor>,
public class LoginActivity extends AppCompatActivity implements  View.OnClickListener {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private Button btnCriarConta;
    private Button btnLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.txt_email_login);

        btnCriarConta = findViewById(R.id.btn_criar_conta_login);
        btnLogar = findViewById(R.id.btn_entrar_login);

        btnLogar.setOnClickListener(this);

        btnCriarConta.setOnClickListener(this);


        mPasswordView = (EditText) findViewById(R.id.txt_senha_login);

        mProgressView = findViewById(R.id.login_progress);
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_criar_conta_login:
                Intent intent = new Intent(LoginActivity.this,DadosPessoaisActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_entrar_login:
                TaskLoginCliente taskLoginCliente = new TaskLoginCliente(mEmailView.getText().toString(),mPasswordView.getText().toString());
                taskLoginCliente.execute();

                try {
                    String token = (String) taskLoginCliente.get();
                    if(token!=null) {

                        TaskLoginClienteToken loginClienteToken = new TaskLoginClienteToken(mEmailView.getText().toString(),mPasswordView.getText().toString(),token);
                        loginClienteToken.execute();

                        Cliente clienteLogado = (Cliente) loginClienteToken.get();

                        if(clienteLogado.getIdCliente()!=null){
                            Intent intentMain = new Intent(LoginActivity.this,MainActivity.class);
                            intentMain.putExtra("clienteLogado",clienteLogado);
                            startActivity(intentMain);
                        }else{
                            Toast.makeText(LoginActivity.this,"Email ou senhas incorretos",Toast.LENGTH_LONG).show();
                        }

                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }

    }


    @Override
    public void onBackPressed()
    {
        Intent intentVoltar = new Intent(LoginActivity.this,TipoLoginActivity.class);
        startActivity(intentVoltar);
        finish();
    }
}

