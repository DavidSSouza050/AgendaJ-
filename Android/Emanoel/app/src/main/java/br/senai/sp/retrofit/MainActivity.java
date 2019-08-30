package br.senai.sp.retrofit;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import br.senai.sp.retrofit.services.Carro;
import br.senai.sp.retrofit.services.RetroFitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

  List<Carro> carros;
  RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    Call<List<Carro>> call = new RetroFitConfig()
         .getCarroService().buscarCarros();

    call.enqueue(new Callback<List<Carro>>() {
      @Override
      public void onResponse(Call<List<Carro>> call, Response<List<Carro>> response) {
        carregarCarros(response.body());
      }

      @Override
      public void onFailure(Call<List<Carro>> call, Throwable t) {
        Log.i("Retrofit",t.getMessage());
      }
    });

  }

  private void carregarCarros(final List<Carro> carros){
    this.carros = carros;
    CarroAdapter carroAdapter = new CarroAdapter(this.carros, this, new CarroAdapter.CarroOnClickListener() {
      @Override
      public void onCliclCarro(View view, int index) {
        Carro c = carros.get(index);
        new AlertDialog.Builder(MainActivity.this).setTitle(c.nome).setMessage(c.desc).show();
      }
    });
    recyclerView.setAdapter(carroAdapter);
  }
}
