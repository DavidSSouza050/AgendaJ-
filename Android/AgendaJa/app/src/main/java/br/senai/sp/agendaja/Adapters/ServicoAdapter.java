package br.senai.sp.agendaja.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.senai.sp.agendaja.Model.Servico;
import br.senai.sp.agendaja.R;

public class ServicoAdapter extends RecyclerView.Adapter<ServicoAdapter.ServicoViewHolder>{
  private List<Servico> servicosList;
  private Context context;
  private Servico servico;
  private ServicoViewHolder.ClickReserva clickReserva;
  private int idCategoria;


  public ServicoAdapter(List<Servico> servicosList, Context context, int idCategoria, ServicoViewHolder.ClickReserva clickReserva) {
    this.servicosList = servicosList;
    this.context = context;
    this.clickReserva = clickReserva;
    this.idCategoria = idCategoria;
  }

  @NonNull
  @Override
  public ServicoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view  = View.inflate(context, R.layout.adapter_recycler_reservas,viewGroup);
    ServicoViewHolder servicoViewHolder = new ServicoViewHolder(view);

    return servicoViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull ServicoViewHolder servicoViewHolder, int i) {
     servico = servicosList.get(i);

    if(servico.getIdCategoria()==idCategoria){
      servicoViewHolder.nomeServico.setText(servico.getNomeServico());
      servicoViewHolder.custoServico.setText("R$ " + String.valueOf(servico.getPrecoServico()));
      servicoViewHolder.tempoServico.setText(String.valueOf(servico.getDuracao()));
      servicoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          clickReserva.onClickReserva(servico);
        }
      });
    }

  }

  @Override
  public int getItemCount() {
    return 0;
  }

  public static class ServicoViewHolder extends RecyclerView.ViewHolder{
    private TextView nomeServico;
    private TextView custoServico;
    private TextView tempoServico;
    private Button btnReservar;


    public ServicoViewHolder(@NonNull View itemView) {
      super(itemView);
      nomeServico = itemView.findViewById(R.id.text_servico_do_estabelecimento);
      custoServico = itemView.findViewById(R.id.text_preco_do_servico);
      tempoServico = itemView.findViewById(R.id.text_tempo_do_servico);
      btnReservar = itemView.findViewById(R.id.button_reservar_do_estabelecimento);


    }

    public interface ClickReserva{
      public void onClickReserva(Servico servico);
    }

  }



}
