package blacklinden.com.cannabisgrowthsimulator;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import blacklinden.com.cannabisgrowthsimulator.shop.AccFrag;
import blacklinden.com.cannabisgrowthsimulator.shop.LampFrag;
import blacklinden.com.cannabisgrowthsimulator.shop.NutriSoilFrag;
import blacklinden.com.cannabisgrowthsimulator.shop.SeedFrag;
import blacklinden.com.cannabisgrowthsimulator.shop.SkinFrag;
import blacklinden.com.cannabisgrowthsimulator.sql.ScoreVM;

public class ShopActivity extends FragmentActivity {

    private static final int NUM_PAGES = 5;

    private ViewPager pager;
    private MediaPlayer mp;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        TextView coins = findViewById(R.id.coins);
        TextView rank = findViewById(R.id.rang);
        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(4);

        mp = MediaPlayer.create(this,R.raw.kasszahang);

        ScoreVM scoreVM = ViewModelProviders.of(this).get(ScoreVM.class);
        scoreVM.get().observe(this,scoreEntity -> {
            if (scoreEntity != null) {
                coins.setText(Integer.toString(scoreEntity.getScore()));
                rank.setText(scoreEntity.getRank());
            }
        });

        final ImageView[] vpTmb = {
                findViewById(R.id.vp1),
                findViewById(R.id.vp2),
                findViewById(R.id.vp3),
                findViewById(R.id.vp4),
                findViewById(R.id.vp5)
        };
        vpTmb[0].setColorFilter(Color.BLUE,PorterDuff.Mode.XOR);
        PagerAdapter pagerAdapter = new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<vpTmb.length;i++) {
                    if (i!=position) vpTmb[i].clearColorFilter();
                    else vpTmb[position].setColorFilter(Color.BLUE,PorterDuff.Mode.XOR);;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onBackPressed() {
            if(!isTaskRoot())
            super.onBackPressed();
            else{
                Intent i = new Intent(this,Main2Activity.class);
                startActivity(i);
                finish();
            }
    }

    public void playSound(){
        mp.start();
    }

    public void vpScroll(View view) {
       pager.setCurrentItem(Integer.parseInt((String)view.getTag()),true);
    }


    private static class MyAdapter extends FragmentStatePagerAdapter {
        private Fragment[] frags;
        MyAdapter(FragmentManager fm) {
            super(fm);
            frags = new Fragment[] {
                    new SeedFrag(),
                    new NutriSoilFrag(),
                    new LampFrag(),
                    new AccFrag(),
                    new SkinFrag()

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