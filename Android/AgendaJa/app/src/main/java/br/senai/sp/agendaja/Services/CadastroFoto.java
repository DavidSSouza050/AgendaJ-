package br.senai.sp.agendaja.Services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.File;

import br.senai.sp.agendaja.modal.Cliente;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroFoto {
  private FileService fileService;
  private Boolean resposta;


  public Boolean CadastrarFoto(String imagePath, int id){

    Log.d("caminho da imagem",imagePath);

    File file = new File(imagePath);
    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
    MultipartBody.Part body = MultipartBody.Part.createFormData("foto",file.getName(),requestBody);
    RequestBody idCliente = RequestBody.create(MediaType.parse("id"),String.valueOf(id));

    fileService = RetrofitConfig.getFileService();

    Call<Cliente> call = fileService.upload(body,idCliente);

    call.enqueue(new Callback<Cliente>() {
      @Override
      public void onResponse(Call<Cliente> call, Response<Cliente> response) {
        if(response.isSuccessful()){
          resposta = true;
        }
      }

      @Override
      public void onFailure(Call<Cliente> call, Throwable t) {
          resposta = false;
      }
    });
  return resposta;
  }

}
