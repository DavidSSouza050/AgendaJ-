package br.senai.sp.agendaja;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              IntentLogin();
            }
        },3000);
    }

    public void IntentLogin(){
        Intent intent = new Intent(SplashActivity.this,TipoLoginActivity.class);
        startActivity(intent);
        finish();
    }
}
