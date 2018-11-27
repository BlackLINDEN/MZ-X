package blacklinden.com.cannabisgrowthsimulator.ui;
import android.os.Bundle;
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

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.ui.medium.Elem;
import blacklinden.com.cannabisgrowthsimulator.ui.medium.ElemAdapter;

public class Lights extends Fragment {


    private RecyclerView recyclerView;
    private ElemAdapter adapter;
    private ArrayList<Elem> lampArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.lampa_frag, container, false);

        recyclerView = root.findViewById(R.id.recy);
        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(),1));

        lampArrayList = new ArrayList<>();
        adapter = new ElemAdapter(root.getContext(), lampArrayList);
        recyclerView.setAdapter(adapter);

        createListData();

        return root;
    }

    private void createListData() {

        Elem planet = new Elem("Feith Electric", "LED", 250, 3500, 13500,R.drawable.blue_led,R.drawable.feher_csova, Objects.requireNonNull(getContext()).getDrawable(R.drawable.ic_jjj));
        lampArrayList.add(planet);

        planet = new Elem("PRO STAR DUAL","LED", 200, 6600, 11200,R.drawable.fullspec_led,R.drawable.lila_csova,Objects.requireNonNull(getContext()).getDrawable(R.drawable.ic_rrr));
        lampArrayList.add(planet);

        planet = new Elem("CFL Grow","CFL", 150, 2700, 7850,R.drawable.cfl_yellow,R.drawable.httr_vill001,Objects.requireNonNull(getContext()).getDrawable(R.drawable.ic_light_bulb_technology_svgrepo_com));
        lampArrayList.add(planet);

        planet = new Elem("HPS Grow","HALOGEN",600,2500,10200,R.drawable.avd_anim,R.drawable.narancs_csova,Objects.requireNonNull(getContext()).getDrawable(R.drawable.hps));
        lampArrayList.add(planet);

        planet = new Elem("Desk Bulb","CFL",60,4700,1200,R.drawable.cflkek,R.drawable.feher_csova,Objects.requireNonNull(getContext()).getDrawable(R.drawable.ic_ggg));
        lampArrayList.add(planet);


        adapter.notifyDataSetChanged();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }

}