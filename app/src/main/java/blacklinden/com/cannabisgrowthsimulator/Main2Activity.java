package blacklinden.com.cannabisgrowthsimulator;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;
import blacklinden.com.cannabisgrowthsimulator.serv.Constants;
import blacklinden.com.cannabisgrowthsimulator.serv.LService;
import blacklinden.com.cannabisgrowthsimulator.ui.CardItem;
import blacklinden.com.cannabisgrowthsimulator.ui.CardPagerAdapter;
import blacklinden.com.cannabisgrowthsimulator.ui.ShadowTransformer;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

    Intent service;
    private Button magv;
    private ViewPager mViewPager;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Mentés.getInstance(this);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        ((CheckBox) findViewById(R.id.checkBox)).setOnCheckedChangeListener(this);


        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1,R.drawable.cannabis));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_2,R.drawable.dpam));



        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        magv = findViewById(R.id.magv);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);

        service = new Intent(Main2Activity.this, LService.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //checkService();
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    public void mag(View v){
       checkService();
    }

    private void checkService(){
        Toast.makeText(this,isMyServiceRunning()+" ",Toast.LENGTH_SHORT).show();

        if(!isMyServiceRunning()) {
            Kender.getInstance().clear();
            mViewPager.setVisibility(View.VISIBLE);
            magv.setVisibility(View.GONE);
        }
        else {
            Toast.makeText(this,"theres a weed grow now",Toast.LENGTH_SHORT).show();
        }
    }


    public void startSim(View v){

        Intent i = new Intent(this,MainActivity.class);

        if(Kender.getInstance().getFajta()>0) {
            if (!isMyServiceRunning()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    service.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);

                    startForegroundService(service);
                } else {
                    service.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
                    //LService.IS_SERVICE_RUNNING = true;
                    startService(service);
                }
            }
            // i.putExtra("fajta",2);
            startActivity(i);

        }else
            Toast.makeText(this,"PICK A SEED",Toast.LENGTH_SHORT).show();
    }

    private boolean isMyServiceRunning() {
        ActivityManager manager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (LService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void setStrain(View v){
    Kender.getInstance().fajta(mViewPager.getCurrentItem()+1);
    Toast.makeText(this,""+(mViewPager.getCurrentItem()+1),Toast.LENGTH_SHORT).show();
    mViewPager.setVisibility(View.GONE);
    magv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mCardShadowTransformer.enableScaling(b);

    }

    public void menuActivity(View v){
        Intent ii = new Intent(Main2Activity.this,InventoryActivity.class);
        startActivity(ii);
    }
}
