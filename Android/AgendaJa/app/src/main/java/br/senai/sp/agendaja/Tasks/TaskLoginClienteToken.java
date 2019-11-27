package br.senai.sp.agendaja.Tasks;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Cliente;

public class TaskLoginClienteToken extends AsyncTask {

  private String email;
  private String senha;
  private String dados;
  private String token;
  private Cliente clienteLogin;

  public TaskLoginClienteToken(String email, String senha, String token) {
    this.email = email;
    this.senha = senha;
    this.token = token;
  }

  @Override
  protected Object doInBackground(Object[] objects) {

    JSONStringer jsLogin = new JSONStringer();

    try {
      jsLogin.object();
      jsLogin.key("email").value(email);
      jsLogin.key("senha").value(senha);
      jsLogin.endObject();

      URL url = new URL("http://"+ MainActivity.IP_SERVER +"/clientes/login");
      HttpURLConnection connection =(HttpURLConnection) url.openConnection();

      connection.setRequestProperty("Content-type","application/json");
      connection.setRequestProperty("Accept","application/json");
      connection.setRequestProperty("token",token);
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
        clienteLogin.setSexo(object.getString("sexo"));
        clienteLogin.setCelular(object.getString("celular"));
        clienteLogin.setSenha(object.getString("senha"));
        clienteLogin.setFoto(object.getString("fotoCliente"));
      }else{
        clienteLogin = null;
      }

    } catch (JSONException e) {
      e.printStackTrace();
    } catch (ProtocolException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }


    if(clienteLogin.getIdCliente()!=null){
      return clienteLogin;
    }else{
      return null;
    }
  }
}
