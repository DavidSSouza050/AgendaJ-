package br.senai.sp.agendaja.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Agendamento;
import br.senai.sp.agendaja.Model.Endereco;
import br.senai.sp.agendaja.R;
import br.senai.sp.agendaja.Tasks.TaskGetEnderecoIdEstab;

public class AgendamentosAdapter  extends RecyclerView.Adapter<AgendamentosAdapter.AgendamentoViewHolder>{
    private Context context;
    private List<Agendamento> agendamentoList;
    private Endereco endereco;

    public AgendamentosAdapter(Context context,List<Agendamento> agendamentoList) {
        this.context = context;
        this.agendamentoList = agendamentoList;
    }

    @NonNull
    @Override
    public AgendamentoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_recycler_reservas,viewGroup,false);
        AgendamentoViewHolder agendamentoViewHolder = new AgendamentoViewHolder(view);

        return agendamentoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AgendamentoViewHolder agendamentoViewHolder, int i) {
        Agendamento agendamento = agendamentoList.get(i);

        TaskGetEnderecoIdEstab getEnderecoIdEstab = new TaskGetEnderecoIdEstab(agendamento.getIdEstabelecimento(), MainActivity.TOKEN);
        getEnderecoIdEstab.execute();

        String[] dataHorario  = agendamento.getDataAgendamento().split(" ");
        String[] data= dataHorario[0].split("-");
        String horario = dataHorario[1];



        try {
            if(getEnderecoIdEstab.get()!=null){
                endereco = (Endereco) getEnderecoIdEstab.get();
                }

        agendamentoViewHolder.txtNomeServico.setText(agendamento.getNomeServico());
        agendamentoViewHolder.txtNomeEstabelecimento.setText(agendamento.getNomeEstabelecimento());
        agendamentoViewHolder.txtEnderecoEstabelecimento.setText( endereco.getCep() + ", " + endereco.getCidade() + "-" + endereco.getEstado());
        agendamentoViewHolder.txtDia.setText(data[2]);
        agendamentoViewHolder.txtMes.setText(data[1]);
        agendamentoViewHolder.txtAno.setText(data[0]);
        agendamentoViewHolder.txtHora.setText(horario);


    } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
    }

        @Override
    public int getItemCount() {
            return agendamentoList!=null? agendamentoList.size() : 0;
    }

    public static class AgendamentoViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNomeServico;
        private TextView txtEnderecoEstabelecimento;
        private TextView txtNomeEstabelecimento;
        private TextView txtMes;
        private TextView txtDia;
        private TextView txtAno;
        private TextView txtHora;
        private Button btnCancelar;


        public AgendamentoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNomeServico = itemView.findViewById(R.id.text_servico_reservas);
            txtEnderecoEstabelecimento = itemView.findViewById(R.id.text_endereco_reservas);
            txtNomeEstabelecimento = itemView.findViewById(R.id.text_nome_estabelecimento_reservas);
            txtMes = itemView.findViewById(R.id.text_dia_reservas);
            txtDia = itemView.findViewById(R.id.text_mes_reservas);
            txtAno = itemView.findViewById(R.id.text_ano_reservas);
            txtHora = itemView.findViewById(R.id.text_hora_reservas);
            btnCancelar = itemView.findViewById(R.id.btn_cancelar_reservas);

        }
    }
}
