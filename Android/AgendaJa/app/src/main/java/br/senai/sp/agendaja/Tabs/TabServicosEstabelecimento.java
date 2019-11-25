package br.senai.sp.agendaja.Tabs;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.FazerReservaActivity;
import br.senai.sp.agendaja.Model.Estabelecimento;
import br.senai.sp.agendaja.Model.Servico;
import br.senai.sp.agendaja.R;
import br.senai.sp.agendaja.Tasks.TaskGetListServicos;
import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;

public class TabServicosEstabelecimento extends Fragment{
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

        ((TextView)view.findViewById(R.id.tv_parent_name)).setText(nomeServico);
        view.findViewById(R.id.arrow).setBackgroundResource(b? R.drawable.ic_arrow: R.drawable.ic_arrow_dwon);
      }

      @Override
      public void renderChild(View view, final Servico servico, int i, int i1){
        nomeServico = view.findViewById(R.id.text_servico_do_estabelecimento);
        precoServico = view.findViewById(R.id.text_preco_do_servico);
        duracaoServico = view.findViewById(R.id.text_tempo_do_servico);
        btnReserva = view.findViewById(R.id.button_reservar_do_estabelecimento);


        nomeServico.setText(servico.getNomeServico());
        precoServico.setText("R$" + String.valueOf(servico.getPrecoServico()));
        duracaoServico.setText(String.valueOf(servico.getDuracao()) + "min");
        btnReserva.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intentFazerReserva = new Intent(getActivity(), FazerReservaActivity.class);
            intentFazerReserva.putExtra("estabelecimento",estabelecimento);
            intentFazerReserva.putExtra("servicoEscolhido",servico);
            startActivity(intentFazerReserva);
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

    List<Servico> servicosCompativeis = new ArrayList<>();

    String categoria = nomeCategorias.get(i);

    if(categoria!=null){
      for(int numServicos=0;numServicos<servicoList.size();numServicos++){
        Servico servico = servicoList.get(numServicos);

        if(servico.getCategoriaServico().equals(categoria)){
          servicosCompativeis.add(servico);
        }

      }

      servicoSection.parent = categoria;
      servicoSection.children.addAll(servicosCompativeis);
    }

    return servicoSection;
  }

  private int getCountCategorias(List<Servico> listaCountServicos){
    List<Integer> anterior = new ArrayList<>();

    int total = 0;

    nomeCategorias = new ArrayList<>();

    //estrutura de repetição para verificar o numero de categorias e quantas categorias existem
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

}
