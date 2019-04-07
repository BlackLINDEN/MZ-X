package blacklinden.com.cannabisgrowthsimulator.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import blacklinden.com.cannabisgrowthsimulator.pojo.NutriEntity;
import blacklinden.com.cannabisgrowthsimulator.sql.NutriVM;
import blacklinden.com.cannabisgrowthsimulator.ui.medium.ElemAdapter;
import blacklinden.com.cannabisgrowthsimulator.ui.medium.FertilAdapter;
import blacklinden.com.cannabisgrowthsimulator.ui.medium.Fertilizer;

public class Nutrition extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Fertilizer> fertilizers;
    private FertilAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.nutri_frag, container, false);

        recyclerView = root.findViewById(R.id.recikl);
        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(),1));

        fertilizers = new ArrayList<>();
        adapter = new FertilAdapter(root.getContext(), fertilizers);
        recyclerView.setAdapter(adapter);

        NutriVM nutriVM = ViewModelProviders.of(this).get(NutriVM.class);
        nutriVM.getAll().observe(this,this::createLiveData);


        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {


    }

    private void createLiveData(List<NutriEntity> nutrik){
        for(NutriEntity n:nutrik) {
            switch (n.getFajta()){
                case "BioGrow":
                    fertilizers.add( new Fertilizer(n.getFajta(),R.drawable.tapszer2,Objects.requireNonNull(getContext()).getDrawable(R.drawable.tapszer2),6,0,1));

                    break;

                case "HorseShit":
                    fertilizers.add( new Fertilizer(n.getFajta(),R.drawable.tapszer3,Objects.requireNonNull(getContext()).getDrawable(R.drawable.tapszer3),4,2,4));

                    break;

                case "Ionic":

                    fertilizers.add( new Fertilizer(n.getFajta(),R.drawable.tapszer4,Objects.requireNonNull(getContext()).getDrawable(R.drawable.tapszer4),2,5,2));

                    break;

                case "Piranha":
                    fertilizers.add( new Fertilizer(n.getFajta(),R.drawable.tapszer6,Objects.requireNonNull(getContext()).getDrawable(R.drawable.tapszer6),1,8,1));

                    break;
            }
        }

        adapter.notifyDataSetChanged();


    }

    /*private void createListData(){
        Fertilizer fertilizer = new Fertilizer("BioGrow",getResources().getDrawable(R.drawable.tapszer2),6,0,0);
        fertilizers.add(fertilizer);

        fertilizer = new Fertilizer("Ionic Bloom",getResources().getDrawable(R.drawable.tapszer4),2,6,2);
        fertilizers.add(fertilizer);

        fertilizer = new Fertilizer("Piranha Grow",getResources().getDrawable(R.drawable.tapszer6),0,8,1);
        fertilizers.add(fertilizer);

        fertilizer = new Fertilizer("FloraGrow Flowering",getResources().getDrawable(R.drawable.nutri_flower),0,5,4);
        fertilizers.add(fertilizer);

        adapter.notifyDataSetChanged();

    }*/

}
