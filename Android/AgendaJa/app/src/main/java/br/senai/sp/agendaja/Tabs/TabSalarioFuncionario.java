package br.senai.sp.agendaja.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.agendaja.R;

public class TabSalarioFuncionario extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.tab_salario_funcionario,container,false);
  }
}
