package br.senai.sp.agendaja.Tasks;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Endereco;

public class TaskGetEnderecoCliente extends AsyncTask {
  private int codCliente;
  private String dados = "";
  private Endereco endereco;

  public TaskGetEnderecoCliente(int codCliente) {
    this.codCliente = codCliente;
  }

  @Override
  protected Object doInBackground(Object[] objects) {

    try {

      URL url = new URL("http://" + MainActivity.IP_SERVER + "/enderecosClientes/endereco/" + codCliente);

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestMethod("GET");
      connection.setRequestProperty("token",MainActivity.TOKEN);


      InputStream inputStream = connection.getInputStream();
      InputStreamReader streamReader = new InputStreamReader(inputStream);

      BufferedReader bufferedReader = new BufferedReader(streamReader);

      String linha = "";

      while(linha!=null){
        linha = bufferedReader.readLine();
        dados = dados+linha;
      }

      if(dados!=null){
        JSONArray jsonArray = new JSONArray(dados);
        JSONObject object = (JSONObject) jsonArray.get(0);
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(object.getInt("idEndereco"));
        endereco.setLogradouro(object.getString("logradouro"));
        endereco.setBairro(object.getString("bairro"));
        endereco.setCep(object.getString("cep"));
        endereco.setNumero(object.getInt("numero"));
        endereco.setCodIBGE(String.valueOf(object.getJSONObject("idCidade").getInt("idCidade")));
        endereco.setCidade(object.getJSONObject("idCidade").getString("cidade"));

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
