package br.senai.sp.agendaja.Tasks;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

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

public class TaskGetToken extends AsyncTask {

  private String email;
  private String senha;
  private String dados;
  private String token;
  private ProgressBar progressBar;

  public TaskGetToken(String email, String senha,ProgressBar progressBar) {
    this.email = email;
    this.senha = senha;
    this.progressBar = progressBar;
  }

  @Override
  protected Object doInBackground(Object[] objects) {

    try {

      JSONStringer jsLogin = new JSONStringer();

      jsLogin.object();
      jsLogin.key("email").value(email);
      jsLogin.key("password").value(senha);
      jsLogin.endObject();

      URL url = new URL("http://"+ MainActivity.IP_SERVER +"/login/cliente");
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
         token = object.getString("token");
      }else{
        token = null;
      }




    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }


    return token;
  }

  public void exibirProgress(Boolean resposta){
    progressBar.setVisibility(resposta ? View.VISIBLE : View.GONE);
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    exibirProgress(true);
  }

  @Override
  protected void onPostExecute(Object o) {
    super.onPostExecute(o);
    exibirProgress(false);
  }
}
