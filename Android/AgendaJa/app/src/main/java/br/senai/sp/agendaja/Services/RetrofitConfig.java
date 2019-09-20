package br.senai.sp.agendaja.Services;

import retrofit2.Retrofit;

public class RetrofitConfig {

  private RetrofitConfig() {
  }

  public static  final String URL = "http://10.107.144.13/foto/";

    public static FileService getFileService(){
     return RetrofitClient.getClient(URL).create(FileService.class);
    }

}
