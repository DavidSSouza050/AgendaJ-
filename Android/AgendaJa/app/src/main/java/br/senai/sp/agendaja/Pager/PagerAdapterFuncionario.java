package br.senai.sp.agendaja.Pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.senai.sp.agendaja.Tabs.TabSalarioFuncionario;
import br.senai.sp.agendaja.Tabs.TabServicosDoMesFuncionario;

public class PagerAdapterFuncionario extends FragmentStatePagerAdapter {
  private int cont;

  public PagerAdapterFuncionario(FragmentManager fm, int cont) {
    super(fm);
    this.cont = cont;
  }

  @Override
  public Fragment getItem(int i) {
    switch (i){
      case 0:
        TabServicosDoMesFuncionario tabServicosDoMesFuncionario = new TabServicosDoMesFuncionario();
        return tabServicosDoMesFuncionario;
      case 1:
        TabSalarioFuncionario tabSalarioFuncionario = new TabSalarioFuncionario();
        return tabSalarioFuncionario;
        default:
          return null;

    }
  }

  @Override
  public int getCount() {
    return cont;
  }
}
