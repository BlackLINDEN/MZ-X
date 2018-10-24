package blacklinden.com.cannabisgrowthsimulator;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.provider.Telephony;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.plattysoft.leonids.ParticleSystem;
import com.takusemba.spotlight.OnSpotlightStateChangedListener;
import com.takusemba.spotlight.OnTargetStateChangedListener;
import com.takusemba.spotlight.Spotlight;
import com.takusemba.spotlight.shape.Circle;
import com.takusemba.spotlight.target.SimpleTarget;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;
import blacklinden.com.cannabisgrowthsimulator.pojo.Lamps;
import blacklinden.com.cannabisgrowthsimulator.pojo.Termény;
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
        Mentés.getInstance(this);
        if(Mentés.getInstance().getString(Mentés.Key.TRMS_LST,"o").equals("o")) {
            List<Termény> trmny = new ArrayList<>();
            String trm = Mentés.getInstance().gsonra(trmny);
            Mentés.getInstance().put(Mentés.Key.TRMS_LST,trm);
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
        setContentView(R.layout.activity_main2);
        Mentés.getInstance(this);





        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        ((CheckBox) findViewById(R.id.checkBox)).setOnCheckedChangeListener(this);


        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, "15-17%","150gr/m2","11 weeks","Auto Hybrid","Feminised",R.drawable.cannabis));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, "17-20%","140gr/m2","14 weeks","Auto Hybrid","Feminised",R.drawable.dpam));



        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        magv = findViewById(R.id.magv);


        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);

        mViewPager.setCurrentItem(2);



        final ImageView v = findViewById(R.id.imageView);
        ViewTreeObserver vto = v.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                v.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                new ParticleSystem(Main2Activity.this, 10, R.drawable.kenderii, 6000)
                        .setFadeOut(5000)
                        .setRotationSpeed(2)
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
                    t.update();
                    System.out.println(".>>>>>>>>TERMENY<<<<<<<<. "+t.getSuly());
                }
        }
        }



    public void tutorialEvent(View v){

       Spotlight.with(Main2Activity.this)
                .setOverlayColor(R.color.background)
                .setDuration(10L)
                .setAnimation(new DecelerateInterpolator(2f))
                .setTargets(

                        new SimpleTarget.Builder(this)
                        .setPoint(magv.getX()+magv.getWidth()/2,magv.getY()+magv.getHeight()/2)
                        .setShape(new Circle(200f))
                        .setTitle("Seed Bank")
                        .setDescription("Before setting up your grow environment, choose the seed you want to plant.")
                        .setOnSpotlightStartedListener(new OnTargetStateChangedListener<SimpleTarget>() {
                            @Override
                            public void onStarted(SimpleTarget target) {
                                // do something
                            }
                            @Override
                            public void onEnded(SimpleTarget target) {
                                magv.performClick();
                            }
                        })
                        .build(),

                        new SimpleTarget.Builder(this)
                .setPoint(magv.getX()+magv.getWidth()/2,magv.getY()+magv.getHeight()/2)
                .setShape(new Circle(magv.getX()+magv.getWidth()))
                .setTitle("Select your Strain")
                .setDescription("You can scroll sideways to select the cannabis strain you wish to grow.\n" +
                        "For the purposes of this tutorial, let's choose Skunk#1 to plant.")
                .setOnSpotlightStartedListener(new OnTargetStateChangedListener<SimpleTarget>() {
                    @Override
                    public void onStarted(SimpleTarget target) {
                        // do something
                    }
                    @Override
                    public void onEnded(SimpleTarget target) {
                        findViewById(R.id.imageButton).performClick();
                    }
                })
                .build(),

                        new SimpleTarget.Builder(this)
                                .setPoint(findViewById(R.id.start).getX()+findViewById(R.id.start).getWidth()/2,
                                        findViewById(R.id.start).getY()+findViewById(R.id.start).getHeight()/2)
                                .setShape(new Circle(findViewById(R.id.start).getWidth()))
                                .setTitle("Start")
                                .setDescription("Now that you have selected your seed,\n" +
                                        "let's head over to your GrowRoom to plant it.")
                                .setOnSpotlightStartedListener(new OnTargetStateChangedListener<SimpleTarget>() {
                                    @Override
                                    public void onStarted(SimpleTarget target) {
                                        // do something
                                    }
                                    @Override
                                    public void onEnded(SimpleTarget target) {

                                    }
                                })
                                .build()
                )
                .setClosedOnTouchedOutside(true)
                .setOnSpotlightStateListener(new OnSpotlightStateChangedListener() {
                    @Override
                    public void onStarted() {
                        Toast.makeText(Main2Activity.this, "spotlight is started", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onEnded() {
                        Toast.makeText(Main2Activity.this, "spotlight is ended", Toast.LENGTH_SHORT).show();
                        findViewById(R.id.start).performClick();
                    }
                }).start();

    }




    public void mag(View v){
       checkService();
    }

    private void checkService(){
        Toast.makeText(this,isMyServiceRunning(this)+" Fut e a Szervíz:"+LService.IS_SERVICE_RUNNING,Toast.LENGTH_SHORT).show();

        if(!LService.IS_SERVICE_RUNNING) {

            findViewById(R.id.start).setVisibility(View.GONE);
            mViewPager.setVisibility(View.VISIBLE);
            magv.setVisibility(View.GONE);
            //stopService(service);
            Kender.getInstance().clear();
        }
        else {
            Toast.makeText(this,"Operation In-Progress",Toast.LENGTH_SHORT).show();
        }
    }


    public void startSim(View v){

        Intent i = new Intent(this,MainActivity.class);

        if(Kender.getInstance().getFajta()>0) {
            if (!isMyServiceRunning(this)) {
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
            Toast.makeText(this,"Pick a Seed",Toast.LENGTH_SHORT).show();
    }

    private boolean isMyServiceRunning(Context mContext) {

            int accessibilityEnabled = 0;
            final String service = getPackageName() + "/" + LService.class.getCanonicalName();
            try {
                accessibilityEnabled = Settings.Secure.getInt(
                        mContext.getApplicationContext().getContentResolver(),
                        android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
                Log.v("Acc", "accessibilityEnabled = " + accessibilityEnabled);
            } catch (Settings.SettingNotFoundException e) {
                Log.e("Acc", "Error finding setting, default accessibility to not found: "
                        + e.getMessage());
            }
            TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

            if (accessibilityEnabled == 1) {
                Log.v("Acc", "***ACCESSIBILITY IS ENABLED*** -----------------");
                String settingValue = Settings.Secure.getString(
                        mContext.getApplicationContext().getContentResolver(),
                        Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
                if (settingValue != null) {
                    mStringColonSplitter.setString(settingValue);
                    while (mStringColonSplitter.hasNext()) {
                        String accessibilityService = mStringColonSplitter.next();

                        Log.v("Acc", "-------------- > accessibilityService :: " + accessibilityService + " " + service);
                        if (accessibilityService.equalsIgnoreCase(service)) {
                            Log.v("Acc", "We've found the correct setting - accessibility is switched on!");
                            return true;
                        }
                    }
                }
            } else {
                Log.v("Acc", "***ACCESSIBILITY IS DISABLED***");
            }

            return false;
        }


    public void setStrain(View v){
    Kender.getInstance();

    Kender.getInstance().fajta(mViewPager.getCurrentItem()+1);
    Toast.makeText(this,""+(mViewPager.getCurrentItem()+1),Toast.LENGTH_SHORT).show();
    mViewPager.setVisibility(View.GONE);
    magv.setVisibility(View.VISIBLE);
    findViewById(R.id.start).setVisibility(View.VISIBLE);
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
