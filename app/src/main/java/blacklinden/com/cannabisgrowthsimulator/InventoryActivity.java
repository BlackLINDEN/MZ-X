package blacklinden.com.cannabisgrowthsimulator;

import android.media.MediaPlayer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import blacklinden.com.cannabisgrowthsimulator.ui.FragAdapter;

public class InventoryActivity extends FragmentActivity {

ViewPager vp;
TabLayout tl;
MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        vp = findViewById(R.id.viewPager);
        vp.setAdapter(new FragAdapter(getSupportFragmentManager()));
        tl = findViewById(R.id.tablayout);
        tl.setupWithViewPager(vp);
        mp = MediaPlayer.create(this,R.raw.shophang);
    }

    @Override
    public void onBackPressed() {
       finish();
    }

    public void backBack(View v){
        finish();
    }

    public void playSound(){ mp.start();}


}