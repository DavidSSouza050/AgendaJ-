package br.senai.sp.agendaja.AcessoGaleria;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class VerificandoAcessoGaleria {

  private String[] appPermissoes = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
  public static final int PERMISSION_REQUEST = 30;
  private Activity activity;

  public VerificandoAcessoGaleria(Activity activity) {
    this.activity = activity;
  }

  //método para dar permissao para a aplicação

  public boolean verificarPermissao(){

    Boolean status;

    List<String> permissoesRequeridas = new ArrayList<>();

    for(String permissao : appPermissoes){
      if(ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
        permissoesRequeridas.add(permissao);
      }
    }

    if(!permissoesRequeridas.isEmpty()){
      ActivityCompat.requestPermissions(activity,permissoesRequeridas.toArray(new String[permissoesRequeridas.size()]),PERMISSION_REQUEST);
      status = true;
    }else{
      status = false;
    }

    return status;
  }


}


