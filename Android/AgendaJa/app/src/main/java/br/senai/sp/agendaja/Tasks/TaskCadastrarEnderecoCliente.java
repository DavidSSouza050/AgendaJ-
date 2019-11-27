package br.senai.sp.agendaja.Tasks;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.agendaja.MainActivity;

public class TaskCadastrarEnderecoCliente extends AsyncTask {
  private int clienteId;
  private int enderecoId;
  private String resposta;

  public TaskCadastrarEnderecoCliente(int clienteId, int enderecoId) {
    this.clienteId = clienteId;
    this.enderecoId = enderecoId;
  }

  @SuppressLint("LongLogTag")
  @Override
  protected Object doInBackground(Object[] objects) {
    try {

      JSONStringer jsEnderecoCliente = new JSONStringer();
      jsEnderecoCliente.object();
      jsEnderecoCliente.key("endereco").object().key("idEndereco").value(enderecoId).endObject();
      jsEnderecoCliente.key("cliente").object().key("idCliente").value(clienteId).endObject();
      jsEnderecoCliente.endObject();

      URL url = new URL("http://" + MainActivity.IP_SERVER + "/enderecosClientes");

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestProperty("Content-type","application/json");
      connection.setRequestProperty("Accept","application/json");
      connection.setRequestMethod("POST");

      connection.setDoInput(true);
      PrintStream output = new PrintStream(connection.getOutputStream());
      output.print(jsEnderecoCliente);
      connection.connect();

      Log.d("essa é a url",String.valueOf(url));
      Log.d("esse é o endereco cliente", String.valueOf(jsEnderecoCliente));

      Scanner scanner = new Scanner(connection.getInputStream());
      resposta = scanner.nextLine();

    } catch (JSONException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }


    if(resposta!=null){
      return true;
    }else{
      return false;
    }
  }
}
