package br.senai.sp.agendaja.Tasks;

import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Cliente;

public class TaskEditarDadosPessoais extends AsyncTask {
  private Cliente clienteEditarDados;
  private String resposta;
  private Cliente clienteEditado;
  private String token;
  private String minutosCertos;

  public TaskEditarDadosPessoais(Cliente clienteEditarDados, String token) {
    this.clienteEditarDados = clienteEditarDados;
    this.token = token;
  }
  @Override
  protected Object doInBackground(Object[] objects) {

//    String concertarData = clienteEditarDados.getDataNascimento().replace("/","");




    try {
      JSONStringer jsCliente = new JSONStringer();
      jsCliente.object();
      jsCliente.key("idCliente").value(clienteEditarDados.getIdCliente());
      jsCliente.key("nome").value(clienteEditarDados.getNome());
      jsCliente.key("sobrenome").value(clienteEditarDados.getSobrenome());
      jsCliente.key("celular").value(clienteEditarDados.getCelular());
      jsCliente.key("cpf").value(clienteEditarDados.getCpf());
      jsCliente.key("sexo").value(clienteEditarDados.getSexo());
      jsCliente.key("dataNascimento").value(clienteEditarDados.getDataNascimento().replace("\'",""));
      jsCliente.key("email").value(clienteEditarDados.getEmail());
      jsCliente.key("senha").value(clienteEditarDados.getSenha());
      jsCliente.endObject();


      Log.d("dados a serem editados",String.valueOf(jsCliente));

      URL url = new URL("http://"+ MainActivity.IP_SERVER+"/clientes/"+clienteEditarDados.getIdCliente());
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      Log.d("idCliente", String.valueOf(clienteEditarDados.getIdCliente()));

      connection.setRequestProperty("Content-type","application/json");
      connection.setRequestProperty("Accept","application/json");
      connection.setRequestProperty("token",token);
      connection.setRequestMethod("PUT");



      connection.setDoInput(true);
      PrintStream stream = new PrintStream(connection.getOutputStream());
      stream.print(jsCliente);
      connection.connect();

      Scanner scanner = new Scanner(connection.getInputStream());
      resposta = scanner.nextLine();

      JSONObject object = new JSONObject(resposta);

      clienteEditado = new Cliente();
      clienteEditado.setIdCliente(object.getInt("idCliente"));
      clienteEditado.setNome(object.getString("nome"));
      clienteEditado.setSobrenome(object.getString("sobrenome"));
      clienteEditado.setDataNascimento(object.getString("dataNascimento"));
      clienteEditado.setSexo(object.getString("sexo"));
      clienteEditado.setCpf(object.getString("cpf"));
      clienteEditado.setCelular(object.getString("celular"));
      clienteEditado.setEmail(object.getString("email"));
      clienteEditado.setSexo(object.getString("senha"));


    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }


    if(clienteEditado.getIdCliente()!=null){
        return clienteEditado;
    }else{
      return null;
    }
  }
}
