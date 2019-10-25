package br.senai.sp.agendaja.Tasks;

import android.os.AsyncTask;

import com.google.gson.JsonObject;

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
import br.senai.sp.agendaja.Model.Estabelecimento;

public class TaskGetListEstabelecimentos extends AsyncTask {
  private String dados = "";
  private List<Estabelecimento> estabelecimentoList;
  private Estabelecimento estabelecimento;

  @Override
  protected Object doInBackground(Object[] objects) {
    try {

      URL url = new URL("http://"+ MainActivity.IP_SERVER +"/estabelecimentos");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestProperty("Content-type","Application/json");

      InputStream inputStream = connection.getInputStream();

      InputStreamReader streamReader = new InputStreamReader(inputStream);
      BufferedReader bufferedReader = new BufferedReader(streamReader);

      String linha = "";

      while(linha!=null){
        linha = bufferedReader.readLine();
        dados = dados + linha;
      }

      JSONArray jsonArray = new JSONArray(dados);
      estabelecimentoList = new ArrayList<>();

      for(int i=0;i<jsonArray.length();i++){
        JSONObject object = (JSONObject) jsonArray.get(i);
        estabelecimento = new Estabelecimento();
        estabelecimento.setIdEstabelecimento(object.getInt("idEstabelecimento"));
        estabelecimento.setCnpj(object.getString("cnpj"));
        estabelecimento.setRazaoSocial(object.getString("razaoSocial"));
        estabelecimento.setNomeEstabelecimento(object.getString("nomeEstabelecimento"));
        estabelecimento.setFoto(object.getString("foto"));
        estabelecimento.setTelefone(object.getString("telefone"));
        estabelecimento.setCelular(object.getString("celular"));

        estabelecimentoList.add(estabelecimento);
      }


    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }


    if(estabelecimentoList!=null){
      return estabelecimentoList;
    }else{
      return null;
    }
  }
}
