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

public class PerfilFragment extends Fragment implements View.OnClickListener{

    private RelativeLayout dadosPessoais;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_perfil, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dadosPessoais = getActivity().findViewById(R.id.caixa_dados_pessoais_perfil);

        dadosPessoais.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.caixa_dados_pessoais_perfil:
                Intent intent = new Intent(getContext(),EditarDadosPessoaisActivity.class);
                startActivity(intent);
        }
    }
}
