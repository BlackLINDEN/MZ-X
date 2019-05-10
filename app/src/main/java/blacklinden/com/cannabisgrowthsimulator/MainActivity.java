package blacklinden.com.cannabisgrowthsimulator;


import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.circularprogressbar.CircularProgressBar;
import com.github.alexjlockwood.kyrie.KyrieDrawable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import blacklinden.com.cannabisgrowthsimulator.canvas.CanvasView;
import blacklinden.com.cannabisgrowthsimulator.eszk.AlsFiok;
import blacklinden.com.cannabisgrowthsimulator.eszk.FelsFiok;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.eszk.ScreenShot;
import blacklinden.com.cannabisgrowthsimulator.eszk.ThermoView;
import blacklinden.com.cannabisgrowthsimulator.eszk.Ventil;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;
import blacklinden.com.cannabisgrowthsimulator.pojo.NutriEntity;
import blacklinden.com.cannabisgrowthsimulator.pojo.SoilEntity;
import blacklinden.com.cannabisgrowthsimulator.pojo.Termény;
import blacklinden.com.cannabisgrowthsimulator.serv.LService;
import blacklinden.com.cannabisgrowthsimulator.sql.MagVM;
import blacklinden.com.cannabisgrowthsimulator.sql.NutriVM;
import blacklinden.com.cannabisgrowthsimulator.sql.SoilVM;
import blacklinden.com.cannabisgrowthsimulator.ui.kolibri.Kolibri;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    CanvasView canvasView;
    ThermoView thermoView;


    CardView polc;
    ImageButton bulb, seed, cserép, táp, kanna, ollo;
    Ventil ventilObj;
    ImageView fátyol;
    private DrawerLayout drawerLayout;
    boolean ventilBE;
    TextView napTV,indicator;
    private LService lService;
    Intent service, intent;
    private Dialog dialog;
    private MediaPlayer nutriGoo, loccs, gong;
    private PopupWindow quickAction;
    private ImageView tapeta;
    private TypedValue outValue;
    private KyrieDrawable vectorDrawable;
    private Kolibri kolibriAnimator;
    private ImageView ivLC;
    private LayoutInflater inflater;
    private View customView;
    private Gson gson;
    private Handler fieldHandler = new Handler();
    private NutriVM nutriVM;
    private SoilVM soilVM;
    private HashMap<String,Integer> soilMennyi;
    private HashMap<String,Integer> nutriMennyi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson = new GsonBuilder().create();
        String skin = Mentés.getInstance(this).getString(Mentés.Key.SKN,"");
        FelsFiok felsFiok = findViewById(R.id.felso);
        AlsFiok alsFiok = findViewById(R.id.also);
        tapeta = findViewById(R.id.httr_tapeta);
        switch (skin) {
            case "a":
                felsFiok.setBackgroundResource(R.drawable.jamanfelso);
                alsFiok.setBackgroundResource(R.drawable.jamanalso);
                tapeta.setImageResource(R.drawable.jamankozepso);
            break;
            case "b":
                felsFiok.setBackgroundResource(R.drawable.goafelso);
                alsFiok.setBackgroundResource(R.drawable.goaalso);
                tapeta.setImageResource(R.drawable.goakozepso);
            break;
            case "c":
                felsFiok.setBackgroundResource(R.drawable.cavefelso);
                alsFiok.setBackgroundResource(R.drawable.cavealso);
                tapeta.setImageResource(R.drawable.cavekozepso);
                break;
            case "d":
                felsFiok.setBackgroundResource(R.drawable.boxfelso);
                alsFiok.setBackgroundResource(R.drawable.boxalso);
                tapeta.setImageResource(R.drawable.boxkozepso);
                break;
        }
        intent = new Intent(this, Main2Activity.class);
        drawerLayout = findViewById(R.id.drawer_layout);
        outValue = new TypedValue();
        this.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        //alsFiok = findViewById(R.id.also);

        ivLC = findViewById(R.id.ivLC);



        TextView kolibriTV = findViewById(R.id.kolibri);
        kolibriTV.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        final AnimationDrawable kolibri = (AnimationDrawable) kolibriTV.getCompoundDrawables()[3];
        kolibri.start();
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        final float w = point.x;
        kolibriAnimator = new Kolibri(w, kolibriTV);
        kolibriAnimator.setState("repdes",null);

        //if(Mentés.getInstance().getString(Mentés.Key.BELEP,"0").equals("0")) kolibriAnimator.setTutorial_e(true);
        kolibriAnimator.run();

        nutriGoo = MediaPlayer.create(this, R.raw.nutri);
        loccs = MediaPlayer.create(this, R.raw.loccs);
        gong = MediaPlayer.create(this, R.raw.gong);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    // set item as selected to persist highlight
                    menuItem.setChecked(false);
                    // close drawer when item is tapped
                    drawerLayout.closeDrawers();

                    switch (menuItem.getItemId()) {
                        case R.id.killit:
                            lService.vége();
                                finish();
                            Kender.getInstance().clear();
                            break;

                        case R.id.back:
                            if (isTaskRoot())
                                startActivity(intent);

                            else
                                finish();

                            break;

                        case R.id.inventory:
                            Intent i = new Intent(MainActivity.this, InventoryActivity.class);
                            startActivity(i);
                            finish();
                            break;

                        case R.id.colibri:
                            kolibriAnimator.run();
                            kolibriTV.setVisibility(View.VISIBLE);
                            break;

                        case R.id.snap:
                            drawerLayout.closeDrawer(Gravity.NO_GRAVITY);
                            Bitmap bitmap=ScreenShot.takescreenshotOfRootView(canvasView);
                            //storeScreenshot(bitmap);
                            break;

                    }

                    return true;
                });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.densityDpi;

        kanna = findViewById(R.id.locsol);
        seed = findViewById(R.id.daseed);
        cserép = findViewById(R.id.cserep);
        táp = findViewById(R.id.tap1);
        bulb = findViewById(R.id.gmb);

        ViewTreeObserver vto = bulb.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bulb.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                kolibriAnimator.flyTo(bulb);

            }
        });


        kanna.setImageDrawable(getDrawable(Kender.getInstance().VV.setDrawCode()));
        cserép.setImageDrawable(getDrawable(Kender.getInstance().CC.setDrawableCode()));
        táp.setImageDrawable(getDrawable(Kender.getInstance().nutes.setDrawCode()));
        try {
            vectorDrawable = KyrieDrawable.create(this.getApplicationContext(), Kender.getInstance().FF.setDrawCode(""));
        }catch (Exception e){
            vectorDrawable = KyrieDrawable.create(this.getApplicationContext(),R.drawable.yellow_hps);
        }
        if (Kender.getInstance().FF.setDrawCode("") == R.drawable.yellow_hps)
            bulb.setRotation(90);
        else bulb.setRotation(180);
        bulb.setImageDrawable(vectorDrawable);

        seed.setOnTouchListener(this);
        cserép.setOnDragListener(this);
        kanna.setOnTouchListener(this);
        táp.setOnTouchListener(this);
        ollo = findViewById(R.id.ollo);

        napTV = findViewById(R.id.nap);
        indicator = findViewById(R.id.seedNameIndicator);
        setIndicator();
        ventilBE = false;
        canvasView = findViewById(R.id.canvas);
        canvasView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        final float cserepMagassag = 59;
        canvasView.metrix((density / 160) * cserepMagassag);
        Kender.getInstance().metrix(dm.heightPixels / density);
        thermoView = findViewById(R.id.thermo);


        fátyol = findViewById(R.id.fatyol);
        //fátyol.setImageDrawable(getDrawable(Kender.getInstance().FF.setDrawCode(1)));
        polc = findViewById(R.id.polc);

        ventilObj = findViewById(R.id.ventil);
        ventilObj.setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setCanceledOnTouchOutside(false);
        Button dialogButton = dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(v -> {
            v.setVisibility(View.GONE);
            dialog.findViewById(R.id.pb).setVisibility(View.VISIBLE);

            if(saveWeed()){

                fieldHandler.postDelayed(ii,2000);
            }else{
                v.setClickable(false);
                v.setVisibility(View.VISIBLE);
                ((Button)v).setText("UNSAVED");
                fieldHandler.postDelayed(ii,2000);


            }

        });


        if (Kender.getInstance().FF.beKapcs)
            fátyol.setVisibility(View.VISIBLE);
        else
            fátyol.setVisibility(View.GONE);


        service = new Intent(MainActivity.this, LService.class);
        bindService(service, kapcsolat, Context.BIND_AUTO_CREATE);


        clearCanvas();

        nutriMennyi = new HashMap<>();


        nutriVM = ViewModelProviders.of(this).get(NutriVM.class);
        nutriVM.getAll().observe(this,nutriEntities -> {
            if (nutriEntities != null) {
                Toast.makeText(this," nutri!=null",Toast.LENGTH_SHORT).show();
                for(NutriEntity n:nutriEntities) {
                    if (n.getFajta().equals(Kender.getInstance().nutes.nuteName)) {

                         if (nutriMennyi.containsKey(n.getFajta()))
                            nutriMennyi.replace(n.getFajta(), n.getMennyi());
                         else
                             nutriMennyi.put(n.getFajta(), n.getMennyi());
                    }
                }

            }else {
                nutriMennyi.put("BioGrow", 0);
                Toast.makeText(this," nutri=null",Toast.LENGTH_SHORT).show();
            }
        });
        soilMennyi = new HashMap<>();
        soilVM = ViewModelProviders.of(this).get(SoilVM.class);
        soilVM.getAll().observe(this,soilEntities -> {
            if (soilEntities != null) {

                for(SoilEntity s:soilEntities) {
                    if (s.getFajta().equals(Kender.getInstance().CC.föld.soilName)) {

                        if (soilMennyi.containsKey(s.getFajta()))
                            soilMennyi.replace(s.getFajta(), s.getMennyi());
                        else
                            soilMennyi.put(s.getFajta(), s.getMennyi());
                    }
                }

            }else
                soilMennyi.put("Dirt from outside",1);
        });

    }


    Runnable ii = new Runnable() {
        @Override
        public void run() {
            if (isTaskRoot()) {
                lService.stopForeground(true);
                lService.stopSelf();
                Kender.getInstance().clear();
                startActivity(intent);
                finish();
            }else {
                lService.stopForeground(true);
                lService.stopSelf();
                Kender.getInstance().clear();
                finish();
            }
        }
    };

    private void setIndicator(){
        indicator.setText(Kender.getInstance().getStrFajta());
        indicator.setCompoundDrawablesWithIntrinsicBounds( 0,
                Kender.getInstance().fajtaDrawCode, 0, 0 );
    }

    @Override
    protected void onPause() {
        super.onPause();

        thermoView.handler.removeCallbacks(thermoView.oo);
        kolibriAnimator.dispose();
    }



    @Override
    protected void onResume() {
        super.onResume();
        setIndicator();
        fátyol.setImageDrawable(getDrawable(Kender.getInstance().FF.setDrawCode(1)));
        thermoView.handler.postDelayed(thermoView.oo, 1000);
        kanna.setImageDrawable(getDrawable(Kender.getInstance().VV.setDrawCode()));
        cserép.setImageDrawable(getDrawable(Kender.getInstance().CC.setDrawableCode()));
        táp.setImageDrawable(getDrawable(Kender.getInstance().nutes.setDrawCode()));
        try {
            vectorDrawable = KyrieDrawable.create(this.getApplicationContext(), Kender.getInstance().FF.setDrawCode(""));
        }catch (Exception e){
            vectorDrawable = KyrieDrawable.create(this.getApplicationContext(),R.drawable.yellow_hps);
        }
        if (Kender.getInstance().FF.beKapcs) {
            vectorDrawable.start();
            tapeta.getBackground().clearColorFilter();
            tapeta.getDrawable().clearColorFilter();
        } else {
            tapeta.getBackground().setColorFilter(this.getColor(R.color.cardview_dark_background),PorterDuff.Mode.MULTIPLY);
            tapeta.getDrawable().setColorFilter(this.getColor(R.color.cardview_dark_background),PorterDuff.Mode.MULTIPLY);
        }
        if (Kender.getInstance().FF.setDrawCode("a") == R.drawable.avd_anim)
            bulb.setRotation(90);
        else bulb.setRotation(180);
        bulb.setImageDrawable(vectorDrawable);
    }

    public void cserépGomb(View v) {
        if (quickAction == null) {

            if(inflater==null)
                inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);


            if(inflater != null)
            customView = inflater.inflate(R.layout.quick_action, null);


            quickAction = new PopupWindow(
                    customView,
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );


            quickAction.setElevation(5.0f);



            quickAction.showAtLocation(cserép, Gravity.CENTER, 0, 0);
        } else {
            quickAction.dismiss();
            quickAction = null;
        }
        kwikkEksn();
    }
    private void kwikkEksn(){


        if(customView!=null) {

            ((CircularProgressBar) customView.findViewById(R.id.water)).setProgress(Kender.getInstance().VV.getVÍZ_Mennyiség());

            ((CircularProgressBar) customView.findViewById(R.id.sugar)).setProgress(Kender.getInstance().getCukor());

            ((CircularProgressBar) customView.findViewById(R.id.starch)).setProgress(Kender.getInstance().getRost());

            ((CircularProgressBar) customView.findViewById(R.id.natrium)).setProgress(Kender.getInstance().nutes.N);

            ((CircularProgressBar) customView.findViewById(R.id.foszfor)).setProgress(Kender.getInstance().nutes.P);

            ((CircularProgressBar) customView.findViewById(R.id.kalium)).setProgress(Kender.getInstance().nutes.K);


        }
    }




    private void clearCanvas() {
        thermoView.oo.run();
    }

    //
    public void cl(View v) {

        if (fátyol.getVisibility() == View.GONE) {

            fátyol.setVisibility(View.VISIBLE);
            //bulb.setBackground(getDrawable(R.drawable.feny60));
            vectorDrawable.start();
            tapeta.getBackground().clearColorFilter();
            tapeta.getDrawable().clearColorFilter();
            Kender.getInstance().FF.beKapcs = true;
            polc.setElevation(5);
            polc.setCardElevation(60);
            kolibriAnimator.flyTo(kanna);
        } else if (fátyol.getVisibility() == View.VISIBLE && !vectorDrawable.isRunning()) {
            fátyol.setVisibility(View.GONE);
            vectorDrawable.setCurrentPlayTime(0);
            tapeta.getBackground().setColorFilter(this.getColor(R.color.cardview_dark_background),PorterDuff.Mode.MULTIPLY);
            tapeta.getDrawable().setColorFilter(this.getColor(R.color.cardview_dark_background),PorterDuff.Mode.MULTIPLY);
            bulb.setBackgroundResource(outValue.resourceId);
            Kender.getInstance().FF.beKapcs = false;
            polc.setCardElevation(5);
            polc.setElevation(20);
            Toast.makeText(this, "lámpa " + Kender.getInstance().FF.beKapcs, Toast.LENGTH_SHORT).show();
        }

    }


    public void ventilator(View v) {
        if (!ventilBE) {
            ventilObj.indit();
            ventilBE = true;

        } else {

            ventilObj.stop();
            ventilBE = false;
        }
    }

    @Override
    public void onBackPressed() {

            if(!isTaskRoot())
                super.onBackPressed();
            else{
                Intent i = new Intent(this,Main2Activity.class);
                finish();
                startActivity(i);

            }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thermoView.handler.removeCallbacks(thermoView.oo);
        unbindService(kapcsolat);
        h.removeCallbacks(r);

        if (dialog.isShowing()) {

            dialog.dismiss();
        }

        kolibriAnimator.dispose();
        if(quickAction!=null)quickAction.dismiss();
    }

    private ServiceConnection kapcsolat = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {


        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LService.Binderem myBinder = (LService.Binderem) service;
            lService = myBinder.getService();
            r.run();
            if (LService.IS_SERVICE_RUNNING)
                seed.setVisibility(View.GONE);
        }
    };

    Handler h = new Handler(Looper.myLooper());
    Runnable r = new Runnable() {
        @Override
        public void run() {


                if(ventilBE)Kender.getInstance().FF.setHő();

                    canvasView.told(lService.al, lService.ism);

            if(quickAction!=null)kwikkEksn();


                            napTV.setText(String.valueOf(lService.ism / 6));


            if(!Kender.getInstance().flowering&&lService.ism<50)
                ivLC.setImageResource(R.drawable.ic_seedling);
            else if(!Kender.getInstance().flowering)
                ivLC.setImageResource(R.drawable.ic_vegetative);
            else
                ivLC.setImageResource(R.drawable.ic_floweing);



                    if (Kender.getInstance().flowering) ollo.setVisibility(View.VISIBLE);


                    if (lService.stopIt) {

                        if (lService.halott) {
                            TextView text = dialog.findViewById(R.id.text);
                            text.setText("Your Plant Died!" + Kender.getInstance().causeofdeath +
                                    "\n Raw Yield Salvaged: " + lService.hányGrammLett() + " gr");
                            ImageView image = dialog.findViewById(R.id.image);
                            image.setImageResource(R.drawable.koponyacsont);
                            h.removeCallbacks(r);

                            dialog.show();

                        } else {
                            TextView text = dialog.findViewById(R.id.text);
                            text.setText("Produce Harvested!\n Raw Yield Collected: " + lService.hányGrammLett() + " gr");
                            ImageView image = dialog.findViewById(R.id.image);
                            image.setImageResource(R.drawable.koszoru);
                            h.removeCallbacks(r);

                            dialog.show();
                        }

                    } else h.postDelayed(this, 2000);


        }
    };

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        View.DragShadowBuilder mShadow = new View.DragShadowBuilder(view);
        ClipData.Item item = new ClipData.Item(view.getTag().toString());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
        switch (view.getId()) {

            case R.id.daseed:
                view.startDragAndDrop(data, mShadow, null, 0);
                break;

            case R.id.locsol:
                view.startDragAndDrop(data, mShadow, null, 0);
                break;

            case R.id.tap1:
                view.startDragAndDrop(data, mShadow, null, 0);
                break;
        }

        return false;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:

                ((ImageView) v).setColorFilter(Color.YELLOW);
                seed.setVisibility(View.GONE);
                kanna.setVisibility(View.GONE);
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_ENTERED:

                ((ImageView) v).setColorFilter(
                        ContextCompat.getColor(MainActivity.this, R.color.colorAccent),
                        android.graphics.PorterDuff.Mode.MULTIPLY
                );

                if(event.getClipDescription().getLabel().toString().equals("Give some water")&&
                        Kender.getInstance().VV.getVÍZ_Mennyiség()<=Kender.getInstance().CC.potSize/2
                        ) {

                    Kender.getInstance().VV.locsol();
                    loccs.start();

            }

                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:

                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                ((ImageView) v).clearColorFilter();
                ((ImageView) v).setColorFilter(Color.YELLOW);

                v.invalidate();
                return true;

            case DragEvent.ACTION_DROP:
                String clipData = event.getClipDescription().getLabel().toString();
                if(clipData.equals("Plant the seed")) {
                    lService.startThread();
                    gong.start();
                    MagVM magVM = ViewModelProviders.of(this).get(MagVM.class);
                    magVM.update(Kender.getInstance().getStrFajta(), Kender.getInstance().getLevonasElottiMagSzam() - 1);
                    String soilName = Kender.getInstance().CC.föld.soilName;
                    if (soilMennyi.get(soilName) != null) {
                        if (soilMennyi.get(soilName) >= 1) {

                            if (!soilName.equals("Dirt from outside")) {
                                soilVM.update(soilName, soilMennyi.get(soilName) - 1);
                                if (soilMennyi.get(soilName) == 1) soilMennyi.clear();
                            }
                        }
                    }
                }
                else if(clipData.equals("tap1")) {
                    String name = Kender.getInstance().nutes.nuteName;
                    if (nutriMennyi.get(name) != null) {
                        if (nutriMennyi.get(name) >= 1) {
                            nutriGoo.start();
                            Kender.getInstance().CC.föld.Nitrogén += Kender.getInstance().nutes.iN;
                            Kender.getInstance().CC.föld.Foszfor += Kender.getInstance().nutes.iP;
                            Kender.getInstance().CC.föld.Kálium += Kender.getInstance().nutes.iK;
                            nutriVM.update(name, nutriMennyi.get(name) - 1);
                            if(nutriMennyi.get(name)==1) nutriMennyi.clear();
                        }
                    }else
                        Toast.makeText(this,"EMPTY",Toast.LENGTH_SHORT).show();
                }
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_ENDED:


                ((ImageView) v).clearColorFilter();
                if (event.getResult()) {
                    if(!LService.IS_SERVICE_RUNNING) seed.setVisibility(View.VISIBLE);
                    kanna.setVisibility(View.VISIBLE);
                    //Toast.makeText(MainActivity.this, "Awesome!", Toast.LENGTH_SHORT).show();

                } else {
                    if(!LService.IS_SERVICE_RUNNING)
                        seed.setVisibility(View.VISIBLE);
                    kanna.setVisibility(View.VISIBLE);

                }
                return true;

            default:
                return false;
        }
    }

    public void szüret(View view){
        //lService.szüretelve=true;
        //findViewById(R.id.ollo).setVisibility(View.GONE);
        lService.harvest();
    }

    public boolean saveWeed(){

        Type tList = new TypeToken<ArrayList<Termény>>(){}.getType();
        List<Termény> termenyList = gson.fromJson(Mentés.getInstance().getString(Mentés.Key.TRMS_LST),tList);
        if(termenyList.size()<3) {
            lService.saveWeed();
            return true;
        }
        else return false;
    }

    public void nyissMenut(View v){
        drawerLayout.openDrawer(Gravity.START);
    }


    public String storeScreenshot(Bitmap bitmap) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(fos).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}


