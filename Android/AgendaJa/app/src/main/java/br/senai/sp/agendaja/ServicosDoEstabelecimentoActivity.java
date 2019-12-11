package br.senai.sp.agendaja;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.agendaja.Model.Estabelecimento;
import br.senai.sp.agendaja.Pager.PagerAdapterEstabelecimento;
import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;

public class ServicosDoEstabelecimentoActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    private ViewPager viewPager;
    private ImageView imagemEstabelecimento;
    private TextView nomeEstabelecimento;
    private TextView enderecoEstabelecimento;
    private Estabelecimento estabelecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos_do_estabelecimento);
//        Toolbar toolbar = findViewById(R.id.toolbar_servicos_estabelecimento);
//        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tabLayout_servicos_estabelecimento);
        tabLayout.addTab(tabLayout.newTab().setText("Serviços"));
        tabLayout.addTab(tabLayout.newTab().setText("Detalhes"));
        tabLayout.addTab(tabLayout.newTab().setText("Avaliações"));
        tabLayout.addTab(tabLayout.newTab().setText("Portifólio"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        viewPager = findViewById(R.id.pager_servicos_estabelecimento);
        PagerAdapterEstabelecimento adapterEstabelecimento = new PagerAdapterEstabelecimento(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapterEstabelecimento);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(this);

        //pegando o estabelecimento que veio com a intent

        Intent intentEstab = getIntent();
        if(intentEstab.getSerializableExtra("estabelecimento")!=null){
            estabelecimento = (Estabelecimento) intentEstab.getSerializableExtra("estabelecimento");
        }



        ///instanciando os elementos da view
        imagemEstabelecimento = findViewById(R.id.linearLayout2);
        nomeEstabelecimento = findViewById(R.id.textView);
        enderecoEstabelecimento = findViewById(R.id.textView3);


        //setando os valores nos elementos da view

        try {
            URL url = new URL(MainActivity.IP_FOTO+ estabelecimento.getFoto());
            Picasso.get().load(String.valueOf(url)).into(imagemEstabelecimento);

            nomeEstabelecimento.setText(estabelecimento.getNomeEstabelecimento());

            enderecoEstabelecimento.setText(estabelecimento.getBairro() + " " + estabelecimento.getCep() + ", " + estabelecimento.getCidade() + "-" + estabelecimento.getEstado());

        } catch (MalformedURLException e) {
            e.printStackTrace();
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
