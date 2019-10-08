package br.senai.sp.agendaja.Services;

import br.senai.sp.agendaja.MainActivity;
import retrofit2.Retrofit;

public class RetrofitConfig {

  private RetrofitConfig() {
  }

  public static  final String URL = "http://" + MainActivity.IP_SERVER +"/foto/";

    public static FileService getFileService(){
     return RetrofitClient.getClient(URL).create(FileService.class);
    }

}
