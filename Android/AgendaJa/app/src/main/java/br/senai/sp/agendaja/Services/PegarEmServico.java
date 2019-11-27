package br.senai.sp.agendaja.Services;

import br.senai.sp.agendaja.Model.EmServico;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PegarEmServico {

  @FormUrlEncoded
  @POST("emServico")
  Call<EmServico> pegarFuncionariosEmServico(@Field("dia_mes") int diaMes,@Field("mes") int mes, @Field("ano") int ano, @Field("idEstabelecimento") int idEstabelecimento, @Header("token") String token );
}
