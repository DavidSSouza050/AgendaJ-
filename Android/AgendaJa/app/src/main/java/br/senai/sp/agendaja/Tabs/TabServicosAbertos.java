package br.senai.sp.agendaja.Tabs;

import android.os.Bundle;
import androidx.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.agendaja.R;

public class TabServicosAbertos extends Fragment {

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.activity_tab_servicos_abertos,container, false);
  }
}
