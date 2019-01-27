package blacklinden.com.cannabisgrowthsimulator;


import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import android.widget.ImageView;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.plattysoft.leonids.ParticleSystem;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;
import blacklinden.com.cannabisgrowthsimulator.pojo.Lamps;
import blacklinden.com.cannabisgrowthsimulator.pojo.Stash;
import blacklinden.com.cannabisgrowthsimulator.pojo.Termény;
import blacklinden.com.cannabisgrowthsimulator.serv.Constants;
import blacklinden.com.cannabisgrowthsimulator.serv.LService;
import blacklinden.com.cannabisgrowthsimulator.ui.CardItem;
import blacklinden.com.cannabisgrowthsimulator.ui.CardItem2;
import blacklinden.com.cannabisgrowthsimulator.ui.CardPagerAdapter;

import blacklinden.com.cannabisgrowthsimulator.ui.CardPagerAdapter2;
import blacklinden.com.cannabisgrowthsimulator.ui.ShadowTransformer;
import blacklinden.com.cannabisgrowthsimulator.ui.kolibri.Kolibri;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

    Intent service;
    private Button magv;
    private ViewPager mViewPager;
    private AnimationDrawable kolibri;
    private CardPagerAdapter mCardAdapter;
    private CardPagerAdapter2 mCardAdapter2;
    private ShadowTransformer mCardShadowTransformer;
    private Kolibri kolibriAnimator;
    private ImageView kolibriTV;
    public static final String[] sss = {"B1","B2","B3","B4","B5","B6"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mentés.getInstance(this);


        if(Mentés.getInstance().getString(Mentés.Key.TRMS_LST,"o").equals("o")) {
            List<Termény> trmny = new ArrayList<>();
            String trm = Mentés.getInstance().gsonra(trmny);
            Mentés.getInstance().put(Mentés.Key.TRMS_LST,trm);
        }

        if(Mentés.getInstance().getString(Mentés.Key.ERllT_LST,"o").equals("o")) {
            List<Termény> trmny = new ArrayList<>();
            String trm = Mentés.getInstance().gsonra(trmny);
            Mentés.getInstance().put(Mentés.Key.ERllT_LST,trm);
        }

        if(Mentés.getInstance().getString(Mentés.Key.VGTRMK_LST,"o").equals("o")) {
            List<Stash> trmny = new ArrayList<>();
            String trm = Mentés.getInstance().gsonra(trmny);
            Mentés.getInstance().put(Mentés.Key.VGTRMK_LST,trm);
        }

        if(Mentés.getInstance().getString(Mentés.Key.TESZT_OBJ,"o").equals("o")){
            Lamps lamps = new Lamps("HPS Grow","HALOGEN",600,2500,10200,
                    R.drawable.avd_anim,R.drawable.narancs_csova);
            System.out.println("lamps");
            String s = Mentés.getInstance().gsonra(lamps);
            System.out.println("gsonra");
            Mentés.getInstance().put(Mentés.Key.TESZT_OBJ,s);
            System.out.println("mentés");
        }

        if(Mentés.getInstance().getInt(Mentés.Key.B6,0)==0) {
          Mentés.getInstance().put(Mentés.Key.B6,1);

        }

        for (String ss : sss) {
            Mentés.getInstance().put(Mentés.Key.valueOf(ss), 1);
        }


        Mentés.getInstance().put(Mentés.Key.SAMPLE_ZSETON,100);


        setContentView(R.layout.activity_main2);


        kolibriTV = findViewById(R.id.kolibri);
        kolibriTV.setBackgroundResource(R.drawable.kolibri_anim);
        kolibri = (AnimationDrawable) kolibriTV.getBackground();
        kolibri.start();
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        final float w = point.x;
        kolibriAnimator = new Kolibri(w,kolibriTV);
        if(Mentés.getInstance().getString(Mentés.Key.BELEP,"0").equals("0"))
            kolibriAnimator.setTutorial_e(true);
        kolibriAnimator.run();

        mViewPager = findViewById(R.id.viewPager);

        ((CheckBox) findViewById(R.id.checkBox)).setOnCheckedChangeListener(this);


        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, "15-17%","150gr/m2","11 weeks","Auto Hybrid","Feminised",R.drawable.gambi24));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, "17-20%","140gr/m2","14 weeks","Auto Hybrid","Feminised",R.drawable.dpam));
        mCardAdapter.addCardItem(new CardItem(R.string.shit,"8-12%","200gr/m2","13 weeks","Auto Crap","Feminised",R.drawable.budbud));
        mCardAdapter.addCardItem(new CardItem(R.string.blueb,"12-18%","200gr/m2","13 weeks","Auto Crap","Feminised",R.drawable.budbud));
        mCardAdapter.addCardItem(new CardItem(R.string.title_northernlight,"13%","170gr/m2","15 weeks","Auto Indica","Feminised",R.drawable.budbud));
        mCardAdapter.addCardItem(new CardItem(R.string.grapeape,"14%","180gr/m2","15 weeks","Auto Indica","Feminised",R.drawable.budbud));

        mCardAdapter2 = new CardPagerAdapter2();
        mCardAdapter2.addCardItem(new CardItem2(R.string.boxtitle,"textile",R.drawable.old_box));
        mCardAdapter2.addCardItem(new CardItem2(R.string.boxtitle_1,"textile",R.drawable.old_box));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);

        mViewPager.setOffscreenPageLimit(3);
        magv = findViewById(R.id.magv);



        mViewPager.setPageTransformer(false, mCardShadowTransformer);

        mViewPager.setCurrentItem(3);



        final ImageView v = findViewById(R.id.imageView);
        ViewTreeObserver vto = v.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                v.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                new ParticleSystem(Main2Activity.this, 10, R.drawable.kenderii, 6000)
                        .setFadeOut(5000)
                        .setRotationSpeed(50)
                        .setSpeedModuleAndAngleRange(0.1f,0.5f,0,365)
                        .oneShot(v,50);

            }
        });




        service = new Intent(Main2Activity.this, LService.class);


    }

    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    protected void onStart() {
        super.onStart();


    }


    public void mutiTermest(View v){
        if(!Mentés.getInstance().getString(Mentés.Key.TRMS_LST,"o").equals("o")) {
            String s = Mentés.getInstance().getString(Mentés.Key.TRMS_LST);
            Gson gson = new Gson();
            Type tList = new TypeToken<ArrayList<Termény>>(){}.getType();
            List<Termény> termenyList = gson.fromJson(Mentés.getInstance().getString(Mentés.Key.TRMS_LST),tList);
            if(termenyList.isEmpty())
            System.out.println("----------------ÜRES------------");
            else
                for(Termény t: termenyList) {

                    System.out.println(".>>>>>>>>TERMENY<<<<<<<<. "+t.getSuly()+" "+t.getFajta());
                }
        }
        }



    public void tutorialEvent(View v){
        kolibriAnimator.setTutorial_e(true);
        kolibriAnimator.flyTo(findViewById(R.id.start));

    }


    public void szarito(View v){
        Intent i = new Intent(this, StashActivity.class);
        startActivity(i);
    }



    public void mag(View v){
        if(!LService.IS_SERVICE_RUNNING) {
            mViewPager.setAdapter(mCardAdapter);

            findViewById(R.id.start).setVisibility(View.GONE);
            mViewPager.setVisibility(View.VISIBLE);
            magv.setVisibility(View.GONE);
            findViewById(R.id.box).setVisibility(View.GONE);
            kolibriAnimator.flyTo(mViewPager);
            //Kender.getInstance().clear();
        }
        else {
            Toast.makeText(this,"Operation In-Progress",Toast.LENGTH_SHORT).show();
            kolibriAnimator.flyTo(magv);
        }
    }




    public void startSim(View v){

        Intent i = new Intent(this,MainActivity.class);

        if(Kender.getInstance().getFajta()>0) {
            if (!LService.IS_SERVICE_RUNNING) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    service.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
                    startForegroundService(service);
                    startActivity(i);
                } else {
                    service.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
                    //LService.IS_SERVICE_RUNNING = true;
                    startService(service);
                    startActivity(i);
                }

            }else if(isTaskRoot()) startActivity(i); else finish();


        }else {
            kolibriAnimator.flyTo(findViewById(R.id.magv));

        }
    }



    public void setStrain(View v) {
        if(Mentés.getInstance().getInt(Mentés.Key.valueOf(sss[mViewPager.getCurrentItem()]),0)>0){
        Kender.getInstance();
        Kender.getInstance().fajta(mViewPager.getCurrentItem() + 1);
        Toast.makeText(this, "" + (mViewPager.getCurrentItem() + 1), Toast.LENGTH_SHORT).show();
        kolibriAnimator.flyTo(findViewById(R.id.shop));
    }else kolibriAnimator.flyTo(findViewById(R.id.seedsLeft),"You don't have this seed.");
        
    mViewPager.setVisibility(View.GONE);
    magv.setVisibility(View.VISIBLE);
    findViewById(R.id.box).setVisibility(View.VISIBLE);
    findViewById(R.id.start).setVisibility(View.VISIBLE);


    /*int i = Mentés.getInstance().getInt(Mentés.Key.SAMPLE_ZSETON,0)-1;
    Mentés.getInstance().put(Mentés.Key.SAMPLE_ZSETON,i);
       */

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

    public void stopTut(View v){
        kolibriAnimator.setTutorial_e(false);
        kolibriAnimator.run();
    }

    public void nyissBox(View v){

        if(!LService.IS_SERVICE_RUNNING) {
            mViewPager.setAdapter(mCardAdapter2);
            findViewById(R.id.start).setVisibility(View.GONE);
            mViewPager.setVisibility(View.VISIBLE);
            magv.setVisibility(View.GONE);
            findViewById(R.id.box).setVisibility(View.GONE);
            kolibriAnimator.flyTo(mViewPager);
            //Kender.getInstance().clear();
        }
        else {
            Toast.makeText(this,"Operation In-Progress",Toast.LENGTH_SHORT).show();
            kolibriAnimator.flyTo(findViewById(R.id.box),true);
        }
    }

    public void setBox(View v){
        Kender.getInstance().setBox(mViewPager.getCurrentItem()+1);
        Toast.makeText(this,""+(mViewPager.getCurrentItem()+1),Toast.LENGTH_SHORT).show();
        mViewPager.setVisibility(View.GONE);
        magv.setVisibility(View.VISIBLE);
        findViewById(R.id.box).setVisibility(View.VISIBLE);
        findViewById(R.id.start).setVisibility(View.VISIBLE);
        kolibriAnimator.flyTo(findViewById(R.id.shop));
    }
}
