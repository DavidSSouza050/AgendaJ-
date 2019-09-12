package br.senai.sp.agendaja.tasks;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.agendaja.modal.Cliente;

public class CadastrarFotoCliente extends AsyncTask {


  private Bitmap fotoUsuario;
  private Cliente clienteComFoto;
  private String resposta;
  private Integer idCliente;

  public CadastrarFotoCliente(Bitmap fotoUsuario, int idCliente) {
      this.fotoUsuario = fotoUsuario;
      this.idCliente = idCliente;
  }

  @Override
  protected Object doInBackground(Object[] objects) {

    try {


      URL url = new URL("http://10.107.144.13:8080/foto/cliente");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();


      connection.setDoOutput(true);
      connection.setUseCaches(false);
      connection.setRequestProperty("Content-type","multipart/form-data");
      connection.setRequestProperty("Accept","application/json");
      connection.setRequestMethod("POST");




    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }


    if(clienteComFoto.getIdCliente() != null){
      return clienteComFoto;
    }else{
      return null;
    }
  }
}
