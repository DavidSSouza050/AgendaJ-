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

import br.senai.sp.agendaja.Adapters.AvalicaoAdapter;
import br.senai.sp.agendaja.Model.Avaliacao;
import br.senai.sp.agendaja.Model.Estabelecimento;
import br.senai.sp.agendaja.R;
import br.senai.sp.agendaja.Tasks.TaskGetAvaliacoesEstabelecimento;

public class TabAvaliacoesEstabelecimento extends Fragment {
  private RecyclerView recyclerViewAvaliacoes;
  private List<Avaliacao> avaliacaoList;
  private Estabelecimento estabelecimento;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.tab_avaliacoes_estabelecimento, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    estabelecimento = (Estabelecimento) getActivity().getIntent().getSerializableExtra("estabelecimento");

    recyclerViewAvaliacoes = getActivity().findViewById(R.id.recycler_avaliacoes_estabelecimento);
    recyclerViewAvaliacoes.setLayoutManager(new LinearLayoutManager(getContext()));

    TaskGetAvaliacoesEstabelecimento getAvaliacoesEstabelecimento = new TaskGetAvaliacoesEstabelecimento(estabelecimento.getIdEstabelecimento());
    getAvaliacoesEstabelecimento.execute();


    try {

      if(getAvaliacoesEstabelecimento.get()!=null){
        avaliacaoList = (List<Avaliacao>) getAvaliacoesEstabelecimento.get();

        setAdapterAvaliacoes(recyclerViewAvaliacoes,avaliacaoList);

      }

    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


  }

  public void setAdapterAvaliacoes(RecyclerView recyclerView, List<Avaliacao> avaliacaoList){
    AvalicaoAdapter avalicaoAdapter = new AvalicaoAdapter(avaliacaoList,getContext());
    recyclerView.setAdapter(avalicaoAdapter);
  }

}
