package br.senai.sp.agendaja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.Adapters.FuncionarioAdapter;
import br.senai.sp.agendaja.Adapters.HorarioAdapter;
import br.senai.sp.agendaja.CalculoHorario.CalculoHorario;
import br.senai.sp.agendaja.Model.EmServico;
import br.senai.sp.agendaja.Model.Estabelecimento;
import br.senai.sp.agendaja.Model.Funcionario;
import br.senai.sp.agendaja.Model.Horario;
import br.senai.sp.agendaja.Model.Servico;
import br.senai.sp.agendaja.Services.PegarFuncionariosEmServico;
import br.senai.sp.agendaja.Tasks.TaskGetFuncionarios;
import br.senai.sp.agendaja.Verificacao.VerificandoHorarios;
import br.senai.sp.agendaja.Verificacao.VerificandoHorariosFuncionarios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private List<String>horariosDisponiveis;
    private Funcionario funcionarioEscolhido;
    private String horarioEscolhido;
    private String dataEscolhida;
    private VerificandoHorarios verificandoHorarios;
    private List<EmServico> emServicoList;
    private String arrayServicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_reserva);

        //Instanciando estabelecimento e servico escolhido
        Intent getObjects = getIntent();

        estabelecimento = (Estabelecimento) getObjects.getSerializableExtra("estabelecimento");
        servicoEscolhido = (Servico) getObjects.getSerializableExtra("servicoEscolhido");


        verificandoHorarios = new VerificandoHorarios();

        horariosDisponiveis  = verificandoHorarios.verificandoHorarios(estabelecimento,horarioDoDia);

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

        Date date = new Date();
        calendarioReserva.setMinDate(date.getTime());

        listaDeFuncionarios.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager layoutManager = new LinearLayoutManager(FazerReservaActivity.this,LinearLayout.HORIZONTAL,false);

        listaDeFuncionarios.setLayoutManager(layoutManager);

        //Pegando os funcionarios


        //setando os listenners no calendar e no botao;
        btnSalvar.setOnClickListener(this);
        calendarioReserva.setOnDateChangeListener(this);
    }

    @Override
    public void onClick(View v) {

      switch (v.getId()){
        case R.id.btn_salvar_agendamento:
            if(validar()){



//                Intent intentConfirmarReserva  = new Intent(FazerReservaActivity.this,ConfirmarReservaActivity.class);
//                intentConfirmarReserva.putExtra("estabelecimentoEscolhido",estabelecimento);
//                intentConfirmarReserva.putExtra("servicoEscolhido",servicoEscolhido);
//                intentConfirmarReserva.putExtra("horaEscolhido",horarioEscolhido);
//                intentConfirmarReserva.putExtra("dataEscolhida",dataEscolhida);
//                intentConfirmarReserva.putExtra("funcionarioEscolhido",funcionarioEscolhido);
//                startActivity(intentConfirmarReserva);


//              }else{
                Intent intentConfirmarReserva  = new Intent(FazerReservaActivity.this,ConfirmarReservaActivity.class);
                intentConfirmarReserva.putExtra("estabelecimentoEscolhido",estabelecimento);
                intentConfirmarReserva.putExtra("servicoEscolhido",servicoEscolhido);
                intentConfirmarReserva.putExtra("horaEscolhido",horarioEscolhido);
                intentConfirmarReserva.putExtra("dataEscolhida",dataEscolhida);
                intentConfirmarReserva.putExtra("funcionarioEscolhido",funcionarioEscolhido);
                startActivity(intentConfirmarReserva);
              //}

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
  public void onClickHorario(LinearLayout linearLayout, final String horario) {
    if(statusHorario==0){
      linearLayout.setBackgroundColor(R.color.colorLilasClaro);
      statusHorario++;
    }else if(statusHorario==1){
      linearLayout.setBackgroundColor(R.color.colorBrancoAcinzentadoo);
      statusHorario--;
    }

    this.horarioEscolhido = horario;

    String[] horarioList = horario.split(":");

    Double minutos = Double.parseDouble(horarioList[1]);
    int soma = (int) (minutos+servicoEscolhido.getDuracao());

    String horarioSomado = horarioList[0] + ":" + soma;

    final String horarioFinal = CalculoHorario.calcularHorarioFinal(horarioSomado);

    Integer ano = 0;
    Integer mes = 0;
    Integer dia = 0;

    if(dataEscolhida==null) {
      Date d = new Date();
      d.getTime();

      Calendar c = Calendar.getInstance();
      c.setTime(d);

       ano = c.get(Calendar.YEAR);
       mes = c.get(Calendar.MONTH) + 1;
       dia = c.get(Calendar.DAY_OF_MONTH);

      dataEscolhida = String.valueOf(ano + "-" + mes + "-" + dia);

    }else{

      String[] dataArray = dataEscolhida.split("-");
      ano = Integer.parseInt(dataArray[0]);
      mes = Integer.parseInt(dataArray[1]);
      dia = Integer.parseInt(dataArray[2]);
    }

    PegarFuncionariosEmServico funcionariosEmServico = new PegarFuncionariosEmServico(dia,mes,ano,estabelecimento.getIdEstabelecimento());

    Call<List<EmServico>> callListEmServico = funcionariosEmServico.postPegarFuncionariosEmServico();

    callListEmServico.enqueue(new Callback<List<EmServico>>() {
      @Override
      public void onResponse(Call<List<EmServico>> call, Response<List<EmServico>> response) {
        if(response.isSuccessful()){
          Log.d("sucesso","sucesso");
          emServicoList = response.body();

          mostrarFuncionarios(emServicoList,horario,horarioFinal);

          Log.d("responseBody", String.valueOf(response.body()));
         if(response.body()!=null){
           arrayServicos = String.valueOf(response.body());
         }
        }else{
            Log.d("foi fracasso",response.errorBody().toString());
        }
      }

      @Override
      public void onFailure(Call<List<EmServico>> call, Throwable t) {
        Log.d("nao foi sucesso","deu erro");
      }
    });

  }

  private void mostrarFuncionarios(List<EmServico> emServicoList,String horario,String horarioFinal){

    TaskGetFuncionarios funcionarios = new TaskGetFuncionarios(estabelecimento.getIdEstabelecimento());
    funcionarios.execute();

    try {

      if(funcionarios.get()!=null ){
        funcionariosList = (List<Funcionario>) funcionarios.get();
      }

    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    if(emServicoList==null){
      setAdapaterFuncionario(funcionariosList,listaDeFuncionarios);
    }else{
      List<Funcionario> funcionariosDisponiveis = new ArrayList<>();
      VerificandoHorariosFuncionarios horariosFuncionarios = new VerificandoHorariosFuncionarios();


      for(int i=0;i<funcionariosList.size();i++){
        Funcionario funcionario = funcionariosList.get(i);
        List<EmServico> servicosFuncionarios= new ArrayList<>();

        for(int cont=0;cont<emServicoList.size();cont++){

          if(emServicoList.get(cont).getIdFuncionario()==funcionario.getIdFuncionario()){
            servicosFuncionarios.add(emServicoList.get(cont));
          }

        }

        int verificador = horariosFuncionarios.verificarHorariosFuncionarios(servicosFuncionarios,horario,horarioFinal);

        if(verificador==0){
          funcionariosDisponiveis.add(funcionario);
        }

      }

      if(funcionariosDisponiveis!=null){
        setAdapaterFuncionario(funcionariosDisponiveis,listaDeFuncionarios);
      }else{
        Toast.makeText(FazerReservaActivity.this,"Sem funcionarios disponiveis para esse horario",Toast.LENGTH_LONG).show();
      }


    }

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
