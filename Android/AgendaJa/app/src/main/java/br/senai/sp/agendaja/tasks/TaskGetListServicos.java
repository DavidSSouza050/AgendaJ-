package br.senai.sp.agendaja.Tasks;

import android.content.Intent;
import android.os.AsyncTask;

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

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Servico;

public class TaskGetListServicos extends AsyncTask {
  private int idEstabelecimento;
  private List<Servico> servicoList;
  private String dados = "";

  public TaskGetListServicos(int idEstabelecimento) {
    this.idEstabelecimento = idEstabelecimento;
  }

  @Override
  protected Object doInBackground(Object[] objects) {

    try {

      URL url = new URL("http://" + MainActivity.IP_SERVER + "/servicos/estabelecimento/" + idEstabelecimento);
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

      JSONArray jsonArray = new JSONArray(dados);
      servicoList = new ArrayList<>();
      for(int i = 0;i<jsonArray.length();i++){
        Servico servico = new Servico();
        JSONObject object = (JSONObject) jsonArray.get(i);
        servico.setIdServico(object.getInt("idServico"));
        servico.setNomeServico(object.getString("servico"));
        servico.setPrecoServico(object.getDouble("preco"));
        servico.setDuracao(object.getInt("duracaoServico"));
        servico.setIdCategoria(object.getJSONObject("categoriaServico").getInt("idCategoriaServico"));
        servico.setCategoriaServico(object.getJSONObject("categoriaServico").getString("categoriaServico"));

        servicoList.add(servico);

      }




    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }


    if(servicoList!=null){
      return servicoList;
    }else{
      return null;
    }
  }
}
