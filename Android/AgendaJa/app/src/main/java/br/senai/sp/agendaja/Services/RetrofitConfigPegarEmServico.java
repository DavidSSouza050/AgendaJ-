package br.senai.sp.agendaja.Services;

import br.senai.sp.agendaja.MainActivity;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfigPegarEmServico {
  private Retrofit retrofit;
  public static  final String URL = "http://" + MainActivity.IP_SERVER +"/emServico/";

  public RetrofitConfigPegarEmServico() {

  }



  public static PegarEmServico pegarFuncionariosEmServico(){
    return RetrofitClient.getClient(URL).create(PegarEmServico.class);
  }

}
