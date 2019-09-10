package br.senai.sp.agendaja.tasks;

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

import br.senai.sp.agendaja.modal.Endereco;

public class CadastrarEndereco extends AsyncTask{
  private Endereco endereco;
  private Endereco enderecoSalvo;
  private String resposta;

  public CadastrarEndereco(Endereco endereco) {
    this.endereco = endereco;
  }

  @Override
  protected Object doInBackground(Object[] objects) {

    try {
      JSONStringer jsEndereco = new JSONStringer();

      jsEndereco.object();
      jsEndereco.key("logradouro").value(endereco.getLogradouro());
      jsEndereco.key("bairro").value(endereco.getBairro());
      jsEndereco.key("cep").value(endereco.getCep());
      jsEndereco.key("idTipoEndereco").object().key("idTipoEndereco").value(1).endObject();
      jsEndereco.key("idCidade").object().key("idCidade").value(endereco.getCodIBGE()).endObject();
      jsEndereco.endObject();


      URL url = new URL("HTTP://10.107.144.13:8080/endereco");

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestProperty("Content-type", "application/json");
      connection.setRequestProperty("Accept", "application/json");
      connection.setRequestMethod("POST");

      connection.setDoInput(true);
      PrintStream output = new PrintStream(connection.getOutputStream());
      output.print(jsEndereco);
      connection.connect();

      Scanner scanner = new Scanner(connection.getInputStream());
       resposta  = scanner.nextLine();

       enderecoSalvo = new Endereco();

      JSONObject objectResposta = new JSONObject(resposta);
      enderecoSalvo.setCep(objectResposta.getString("cep"));
      enderecoSalvo.setIdEndereco(objectResposta.getInt("idEndereco"));
      enderecoSalvo.setBairro(objectResposta.getString("bairro"));
      enderecoSalvo.setLogradouro(objectResposta.getString("logradouro"));



    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    /*
    * "logradouro" : "kajsdfh"
    * "cep":""
    * "numero": null
    * bairro:
    * "idCidade" :{
    *   idCidade: ibge
    * }
    *
    *
    * */

    if(enderecoSalvo!=null){
      return enderecoSalvo.getIdEndereco();
    }else{
      return 0;
    }
  }
}
