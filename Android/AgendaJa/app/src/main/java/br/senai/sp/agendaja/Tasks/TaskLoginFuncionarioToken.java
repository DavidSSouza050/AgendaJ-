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
import br.senai.sp.agendaja.Model.Funcionario;

public class TaskLoginFuncionarioToken extends AsyncTask {
  private String email;
  private String senha;
  private String token;
  private String dados = "";
  private Funcionario funcionario;

  public TaskLoginFuncionarioToken(String email, String senha, String token) {
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

      URL url = new URL("http://"+ MainActivity.IP_SERVER +"/funcionarios/login");
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
        funcionario = new Funcionario();
        funcionario.setIdFuncionario(object.getInt("idFuncionario"));
        funcionario.setNomeFuncionario(object.getString("nome"));
        funcionario.setFotoFuncionario(object.getString("foto"));
        funcionario.setEmailFuncionario(object.getString("email"));
        funcionario.setSenhaFuncionario(object.getString("senha"));

      }else{
        funcionario = null;
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


    if(funcionario!=null){
      return funcionario;
    }else{
      return null;
    }
  }
}
