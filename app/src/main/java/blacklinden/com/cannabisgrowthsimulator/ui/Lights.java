package blacklinden.com.cannabisgrowthsimulator.ui;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.pojo.Lampa;
import blacklinden.com.cannabisgrowthsimulator.sql.LampaVM;
import blacklinden.com.cannabisgrowthsimulator.ui.medium.Elem;
import blacklinden.com.cannabisgrowthsimulator.ui.medium.ElemAdapter;

public class Lights extends Fragment {


    private RecyclerView recyclerView;
    private ElemAdapter adapter;
    private ArrayList<Elem> lampArrayList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.lampa_frag, container, false);

        recyclerView = root.findViewById(R.id.recy);
        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(),1));

        lampArrayList = new ArrayList<>();
        adapter = new ElemAdapter(root.getContext(), lampArrayList);
        recyclerView.setAdapter(adapter);

        //observer interface helyett
        LampaVM lampaVM = ViewModelProviders.of(this).get(LampaVM.class);
        lampaVM.getAll().observe(this, this::createLiveData);


        //createListData();

        return root;
    }

    private void createLiveData(List<Lampa> lampas){
        for(Lampa l:lampas){

            switch (l.getFajta()){
                case "a":
                    lampArrayList.add(new Elem("Feith Electric", "LED", 250, 3500, 13500,
                            R.drawable.blue_led,R.drawable.feher_csova, Objects.requireNonNull(getContext()).getDrawable(R.drawable.ic_jjj)
                    ));
                    break;

                case "b":
                    lampArrayList.add(new Elem("PRO STAR DUAL","LED", 200, 6600, 11200,
                            R.drawable.fullspec_led,R.drawable.lila_csova,Objects.requireNonNull(getContext()).getDrawable(R.drawable.ic_rrr)
                   ));

                    break;

                case "c":
                    lampArrayList.add(new Elem("CFL Grow","CFL", 150, 2700, 7850,
                            R.drawable.cfl_yellow,R.drawable.httr_vill001,Objects.requireNonNull(getContext()).getDrawable(R.drawable.ic_light_bulb_technology_svgrepo_com)
                    ));
                    break;

                case "d":

                    lampArrayList.add(new Elem("HPS Grow","HALOGEN",600,2500,10200,
                            R.drawable.avd_anim,R.drawable.narancs_csova,Objects.requireNonNull(getContext()).getDrawable(R.drawable.hps)
                    ));
                    break;

                case "e":
                    lampArrayList.add(new Elem("Desk Bulb","CFL",60,4700,1200,
                            R.drawable.cflkek,R.drawable.feher_csova,Objects.requireNonNull(getContext()).getDrawable(R.drawable.ic_ggg)
                    ));
                    break;
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void createListData() {

        Elem lampa = new Elem("Feith Electric", "LED", 250, 3500, 13500,
                R.drawable.blue_led,R.drawable.feher_csova, Objects.requireNonNull(getContext()).getDrawable(R.drawable.ic_jjj));
        lampArrayList.add(lampa);

        lampa = new Elem("PRO STAR DUAL","LED", 200, 6600, 11200,
                R.drawable.fullspec_led,R.drawable.lila_csova,Objects.requireNonNull(getContext()).getDrawable(R.drawable.ic_rrr));
        lampArrayList.add(lampa);

        lampa = new Elem("CFL Grow","CFL", 150, 2700, 7850,
                R.drawable.cfl_yellow,R.drawable.httr_vill001,Objects.requireNonNull(getContext()).getDrawable(R.drawable.ic_light_bulb_technology_svgrepo_com));
        lampArrayList.add(lampa);

        lampa = new Elem("HPS Grow","HALOGEN",600,2500,10200,
                R.drawable.avd_anim,R.drawable.narancs_csova,Objects.requireNonNull(getContext()).getDrawable(R.drawable.hps));
        lampArrayList.add(lampa);

        lampa = new Elem("Desk Bulb","CFL",60,4700,1200,
                R.drawable.cflkek,R.drawable.feher_csova,Objects.requireNonNull(getContext()).getDrawable(R.drawable.ic_ggg));
        lampArrayList.add(lampa);


        adapter.notifyDataSetChanged();
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {


    }

}