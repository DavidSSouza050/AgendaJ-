package br.senai.sp.agendaja.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Agendamento;
import br.senai.sp.agendaja.Model.Endereco;
import br.senai.sp.agendaja.R;
import br.senai.sp.agendaja.Tasks.TaskGetEnderecoIdEstab;

public class AgendamentosFinalizadosAdapter extends RecyclerView.Adapter<AgendamentosFinalizadosAdapter.AgendamentoFinalizadoViewHolder>{
    private Context context;
    private List<Agendamento> agendamentoList;
    private Endereco endereco;

    public AgendamentosFinalizadosAdapter(Context context,List<Agendamento> agendamentoList) {
        this.agendamentoList = agendamentoList;
        this.context = context;
    }

    @NonNull
    @Override
    public AgendamentoFinalizadoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_recycler_reservas_finalizadas,viewGroup,false);
        AgendamentoFinalizadoViewHolder agendamentoFinalizadoViewHolder = new AgendamentoFinalizadoViewHolder(view);

        return agendamentoFinalizadoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AgendamentoFinalizadoViewHolder agendamentoFinalizadoViewHolder, int i) {
        final Agendamento agendamento = agendamentoList.get(i);

        TaskGetEnderecoIdEstab getEnderecoIdEstab = new TaskGetEnderecoIdEstab(agendamento.getIdEstabelecimento(), MainActivity.TOKEN);
        getEnderecoIdEstab.execute();

        String[] dataHorario = agendamento.getDataAgendamento().split(" ");
        String[] data = dataHorario[0].split("-");
        String horario = dataHorario[1];


        try {
            if (getEnderecoIdEstab.get() != null) {
                endereco = (Endereco) getEnderecoIdEstab.get();
                agendamentoFinalizadoViewHolder.txtHorario.setText(horario);
                agendamentoFinalizadoViewHolder.txtAno.setText(data[0]);
                agendamentoFinalizadoViewHolder.txtMes.setText(data[1]);
                agendamentoFinalizadoViewHolder.txtDia.setText(data[2]);
                agendamentoFinalizadoViewHolder.txtNomeServico.setText(agendamento.getNomeServico());
                agendamentoFinalizadoViewHolder.txtNomeEstabelecimento.setText(agendamento.getNomeEstabelecimento());
                agendamentoFinalizadoViewHolder.txtEnderecoEstabelecimento.setText(endereco.getCep() + ", " + endereco.getCidade() + "-" + endereco.getEstado());


            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            if(getItemCount()!=agendamentoList.size()){
                notifyItemInserted(i);
            }

    }

        @Override
    public int getItemCount() {
        return agendamentoList!=null?agendamentoList.size():0;
    }

    public class AgendamentoFinalizadoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNomeServico;
        private TextView txtNomeEstabelecimento;
        private TextView txtEnderecoEstabelecimento;
        private TextView txtMes;
        private TextView txtDia;
        private TextView txtAno;
        private TextView txtHorario;

        public AgendamentoFinalizadoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNomeServico = itemView.findViewById(R.id.txet_servico_reservas_funcionario);
            txtNomeEstabelecimento = itemView.findViewById(R.id.txet_nome_estabelecimento_funcionario);
            txtEnderecoEstabelecimento = itemView.findViewById(R.id.text_endereco_reservas_funcionario);
            txtMes = itemView.findViewById(R.id.text_dia_reservas_funcionario);
            txtDia = itemView.findViewById(R.id.text_mes_reservas_funcionario);
            txtAno = itemView.findViewById(R.id.text_ano_reservas_funcionario);
            txtHorario = itemView.findViewById(R.id.text_hora_reservas_funcionario);

        }
    }


}
