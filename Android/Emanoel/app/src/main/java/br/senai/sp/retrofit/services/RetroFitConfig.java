package br.senai.sp.retrofit.services;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetroFitConfig {

  private Retrofit retrofit;

  public RetroFitConfig(){

    this.retrofit = new Retrofit.Builder()
         .baseUrl("http://www.livroandroid.com.br/livro/carros/v2/")
         .addConverterFactory(JacksonConverterFactory.create())
         .build();
  }

  public CarroService getCarroService(){
    return this.retrofit.create(CarroService.class);
  }

}
