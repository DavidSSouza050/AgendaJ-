package br.senai.sp.agendaja.Pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.senai.sp.agendaja.Tabs.TabAvaliacoesEstabelecimento;
import br.senai.sp.agendaja.Tabs.TabDetalhesEstabelecimento;
import br.senai.sp.agendaja.Tabs.TabPortifolioEstabelecimento;
import br.senai.sp.agendaja.Tabs.TabServicosEstabelecimento;
import br.senai.sp.agendaja.Tabs.TabServicosFechados;

public class PagerAdapterEstabelecimento extends FragmentStatePagerAdapter {

  private int number;

  public PagerAdapterEstabelecimento(FragmentManager fm, int number) {
    super(fm);

    this.number = number;
  }

  @Override
  public Fragment getItem(int i) {
    switch (i){
      case 0:
        TabServicosEstabelecimento servicosEstabelecimento = new TabServicosEstabelecimento();
        return servicosEstabelecimento;
      case 1:
        TabDetalhesEstabelecimento detalhesEstabelecimento = new TabDetalhesEstabelecimento();
        return detalhesEstabelecimento;
      case 2:
        TabAvaliacoesEstabelecimento avaliacoesEstabelecimento = new TabAvaliacoesEstabelecimento();
        return avaliacoesEstabelecimento;
      case 3:
        TabPortifolioEstabelecimento portifolioEstabelecimento = new TabPortifolioEstabelecimento();
        return portifolioEstabelecimento;
        default:
          return null;

    }
  }

  @Override
  public int getCount() {
    return number;
  }
}
