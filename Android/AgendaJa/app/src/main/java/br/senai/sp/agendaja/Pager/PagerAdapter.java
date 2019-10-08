package br.senai.sp.agendaja.Pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.senai.sp.agendaja.Tabs.TabServicosAbertos;
import br.senai.sp.agendaja.Tabs.TabServicosFechados;

public class PagerAdapter extends FragmentStatePagerAdapter {

  private int number;

  public PagerAdapter(FragmentManager fm, int number) {
    super(fm);
    this.number = number;
  }

  @Override
  public Fragment getItem(int i) {
    switch (i){
      case 0:
        TabServicosAbertos tabServicosAbertos = new TabServicosAbertos();
        return tabServicosAbertos;
      case 1:
        TabServicosFechados tabServicosFechados= new TabServicosFechados();
        return tabServicosFechados;
        default:
          return null;

    }
  }

  @Override
  public int getCount() {
    return number;
  }
}
