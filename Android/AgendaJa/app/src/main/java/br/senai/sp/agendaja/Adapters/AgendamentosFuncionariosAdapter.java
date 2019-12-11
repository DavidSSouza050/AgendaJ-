package br.senai.sp.agendaja.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.CalculoHorario.CalculoHorario;
import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Agendamento;
import br.senai.sp.agendaja.Model.Endereco;
import br.senai.sp.agendaja.R;
import br.senai.sp.agendaja.Tasks.TaskGetEnderecoIdEstab;

public class AgendamentosFuncionariosAdapter  extends RecyclerView.Adapter<AgendamentosFuncionariosAdapter.AgendamentosFuncionariosViewHolder>{
  private Context context;
  private List<Agendamento> agendamentoList;
  private Endereco endereco;
  private String token;

  public AgendamentosFuncionariosAdapter(Context context, List<Agendamento> agendamentoList,String token) {
    this.context = context;
    this.agendamentoList = agendamentoList;
    this.token = token;
  }

  @Override
  public AgendamentosFuncionariosViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context).inflate(R.layout.adapter_recycler_minha_agenda,viewGroup,false);
    AgendamentosFuncionariosViewHolder funcionariosViewHolder = new AgendamentosFuncionariosViewHolder(view);
    return  funcionariosViewHolder;
  }

  @SuppressLint("LongLogTag")
  @Override
  public void onBindViewHolder(AgendamentosFuncionariosViewHolder agendamentosFuncionariosViewHolder, int i) {
    final Agendamento agendamento = agendamentoList.get(i);

    Log.d("idAgendamentoEstabelecimento", String.valueOf(agendamento.getIdEstabelecimento()));

    String[] dataHorario  = agendamento.getDataAgendamento().split(" ");
    final String[] data= dataHorario[0].split("-");
    final String horario = dataHorario[1];

    String[] horaEMinuto = horario.split(":");

    String horarioFinal = CalculoHorario.calcularHorarioFinal(horario);

    final String horaFinal = horarioFinal.split(":")[0];
    final String minutosFinais = horarioFinal.split(":")[1];


      agendamentosFuncionariosViewHolder.txtNomeServico.setText(agendamento.getNomeServico());
      agendamentosFuncionariosViewHolder.txtDia.setText(data[2]);
      agendamentosFuncionariosViewHolder.txtMes.setText(data[1]);
      agendamentosFuncionariosViewHolder.txtAno.setText(data[0]);
      agendamentosFuncionariosViewHolder.txtHorario.setText(horario + " - " + horarioFinal);
      agendamentosFuncionariosViewHolder.txtNomeCliente.setText(agendamento.getNomeCliente());
      agendamentosFuncionariosViewHolder.txtValor.setText(agendamento.getPreco());





  }

  @Override
  public int getItemCount() {
    return agendamentoList!=null?agendamentoList.size():0;
  }

  public class AgendamentosFuncionariosViewHolder extends RecyclerView.ViewHolder{
    private TextView txtNomeCliente;
    private TextView txtNomeServico;
    private TextView txtDia;
    private TextView txtMes;
    private TextView txtAno;
    private TextView txtHorario;
    private TextView txtValor;


    public AgendamentosFuncionariosViewHolder(View itemView) {
      super(itemView);

      txtNomeCliente = itemView.findViewById(R.id.text_nome_minha_agenda);
      txtNomeServico = itemView.findViewById(R.id.txt_qual_servico_minha_agenda);
      txtDia = itemView.findViewById(R.id.text_dia_reservas_funcionario);
      txtMes = itemView.findViewById(R.id.text_mes_reservas_funcionario);
      txtAno = itemView.findViewById(R.id.text_ano_reservas_funcionario);
      txtHorario = itemView.findViewById(R.id.text_hora_reservas_funcionario);
      txtValor = itemView.findViewById(R.id.txt_real_minha_agenda);

    }
  }
}
