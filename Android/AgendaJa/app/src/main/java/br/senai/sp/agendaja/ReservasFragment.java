package br.senai.sp.agendaja;

import android.os.Bundle;
import androidx.annotation.NonNull;;
import androidx.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import br.senai.sp.agendaja.Pager.PagerAdapterCliente;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ReservasFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    private ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reservas, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        //((AppCompatActivity)(getActivity())).setSupportActionBar(toolbar);

        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Em aberto"));
        tabLayout.addTab(tabLayout.newTab().setText("Finalizados"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        viewPager = getActivity().findViewById(R.id.pager);
        PagerAdapterCliente pagerAdapterCliente = new PagerAdapterCliente(getActivity().getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapterCliente);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(this);


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
