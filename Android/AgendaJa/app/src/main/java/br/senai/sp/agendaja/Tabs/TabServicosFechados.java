package br.senai.sp.agendaja.Tabs;

import android.os.Bundle;
import androidx.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.Adapters.AgendamentosFinalizadosAdapter;
import br.senai.sp.agendaja.Model.Agendamento;
import br.senai.sp.agendaja.R;
import br.senai.sp.agendaja.Tasks.TaskGetAgendamentosFinalizados;

public class TabServicosFechados extends Fragment {

  private RecyclerView recyclerServicosFinalizados;
  private List<Agendamento> agendamentoList;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.activity_tab_servicos_fechados,container, false);
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    recyclerServicosFinalizados = getActivity().findViewById(R.id.recycler_servicos_finalizados);
    recyclerServicosFinalizados.setLayoutManager(new LinearLayoutManager(getContext()));

    TaskGetAgendamentosFinalizados getAgendamentosFinalizados = new TaskGetAgendamentosFinalizados();
    getAgendamentosFinalizados.execute();

    try {

      if(getAgendamentosFinalizados.get()!=null){
            agendamentoList = (List<Agendamento>) getAgendamentosFinalizados.get();
            setAdapterServicosFinalizados(recyclerServicosFinalizados,agendamentoList);
      }

    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


  }

  public void setAdapterServicosFinalizados(RecyclerView recyclerView, List<Agendamento> agendamentoList){
    AgendamentosFinalizadosAdapter agendamentosFinalizadosAdapter = new AgendamentosFinalizadosAdapter(getContext(),agendamentoList);
    recyclerView.setAdapter(agendamentosFinalizadosAdapter);
  }

}
