package br.senai.sp.agendaja.Tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.Adapters.ServicoAdapter;
import br.senai.sp.agendaja.Model.Estabelecimento;
import br.senai.sp.agendaja.Model.Servico;
import br.senai.sp.agendaja.R;
import br.senai.sp.agendaja.Tasks.TaskGetListServicos;
import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;

public class TabServicosEstabelecimento extends Fragment implements ServicoAdapter.ServicoViewHolder.ClickReserva{
  private List<Servico> servicoList;
  private Estabelecimento estabelecimento;
  private Servico servico;
  private List<String> nomeCategorias;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view  =  inflater.inflate(R.layout.tab_servicos_estabelecimento,container, false);

    estabelecimento = (Estabelecimento) getActivity().getIntent().getSerializableExtra("estabelecimento");

    TaskGetListServicos taskGetListServicos = new TaskGetListServicos(estabelecimento.getIdEstabelecimento());
    taskGetListServicos.execute();

    try {

      if(taskGetListServicos.get()!=null){
        servicoList = (List<Servico>) taskGetListServicos.get();
      }


    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


    ExpandableLayout expandableLayout = view.findViewById(R.id.list_categorias_servicos);

    expandableLayout.setRenderer(new ExpandableLayout.Renderer<String, Servico>() {
      @Override
      public void renderParent(View view, String nomeServico, boolean b, int i) {

        servico = servicoList.get(i);

        ((TextView)view.findViewById(R.id.tv_parent_name)).setText(servico.getCategoriaServico());
        view.findViewById(R.id.arrow).setBackgroundResource(b? R.drawable.ic_arrow: R.drawable.ic_arrow_dwon);
      }

      @Override
      public void renderChild(View view, Servico servico, int i, int i1){

        RecyclerView listaServicos = view.findViewById(R.id.lista_servicos_estabelecimento);
        listaServicos.setLayoutManager(new LinearLayoutManager(getActivity()));

        ServicoAdapter servicoAdapter = new ServicoAdapter(servicoList, getActivity(), servico.getIdCategoria(), new ServicoAdapter.ServicoViewHolder.ClickReserva() {
          @Override
          public void onClickReserva(Servico servico) {

          }
        });

        listaServicos.setAdapter(servicoAdapter);


      }
    });

    for(int i=0;i<=getCountCategorias(servicoList);i++){
      expandableLayout.addSection(getSection(i));
    }

    return view;
  }


  public Section<String,Servico> getSection(int i){
    Section<String, Servico> servicoSection = new Section<>();


    int numeroCategorias = getCountCategorias(servicoList);

      servicoSection.parent = nomeCategorias.get(i);

      return servicoSection;
  }

  private int getCountCategorias(List<Servico> listaCountServicos){
    List<Integer> anterior = new ArrayList<>();
    anterior.add(0);

    int atual;
    int total = 0;

    nomeCategorias = new ArrayList<>();

    for (int i=0;i<listaCountServicos.size();i++){
          Servico servicoContagem = listaCountServicos.get(i);

          for(int idCateg = 0; idCateg<anterior.size();idCateg++){
            if(servicoContagem.getIdCategoria()!=anterior.get(i)){
              total++;
              anterior.add(servicoContagem.getIdCategoria());
              nomeCategorias.add(servicoContagem.getCategoriaServico());
            }else{
              total = total;
              anterior.add(servicoContagem.getIdCategoria());
            }
          }

    }
    return total;
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public void onClickReserva(Servico servico) {

  }
}
