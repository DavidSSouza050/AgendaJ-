package br.senai.sp.agendaja.Services;

import java.util.Observable;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.EmServico;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileServiceEmServico {
  @FormUrlEncoded
  @POST("emServico")
  Call<EmServico> postEmServico(@Field("idAgendamento") int idAgendamento, @Header("token") String token );

}
