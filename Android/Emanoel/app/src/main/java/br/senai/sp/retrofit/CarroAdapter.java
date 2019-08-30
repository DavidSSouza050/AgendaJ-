package br.senai.sp.retrofit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import br.senai.sp.retrofit.services.Carro;

public class CarroAdapter extends RecyclerView.Adapter<CarroAdapter.CarroViewHolder> {

  private List<Carro> carros;
  private Context context;
  private CarroOnClickListener carroOnClickListener;

  public CarroAdapter(List<Carro> carros, Context context, CarroOnClickListener carroOnClickListener) {
    this.carros = carros;
    this.context = context;
    this.carroOnClickListener = carroOnClickListener;
  }

  @NonNull
  @Override
  public CarroViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view = LayoutInflater.from(context)
          .inflate(R.layout.adapter_carro,viewGroup, false);

      CarroViewHolder viewHolder = new CarroViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull CarroViewHolder carroViewHolder, final int position) {

      Carro carro = carros.get(position);
      carroViewHolder.txtNome.setText(carro.nome);
      carroViewHolder.progress.setVisibility(View.VISIBLE);

      carroViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          //carroOnClickListener.onCliclCarro(v,position);

          carros.remove(position);
          notifyItemRemoved(position);
          notifyItemRangeChanged(position,getItemCount());
        }
      });

    try {
      //carregar a url da foto do carro
      URL url = new URL(carro.urlFoto);
      CarregarImg carregarImg = new CarregarImg();
      //passa o viewHolder para o asyncTask poder setar a img no imageView
      carregarImg.viewHolder = carroViewHolder;
      //executa o asyncTask
      carregarImg.execute(url);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public int getItemCount() {
    return carros != null ? carros.size() : 0;
  }

  //interface para expor os eventos de adapter
  public  interface CarroOnClickListener{
    public void onCliclCarro(View view, int index);
  }

  private class CarregarImg extends AsyncTask<URL,Void,Drawable>{

    public CarroViewHolder viewHolder;

    @Override
    protected Drawable doInBackground(URL... urls) {

      //pega a url passada pelo execute
      URL url = urls[0];
      InputStream content = null;

      try {
        //pega o conteudo da resposta http
        content = (InputStream) url.getContent();
        //trasnforma o inputStream em Drawable
        Drawable drw = Drawable.createFromStream(content,"src");
        return drw;
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }

    @Override
    protected void onPostExecute(Drawable drawable) {
      super.onPostExecute(drawable);
      //recebe o drawable e coloca no imageView
      viewHolder.imgCarro.setImageDrawable(drawable);
      //esconde a progress bar
      viewHolder.progress.setVisibility(View.INVISIBLE);
    }
  }

  public class CarroViewHolder extends RecyclerView.ViewHolder{

    TextView txtNome;
    ImageView imgCarro;
    ProgressBar progress;

    public  CarroViewHolder(@NonNull View itemView){
      super(itemView);

      txtNome = itemView.findViewById(R.id.text);
      imgCarro = itemView.findViewById(R.id.img);
      progress = itemView.findViewById(R.id.progressImg);

    }
  }
}
