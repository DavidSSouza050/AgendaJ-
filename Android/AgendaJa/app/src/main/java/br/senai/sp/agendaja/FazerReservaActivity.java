package br.senai.sp.agendaja;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.Adapters.FuncionarioAdapter;
import br.senai.sp.agendaja.Model.Estabelecimento;
import br.senai.sp.agendaja.Model.Funcionario;
import br.senai.sp.agendaja.Model.Servico;
import br.senai.sp.agendaja.Tasks.TaskGetFuncionarios;

public class FazerReservaActivity extends AppCompatActivity implements View.OnClickListener, CalendarView.OnDateChangeListener{

    private CalendarView calendarioReserva;
    private RecyclerView listaDeFuncionarios;
    private List<Funcionario> funcionariosList;
    private Estabelecimento estabelecimento;
    private Servico servicoEscolhido;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_reserva);

        //Instanciando estabelecimento e servico escolhido
        Intent getObjects = getIntent();

        estabelecimento = (Estabelecimento) getObjects.getSerializableExtra("estabelecimento");
        servicoEscolhido = (Servico) getObjects.getSerializableExtra("servicoEscolhido");


        //instanciando os elementos da view
        calendarioReserva = findViewById(R.id.calendarView);
        listaDeFuncionarios = findViewById(R.id.recycler_funcionarios);
        btnSalvar = findViewById(R.id.btn_salvar_agendamento);

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
        FuncionarioAdapter adapter = new FuncionarioAdapter(funcionariosList,FazerReservaActivity.this);
        recyclerViewFuncionario.setAdapter(adapter);
    }
}
