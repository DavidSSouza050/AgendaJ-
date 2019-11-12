package br.senai.sp.agendaja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.Adapters.FuncionarioAdapter;
import br.senai.sp.agendaja.Adapters.HorarioAdapter;
import br.senai.sp.agendaja.CalculoHorario.CalculoHorario;
import br.senai.sp.agendaja.Model.Estabelecimento;
import br.senai.sp.agendaja.Model.Funcionario;
import br.senai.sp.agendaja.Model.Horario;
import br.senai.sp.agendaja.Model.Servico;
import br.senai.sp.agendaja.Tasks.TaskGetFuncionarios;

import static br.senai.sp.agendaja.R.color.colorCinza;

public class FazerReservaActivity extends AppCompatActivity implements View.OnClickListener, CalendarView.OnDateChangeListener, FuncionarioAdapter.ClickFuncionario,HorarioAdapter.ClickHorario{

    private CalendarView calendarioReserva;
    private RecyclerView listaDeFuncionarios;
    private List<Funcionario> funcionariosList;
    private Estabelecimento estabelecimento;
    private Servico servicoEscolhido;
    private Button btnSalvar;
    private int status = 0;
    private RecyclerView recyclerHorarios;
    private Horario horarioDoDia;
    private List<Horario> horarioList;
    private List<String>horariosDisponiveis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_reserva);

        //Instanciando estabelecimento e servico escolhido
        Intent getObjects = getIntent();

        estabelecimento = (Estabelecimento) getObjects.getSerializableExtra("estabelecimento");
        servicoEscolhido = (Servico) getObjects.getSerializableExtra("servicoEscolhido");

      Log.d("horarios", String.valueOf(estabelecimento.getHorarios()));

        horariosDisponiveis = verificandoHorarios();

        Log.d("horarios disponiveis",String.valueOf(horariosDisponiveis));

        //instanciando os elementos da view
        calendarioReserva = findViewById(R.id.calendarView);
        listaDeFuncionarios = findViewById(R.id.recycler_funcionarios);
        btnSalvar = findViewById(R.id.btn_salvar_agendamento);
        recyclerHorarios = findViewById(R.id.recycler_horarios);

        LinearLayoutManager layoutHorarios = new LinearLayoutManager(FazerReservaActivity.this,LinearLayout.HORIZONTAL,false);
        recyclerHorarios.setLayoutManager(layoutHorarios);
        //setando o adapter de horarios na recycler

        if(estabelecimento.getHorarios()!=null){
          setAdapterHorarios(recyclerHorarios,estabelecimento.getHorarios());
        }



        listaDeFuncionarios.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager layoutManager = new LinearLayoutManager(FazerReservaActivity.this,LinearLayout.HORIZONTAL,false);

        listaDeFuncionarios.setLayoutManager(layoutManager);

        //Pegando os funcionarios

        TaskGetFuncionarios funcionarios = new TaskGetFuncionarios(estabelecimento.getIdEstabelecimento());
        funcionarios.execute();


        try {
            if(funcionarios.get()!=null ){
                funcionariosList = (List<Funcionario>) funcionarios.get();
            }
            setAdapaterFuncionario(funcionariosList,listaDeFuncionarios);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

    }


    public void setAdapaterFuncionario(List<Funcionario> funcionariosList, RecyclerView recyclerViewFuncionario){
        FuncionarioAdapter adapter = new FuncionarioAdapter(funcionariosList,FazerReservaActivity.this,this);
        recyclerViewFuncionario.setAdapter(adapter);
    }


  @SuppressLint("ResourceAsColor")
  @Override
  public void onClickFuncionario(LinearLayout linearLayout, Funcionario funcionario) {

    if(status==0){
      linearLayout.setBackgroundResource(R.color.colorCinza);
      status++;
    }else if(status==1){
      linearLayout.setBackgroundResource(R.color.colorTrasparente);
       status--;
    }

  }

  public void setAdapterHorarios(RecyclerView recyclerHorarios, List<Horario> horarioList){
      //HorarioAdapter horarioAdapter = new HorarioAdapter(FazerReservaActivity.this,horarioList,this);
      //recyclerHorarios.setAdapter(horarioAdapter);
  }

  @Override
  public void onClickHorario() {
    Toast.makeText(this,"Teste horario",Toast.LENGTH_LONG).show();
  }

  public List<String> verificandoHorarios(){

      horarioList = estabelecimento.getHorarios();

    List<String> horariosDoEstabelecimento = new ArrayList<>();

    Calendar c = Calendar.getInstance();

    int idDiaAtual = c.get(Calendar.DAY_OF_WEEK);


    Double contFor;


    for(int cont=0;cont<estabelecimento.getHorarios().size();cont++){

      Horario horario = horarioList.get(cont);

      if(horario.getIdDiaSemana()==idDiaAtual){
        horarioDoDia = horario;
        Toast.makeText(FazerReservaActivity.this,"O numero é" + cont,Toast.LENGTH_LONG).show();
      }
    }

    String[] arrayHorarioAbertura = horarioDoDia.getHorarioAbertura().split(":");
    String[] arrayHorarioFechamento = horarioDoDia.getHorarioFechamento().split(":");


    if(Integer.parseInt(arrayHorarioAbertura[1])==0  && Integer.parseInt(arrayHorarioFechamento[2])==0){
      for(int cont=Integer.parseInt(arrayHorarioAbertura[0]);cont<=Integer.parseInt(arrayHorarioFechamento[0]);cont=cont+20){
        String horarioAtual = horarioDoDia.getHorarioAbertura();
        String horarioFinal = CalculoHorario.calcularHorarioFinal(horarioAtual,cont);

        horariosDoEstabelecimento.add(horarioFinal);
      }

    }else{

      //Essa parte do código diz repeito a os estabelecimento que abrem em um horário que termina em zero e fecha em um horário que termina em zero

      //Aqui eu divido os minutos do horario de abertura do salo por 60 para transformar tudo em horas, o intuito é trabalhar com um double na hora de fazer o for();
      Double minAbertura = Double.parseDouble(arrayHorarioAbertura[1])/60;
      //aqui eu formato para que na divisao descrita acima apareça apenas as duas primeiras casas do numero
      DecimalFormat dfAbertura = new DecimalFormat("#.##");
      minAbertura = Double.valueOf(dfAbertura.format(minAbertura));


      //aqui também existe a divisão para que os minutos do horário de fechamento fique convertido em horas para trabalhar com double no for();
      Double minFechamento = Double.parseDouble(arrayHorarioFechamento[1])/60;
      //aqui também formatado para que apareca apenas duas casas decimais após a divisão
      DecimalFormat dfFechamento = new DecimalFormat("#.##");
      minFechamento = Double.valueOf(dfFechamento.format(minFechamento));

      //essa é a hora com os minutos convertidos em horas também, tanto a de abertura quanto a de fechamento
      Double horaAberturaFormatada = Double.valueOf(arrayHorarioAbertura[0]) + minAbertura;
      Double horaFechamentoFormatada = Double.valueOf(arrayHorarioFechamento[0]) + minFechamento;

      //esse é o contados que também é convertido de minutos para horas
      Float contador = Float.valueOf((20/60));
//      DecimalFormat dfContador = new DecimalFormat("#.#");
//      contador = Double.valueOf(dfContador.format(contador));

      Log.d("contador", String.valueOf(contador));

      Log.d("horaAberturaFormatada", String.valueOf(horaAberturaFormatada));
      Log.d("horaFechamentoFormatada", String.valueOf(horaFechamentoFormatada));

      for(contFor=(horaAberturaFormatada);contFor<=(horaFechamentoFormatada);contFor=contFor+Double.parseDouble(String.valueOf(contador))){

        //nessa parte do codigo faz-se o processo inverso feito acima, as horas decimais são convertidas para minnutos novamente e é chamado o método calcularHorarioFinal que faz a conta e devolve um string;
        int contadorReverso = (int) (contFor*60);

        Double minutosReversos = horaAberturaFormatada%1;

        Log.d("minutos reversos", String.valueOf(minutosReversos));




        //Log.d("primeiro horarioReverso", String.valueOf(arrayHorarioReverso[0]));

//        int minutosReverso =  Integer.parseInt(horarioAberturaReverso[1])*60;
//
//        String horaReversa = horarioAberturaReverso[0] + ":" + minutosReverso;
//
//        String horaFinal = CalculoHorario.calcularHorarioFinal(horaReversa,contadorReverso);
//
//        horariosDoEstabelecimento.add(horaFinal);

      }

    }

    horariosDoEstabelecimento.add(0,horarioDoDia.getHorarioAbertura());
    horariosDoEstabelecimento.add(horariosDoEstabelecimento.size()-1,horarioDoDia.getHorarioFechamento());

    return horariosDoEstabelecimento;
  }

}
