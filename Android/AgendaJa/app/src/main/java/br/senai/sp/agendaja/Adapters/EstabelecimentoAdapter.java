package br.senai.sp.agendaja.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Endereco;
import br.senai.sp.agendaja.Model.Estabelecimento;
import br.senai.sp.agendaja.R;
import br.senai.sp.agendaja.Tasks.TaskGetEnderecoIdEstab;

public class EstabelecimentoAdapter extends RecyclerView.Adapter<EstabelecimentoAdapter.EstabelecimentoViewHolder>{

  private List<Estabelecimento> estabelecimentosList;
  private Context context;
  private String token;
  private Endereco endereco;

  public EstabelecimentoAdapter(List<Estabelecimento> estabelecimentosList, Context context, String token) {
    this.estabelecimentosList = estabelecimentosList;
    this.context = context;
    this.token = token;
  }


  @NonNull
  @Override
  public EstabelecimentoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
    View view = LayoutInflater.from(context).inflate(R.layout.adapter_recycler_estabelecimento,viewGroup,false);
    EstabelecimentoViewHolder estabelecimentoViewHolder = new EstabelecimentoViewHolder(view);

    return estabelecimentoViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull EstabelecimentoViewHolder estabelecimentoViewHolder, int i) {

    final Estabelecimento estabelecimento = estabelecimentosList.get(i);

    TaskGetEnderecoIdEstab getEnderecoIdEstab = new TaskGetEnderecoIdEstab(estabelecimento.getIdEstabelecimento(),token);

    try {

      if(getEnderecoIdEstab.get()!=null){
        endereco = (Endereco) getEnderecoIdEstab.get();

        //Setando os valores nos campos
        estabelecimentoViewHolder.nomeEstabelecimento.setText(estabelecimento.getNomeEstabelecimento());
        URL url = new URL(MainActivity.IP_FOTO + estabelecimento.getFoto());

        Picasso
             .get()
             .load(String.valueOf(url))
             .into(estabelecimentoViewHolder.imagemEstabelecimento);

        estabelecimentoViewHolder.enderecoEstabelecimento.setText(endereco.getBairro() + " " + estabelecimento.getCep() + ", " + estabelecimento.getCidade() + "-" + estabelecimento.getEstado());


      }


    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }


  }

  @Override
  public int getItemCount() {
    return estabelecimentosList!=null? estabelecimentosList.size() : 0;
  }

  public class EstabelecimentoViewHolder extends RecyclerView.ViewHolder{

    private TextView nomeEstabelecimento;
    private ImageView imagemEstabelecimento;
    private TextView enderecoEstabelecimento;
    private RatingBar notaEstabelecimento;
    private ImageButton amei;

    public EstabelecimentoViewHolder(@NonNull View itemView) {
      super(itemView);

      nomeEstabelecimento = itemView.findViewById(R.id.txt_nome_estabelecimento_adapter);
      imagemEstabelecimento = itemView.findViewById(R.id.img_estabelecimento_adapter);
      enderecoEstabelecimento = itemView.findViewById(R.id.txt_nome_endereco_adapter);

    }
  }

}
