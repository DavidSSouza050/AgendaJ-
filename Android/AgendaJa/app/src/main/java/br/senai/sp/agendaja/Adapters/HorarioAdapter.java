package br.senai.sp.agendaja.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import br.senai.sp.agendaja.CalculoHorario.CalculoHorario;
import br.senai.sp.agendaja.Model.Horario;
import br.senai.sp.agendaja.R;

public class HorarioAdapter extends RecyclerView.Adapter<HorarioAdapter.HorarioViewHolder>{
  private Context context;
  private List<Horario> horarioList;
  private List<String> horariosUtilizados;
  private Horario horarioDoDia;

  public HorarioAdapter(Context context,List<Horario> horarioList) {
    this.context = context;
    this.horarioList = horarioList;
  }

  @NonNull
  @Override
  public HorarioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context).inflate(R.layout.adapter_horario,viewGroup,false);

    HorarioViewHolder horarioViewHolder = new HorarioViewHolder(view);

    return horarioViewHolder;
  }

  @Override
  @SuppressLint("WrongConstant")
  public void onBindViewHolder(@NonNull HorarioViewHolder horarioViewHolder, int i) {



  }

  @Override
  public int getItemCount() {
    return horariosUtilizados!=null?horariosUtilizados.size():0;
  }

  public class HorarioViewHolder extends RecyclerView.ViewHolder{

    private TextView txtHorario;

    public HorarioViewHolder(@NonNull View itemView) {
      super(itemView);

      txtHorario = itemView.findViewById(R.id.txt_horario);
    }
  }

  public List<String> verificandoHorarios(){
    Calendar c = Calendar.getInstance();

    int idDiaAtual = c.get(Calendar.DAY_OF_WEEK);

    for(int cont=0;cont<horarioList.size();cont++){
      if(horarioList.get(cont).getIdDiaSemana()==idDiaAtual){
        horarioDoDia = (Horario) horarioList.get(cont);
      }
    }

    String[] arrayHorarioAbertura = horarioDoDia.getHorarioAbertura().split(":");
    String[] arrayHorarioFechamento = horarioDoDia.getHorarioFechamento().split(":");


    if(Integer.parseInt(arrayHorarioAbertura[1])==0  && Integer.parseInt(arrayHorarioFechamento[2])==0){
      for(int cont=Integer.parseInt(arrayHorarioAbertura[0]);cont<=Integer.parseInt(arrayHorarioFechamento[0]);cont=cont+20){
        String horarioAtual = horarioDoDia.getHorarioAbertura();
        String horarioFinal = CalculoHorario.calcularHorarioFinal(horarioAtual,cont);

        horariosUtilizados.add(horarioFinal);
      }

    }else{

      //Double valorInicial = Double (arrayHorarioAbertura[0],((arrayHorarioAbertura[1])/60));

    }

    horariosUtilizados.add(0,horarioDoDia.getHorarioAbertura());
    horariosUtilizados.add(horariosUtilizados.size()-1,horarioDoDia.getHorarioFechamento());

    return horariosUtilizados;
  }
}
