package br.senai.sp.agendaja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginEstabelecimentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_estabelecimento);
    }

    @Override
    public void onBackPressed()
    {
        Intent intentVoltar = new Intent(LoginEstabelecimentoActivity.this,TipoLoginActivity.class);
        startActivity(intentVoltar);
        finish();
    }
}
