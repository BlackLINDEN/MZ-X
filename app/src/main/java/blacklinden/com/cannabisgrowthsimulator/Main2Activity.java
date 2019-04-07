package blacklinden.com.cannabisgrowthsimulator;



import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.plattysoft.leonids.ParticleSystem;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.eszk.Teknős;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;
import blacklinden.com.cannabisgrowthsimulator.pojo.Lampa;
import blacklinden.com.cannabisgrowthsimulator.pojo.Lamps;
import blacklinden.com.cannabisgrowthsimulator.pojo.SelectableStashItem;
import blacklinden.com.cannabisgrowthsimulator.pojo.Stash;
import blacklinden.com.cannabisgrowthsimulator.pojo.Termény;
import blacklinden.com.cannabisgrowthsimulator.pojo.Vegtermek;
import blacklinden.com.cannabisgrowthsimulator.serv.Constants;
import blacklinden.com.cannabisgrowthsimulator.serv.LService;
import blacklinden.com.cannabisgrowthsimulator.sql.LampaVM;
import blacklinden.com.cannabisgrowthsimulator.sql.MagVM;
import blacklinden.com.cannabisgrowthsimulator.sql.ScoreVM;
import blacklinden.com.cannabisgrowthsimulator.sql.VegtermekViewModel;
import blacklinden.com.cannabisgrowthsimulator.ui.CardItem;
import blacklinden.com.cannabisgrowthsimulator.ui.CardItem2;
import blacklinden.com.cannabisgrowthsimulator.ui.CardPagerAdapter;
import blacklinden.com.cannabisgrowthsimulator.ui.CardPagerAdapter2;
import blacklinden.com.cannabisgrowthsimulator.ui.ShadowTransformer;
import blacklinden.com.cannabisgrowthsimulator.ui.grind.Bong;
import blacklinden.com.cannabisgrowthsimulator.ui.grind.GrinderTartalomCV;
import blacklinden.com.cannabisgrowthsimulator.ui.kolibri.Kolibri;
import blacklinden.com.cannabisgrowthsimulator.ui.kolibri.Repdes;
import blacklinden.com.cannabisgrowthsimulator.ui.recy.SelectableAdapter;
import blacklinden.com.cannabisgrowthsimulator.ui.recy.SelectableViewHolder;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener, SelectableViewHolder.OnItemSelectedListener,
        CompoundButton.OnCheckedChangeListener {


    Intent service;
    private Button magv;
    private ViewPager mViewPager;
    private CardPagerAdapter mCardAdapter;
    private CardPagerAdapter2 mCardAdapter2;
    private ShadowTransformer mCardShadowTransformer;
    private Kolibri kolibriAnimator;
    public static final String[] sss = {"B1","B2","B3","B4","B5","B6"};
    private boolean pakkokNyitva=false;
    private CardView score;
    private TextView achievTv,stashedTv,rackTV,jarTv,buy;
    private RecyclerView recyclerView;
    private SelectableAdapter adapter;
    private VegtermekViewModel viewModel;
    private Gson gson;
    private GrinderTartalomCV grinderTartalomCV;
    private ImageView grinder,logo,kolibriTV;
    private Bong bong;
    private RelativeLayout fiok;
    private Button grindIt;
    private FloatingActionButton fab;
    private int currentXp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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

            String lampsString = Mentés.getInstance().gsonra(lamps);

            Mentés.getInstance().put(Mentés.Key.TESZT_OBJ,lampsString);

       }

        gson = new GsonBuilder().create();



        Mentés.getInstance().put(Mentés.Key.SAMPLE_ZSETON,100);





        kolibriTV = findViewById(R.id.kolibri);
        kolibriTV.setBackgroundResource(R.drawable.kolibri_anim);
        AnimationDrawable kolibri = (AnimationDrawable) kolibriTV.getBackground();
        kolibri.start();
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        final float w = point.x;
        kolibriAnimator = new Kolibri(w, kolibriTV);
        //if(Mentés.getInstance().getString(Mentés.Key.BELEP,"0").equals("0")) kolibriAnimator.setTutorial_e(true);
        kolibriAnimator.setState("repdes");
        kolibriAnimator.run();

        mViewPager = findViewById(R.id.viewPager);
        buy = findViewById(R.id.buyme);
        ((CheckBox) findViewById(R.id.checkBox)).setOnCheckedChangeListener(this);



        mCardAdapter = new CardPagerAdapter();

        MagVM magVM = ViewModelProviders.of(this).get(MagVM.class);
        magVM.getAll().observe(this, magEntities -> {
            mCardAdapter.addLiveData(magEntities);
            mCardAdapter.notifyDataSetChanged();
            Toast.makeText(this,"magVM_observe",Toast.LENGTH_SHORT).show();
        });



        mCardAdapter2 = new CardPagerAdapter2();
        mCardAdapter2.addCardItem(new CardItem2(R.string.boxtitle,"textile",R.drawable.old_box));
        mCardAdapter2.addCardItem(new CardItem2(R.string.boxtitle_1,"textile",R.drawable.old_box));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);

        mViewPager.setOffscreenPageLimit(3);
        magv = findViewById(R.id.magv);



        mViewPager.setPageTransformer(false, mCardShadowTransformer);

        mViewPager.setCurrentItem(3);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(buy.getVisibility()==View.VISIBLE)
                buy.setText(Integer.toString((position+1)*10));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        logo = findViewById(R.id.imageView);
        ViewTreeObserver vto = logo.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                logo.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                new ParticleSystem(Main2Activity.this, 10, R.drawable.kenderii, 6000)
                        .setFadeOut(5000)
                        .setRotationSpeed(50)
                        .setSpeedModuleAndAngleRange(0.1f,0.5f,0,365)
                        .oneShot(logo,50);

            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                this, 3, GridLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.selection_list);
        recyclerView.setLayoutManager(gridLayoutManager);


        TextView xp = findViewById(R.id.xp);

        score = findViewById(R.id.scoreGomb);
        achievTv = findViewById(R.id.achiev);
        TextView coins = findViewById(R.id.coins);
        ScoreVM scoreVM = ViewModelProviders.of(this).get(ScoreVM.class);
        scoreVM.get().observe(this, scoreEntity -> {

            if (scoreEntity != null) {
                currentXp = scoreEntity.getXp();
            }
            if (scoreEntity != null) {
                xp.setText(Integer.toString(scoreEntity.getXp()));
            }

            if (scoreEntity != null) {
                coins.setText(Integer.toString(scoreEntity.getScore()));
            }
            if (scoreEntity != null) {
                achievTv.setText(scoreEntity.getRank());
            }

        });


        stashedTv = findViewById(R.id.stashed);
        rackTV = findViewById(R.id.rack);
        jarTv = findViewById(R.id.jars);

        grinderTartalomCV = findViewById(R.id.grinderTartalom);
        grinder = findViewById(R.id.grinder2);
        grindIt = findViewById(R.id.grindIt);

        fab = findViewById(R.id.fab);
        fiok = findViewById(R.id.fiokajto);

        bong = findViewById(R.id.bong);
        bong.setListener(new Bong.BongListener() {
            @Override
            public void inhale() {

                kolibriAnimator.flyTo(bong);
            }

            @Override
            public void exhale(int counter) {

                new ParticleSystem(Main2Activity.this,50,R.drawable.fust_particle,5000L)
                        .setFadeOut(2500)
                        .setRotationSpeed(100)
                        .setScaleRange(2,5)
                        .setSpeedModuleAndAngleRange(0.01f,0.05f,10,80)
                        .emitWithGravity(kolibriTV,Gravity.CENTER_VERTICAL,counter,1000);

                scoreVM.updateXP(currentXp+100);


            }

        });

        grinderTartalomCV.setGrinderListener(new GrinderTartalomCV.GrinderListener() {


            @Override
            public void onEmpty() {
                if(grindIt.getVisibility()==View.VISIBLE)
                grindIt.setVisibility(View.GONE);

                if(!pakkokNyitva) {

                    bong.animate()
                            .translationX(0)
                            .translationZ(0)
                            .rotation(-20)
                            .setDuration(500)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    grinder.animate()
                                            .translationY(0)
                                            .setStartDelay(200)
                                            .setDuration(500)
                                            .setListener(new AnimatorListenerAdapter() {
                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    super.onAnimationEnd(animation);
                                                    findViewById(R.id.frameGrinder).setVisibility(View.GONE);
                                                }
                                            })
                                            .start();
                                }
                            })
                            .start();
                }
                Toast.makeText(getBaseContext(),"onEmpty",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFilled() {
                if(grindIt.getVisibility()==View.GONE)
                 grindIt.setVisibility(View.VISIBLE);
                Toast.makeText(getBaseContext(),"onFill",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onGrinded() {
                if(grindIt.getVisibility()==View.VISIBLE)
                    grindIt.setVisibility(View.GONE);
                kolibriAnimator.setState("tutorial");
                Toast.makeText(getBaseContext(),"onGrind",Toast.LENGTH_SHORT).show();
            }
        });
        
        updateStaticTV();

        adapter = new SelectableAdapter(this,true);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(VegtermekViewModel.class);

        viewModel.getAll().observe(this, vegtermekek -> {
            // Update the cached copy of the words in the adapter.
            adapter.setLiveValues(vegtermekek);
            stashedTv.setText(Float.toString(vegtermekek.size()));
        });

        service = new Intent(Main2Activity.this, LService.class);


    }

    @Override
    protected void onResume() {
        super.onResume();
        insert();
        updateStaticTV();
        kolibriAnimator.setState("repdes");

    }

    @Override
    protected void onStart() {
        super.onStart();
        //insert();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

    }

    private int[] updateStashConsumption(int mennyi){
        int[] levont = new int[2];
       if(mennyi<6f) {
           //viewModelnek
           levont[0] = 0;
           //grinderFillupnak
           levont[1] = mennyi;
       }
       else {
           //viewModelnek
           levont[0] = mennyi - 6;
           //grinderFillupnak
           levont[1] = 6;
       }
           return levont;
    }


    private void insert(){
        String rawVgktrmk = Mentés.getInstance().getString(Mentés.Key.VGTRMK_LST,"o");

        Type vList = new TypeToken<ArrayList<Stash>>() {
        }.getType();
        List<Stash> vgtrmkList = gson.fromJson(rawVgktrmk, vList);
        if(!vgtrmkList.isEmpty()) {

            for (Stash s : vgtrmkList) {

                viewModel.insert(new Vegtermek(s.getFajta(),s.getMennyi()));

                Toast.makeText(
                        getApplicationContext(),
                        "ADDED",
                        Toast.LENGTH_LONG).show();
            }

            List<Stash> trmny = new ArrayList<>();
            String trm = Mentés.getInstance().gsonra(trmny);
            Mentés.getInstance().put(Mentés.Key.VGTRMK_LST,trm);

        }
    }



    @SuppressLint("SetTextI18n")
    private void updateStaticTV(){
        String rawTermes = Mentés.getInstance().getString(Mentés.Key.TRMS_LST,"o");
        Type tList = new TypeToken<ArrayList<Termény>>() {
        }.getType();
        List<Termény> trmkList = gson.fromJson(rawTermes, tList);

        String rawErlelmeny = Mentés.getInstance().getString(Mentés.Key.ERllT_LST,"o");
        Type eList = new TypeToken<ArrayList<Termény>>() {
        }.getType();
        List<Termény> erlltList = gson.fromJson(rawErlelmeny, eList);


        if(!trmkList.isEmpty()){
            float tempR=0f;
            for(Termény t:trmkList)
                tempR+=t.getSuly();

                rackTV.setText(Float.toString(tempR));
        }else  rackTV.setText("0");

        if(!erlltList.isEmpty()){
            float tempJ=0;
            for(Termény t:erlltList)
                tempJ+=t.getSuly();
                jarTv.setText(Float.toString(tempJ));
        }else jarTv.setText("0");

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
            score.setVisibility(View.GONE);
            findViewById(R.id.start).setVisibility(View.GONE);
            mViewPager.setVisibility(View.VISIBLE);
            magv.setVisibility(View.GONE);
            findViewById(R.id.box).setVisibility(View.GONE);
            buy.setVisibility(View.VISIBLE);
            buy.setText(Integer.toString((mViewPager.getCurrentItem()+1)*10));
            kolibriAnimator.flyTo(mViewPager);

        }
        else {
            Toast.makeText(this,"Operation In-Progress",Toast.LENGTH_SHORT).show();
            kolibriAnimator.flyTo(magv);
        }
    }

    public void closeMag(View v){
        mViewPager.setVisibility(View.GONE);
        score.setVisibility(View.VISIBLE);
        magv.setVisibility(View.VISIBLE);
        findViewById(R.id.box).setVisibility(View.VISIBLE);
        findViewById(R.id.start).setVisibility(View.VISIBLE);
        buy.setVisibility(View.GONE);
        mViewPager.setAdapter(null);
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

            }else if(isTaskRoot()){
                if(Kender.getInstance().getFöld().equals("0"))
                Kender.getInstance().setFöld("coco");
                startActivity(i);

            }else finish();

                kolibriAnimator.dispose();
        }else {
            kolibriAnimator.flyTo(findViewById(R.id.magv));

        }
    }


//Mentés.getInstance().getInt(Mentés.Key.valueOf(sss[mViewPager.getCurrentItem()]),0)
    public void setStrain(View v) {
       int i = Integer.parseInt((String) mCardAdapter.getCardViewAt(mViewPager.getCurrentItem()).findViewById(R.id.seedsLeft).getTag());

        if(i>0){
        Kender.getInstance();
        Kender.getInstance().fajta(mViewPager.getCurrentItem() + 1,numberToCharacter(mViewPager.getCurrentItem()),i);
        Toast.makeText(this, i+" " + (mViewPager.getCurrentItem() + 1), Toast.LENGTH_SHORT).show();
        kolibriAnimator.flyTo(findViewById(R.id.shop));

            mViewPager.setVisibility(View.GONE);
            score.setVisibility(View.VISIBLE);
            magv.setVisibility(View.VISIBLE);
            findViewById(R.id.box).setVisibility(View.VISIBLE);
            findViewById(R.id.start).setVisibility(View.VISIBLE);
            buy.setVisibility(View.GONE);
            mViewPager.setAdapter(null);

        }else kolibriAnimator.flyTo(findViewById(R.id.seedsLeft));
        


    /*int i = Mentés.getInstance().getInt(Mentés.Key.SAMPLE_ZSETON,0)-1;
    Mentés.getInstance().put(Mentés.Key.SAMPLE_ZSETON,i);
       */

    }

    public String numberToCharacter(int i) {
        String[] stmb = {"a","b","c","d","e","f"};
       return String.valueOf(stmb[i]);
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
    public void openShop(View v){
        Intent i = new Intent(this,ShopActivity.class);
        startActivity(i);
    }

    public void nyissBox(View v){

        if(!LService.IS_SERVICE_RUNNING) {
            mViewPager.setAdapter(mCardAdapter2);
            findViewById(R.id.start).setVisibility(View.GONE);
            score.setVisibility(View.GONE);
            mViewPager.setVisibility(View.VISIBLE);
            magv.setVisibility(View.GONE);
            findViewById(R.id.box).setVisibility(View.GONE);
            kolibriAnimator.flyTo(mViewPager);
        }
        else {
            Toast.makeText(this,"Operation In-Progress",Toast.LENGTH_SHORT).show();
            kolibriAnimator.flyTo(findViewById(R.id.box));
        }
    }

    public void setBox(View v){

        Kender.getInstance().setBox(mViewPager.getCurrentItem()+1);

        mViewPager.setAdapter(null);
        mViewPager.setVisibility(View.GONE);
        score.setVisibility(View.VISIBLE);
        magv.setVisibility(View.VISIBLE);
        findViewById(R.id.box).setVisibility(View.VISIBLE);
        findViewById(R.id.start).setVisibility(View.VISIBLE);
        kolibriAnimator.flyTo(findViewById(R.id.shop));
        buy.setVisibility(View.GONE);

    }

    public void fiokajtoNyitas(View v) {
        if (!pakkokNyitva) {
            recyclerView.setVisibility(View.VISIBLE);
            pakkokNyitva = true;
            float d = fiok.getWidth();// (v.getWidth()-v.getWidth()/6);
            fiok.animate().translationX(-d).setDuration(500).setInterpolator(new BounceInterpolator()).start();
        } else {
            recyclerView.setVisibility(View.GONE);
            pakkokNyitva=false;
            fiok.animate().translationX(0).setDuration(500).setInterpolator(new BounceInterpolator()).start();
        }

        logoGrinderOpener(pakkokNyitva);
    }

    public void buy(View view) {
      /*  if(mViewPager.getAdapter() instanceof CardPagerAdapter){

        }
*/
    }


    @Override
    public void onItemSelected(SelectableStashItem selectableItem) {

        List<Stash> selectedItems = adapter.getSelectedItems();
        Snackbar.make(recyclerView,"Selected: "+selectableItem.getMennyi()+
                ", Total selected: "+ selectedItems.size(),Snackbar.LENGTH_LONG).show();
        //viewModel.update(selectableItem.getId(),10);
        //(int)selectableItem.getMennyi()
        if(selectedItems.isEmpty()) {
            grinderTartalomCV.dispose();
            grinderTartalomCV.invalidate();
        }else {

            findViewById(R.id.frameGrinder).setVisibility(View.VISIBLE);
            grinderTartalomCV.fillUp(Teknős.flowerStrain(
                    this, selectableItem.getFajta()), updateStashConsumption((int)selectableItem.getMennyi()),selectableItem.getId()
            );


        }

    }


    public void deleteSQL(View view) {
        viewModel.deleteAll();
    }

    private void logoGrinderOpener(boolean csukva){
        if(csukva) {

                if (grinderTartalomCV.getIsEmpty())
                openTopFromNothing();
                else
                openTop();


        }else closeTop();

    }

    private void openTop() {

        FrameLayout frame = findViewById(R.id.frameGrinder);
        if(frame.getVisibility()==View.GONE)
        frame.setVisibility(View.VISIBLE);

        grinder.animate()
                .translationZ(20)
                .rotation(20)
                .setInterpolator(new LinearInterpolator())
                .setDuration(1000)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {

                        grinder.animate()
                                .translationX(grinder.getWidth())
                                .setDuration(500)
                                .start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }).start();
    }
    private void closeTop(){
        grinder.animate()
                .translationX(0)
                .translationZ(0)
                .rotation(0)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(500)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        if(grinderTartalomCV.getIsEmpty()){
                            findViewById(R.id.frameGrinder).setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                })
                .start();
    }

    public void grindIt(View view){
        if(pakkokNyitva) fab.performClick();

        adapter.resetSelectedItems();


            grinder.animate()
                    .setStartDelay(200)
                    .rotation(360)
                    .setDuration(1000)
                    .setInterpolator(new DecelerateInterpolator())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            grinderTartalomCV.setGrinded(true);
                            grinder.animate()
                                    .setStartDelay(200)
                                    .translationY(-grinder.getHeight())
                                    .setDuration(2000)
                                    .setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);

                                                         fillBong();

                                        }})
                                    .start();

                                }

                            })
                    .start();

    }

    private void fillBong() {

        bong.animate()
                .translationX(bong.getHeight())
                .setStartDelay(200)
                .setDuration(1000)
                .setInterpolator(new BounceInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        viewModel.update(grinderTartalomCV.getStashID(),grinderTartalomCV.getLevonando());
                        grinderTartalomCV.dispose();
                        grinderTartalomCV.invalidate();
                        bong.fillUp();

                        }

                }).start();

    }


    private void openTopFromNothing(){

        FrameLayout frame = findViewById(R.id.frameGrinder);
                frame.setVisibility(View.VISIBLE);

        int cx = logo.getRight() / 2;
        int cy = logo.getBottom()/2;
        int finalRadius = (int) Math.hypot(logo.getWidth()/2,
                logo.getHeight()/2);
        //legyen mező
        Animator a = ViewAnimationUtils.createCircularReveal(frame, cx,
                cy, 0, finalRadius);
        a.setDuration(300);
        a.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                grinder.animate()
                        .translationZ(20)
                        .rotation(20)
                        .setInterpolator(new LinearInterpolator())
                        .setDuration(1000)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {

                                grinder.animate()
                                        .translationX(grinder.getWidth())
                                        .setDuration(500)
                                        .start();
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        }).start();

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        a.start();
    }


}
