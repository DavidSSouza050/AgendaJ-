package br.senai.sp.agendaja;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import br.senai.sp.agendaja.Model.Funcionario;
import br.senai.sp.agendaja.Pager.PagerAdapterFuncionario;

public class MainActivityFuncionario extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

  private ViewPager viewPager;
  public static  Funcionario FUNCIONARIOLOGADO;
  public static String TOKENFUNCIONARIO;
  private Button btnLogar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_funcionario);
//    Toolbar toolbar = findViewById(R.id.toolbar_funcionario);
//    setSupportActionBar(toolbar);

    TabLayout tabLayout = findViewById(R.id.tabLayout_funcionario);
    tabLayout.addTab(tabLayout.newTab().setText("Minha Agenda"));
    tabLayout.addTab(tabLayout.newTab().setText("Financeiro"));
    tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

    viewPager = findViewById(R.id.pager_funcionario);
    PagerAdapterFuncionario pagerAdapterFuncionario = new PagerAdapterFuncionario(getSupportFragmentManager(),tabLayout.getTabCount());
    viewPager.setAdapter(pagerAdapterFuncionario);
    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    tabLayout.addOnTabSelectedListener(this);


    Intent intentGet = getIntent();

    if(intentGet.getStringExtra("token")!=null){
      TOKENFUNCIONARIO = intentGet.getStringExtra("token");
      Log.d("token func",TOKENFUNCIONARIO);
    }

    if(intentGet.getSerializableExtra("FUNCLOGADO")!=null){
      FUNCIONARIOLOGADO = (Funcionario) intentGet.getSerializableExtra("FUNCLOGADO");
    }



  }

  @Override
  public void onTabSelected(TabLayout.Tab tab) {
    viewPager.setCurrentItem(tab.getPosition());
  }

  @Override
  public void onTabUnselected(TabLayout.Tab tab) {

  }

  @Override
  public void onTabReselected(TabLayout.Tab tab) {

  }

}
