package br.senai.sp.agendaja.Tasks;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.senai.sp.agendaja.MainActivity;

public class TaskGetNotaEstabelecimento extends AsyncTask{
  private int idEstabelecimento;
  private String dados = "";
  private int nota;


  public TaskGetNotaEstabelecimento(int idEstabelecimento) {
    this.idEstabelecimento = idEstabelecimento;
  }

  @Override
  protected Object doInBackground(Object[] objects) {

    try {

      URL url = new URL("http://"+ MainActivity.IP_SERVER+"/avaliacoesEstabelecimentos/avaliacao/media/estabelecimento/"+idEstabelecimento);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestProperty("token",MainActivity.TOKEN);
      connection.setRequestMethod("GET");


      InputStream inputStream = connection.getInputStream();
      InputStreamReader streamReader = new InputStreamReader(inputStream);
      BufferedReader bufferedReader = new BufferedReader(streamReader);


      String linha = "";

      while(linha!=null){
        linha = bufferedReader.readLine();
        dados = dados + linha;
      }

      if(dados!=null){
        JSONObject object = new JSONObject(dados);
        nota = object.getInt("media");

      }


    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }


    if(nota!=0){
      return nota;
    }else{
      return 0;
    }
  }
}
