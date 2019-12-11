package br.senai.sp.agendaja.Services;

import br.senai.sp.agendaja.MainActivity;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfigPegarEmServico {
  private Retrofit retrofit;
  public static  final String URL_OCUPADOS = "http://" + MainActivity.IP_SERVER +"/";

  public RetrofitConfigPegarEmServico() {

  }



  public static PegarEmServico pegarFuncionariosEmServico(){
    return RetrofitClient.getClient(URL_OCUPADOS).create(PegarEmServico.class);
  }

}
