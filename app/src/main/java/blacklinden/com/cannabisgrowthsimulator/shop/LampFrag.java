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
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.databinding.LampFragShopBinding;
import blacklinden.com.cannabisgrowthsimulator.pojo.Lampa;
import blacklinden.com.cannabisgrowthsimulator.sql.LampaVM;
import blacklinden.com.cannabisgrowthsimulator.sql.ScoreVM;

public class LampFrag extends Fragment {

    private String rank;
    private ScoreVM scoreVM;
    private final ArrayList<String> permanentItems = new ArrayList<>();
    private static final List<String> rawRankLst = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        //permanentItems.add("f");

        scoreVM = ViewModelProviders.of(LampFrag.this).get(ScoreVM.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LampFragShopBinding binding =
                DataBindingUtil.inflate(
                        inflater, R.layout.lamp_frag_shop, container, false);


        LampaVM lampaVM =
                ViewModelProviders.of(LampFrag.this).get(LampaVM.class);


        ShopItemAdapter recyclerViewAdapter =
                new ShopItemAdapter(new ArrayList<>(), item -> {
                    lampaVM.insert(new Lampa(item.getName()));
                    scoreVM.updateScore(item.getPrice());
                });

        binding.frag2recy.setLayoutManager(
                new LinearLayoutManager(getActivity()));

        binding.frag2recy.setAdapter(recyclerViewAdapter);


        scoreVM.get().observe(LampFrag.this,scoreEntity -> {
            if (scoreEntity != null) {
                rank = scoreEntity.getRank();
                recyclerViewAdapter.setScore(scoreEntity.getScore());
                Toast.makeText(this.getContext(),"src_lamp",Toast.LENGTH_SHORT).show();
            }
            });

        lampaVM.getAll().observe(
                LampFrag.this, lampas -> {
                    if (lampas != null) {
                        List<ShopItem> temp = new ArrayList<>();
                        Toast.makeText(this.getContext(),"lmp_lampfrag_obs",Toast.LENGTH_SHORT).show();
                        List<String> entityNames = new ArrayList<>();
                        for(Lampa l:lampas) entityNames.add(l.getFajta());
                        List<String> result = permanentItems.stream()
                                .filter(not(new HashSet<>(entityNames)::contains))
                                .collect(Collectors.toList());
                        for(String ms:result) {
                            switch (ms) {
                                case "a":
                                    temp.add(new ShopItem(ms, 10, R.drawable.avd_anim, false));
                                break;
                                case "b": temp.add(new ShopItem(ms, 20, R.drawable.ic_rrr, false));
                                break;
                                case "c":
                                    temp.add(new ShopItem(ms, 30, R.drawable.ic_light_bulb_technology_svgrepo_com,!(rawRankLst.indexOf(rank)>=2)));
                                break;
                                case "d":
                                    temp.add(new ShopItem(ms, 40, R.drawable.ic_ggg,!(rawRankLst.indexOf(rank)>=4)));
                                break;
                                case "e":
                                    temp.add(new ShopItem(ms, 50, R.drawable.ic_high_pressure_sodium_vapor_lamp,!(rawRankLst.indexOf(rank)>=3)));
                                break;

                            }

                        }
                        recyclerViewAdapter.setShopItems(temp);
                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                });



        return binding.getRoot();
    }

    private static <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }
}

