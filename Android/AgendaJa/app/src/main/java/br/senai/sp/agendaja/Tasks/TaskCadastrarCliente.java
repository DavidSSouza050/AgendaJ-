package br.senai.sp.agendaja.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Cliente;

public class TaskCadastrarCliente extends AsyncTask {
  private Cliente cliente;
  private String resposta;
  private Cliente clienteVoltado;

  public TaskCadastrarCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  @Override
  protected Object doInBackground(Object[] objects) {

    JSONStringer jsCliente = new JSONStringer();

    try {

      jsCliente.object();
      jsCliente.key("nome").value(cliente.getNome());
      jsCliente.key("sobrenome").value(cliente.getSobrenome());
      jsCliente.key("celular").value(cliente.getCelular());
      jsCliente.key("cpf").value(cliente.getCpf());
      jsCliente.key("sexo").value(cliente.getSexo());
      jsCliente.key("dataNascimento").value(cliente.getDataNascimento());
      jsCliente.key("email").value(cliente.getEmail());
      jsCliente.key("senha").value(cliente.getSenha());
      jsCliente.endObject();

      URL url = new URL("http://"+ MainActivity.IP_SERVER+"/clientes");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestProperty("Content-type","application/json");
      connection.setRequestProperty("Accept","application/json");
      connection.setRequestMethod("POST");

      connection.setDoInput(true);
      PrintStream output = new PrintStream(connection.getOutputStream());
      output.print(jsCliente);
      connection.connect();

       Scanner scanner = new Scanner(connection.getInputStream());
       resposta = scanner.nextLine();

      Log.d("Esse é o scanner", String.valueOf(new Scanner(connection.getInputStream())));

       clienteVoltado = new Cliente();
       JSONObject object = new JSONObject(resposta);

      clienteVoltado.setNome(object.getString("nome"));
      clienteVoltado.setIdCliente(object.getInt("idCliente"));
      clienteVoltado.setSobrenome(object.getString("sobrenome"));
      clienteVoltado.setSenha(object.getString("senha"));
      clienteVoltado.setEmail(object.getString("email"));
      clienteVoltado.setCelular(object.getString("celular"));
      clienteVoltado.setCpf(object.getString("cpf"));
      clienteVoltado.setSexo(object.getString("sexo"));
      clienteVoltado.setDataNascimento(object.getString("dataNascimento"));

    } catch (JSONException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }


    if(clienteVoltado.getIdCliente()!=null){
      return clienteVoltado;
    }else{
      return 0;
    }
  }
}
