package br.senai.sp.agendaja.tasks;

import android.os.AsyncTask;

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
import br.senai.sp.agendaja.modal.Cliente;

public class EditarDadosPessoaisCliente extends AsyncTask {
  private Cliente clienteEditarDados;
  private String resposta;
  private Cliente clienteEditado;

  public EditarDadosPessoaisCliente(Cliente clienteEditarDados) {
    this.clienteEditarDados = clienteEditarDados;
  }

  @Override
  protected Object doInBackground(Object[] objects) {
    try {
      JSONStringer jsCliente = new JSONStringer();
      jsCliente.object();
      jsCliente.key("idCliente").value(clienteEditarDados.getIdCliente());
      jsCliente.key("nome").value(clienteEditarDados.getNome());
      jsCliente.key("sobrenome").value(clienteEditarDados.getSobrenome());
      jsCliente.key("celular").value(clienteEditarDados.getCelular());
      jsCliente.key("cpf").value(clienteEditarDados.getCpf());
      jsCliente.key("sexo").value(clienteEditarDados.getSexo());
      jsCliente.key("dataNascimento").value(clienteEditarDados.getDataNascimento());
      jsCliente.key("email").value(clienteEditarDados.getEmail());
      jsCliente.key("senha").value(clienteEditarDados.getSenha());
      jsCliente.key("endereco").object().key("idEndereco").value(clienteEditarDados.getIdEndereco()).endObject();
      jsCliente.endObject();


      URL url = new URL("http://"+ MainActivity.IP_SERVER+"/cliente/"+clienteEditarDados.getIdCliente());
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestProperty("Content-type","application/json");
      connection.setRequestProperty("Accept","application/json");
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
