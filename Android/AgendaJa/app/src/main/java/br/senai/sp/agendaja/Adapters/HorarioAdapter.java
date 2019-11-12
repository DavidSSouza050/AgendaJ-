package br.senai.sp.agendaja.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.senai.sp.agendaja.CalculoHorario.CalculoHorario;
import br.senai.sp.agendaja.Model.Horario;
import br.senai.sp.agendaja.R;

public class HorarioAdapter extends RecyclerView.Adapter<HorarioAdapter.HorarioViewHolder>{
  private Context context;
  private List<Horario> horarioList;
  private List<String> horariosUtilizados;
  private ClickHorario clickHorario;

  public HorarioAdapter(Context context,List<Horario> horarioList,ClickHorario clickHorario) {
    this.context = context;
    this.horarioList = horarioList;
    this.clickHorario = clickHorario;
  }

  @NonNull
  @Override
  public HorarioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context).inflate(R.layout.adapter_horario,viewGroup,false);

    HorarioViewHolder horarioViewHolder = new HorarioViewHolder(view);

    return horarioViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull HorarioViewHolder horarioViewHolder, int i) {


    String horariosDaLista = horariosUtilizados.get(i);

    horarioViewHolder.txtHorario.setText(horariosDaLista);

    horarioViewHolder.txtHorario.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        clickHorario.onClickHorario();
      }
    });

  }

  @Override
  public int getItemCount() {
    return horarioList!=null?horarioList.size():0;
  }

  public class HorarioViewHolder extends RecyclerView.ViewHolder{

    private TextView txtHorario;

    public HorarioViewHolder(@NonNull View itemView) {
      super(itemView);

      txtHorario = itemView.findViewById(R.id.txt_horario);
    }
  }

  public interface ClickHorario{
    public void onClickHorario();
  }

}
