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
import android.widget.TextView;

import br.senai.sp.agendaja.Model.Cliente;

public class MainActivity extends AppCompatActivity{

    private TextView dadosPessoais;
    public static String  IP_SERVER  = "52.55.177.56:8080";
    public static String IP_FOTO = "http://52.55.177.56/";
    public static String TOKEN;
    public static Cliente clienteLogado;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent getTokenECliente = getIntent();

        if(getTokenECliente.getStringExtra("token")!=null) {

            TOKEN = getTokenECliente.getStringExtra("token");
            Log.d("Esse é o token", TOKEN);

        }


        clienteLogado = (Cliente) getTokenECliente.getSerializableExtra("clienteLogado");

        if(clienteLogado!=null && clienteLogado.getIdCliente()!=null) {

            Log.d("Esse é o nome", clienteLogado.getNome());

        }

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

}
