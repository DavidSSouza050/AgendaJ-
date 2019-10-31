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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
  private TextView nomeServico;
  private TextView precoServico;
  private TextView duracaoServico;
  private Button btnReserva;


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

    return view;
  }





  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    ExpandableLayout expandableLayout = getActivity().findViewById(R.id.list_categorias_servicos);

    expandableLayout.setRenderer(new ExpandableLayout.Renderer<String, Servico>() {
      @Override
      public void renderParent(View view, String nomeServico, boolean b, int i) {

        servico = servicoList.get(i);

        ((TextView)view.findViewById(R.id.tv_parent_name)).setText(servico.getCategoriaServico());
        view.findViewById(R.id.arrow).setBackgroundResource(b? R.drawable.ic_arrow: R.drawable.ic_arrow_dwon);
      }

      @Override
      public void renderChild(View view, Servico servico, int i, int i1){
        nomeServico = view.findViewById(R.id.text_servico_do_estabelecimento);
        precoServico = view.findViewById(R.id.text_preco_do_servico);
        duracaoServico = view.findViewById(R.id.text_tempo_do_servico);
        btnReserva = view.findViewById(R.id.button_reservar_do_estabelecimento);


        nomeServico.setText(servico.getNomeServico());
        precoServico.setText(String.valueOf(servico.getPrecoServico()));
        duracaoServico.setText(String.valueOf(servico.getDuracao()));
        btnReserva.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Toast.makeText(getContext(),"teste",Toast.LENGTH_LONG).show();
          }
        });

      }
    });


    int numero = getCountCategorias(servicoList);

    for(int i=0;i<numero;i++){
      expandableLayout.addSection(getSection(i));
    }


  }

  public Section<String,Servico> getSection(int i){
    Section<String, Servico> servicoSection = new Section<>();


    // int numeroCategorias = getCountCategorias(servicoList);

    String categoria = nomeCategorias.get(i);


    servicoSection.parent = categoria;
    servicoSection.children.addAll(servicoList);


    return servicoSection;
  }

  private int getCountCategorias(List<Servico> listaCountServicos){
    List<Integer> anterior = new ArrayList<>();

    int total = 0;

    nomeCategorias = new ArrayList<>();

    for (int i=0;i<listaCountServicos.size();i++){
      Servico servicoContagem = listaCountServicos.get(i);

      Number number = new Integer(servicoContagem.getIdCategoria());

      boolean verificar =anterior.contains(number);

      if(verificar){
        total = total;
        anterior = anterior;
        nomeCategorias = nomeCategorias;
      }else{
        anterior.add(servicoContagem.getIdCategoria());
        nomeCategorias.add(servicoContagem.getCategoriaServico());
        total++;

      }
    }
    return total;
  }

  @Override
  public void onClickReserva(Servico servico) {

  }
}
