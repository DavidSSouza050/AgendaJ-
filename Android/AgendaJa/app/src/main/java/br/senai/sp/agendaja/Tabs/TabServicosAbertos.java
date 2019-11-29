package br.senai.sp.agendaja.Tabs;

import android.os.Bundle;
import androidx.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.Nullable;
import br.senai.sp.agendaja.Adapters.AgendamentosAdapter;
import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Agendamento;
import br.senai.sp.agendaja.R;
import br.senai.sp.agendaja.Tasks.TaskGetAgendamentos;

public class TabServicosAbertos extends Fragment {
  private RecyclerView recyclerView;
  private List<Agendamento> agendamentoList;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.activity_tab_servicos_abertos,container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    recyclerView = getActivity().findViewById(R.id.recycler_srevicos_abertos);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    TaskGetAgendamentos taskGetAgendamentos = new TaskGetAgendamentos(MainActivity.CLIENTELOGADO.getIdCliente());
    taskGetAgendamentos.execute();


    try {
      if(taskGetAgendamentos.get()!=null){
        agendamentoList = (List<Agendamento>) taskGetAgendamentos.get();
        setAdapterAgendamentos(recyclerView,agendamentoList);
      }else{
        Toast.makeText(getContext(),"Você não possui agendamentos",Toast.LENGTH_LONG).show();
      }
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


  }

  public void setAdapterAgendamentos(RecyclerView recyclerView, List<Agendamento> agendamentoList){
    AgendamentosAdapter agendamentosAdapter = new AgendamentosAdapter(getContext(),agendamentoList);
    recyclerView.setAdapter(agendamentosAdapter);

  }
}
