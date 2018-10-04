package blacklinden.com.cannabisgrowthsimulator.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.ui.medium.Elem;
import blacklinden.com.cannabisgrowthsimulator.ui.medium.ElemAdapter;
import blacklinden.com.cannabisgrowthsimulator.ui.medium.PotAdapter;

public class Accessories extends Fragment {

    private RecyclerView recyclerView;
    private PotAdapter adapter;
    private ArrayList<Elem> planetArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.kieg_frag, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(),1));

        planetArrayList = new ArrayList<>();
        adapter = new PotAdapter(root.getContext(), planetArrayList);
        recyclerView.setAdapter(adapter);

        createListData();


        return root;
    }

    private void createListData() {
        Elem planet = new Elem("Vintage Geranium", 8,"Slow","Rare", getResources().getDrawable(R.drawable.cserep_old2));
        planetArrayList.add(planet);

        planet = new Elem("Geranium Pot", 8,"Slow","Common", getResources().getDrawable(R.drawable.cserep4));
        planetArrayList.add(planet);

        planet = new Elem("Quadra Pot", 11.2f,"Fast","Common", getResources().getDrawable(R.drawable.black_pot));
        planetArrayList.add(planet);

        planet = new Elem("Molded Rolled Rim Pot", 10,"Steady","Unique", getResources().getDrawable(R.drawable.molded_rolled_rim_pot));
        planetArrayList.add(planet);

        planet = new Elem("Plain Ceramic Pot", 12,"Steady","Common", getResources().getDrawable(R.drawable.plain_ceramic_pot));
        planetArrayList.add(planet);

        planet = new Elem("Air Pot", 7.5f,"Very Fast","Specialized", getResources().getDrawable(R.drawable.air_pot));
        planetArrayList.add(planet);

        planet = new Elem("Dodeca Pot", 10f,"Slow","Unique", getResources().getDrawable(R.drawable.dodeca_pot));
        planetArrayList.add(planet);

        planet = new Elem("Cast Bronze Pot", 12f,"Steady","Rare", getResources().getDrawable(R.drawable.cserep6));
        planetArrayList.add(planet);

        planet = new Elem("Top Hat", 7f,"Steady","Common", getResources().getDrawable(R.drawable.cserep5));
        planetArrayList.add(planet);

        planet = new Elem("Dark Ceramic", 12f,"Steady","Common", getResources().getDrawable(R.drawable.cserep3));
        planetArrayList.add(planet);

        planet = new Elem("Concentric Geranium", 12f,"Fast","Common", getResources().getDrawable(R.drawable.cserep7));
        planetArrayList.add(planet);

        //kanna
        planet = new Elem("Hipster Pipe", 4f,"Steady","Specialized", getResources().getDrawable(R.drawable.kanna1));
        planetArrayList.add(planet);

        planet = new Elem("Army Can", 6f,"Fast","Common", getResources().getDrawable(R.drawable.kanna2));
        planetArrayList.add(planet);

        planet = new Elem("Classic Green", 6f,"Fast","Vintage", getResources().getDrawable(R.drawable.kanna3));
        planetArrayList.add(planet);

        planet = new Elem("Tinman", 5,"Fast","Unique", getResources().getDrawable(R.drawable.kanna4));
        planetArrayList.add(planet);

        planet = new Elem("Fine Garden Can", 6,"Fast","Rare", getResources().getDrawable(R.drawable.kanna5));
        planetArrayList.add(planet);

        planet = new Elem("Millenial Mini", 0.8f,"Fast","Common", getResources().getDrawable(R.drawable.kanna6));
        planetArrayList.add(planet);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }

}
