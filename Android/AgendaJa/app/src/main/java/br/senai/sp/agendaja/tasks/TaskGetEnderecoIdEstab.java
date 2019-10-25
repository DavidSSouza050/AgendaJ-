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
import br.senai.sp.agendaja.Model.Endereco;

public class TaskGetEnderecoIdEstab extends AsyncTask {
  private int idEstabelecimento;
  private Endereco endereco;
  private String token;
  private String dados = "";

  public TaskGetEnderecoIdEstab(int idEstabelecimento, String token){
    this.idEstabelecimento = idEstabelecimento;
    this.token = token;
  }

  @Override
  protected Object doInBackground(Object[] objects) {
    try {

      URL url = new URL("http://"+ MainActivity.IP_SERVER + "/enderecosEstabelecimentos/estabelecimento/"+ idEstabelecimento);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestProperty("Content-type","Application/json");
      connection.setRequestProperty("token",token);

      InputStream inputStream = connection.getInputStream();
      InputStreamReader streamReader = new InputStreamReader(inputStream);
      BufferedReader bufferedReader = new BufferedReader(streamReader);

      String linha = "";

      if(linha!=null){
        linha = bufferedReader.readLine();
        dados = dados + linha;
      }

      if(dados!=null){
        endereco = new Endereco();
        JSONObject object = new JSONObject(dados);
        endereco.setIdEndereco(object.getInt("idEndereco"));
        endereco.setCep(object.getString("cep"));
        endereco.setLogradouro(object.getString("logradouro"));
        endereco.setBairro(object.getString("bairro"));
        endereco.setCidade(object.getJSONObject("idCidade").getString("cidade"));
        String estado = object.getJSONObject("idCidade").getJSONObject("idMicrorregiao").getJSONObject("idEstado").getString("estado");
        endereco.setEstado(estado);
      }




    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }


    if(endereco!=null && endereco.getIdEndereco()!=null){
      return endereco;
    }else{
      return null;
    }
  }
}
