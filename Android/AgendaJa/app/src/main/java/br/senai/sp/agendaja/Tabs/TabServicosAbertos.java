package br.senai.sp.agendaja.Tabs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.Nullable;
import br.senai.sp.agendaja.Adapters.AgendamentosAbertosAdapter;
import br.senai.sp.agendaja.CadastrarAvaliacaoActivity;
import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Agendamento;
import br.senai.sp.agendaja.R;
import br.senai.sp.agendaja.Tasks.TaskCancelarAgendamento;
import br.senai.sp.agendaja.Tasks.TaskFinalizarAgendamento;
import br.senai.sp.agendaja.Tasks.TaskGetAgendamentosAbertos;

public class TabServicosAbertos extends Fragment implements AgendamentosAbertosAdapter.ClickAgendamento {
  private RecyclerView recyclerView;
  private List<Agendamento> agendamentoListDisponiveis;
  private AlertDialog alertDialog;


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


    TaskGetAgendamentosAbertos taskGetAgendamentosAbertos = new TaskGetAgendamentosAbertos(MainActivity.CLIENTELOGADO.getIdCliente());
    taskGetAgendamentosAbertos.execute();


    try {
      if(taskGetAgendamentosAbertos.get()!=null){
        agendamentoListDisponiveis = new ArrayList<>();

        List<Agendamento> agendamentosGeral = (List<Agendamento>) taskGetAgendamentosAbertos.get();

        for(int i = 0;i<agendamentosGeral.size();i++){
          Agendamento agendamento = agendamentosGeral.get(i);

          if(!agendamento.getStatusCancelado().equals("C")){
              agendamentoListDisponiveis.add(agendamento);
          }


        }
        setAdapterAgendamentos(recyclerView,agendamentoListDisponiveis);
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
    AgendamentosAbertosAdapter agendamentosAbertosAdapter = new AgendamentosAbertosAdapter(getContext(),agendamentoList,this);
    recyclerView.setAdapter(agendamentosAbertosAdapter);

  }


  @Override
  public void onClickAgendamentoCancelar(Agendamento agendamento) {

    TaskCancelarAgendamento cancelarAgendamento = new TaskCancelarAgendamento(agendamento.getIdAgendamento());
    cancelarAgendamento.execute();

    try {

      if(cancelarAgendamento.get()!=null){
        String resposta = (String) cancelarAgendamento.get();

        if(!resposta.equals("Agendamento Cancelado")){

          Toast.makeText(getContext(),"Agendamento já finalizado",Toast.LENGTH_LONG).show();

        }

      }else{
        Toast.makeText(getContext(),"Erro ao cancelar agendamento",Toast.LENGTH_LONG).show();
      }

    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }



  }

  @Override
  public void onClickAgendamentoFinalizar(final Agendamento agendamento, String dia, String mes, String ano, String horario) {
    TaskFinalizarAgendamento finalizarAgendamento = new TaskFinalizarAgendamento(agendamento.getIdAgendamento());
    finalizarAgendamento.execute();


    try {

      if(finalizarAgendamento.get()!=null){
        String resposta = (String) finalizarAgendamento.get();

        if(!resposta.equals("Agendamento Finalizado")){
            Toast.makeText(getContext(),"Erro ao finalizar agendamento",Toast.LENGTH_LONG).show();
        }else{
          Toast.makeText(getContext(),"AgendamentoFinalizado",Toast.LENGTH_LONG).show();

          AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
          builder.setTitle("Avaliações");
          builder.setMessage("deseja avaliar esse estabelecimento?");
          builder.setPositiveButton("Avaliar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              Intent intent = new Intent(getContext(), CadastrarAvaliacaoActivity.class);
              intent.putExtra("idEstabelecimento",agendamento.getIdEstabelecimento());
              startActivity(intent);

            }
          });

          builder.setNegativeButton("Nao avaliar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              Toast.makeText(getContext(),"OK",Toast.LENGTH_LONG).show();
            }
          });

          alertDialog = builder.create();
          alertDialog.show();


        }
      }



    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


  }
}
