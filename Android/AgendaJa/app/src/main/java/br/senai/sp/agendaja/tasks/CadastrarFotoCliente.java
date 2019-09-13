package br.senai.sp.agendaja.tasks;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.agendaja.modal.Cliente;

public class CadastrarFotoCliente extends AsyncTask {


  private Bitmap fotoUsuario;
  private Cliente clienteComFoto;
  private String resposta;
  private Integer idCliente;
  private String caminhoFoto;

  public CadastrarFotoCliente(String caminhoFoto, int idCliente) {
    this.caminhoFoto = caminhoFoto;
    this.idCliente = idCliente;
  }

  @Override
  protected Object doInBackground(Object[] objects) {

    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "*****";
    int bytesRead;
    int bytesAvaliables;
    int bufferSize;
    byte[] buffer;
    int maxBufferSize = 1*1024*1024;
    File foto = new File(caminhoFoto);

    try {
      JSONStringer jsFotoCliente = new JSONStringer();
      jsFotoCliente.object();
      jsFotoCliente.key("foto").value(foto);
      jsFotoCliente.key("id").value(idCliente);
      jsFotoCliente.endObject();


      FileInputStream fileInputStream = new FileInputStream(caminhoFoto);
      URL url = new URL("http://10.107.144.13:8080/foto/cliente");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setDoInput(true);
      connection.setDoOutput(true);
      connection.setUseCaches(false);
      connection.setRequestProperty("Connection","Keep-Alive");
      connection.setRequestProperty("ENCTYPE","multipart/form-data");
      connection.setRequestProperty("Content-type","multipart/form-data;boundary=" + boundary);
      connection.setRequestProperty("uploaded_file",caminhoFoto);
      connection.setRequestProperty("Accept","application/json");
      connection.setRequestMethod("POST");

      DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

      outputStream.writeBytes(twoHyphens+boundary+lineEnd);
      outputStream.writeBytes("Content-Disposition: form-data;" + "filename=" + caminhoFoto + "" + lineEnd );
      outputStream.writeBytes(lineEnd);

      bytesAvaliables = fileInputStream.available();
      bufferSize = Math.min(bytesAvaliables,maxBufferSize);
      buffer = new byte[bufferSize];

      bytesRead = fileInputStream.read(buffer,0,bufferSize);

      while(bytesRead>0){
        outputStream.write(buffer,0,bufferSize);
        bytesAvaliables = fileInputStream.available();
        bufferSize = Math.min(bytesAvaliables,maxBufferSize);
        bytesRead = fileInputStream.read(buffer,0,bufferSize);
      }

      outputStream.writeBytes(lineEnd);
      outputStream.writeBytes(twoHyphens+boundary+twoHyphens+lineEnd);

      fileInputStream.close();
      outputStream.flush();
      outputStream.close();

      Scanner scanner = new Scanner(connection.getInputStream());
      resposta = scanner.nextLine();

      JSONObject object = new JSONObject(resposta);
      clienteComFoto = new Cliente();
      clienteComFoto.setIdCliente(object.getInt("idCliente"));
      clienteComFoto.setNome(object.getString("nome"));
      clienteComFoto.setSobrenome(object.getString("sobrenome"));
      clienteComFoto.setEmail(object.getString("email"));
      clienteComFoto.setFoto(new File(object.getString("foto")));

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }


    if(clienteComFoto.getIdCliente() != null){
      return clienteComFoto;
    }else{
      return null;
    }
  }
}
