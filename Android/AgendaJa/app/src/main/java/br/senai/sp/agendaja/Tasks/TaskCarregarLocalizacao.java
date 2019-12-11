package br.senai.sp.agendaja.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import br.senai.sp.agendaja.Model.Endereco;


public class TaskCarregarLocalizacao extends AsyncTask{
  private String editText;
  private Context context;
  private String dados="";
  private Endereco endereco;
  private ProgressBar progressBar ;

  public TaskCarregarLocalizacao(String editText, Context context, ProgressBar progressBar) {
    this.editText = editText;
    this.context = context;
    this.progressBar = progressBar;
  }



  @Override
  protected Object doInBackground(Object[] objects) {

    try {

      URL url = new URL("https://viacep.com.br/ws/"+editText+"/json/");

      Log.d("URL", String.valueOf(url));

      HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

      InputStream stream = connection.getInputStream();

      InputStreamReader reader = new InputStreamReader(stream);

      BufferedReader bufferedReader =  new BufferedReader(reader);

      String linha = "";

      while(linha!=null){
        linha = bufferedReader.readLine();
        dados=dados+linha;
      }


      JSONObject object = new JSONObject(dados);
      Log.d("dados", String.valueOf(object.length()));


        if(object.length()>0){
        endereco = new Endereco();
        endereco.setEstado(object.getString("uf"));
        endereco.setCidade(object.getString("localidade"));
        endereco.setBairro(object.getString("bairro"));
        endereco.setLogradouro(object.getString("logradouro"));
        endereco.setCodIBGE(object.getString("ibge"));
        }

        Log.d("Endereco",endereco.getLogradouro());

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return endereco;
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
  protected void onProgressUpdate(Object[] values) {
    super.onProgressUpdate(values);
    exibirProgress(true);
  }

  @Override
  protected void onPostExecute(Object o) {
    super.onPostExecute(o);

    Handler handler = new Handler();

    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        exibirProgress(false);
      }
    },100);
  }
}
