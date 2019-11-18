package br.senai.sp.agendaja.Tasks;

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

public class TaskCadastrarAgendamento extends AsyncTask {

  private int idCliente;
  private int idEstabelecimento;
  private int idFuncionario;
  private String data;
  private String resposta;
  private int idAgendamento;

  public TaskCadastrarAgendamento(int idCliente, int idEstabelecimento, int idFuncionario, String data) {
    this.idCliente = idCliente;
    this.idEstabelecimento = idEstabelecimento;
    this.idFuncionario = idFuncionario;
    this.data = data;
  }

  @Override
  protected Object doInBackground(Object[] objects) {
    JSONStringer jsAgendamento = new JSONStringer();

    try {

      jsAgendamento.object();
      jsAgendamento.key("cliente").object().key("idCliente").value(idCliente);
      jsAgendamento.key("funcionario").object().key("idFuncionario").value(idFuncionario);
      jsAgendamento.key("estabelecimento").object().key("idEstabelecimento").value(idEstabelecimento);
      jsAgendamento.key("dataHorarioAgendamento").value(data);
      jsAgendamento.endObject();

      URL url = new URL("http://"+ MainActivity.IP_SERVER+"/"+"agendamentos");

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestProperty("Content-type","application/json");
      connection.setRequestProperty("Accept","application/json");
      connection.setRequestProperty("token",MainActivity.TOKEN);
      connection.setRequestMethod("POST");

      connection.setDoInput(true);
      PrintStream stream = new PrintStream(connection.getOutputStream());
      stream.print(jsAgendamento);
      connection.connect();

      Scanner scanner = new Scanner(connection.getInputStream());
      resposta = scanner.nextLine();

      if(resposta!=null){
        JSONObject object = new JSONObject(resposta);
        idAgendamento = object.getInt("idAgendamento");
      }

    } catch (JSONException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }




    if(idAgendamento!=0){
      return idAgendamento;
    }else{
      return  0;
    }
  }
}
