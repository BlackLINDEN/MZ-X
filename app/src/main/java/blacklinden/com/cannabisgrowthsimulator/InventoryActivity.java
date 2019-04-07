package blacklinden.com.cannabisgrowthsimulator;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import androidx.annotation.Nullable;
import blacklinden.com.cannabisgrowthsimulator.pojo.Lampa;
import blacklinden.com.cannabisgrowthsimulator.pojo.Vegtermek;
import blacklinden.com.cannabisgrowthsimulator.sql.LampaVM;
import blacklinden.com.cannabisgrowthsimulator.sql.VegtermekViewModel;
import blacklinden.com.cannabisgrowthsimulator.ui.FragAdapter;

public class InventoryActivity extends FragmentActivity {

ViewPager vp;
TabLayout tl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
    vp = findViewById(R.id.viewPager);
    vp.setAdapter(new FragAdapter(getSupportFragmentManager()));

    tl = findViewById(R.id.tablayout);
    tl.setupWithViewPager(vp);


    }

    @Override
    public void onBackPressed() {
       finish();
    }

    public void backBack(View v){
        finish();
    }


}