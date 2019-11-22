package br.senai.sp.agendaja;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.Adapters.FuncionarioAdapter;
import br.senai.sp.agendaja.Adapters.HorarioAdapter;
import br.senai.sp.agendaja.Model.Estabelecimento;
import br.senai.sp.agendaja.Model.Funcionario;
import br.senai.sp.agendaja.Model.Horario;
import br.senai.sp.agendaja.Model.Servico;
import br.senai.sp.agendaja.Tasks.TaskGetFuncionarios;
import br.senai.sp.agendaja.VerificandoHorarios.VerificandoHorarios;

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
//    private List<Horario> horarioList;
    private List<String>horariosDisponiveis;
    private Funcionario funcionarioEscolhido;
    private String horarioEscolhido;
    private String dataEscolhida;
    private VerificandoHorarios verificandoHorarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_reserva);

        //Instanciando estabelecimento e servico escolhido
        Intent getObjects = getIntent();

        estabelecimento = (Estabelecimento) getObjects.getSerializableExtra("estabelecimento");
        servicoEscolhido = (Servico) getObjects.getSerializableExtra("servicoEscolhido");

      Log.d("horarios", String.valueOf(estabelecimento.getHorarios()));

       verificandoHorarios = new VerificandoHorarios();

      horariosDisponiveis  = verificandoHorarios.verificandoHorarios(estabelecimento,horarioDoDia);

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

          if(horariosDisponiveis!=null){
            setAdapterHorarios(recyclerHorarios,horariosDisponiveis);
          }else{
            Toast.makeText(FazerReservaActivity.this,"Sem horários disponíveis para esta data",Toast.LENGTH_LONG).show();
          }

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

  @Override
  public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
    switch (view.getId()){
      case R.id.calendarView:

        int somaMes = month+1;

        dataEscolhida = String.valueOf(year+"-"+somaMes+"-"+dayOfMonth);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

          Date d = dateFormat.parse(year+"-"+somaMes+"-"+dayOfMonth);

          Calendar calendar = new GregorianCalendar();

          calendar.setTime(d);



          int idDiaSemana = calendar.get(Calendar.DAY_OF_WEEK);

          Log.d("id do dia atual", String.valueOf(idDiaSemana));

          horariosDisponiveis = verificandoHorarios.verificandoHorariosComDiaEspecifico(estabelecimento,horarioDoDia,idDiaSemana);

          if(horariosDisponiveis==null){
            Toast.makeText(FazerReservaActivity.this,"Sem horários disponíveis para esta data",Toast.LENGTH_LONG).show();
          }else{
            setAdapterHorarios(recyclerHorarios,horariosDisponiveis);
          }


        } catch (ParseException e) {
          e.printStackTrace();
        }


    }
  }
}
