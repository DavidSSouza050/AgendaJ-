package br.senai.sp.agendaja.Services;

import br.senai.sp.agendaja.Model.Cliente;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileService {
  @Multipart
  @POST("cliente")
  Call<Cliente> upload(@Part MultipartBody.Part file, @Part("id") RequestBody id);


}
