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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.Adapters.FuncionarioAdapter;
import br.senai.sp.agendaja.Adapters.HorarioAdapter;
import br.senai.sp.agendaja.CalculoHorario.CalculoHorario;
import br.senai.sp.agendaja.model.Estabelecimento;
import br.senai.sp.agendaja.Model.Funcionario;
import br.senai.sp.agendaja.model.Horario;
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
    private int statusHorario;
    private RecyclerView recyclerHorarios;
    private Horario horarioDoDia;
    private List<Horario> horarioList;
    private List<String>horariosDisponiveis;
    private Funcionario funcionarioEscolhido;
    private String horarioEscolhido;
    private String dataEscolhida;

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
          setAdapterHorarios(recyclerHorarios,horariosDisponiveis);
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


        //setando os listenners no calendar e no botao;
        btnSalvar.setOnClickListener(this);
        calendarioReserva.setOnDateChangeListener(this);
    }

    @Override
    public void onClick(View v) {

      switch (v.getId()){
        case R.id.btn_salvar_agendamento:
            if(validar()){
                Intent intentConfirmarReserva  = new Intent(FazerReservaActivity.this,ConfirmarReservaActivity.class);
                intentConfirmarReserva.putExtra("estabelecimentoEscolhido",estabelecimento);
                intentConfirmarReserva.putExtra("servicoEscolhido",servicoEscolhido);
                intentConfirmarReserva.putExtra("horaEscolhido",horarioEscolhido);
                intentConfirmarReserva.putExtra("dataEscolhida",dataEscolhida);
                intentConfirmarReserva.putExtra("funcionarioEscolhido",funcionarioEscolhido);
                startActivity(intentConfirmarReserva);

            }else{
                Toast.makeText(FazerReservaActivity.this,"Escolha o horário e o funcionário",Toast.LENGTH_LONG).show();
            }


          break;
      }

    }

    public boolean validar(){
        boolean validacao = true;

        if(horarioEscolhido==null){
            validacao = false;
        }else if(funcionarioEscolhido==null){
            validacao = false;
        }

        return validacao;
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
      switch (view.getId()){
        case R.id.calendarView:
          dataEscolhida = String.valueOf(year+"/"+month+1+"/"+dayOfMonth);
          Log.d("data escolhida",dataEscolhida);
      }


    }

  @SuppressLint("ResourceAsColor")
  @Override
  public void onClickFuncionario(LinearLayout linearLayout, Funcionario funcionario) {

    if(status==0){
      linearLayout.setBackgroundResource(R.color.colorLilasClaro);
      status++;
    }else if(status==1){
      linearLayout.setBackgroundResource(R.color.colorBrancoAcinzentadoo);
       status--;
    }

    this.funcionarioEscolhido = funcionario;

  }


  @SuppressLint("ResourceAsColor")
  @Override
  public void onClickHorario(LinearLayout linearLayout,String horario) {
    if(statusHorario==0){
      linearLayout.setBackgroundColor(R.color.colorLilasClaro);
      statusHorario++;
    }else if(statusHorario==1){
      linearLayout.setBackgroundColor(R.color.colorBrancoAcinzentadoo);
      statusHorario--;
    }

    this.horarioEscolhido = horario;
  }

  public void setAdapaterFuncionario(List<Funcionario> funcionariosList, RecyclerView recyclerViewFuncionario){
    FuncionarioAdapter adapter = new FuncionarioAdapter(funcionariosList,FazerReservaActivity.this,this);
    recyclerViewFuncionario.setAdapter(adapter);
  }

  public void setAdapterHorarios(RecyclerView recyclerHorarios, List<String> horarioList){
      HorarioAdapter horarioAdapter = new HorarioAdapter(FazerReservaActivity.this,horarioList,this);
      recyclerHorarios.setAdapter(horarioAdapter);
  }


  public List<String> verificandoHorarios(){

      horarioList =  estabelecimento.getHorarios();
    List<String> horariosDoEstabelecimento = new ArrayList<>();

    Calendar c = Calendar.getInstance();

    int idDiaAtual = c.get(Calendar.DAY_OF_WEEK);


    Double contFor;


    for(int cont=0;cont<estabelecimento.getHorarios().size();cont++){

      Horario horario = horarioList.get(cont);

      if(horario.getIdDiaSemana()==idDiaAtual){
        horarioDoDia = horario;
//        Toast.makeText(FazerReservaActivity.this,"O numero é" + cont,Toast.LENGTH_LONG).show();
      }
    }

    String[] arrayHorarioAbertura = horarioDoDia.getHorarioAbertura().split(":");
    String[] arrayHorarioFechamento = horarioDoDia.getHorarioFechamento().split(":");


    if(Integer.parseInt(arrayHorarioAbertura[1])==0  && Integer.parseInt(arrayHorarioFechamento[2])==0){
//      for(int cont=Integer.parseInt(arrayHorarioAbertura[0]);cont<=Integer.parseInt(arrayHorarioFechamento[0]);cont=cont+20){
//        String horarioAtual = horarioDoDia.getHorarioAbertura();
//        String horarioFinal = CalculoHorario.calcularHorarioFinal(horarioAtual,cont);
//
//        horariosDoEstabelecimento.add(horarioFinal);
//      }

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
      Double contador = Double.valueOf((20.00/60.00));
      DecimalFormat dfContador = new DecimalFormat("#.##");
      contador = Double.valueOf(dfContador.format(contador));

      Log.d("contador", String.valueOf(contador));

      Log.d("horaAberturaFormatada", String.valueOf(horaAberturaFormatada));
      Log.d("horaFechamentoFormatada", String.valueOf(horaFechamentoFormatada));

      for(contFor=horaAberturaFormatada;contFor<=horaFechamentoFormatada;contFor=contFor+contador){

        //nessa parte do codigo faz-se o processo inverso feito acima, as horas decimais são convertidas para minnutos novamente e é chamado o método calcularHorarioFinal que faz a conta e devolve um string;

        Double minutosEmHoras = (contFor%1);

        DecimalFormat dfMinutos = new DecimalFormat("#.##");
        dfMinutos.setRoundingMode(RoundingMode.UP);
        minutosEmHoras = Double.valueOf(dfMinutos.format(minutosEmHoras));

//        Log.d("minutos reversos", String.valueOf(contFor));

        int horasParaMinutos =  ((int) (minutosEmHoras*60));


        DecimalFormat dfMinutosReverso = new DecimalFormat("##");
        dfMinutosReverso.setRoundingMode(RoundingMode.UP);


        String horasString = String.valueOf(horasParaMinutos);

        if(horasString.endsWith("9")){
          horasParaMinutos++;
        }else if(horasString.endsWith("8")){
          horasParaMinutos += 2;
        }else if(horasString.endsWith("7")){
          horasParaMinutos += 3;
        }else if(horasString.endsWith("6")){
          horasParaMinutos += 4;
        }else if(horasString.endsWith("5")){
          horasParaMinutos += 5;
        }else if(horasString.endsWith("4")){
          horasParaMinutos += 6;
        }else if (horasString.endsWith("3")){
          horasParaMinutos += 7;
        }else if (horasString.endsWith("2")){
          horasParaMinutos += 8;
        }else if(horasString.endsWith("1")){
          horasParaMinutos += 9;
        }



        int horarioAberturaReverso = (int) (contFor/1);

        String horaReversa = horarioAberturaReverso + ":" + horasParaMinutos;

        Log.d("hora reversa",horaReversa);

        String horaFinal = CalculoHorario.calcularHorarioFinal(horaReversa);

        horariosDoEstabelecimento.add(horaFinal);

      }

    }

    horariosDoEstabelecimento.remove(horariosDoEstabelecimento.size()-1);

    return horariosDoEstabelecimento;
  }

}
