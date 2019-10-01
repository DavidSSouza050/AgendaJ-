package br.senai.sp.agendaja;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.senai.sp.agendaja.modal.Cliente;

public class PerfilFragment extends Fragment implements View.OnClickListener{

    private RelativeLayout dadosPessoais;
    private Cliente clienteLogado;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_perfil, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        clienteLogado = (Cliente) getActivity().getIntent().getSerializableExtra("clienteLogado");

        dadosPessoais = getActivity().findViewById(R.id.caixa_dados_pessoais_perfil);

        dadosPessoais.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.caixa_dados_pessoais_perfil:
                Intent intent = new Intent(getContext(),EditarDadosPessoaisActivity.class);
                if(clienteLogado!=null){
                    intent.putExtra("clienteLogado",clienteLogado);
                }
                startActivity(intent);
        }
    }


}
