package br.senai.sp.agendaja.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.senai.sp.agendaja.Model.Avaliacao;
import br.senai.sp.agendaja.R;

public class AvalicaoAdapter extends RecyclerView.Adapter<AvalicaoAdapter.AvaliacaoViewHolder>{
    private List<Avaliacao> avaliacaoList;
    private Context context;

    public AvalicaoAdapter(List<Avaliacao> avaliacaoList, Context context) {
        this.avaliacaoList = avaliacaoList;
        this.context = context;
    }

    @NonNull
    @Override
    public AvaliacaoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_recycler_avaliacoes,viewGroup,false);
        AvaliacaoViewHolder avaliacaoViewHolder = new AvaliacaoViewHolder(view);
        return avaliacaoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AvaliacaoViewHolder avaliacaoViewHolder, int i) {
        Avaliacao avaliacao = avaliacaoList.get(i);

        avaliacaoViewHolder.txtComentario.setText(avaliacao.getComentario());
        //avaliacaoViewHolder.txtNota.setText(avaliacao.getNotaAvaliacao());

    }

    @Override
    public int getItemCount() {
        return avaliacaoList!=null? avaliacaoList.size():0;
    }

    public class AvaliacaoViewHolder extends RecyclerView.ViewHolder{
        private TextView txtComentario;
        private TextView txtNota;

        public AvaliacaoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtComentario = itemView.findViewById(R.id.txt_comentario_recycler);
            txtNota = itemView.findViewById(R.id.txt_nota_recycler);
        }
    }
}
