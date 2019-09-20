package br.senai.sp.agendaja.tasks;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.agendaja.modal.Cliente;

public class LoginCliente extends AsyncTask {

  private String email;
  private String senha;
  private String dados;
  private Cliente clienteLogin;

  public LoginCliente(String email, String senha) {
    this.email = email;
    this.senha = senha;
  }

  @Override
  protected Object doInBackground(Object[] objects) {

    try {

      JSONStringer jsLogin = new JSONStringer();

      jsLogin.object();
      jsLogin.key("email").value(email);
      jsLogin.key("senha").value(senha);
      jsLogin.endObject();

      URL url = new URL("http://10.107.144.13:8080/cliente/login");
      HttpURLConnection connection =(HttpURLConnection) url.openConnection();

      connection.setRequestProperty("Content-type","application/json");
      connection.setRequestProperty("Accept","application/json");
      connection.setRequestMethod("POST");

      connection.setDoInput(true);
      PrintStream output = new PrintStream(connection.getOutputStream());
      output.print(jsLogin);
      connection.connect();

      Scanner scanner = new Scanner(connection.getInputStream());
      dados = scanner.nextLine();

      JSONObject object = new JSONObject(dados);

      if(dados!=null){
      clienteLogin = new Cliente();
      clienteLogin.setIdCliente(object.getInt("idCliente"));
      clienteLogin.setNome(object.getString("nome"));
      clienteLogin.setSobrenome(object.getString("sobrenome"));
      clienteLogin.setCpf(object.getString("cpf"));
      clienteLogin.setDataNascimento(object.getString("dataNascimento"));
      clienteLogin.setEmail(object.getString("email"));
      clienteLogin.setCelular(object.getString("celular"));
      clienteLogin.setCep(object.getString("cep"));
      }else{
        clienteLogin = null;
      }




    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }


    if(clienteLogin!=null){
      return clienteLogin;
    }else{
      return null;
    }
  }
}
