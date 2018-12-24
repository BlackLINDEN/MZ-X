package blacklinden.com.cannabisgrowthsimulator.ui;

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

import blacklinden.com.cannabisgrowthsimulator.R;
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

        createListData();

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {


    }

    private void createListData(){
        Fertilizer fertilizer = new Fertilizer("BioGrow",getResources().getDrawable(R.drawable.tapszer2),4,3,6);
        fertilizers.add(fertilizer);

        fertilizer = new Fertilizer("Ionic Bloom",getResources().getDrawable(R.drawable.tapszer4),2,6,2);
        fertilizers.add(fertilizer);

        fertilizer = new Fertilizer("Piranha Grow",getResources().getDrawable(R.drawable.tapszer6),0,8,1);
        fertilizers.add(fertilizer);

        fertilizer = new Fertilizer("FloraGrow Flowering",getResources().getDrawable(R.drawable.nutri_flower),0,5,4);
        fertilizers.add(fertilizer);

        adapter.notifyDataSetChanged();

    }

}
