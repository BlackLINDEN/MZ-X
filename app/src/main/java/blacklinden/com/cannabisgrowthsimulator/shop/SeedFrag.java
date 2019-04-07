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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.databinding.SeedFragShopBinding;
import blacklinden.com.cannabisgrowthsimulator.pojo.MagEntity;
import blacklinden.com.cannabisgrowthsimulator.sql.LampaVM;
import blacklinden.com.cannabisgrowthsimulator.sql.MagVM;
import blacklinden.com.cannabisgrowthsimulator.sql.ScoreVM;

public class SeedFrag extends Fragment {
    int fragVal;
    private String rank;
    private ScoreVM scoreVM;
    private static List<String> rawRankLst;
    private final ArrayList<String> permanentItems = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;
        rawRankLst = new ArrayList<>();
        rawRankLst.add("NOVICE");
        rawRankLst.add("CROOK");
        rawRankLst.add("APPRENTICE");
        rawRankLst.add("GARDENER");
        rawRankLst.add("MASTER");
        rawRankLst.add("DOCTOR");

        permanentItems.add("a");
        permanentItems.add("b");
        permanentItems.add("c");
        permanentItems.add("d");
        permanentItems.add("e");
        permanentItems.add("f");

        scoreVM = ViewModelProviders.of(SeedFrag.this).get(ScoreVM.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SeedFragShopBinding binding =
                DataBindingUtil.inflate(
                        inflater, R.layout.seed_frag_shop, container, false);


        HashMap<String,Integer> magvak = new HashMap<>();

        MagVM magVM =
                ViewModelProviders.of(SeedFrag.this).get(MagVM.class);
        magVM.getAll().observe(SeedFrag.this,magEntities -> {
            if(magEntities!=null){
                for(MagEntity m:magEntities)
                    magvak.put(m.getFajta(),m.getMennyi());
            }
        });

        ShopItemAdapter recyclerViewAdapter =
                new ShopItemAdapter(new ArrayList<>(), item -> {
                    if(magvak.containsKey(item.getName()))
                    magVM.update(item.getName(),magvak.get(item.getName())+3);
                    else
                    magVM.insert(new MagEntity(item.getName(),3));
                    scoreVM.updateScore(item.getPrice());
                });

        binding.frag1recy.setLayoutManager(
                new LinearLayoutManager(getActivity()));

        binding.frag1recy.setAdapter(recyclerViewAdapter);


        scoreVM.get().observe(SeedFrag.this,scoreEntity -> {
            if (scoreEntity != null) {
                rank=scoreEntity.getRank();
                recyclerViewAdapter.setScore(scoreEntity.getScore());

                        List<ShopItem> temp = new ArrayList<>();
                        Toast.makeText(this.getContext(),"scr_seedfrag_obs",Toast.LENGTH_SHORT).show();

                        for(String ms:permanentItems){
                            switch (ms) {
                                case "a":
                                    temp.add(new ShopItem(ms, 10, R.drawable.ic_magvak, false));
                                    break;
                                case "b": temp.add(new ShopItem(ms, 20, R.drawable.ic_magvak, false));
                                    break;
                                case "c":
                                        temp.add(new ShopItem(ms, 30, R.drawable.ic_magvak,!(rawRankLst.indexOf(rank)>=2)));

                                    break;
                                case "d":
                                        temp.add(new ShopItem(ms, 40, R.drawable.ic_magvak,!(rawRankLst.indexOf(rank)>=3)));

                                    break;
                                case "e":
                                        temp.add(new ShopItem(ms, 50, R.drawable.ic_magvak,!(rawRankLst.indexOf(rank)>=4)));
                                    break;
                                case "f":
                                        temp.add(new ShopItem(ms, 60, R.drawable.ic_magvak,!(rawRankLst.indexOf(rank)>=5)));
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