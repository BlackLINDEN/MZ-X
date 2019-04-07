package blacklinden.com.cannabisgrowthsimulator.shop;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.databinding.NutriSoilFragShopBinding;
import blacklinden.com.cannabisgrowthsimulator.pojo.NutriEntity;
import blacklinden.com.cannabisgrowthsimulator.sql.NutriVM;
import blacklinden.com.cannabisgrowthsimulator.sql.ScoreVM;

public class NutriSoilFrag extends Fragment {

    private String rank;
    private ScoreVM scoreVM;
    private static List<String> rawRankLst;
    private final ArrayList<String> permanentItems = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rawRankLst = new ArrayList<>();
        rawRankLst.add("NOVICE");
        rawRankLst.add("CROOK");
        rawRankLst.add("APPRENTICE");
        rawRankLst.add("GARDENER");
        rawRankLst.add("MASTER");
        rawRankLst.add("DOCTOR");

        permanentItems.add("BioGrow");
        permanentItems.add("HorseShit");
        permanentItems.add("Ionic");
        permanentItems.add("Piranha");

        scoreVM = ViewModelProviders.of(NutriSoilFrag.this).get(ScoreVM.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       NutriSoilFragShopBinding binding =
                DataBindingUtil.inflate(
                        inflater, R.layout.nutri_soil_frag_shop, container, false);


        HashMap<String,Integer> nutrik = new HashMap<>();

        NutriVM nutriVM =
                ViewModelProviders.of(NutriSoilFrag.this).get(NutriVM.class);
        nutriVM.getAll().observe(NutriSoilFrag.this,nutriEntities -> {
            if(nutriEntities!=null){
                for(NutriEntity m:nutriEntities)
                    nutrik.put(m.getFajta(),m.getMennyi());
            }
        });

        ShopItemAdapter recyclerViewAdapter =
                new ShopItemAdapter(new ArrayList<>(), item -> {
                    if(nutrik.containsKey(item.getName()))
                        nutriVM.update(item.getName(),nutrik.get(item.getName())+15);
                    else
                        nutriVM.insert(new NutriEntity(item.getName(),15));
                    scoreVM.updateScore(item.getPrice());
                });

        binding.reci1.setLayoutManager(
                new LinearLayoutManager(getActivity()));

        binding.reci1.setAdapter(recyclerViewAdapter);


        scoreVM.get().observe(NutriSoilFrag.this,scoreEntity -> {
            if (scoreEntity != null) {
                rank=scoreEntity.getRank();
                recyclerViewAdapter.setScore(scoreEntity.getScore());

                List<ShopItem> temp = new ArrayList<>();


                for(String ms:permanentItems){
                    switch (ms) {
                        case "BioGrow":
                            temp.add(new ShopItem(ms, 10, R.drawable.tapszer2, false));
                            break;
                        case "HorseShit": temp.add(new ShopItem(ms, 20, R.drawable.tapszer3, !(rawRankLst.indexOf(rank)>=2)));
                            break;
                        case "Ionic":
                            temp.add(new ShopItem(ms, 30, R.drawable.tapszer4,!(rawRankLst.indexOf(rank)>=3)));

                            break;
                        case "Piranha":
                            temp.add(new ShopItem(ms, 40, R.drawable.tapszer6,!(rawRankLst.indexOf(rank)>=4)));

                            break;


                    }

                }


                recyclerViewAdapter.setShopItems(temp);
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });



        return binding.getRoot();
    }

}
