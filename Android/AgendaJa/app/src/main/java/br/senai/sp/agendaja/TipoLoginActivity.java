package br.senai.sp.agendaja;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TipoLoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button clienteLogin;
    private Button estabelecimentoLogin;
    private String[] appPermissoes = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static final int PERMISSION_REQUEST = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_login);

        clienteLogin = findViewById(R.id.btn_cliente_login);
        estabelecimentoLogin = findViewById(R.id.btn_estabelecimento_login);

        clienteLogin.setOnClickListener(this);
        estabelecimentoLogin.setOnClickListener(this);

        verificarPermissao();
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

    //método para dar permissao para a aplicação

    public boolean verificarPermissao(){

        Boolean status;

        List<String> permissoesRequeridas = new ArrayList<>();

        for(String permissao : appPermissoes){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                permissoesRequeridas.add(permissao);
            }
        }

        if(!permissoesRequeridas.isEmpty()){
            ActivityCompat.requestPermissions(this,permissoesRequeridas.toArray(new String[permissoesRequeridas.size()]),PERMISSION_REQUEST);
            status = true;
        }else{
            status = false;
        }

        return status;
    }

}
