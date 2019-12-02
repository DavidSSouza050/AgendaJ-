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
import br.senai.sp.agendaja.Model.Agendamento;

public class TaskCadastrarAgendamento extends AsyncTask {

  private int idCliente;
  private int idEstabelecimento;
  private int idFuncionario;
  private String data;
  private String resposta;
  private Agendamento agendamento;

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
      jsAgendamento.key("cliente").object().key("idCliente").value(idCliente).endObject();
      jsAgendamento.key("funcionario").object().key("idFuncionario").value(idFuncionario).endObject();
      jsAgendamento.key("estabelecimento").object().key("idEstabelecimento").value(idEstabelecimento).endObject();
      jsAgendamento.key("dataHorarioAgendado").value(data);
      jsAgendamento.endObject();

      URL url = new URL("http://"+ MainActivity.IP_SERVER+"/agendamentos");

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
         agendamento = new Agendamento();
        agendamento.setIdCliente(object.getJSONObject("cliente").getInt("idCliente"));
        agendamento.setIdAgendamento(object.getInt("idAgendamento"));
        agendamento.setDataAgendamento(object.getString("dataHorarioAgendado"));
        agendamento.setStatusFinalizado(object.getInt("finalizado"));
        agendamento.setIdFuncionario(object.getJSONObject("funcionario").getInt("idFuncionario"));
        agendamento.setIdEstabelecimento(object.getJSONObject("estabelecimento").getInt("idEstabelecimento"));
      }

    } catch (JSONException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }




    if(agendamento!=null && agendamento.getIdAgendamento()>0){
      return agendamento;
    }else{
      return  null;
    }
  }
}
