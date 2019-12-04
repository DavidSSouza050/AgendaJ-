package br.senai.sp.agendaja.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.senai.sp.agendaja.R;

public class ListaNulaAdapter extends RecyclerView.Adapter<ListaNulaAdapter.ListaNulaViewHolder>{
    private Context context;
    private List<String> mensagem;

    public ListaNulaAdapter(Context context, List<String> mensagem) {
        this.context = context;
        this.mensagem = mensagem;
    }

    @NonNull
    @Override
    public ListaNulaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_lista_nula,viewGroup,false);
        ListaNulaViewHolder listaNulaViewHolder = new ListaNulaViewHolder(view);
        return listaNulaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaNulaViewHolder listaNulaViewHolder, int i) {

        String string = mensagem.get(i);

        listaNulaViewHolder.txtMensagem.setText(string);

    }

    @Override
    public int getItemCount() {
        return mensagem!=null?mensagem.size():0;
    }

    public class ListaNulaViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMensagem;

        public ListaNulaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMensagem = itemView.findViewById(R.id.txt_lista_nula);
        }
    }
}
