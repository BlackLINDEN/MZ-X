package blacklinden.com.cannabisgrowthsimulator;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import blacklinden.com.cannabisgrowthsimulator.shop.LampFrag;
import blacklinden.com.cannabisgrowthsimulator.shop.NutriSoilFrag;
import blacklinden.com.cannabisgrowthsimulator.shop.SeedFrag;
import blacklinden.com.cannabisgrowthsimulator.sql.ScoreVM;

public class ShopActivity extends FragmentActivity {

    private static final int NUM_PAGES = 3;

    private ViewPager pager;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        TextView coins = findViewById(R.id.coins);
        TextView rank = findViewById(R.id.rang);
        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(2);

        ScoreVM scoreVM = ViewModelProviders.of(this).get(ScoreVM.class);
        scoreVM.get().observe(this,scoreEntity -> {
            if (scoreEntity != null) {
                coins.setText(Integer.toString(scoreEntity.getScore()));
                rank.setText(scoreEntity.getRank());
            }
        });

        PagerAdapter pagerAdapter = new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            if(!isTaskRoot())
            super.onBackPressed();
            else{
                Intent i = new Intent(this,Main2Activity.class);
                startActivity(i);
                finish();
            }
        } else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }


    private static class MyAdapter extends FragmentStatePagerAdapter {
        private Fragment[] frags;
        MyAdapter(FragmentManager fm) {
            super(fm);
            frags = new Fragment[] {
                    new SeedFrag(),
                    new NutriSoilFrag(),
                    new LampFrag()
            };
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
           return frags[position];
        }
    }

}