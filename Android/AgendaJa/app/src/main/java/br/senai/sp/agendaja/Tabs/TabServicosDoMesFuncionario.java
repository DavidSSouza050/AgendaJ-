package br.senai.sp.agendaja.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.Adapters.AgendamentosFuncionariosAdapter;
import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.MainActivityFuncionario;
import br.senai.sp.agendaja.Model.Agendamento;
import br.senai.sp.agendaja.R;
import br.senai.sp.agendaja.Tasks.TaskGetAgendamentosPendentesFuncionarios;

public class TabServicosDoMesFuncionario extends Fragment{
  private RecyclerView recyclerView;
  private List<Agendamento> agendamentoList;
  private String token;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.tab_servicos_mes_funcionario,container,false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    recyclerView = getActivity().findViewById(R.id.recycler_servicos_funcionarios);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

     token = getActivity().getIntent().getStringExtra("token");


    TaskGetAgendamentosPendentesFuncionarios agendamentosPendentesFuncionarios = new TaskGetAgendamentosPendentesFuncionarios(token);
    agendamentosPendentesFuncionarios.execute();


    try {

      if(agendamentosPendentesFuncionarios.get()!=null){
        agendamentoList = (List<Agendamento>) agendamentosPendentesFuncionarios.get();
        setAdapterAgendamentos(recyclerView,agendamentoList);
      }

    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


  }

  private void setAdapterAgendamentos(RecyclerView recyclerView,List<Agendamento> agendamentoList){
    AgendamentosFuncionariosAdapter agendamentosFuncionariosAdapter = new AgendamentosFuncionariosAdapter(getContext(),agendamentoList,token);
    recyclerView.setAdapter(agendamentosFuncionariosAdapter);
  }
}
