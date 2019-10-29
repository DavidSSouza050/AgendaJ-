package br.senai.sp.agendaja;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.senai.sp.agendaja.Adapters.EstabelecimentoAdapter;
import br.senai.sp.agendaja.Model.Estabelecimento;
import br.senai.sp.agendaja.Tasks.TaskGetListEstabelecimentos;

public class ExplorarFragment extends Fragment implements EstabelecimentoAdapter.ClickCardView{

    private List<Estabelecimento> estabelecimentoList;
    private RecyclerView viewEstabelecimentos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_explorar, container, false);


        viewEstabelecimentos = view.findViewById(R.id.recyclerView_estabelecimentos);
        viewEstabelecimentos.setLayoutManager(new LinearLayoutManager(getActivity()));

        TaskGetListEstabelecimentos getListEstabelecimentos = new TaskGetListEstabelecimentos();
        getListEstabelecimentos.execute();

        try {

            if(getListEstabelecimentos.get()!=null){
                estabelecimentoList = (List<Estabelecimento>) getListEstabelecimentos.get();
            }

            EstabelecimentoAdapter estabelecimentoAdapter  = new EstabelecimentoAdapter(estabelecimentoList,getActivity(),MainActivity.TOKEN, (EstabelecimentoAdapter.ClickCardView) this);
            viewEstabelecimentos.setAdapter(estabelecimentoAdapter);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return view;
    }


    private void carregarAdapterEstabelecimentos(final List<Estabelecimento> estabelecimentoList){
        this.estabelecimentoList = estabelecimentoList;
        EstabelecimentoAdapter estabelecimentoAdapter = new EstabelecimentoAdapter(estabelecimentoList,getActivity(),MainActivity.TOKEN, (EstabelecimentoAdapter.ClickCardView) this);
        viewEstabelecimentos.setAdapter(estabelecimentoAdapter);
    }


    @Override
    public void onClickCard(Estabelecimento estabelecimento) {
        Intent intentServicosEstabelecimento = new Intent(getActivity(),ServicosDoEstabelecimentoActivity.class);
        intentServicosEstabelecimento.putExtra("estabelecimento",estabelecimento);
        startActivity(intentServicosEstabelecimento);
    }
}
