package br.senai.sp.agendaja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TipoLoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button clienteLogin;
    private Button estabelecimentoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_login);

        clienteLogin = findViewById(R.id.btn_cliente_login);
        estabelecimentoLogin = findViewById(R.id.btn_estabelecimento_login);

        clienteLogin.setOnClickListener(this);
        estabelecimentoLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch ( v.getId()){
            case R.id.btn_estabelecimento_login:
                Intent intentLoginEstabelecimento = new Intent(TipoLoginActivity.this,LoginEstabelecimentoActivity.class);
                startActivity(intentLoginEstabelecimento);
                finish();
                break;
            case R.id.btn_cliente_login:
                Intent intentLoginCliente = new Intent(TipoLoginActivity.this,LoginActivity.class);
                startActivity(intentLoginCliente);
                finish();
                break;
        }

    }

}
