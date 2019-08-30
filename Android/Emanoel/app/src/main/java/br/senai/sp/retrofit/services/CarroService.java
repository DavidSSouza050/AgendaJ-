package br.senai.sp.retrofit.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CarroService {

  @GET("carros_luxo.json")
  Call<List<Carro>> buscarCarros();


}
