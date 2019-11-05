package br.senai.sp.agendaja.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Funcionario;

public class TaskGetFuncionarios extends AsyncTask {

  private int idEstabelecimento;
  private List<Funcionario> funcionarioList;
  private String dados = "";


  public TaskGetFuncionarios(int idEstabelecimento){
    this.idEstabelecimento = idEstabelecimento;
  }

  @Override
  protected Object doInBackground(Object[] objects) {

    try {

      URL url = new URL("http://" + MainActivity.IP_SERVER + "/funcionariosEstabelecimentos/estabelecimento/" + idEstabelecimento);
      Log.d("url funcioanrio",String.valueOf(url));
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestMethod("GET");
      connection.setRequestProperty("token",MainActivity.TOKEN);

      InputStream inputStream = connection.getInputStream();
      InputStreamReader streamReader = new InputStreamReader(inputStream);

      BufferedReader bufferedReader = new BufferedReader(streamReader);

      String linha = "";

      while(linha!=null){
        linha = bufferedReader.readLine();
        dados = dados + linha;
      }

      if(dados!=null){
        JSONArray jsonArray = new JSONArray(dados);
        funcionarioList = new ArrayList<>();

        for(int i=0;i<jsonArray.length();i++){
          JSONObject object = (JSONObject) jsonArray.get(i);
          Funcionario funcionario = new Funcionario();
          funcionario.setIdFuncionario(object.getInt("idFuncionario"));
          funcionario.setFotoFuncionario(object.getString("foto"));
          funcionario.setNomeFuncionario(object.getString("nome"));

          funcionarioList.add(funcionario);
        }


      }



    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }

    if(funcionarioList!=null){
      return funcionarioList;
    }else{
      return null;
    }

  }
}
