package blacklinden.com.cannabisgrowthsimulator.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.pojo.AccessoryEntity;
import blacklinden.com.cannabisgrowthsimulator.sql.AccVM;
import blacklinden.com.cannabisgrowthsimulator.ui.medium.Elem;
import blacklinden.com.cannabisgrowthsimulator.ui.medium.PotAdapter;

public class Accessories extends Fragment {

    private RecyclerView recyclerView;
    private PotAdapter adapter;
    private ArrayList<Elem> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.kieg_frag, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(),1));

        arrayList = new ArrayList<>();
        adapter = new PotAdapter(root.getContext(), arrayList);
        recyclerView.setAdapter(adapter);
        AccVM accVM = ViewModelProviders.of(this).get(AccVM.class);
        accVM.getAll().observe(this,this::createLiveData);



        return root;
    }

    private void createLiveData(List<AccessoryEntity> list){
        for(AccessoryEntity l:list){

            switch (l.getFajta()){
                case "a":
                    arrayList.add(new Elem(l.getFajta(), 800,"Slow","Specialized",R.drawable.black_pot,
                            Objects.requireNonNull(getContext()).getDrawable(R.drawable.cserep10)));
                    break;

                case "b":
                    arrayList.add(new Elem(l.getFajta(), 800,"Slow","Specialized",R.drawable.cserep10,
                            Objects.requireNonNull(getContext()).getDrawable(R.drawable.cserep10)));
                    break;

                case "c":
                    arrayList.add(new Elem(l.getFajta(), 600,"Fast","Rare",R.drawable.kanna5,
                            Objects.requireNonNull(getContext()).getDrawable(R.drawable.kanna3)));
                    break;

                case "d":
                    arrayList.add(new Elem(l.getFajta(), 600,"Fast","Rare",R.drawable.kanna5,
                            Objects.requireNonNull(getContext()).getDrawable(R.drawable.kanna2)));
                    break;

                case "e":
                    arrayList.add(new Elem(l.getFajta(), 600,"Fast","Rare",R.drawable.kanna5,
                            Objects.requireNonNull(getContext()).getDrawable(R.drawable.kanna5)));

                    break;
            }
        }
        adapter.notifyDataSetChanged();
    }

    /*private void createListData() {
        Elem planet = new Elem("Stiegl", 8,"Slow","Specialized", getResources().getDrawable(R.drawable.cserep10));
        arrayList.add(planet);

        planet = new Elem("Quadra Pot", 11.2f,"Fast","Common", getResources().getDrawable(R.drawable.black_pot));
        arrayList.add(planet);

        planet = new Elem("Molded Rolled Rim Pot", 10,"Steady","Unique", getResources().getDrawable(R.drawable.molded_rolled_rim_pot));
        arrayList.add(planet);

        planet = new Elem("Plain Ceramic Pot", 12,"Steady","Common", getResources().getDrawable(R.drawable.plain_ceramic_pot));
        arrayList.add(planet);

        planet = new Elem("Air Pot", 7.5f,"Very Fast","Specialized", getResources().getDrawable(R.drawable.air_pot));
        arrayList.add(planet);

        planet = new Elem("Dodeca Pot", 10f,"Slow","Unique", getResources().getDrawable(R.drawable.dodeca_pot));
        arrayList.add(planet);

        planet = new Elem("Cast Bronze Pot", 12f,"Steady","Rare", getResources().getDrawable(R.drawable.cserep6));
        arrayList.add(planet);

        planet = new Elem("Blumentopf", 7f,"Steady","Common", getResources().getDrawable(R.drawable.cserep8));
        arrayList.add(planet);

        planet = new Elem("Seghettata", 12f,"Steady","Common", getResources().getDrawable(R.drawable.cserep9));
        arrayList.add(planet);

        planet = new Elem("Concentric Geranium", 12f,"Fast","Common", getResources().getDrawable(R.drawable.cserep7));
        arrayList.add(planet);

        //kanna
        planet = new Elem("Hipster Pipe", 4f,"Steady","Specialized", getResources().getDrawable(R.drawable.kanna1));
        arrayList.add(planet);

        planet = new Elem("Army Can", 6f,"Fast","Common", getResources().getDrawable(R.drawable.kanna2));
        arrayList.add(planet);

        planet = new Elem("Classic Green", 6f,"Fast","Vintage", getResources().getDrawable(R.drawable.kanna3));
        arrayList.add(planet);

        planet = new Elem("Tinman", 5,"Fast","Unique", getResources().getDrawable(R.drawable.kanna4));
        arrayList.add(planet);

        planet = new Elem("FineCan", 6,"Fast","Rare", getResources().getDrawable(R.drawable.kanna5));
        arrayList.add(planet);

        planet = new Elem("Mini", 0.8f,"Fast","Common", getResources().getDrawable(R.drawable.kanna6));
        arrayList.add(planet);
        adapter.notifyDataSetChanged();
    }*/


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }

}
