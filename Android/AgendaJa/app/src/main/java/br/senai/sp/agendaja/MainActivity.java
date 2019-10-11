package br.senai.sp.agendaja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private TextView dadosPessoais;
    public static String  IP_SERVER  = "3.95.195.11:8080";
    public static String IP_FOTO = "http://3.95.195.11/";
    public static String TOKEN;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent getToken = getIntent();

        TOKEN = getToken.getStringExtra("token");
        Log.d("Esse é o token",TOKEN);


       // dadosPessoais = findViewById(R.id.text_dados_pessoais);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new ExplorarFragment()).commit();

        getSupportActionBar().hide();
    }

    private  BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.navigation_explorar:
                            selectedFragment = new ExplorarFragment();
                            break;
                        case R.id.navigation_reservas:
                            selectedFragment = new ReservasFragment();
                            break;
                        case R.id.navigation_perfil:
                            selectedFragment = new PerfilFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.text_dados_pessoais:
//                Intent intent = new Intent(MainActivity.this,EditarDadosPessoaisActivity.class);
//                startActivity(intent);
//        }
//    }
}
