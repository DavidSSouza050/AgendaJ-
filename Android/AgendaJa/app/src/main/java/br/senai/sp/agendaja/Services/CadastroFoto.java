package br.senai.sp.agendaja.Services;

import android.util.Log;

import java.io.File;

import br.senai.sp.agendaja.Model.Cliente;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class CadastroFoto {
  private FileService fileService;

  public Call<Cliente> CadastrarFoto(String imagePath, int id){

    fileService = RetrofitConfig.getFileService();

    Log.d("caminho da imagem",imagePath);

    File file = new File(imagePath);

    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
    RequestBody idCliente = RequestBody.create(MediaType.parse("text/plain"),String.valueOf(id));
    MultipartBody.Part body = MultipartBody.Part.createFormData("foto",file.getName(),requestBody);


    Call<Cliente> call = fileService.upload(body,idCliente);

  return call;
  }

}
