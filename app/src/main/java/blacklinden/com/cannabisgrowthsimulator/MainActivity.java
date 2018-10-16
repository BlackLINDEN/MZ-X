package blacklinden.com.cannabisgrowthsimulator;


import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import blacklinden.com.cannabisgrowthsimulator.canvas.CanvasView;
import blacklinden.com.cannabisgrowthsimulator.eszk.AlsFiok;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.eszk.StarchView;
import blacklinden.com.cannabisgrowthsimulator.eszk.ThermoView;
import blacklinden.com.cannabisgrowthsimulator.eszk.Ventil;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;
import blacklinden.com.cannabisgrowthsimulator.serv.LService;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    CanvasView canvasView;
    ThermoView thermoView;
    AlsFiok alsFiok;

    CardView polc;
    ImageButton bulb, seed, cserép, táp,kanna,ollo;
    Ventil ventilObj;
    ImageView fátyol;
    private DrawerLayout drawerLayout;
    SeekBar sb1, sb2;
    boolean ventilBE;
    TextView napTV;
    private LService lService;
    Intent service,intent;
    private Dialog dialog;
    private MediaPlayer nutriGoo,loccs,gong;
    private PopupWindow quickAction;
    private boolean cserépGombKapcsoló=false;
    private TypedValue outValue;
    private AnimatedVectorDrawable vectorDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this,Main2Activity.class);
        drawerLayout = findViewById(R.id.drawer_layout);
        outValue = new TypedValue();
        this.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        alsFiok = findViewById(R.id.also);


        nutriGoo = MediaPlayer.create(this,R.raw.nutri);
        loccs = MediaPlayer.create(this,R.raw.loccs);
        gong = MediaPlayer.create(this,R.raw.gong);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();

                        switch (menuItem.getItemId()){
                            case R.id.killit:
                                lService.stopForeground(true);
                                lService.stopSelf();
                                LService.IS_SERVICE_RUNNING=false;
                                Kender.getInstance().clear();
                                if(isTaskRoot())
                                    startActivity(intent);
                                else
                                    finish();
                                break;

                            case R.id.back:
                                if(isTaskRoot())
                                    startActivity(intent);
                                else
                                    finish();
                                break;

                            case R.id.inventory:
                                Intent i = new Intent(MainActivity.this,InventoryActivity.class);
                                startActivity(i);
                        }

                        return true;
                    }
                });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.densityDpi;

        kanna = findViewById(R.id.locsol);
        seed = findViewById(R.id.daseed);
        cserép = findViewById(R.id.cserep);
        táp = findViewById(R.id.tap1);
        bulb = findViewById(R.id.gmb);

        kanna.setImageDrawable(getDrawable(Kender.getInstance().VV.setDrawCode()));
        cserép.setImageDrawable(getDrawable(Kender.getInstance().CC.setDrawableCode()));
        táp.setImageDrawable(getDrawable(Kender.getInstance().nutes.setDrawCode()));
        vectorDrawable =(AnimatedVectorDrawable) getDrawable(Kender.getInstance().FF.setDrawCode());
        if(Kender.getInstance().FF.setDrawCode()==R.drawable.yellow_hps)
            bulb.setRotation(90);
        else bulb.setRotation(180);
        bulb.setImageDrawable(vectorDrawable);

        seed.setOnTouchListener(this);
        cserép.setOnDragListener(this);
        kanna.setOnTouchListener(this);
        táp.setOnTouchListener(this);
        ollo = findViewById(R.id.ollo);

        napTV = findViewById(R.id.nap);
        ventilBE = false;
        canvasView = findViewById(R.id.canvas);
        final float cserepMagassag = 59;
        canvasView.metrix((density / 160) * cserepMagassag);
        Kender.getInstance().metrix(dm.heightPixels/density);
        thermoView = findViewById(R.id.thermo);




        fátyol = findViewById(R.id.fatyol);
        fátyol.setImageDrawable(getDrawable(Kender.getInstance().FF.setDrawCode(1)));
        polc = findViewById(R.id.polc);

        ventilObj = findViewById(R.id.ventil);



        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setCanceledOnTouchOutside(false);
        Button dialogButton = dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if(Kender.getInstance().FF.beKapcs)
            fátyol.setVisibility(View.VISIBLE);
        else
            fátyol.setVisibility(View.GONE);


        service = new Intent(MainActivity.this, LService.class);
        bindService(service, kapcsolat, Context.BIND_AUTO_CREATE);


        clearCanvas();




    }

    @Override
    protected void onPause() {
        super.onPause();

        thermoView.handler.removeCallbacks(thermoView.oo);

    }

    @Override
    protected void onResume() {
        super.onResume();

        fátyol.setImageDrawable(getDrawable(Kender.getInstance().FF.setDrawCode(1)));
        thermoView.handler.postDelayed(thermoView.oo,1000);
        kanna.setImageDrawable(getDrawable(Kender.getInstance().VV.setDrawCode()));
        cserép.setImageDrawable(getDrawable(Kender.getInstance().CC.setDrawableCode()));
        táp.setImageDrawable(getDrawable(Kender.getInstance().nutes.setDrawCode()));
        vectorDrawable =(AnimatedVectorDrawable) getDrawable(Kender.getInstance().FF.setDrawCode("a"));
        if(Kender.getInstance().FF.beKapcs) vectorDrawable.start();
        if(Kender.getInstance().FF.setDrawCode("a")==R.drawable.yellow_hps)
        bulb.setRotation(90);
        else bulb.setRotation(180);
        bulb.setImageDrawable(vectorDrawable);
    }

    public void cserépGomb(View v){
        if( quickAction==null) {
            // Initialize a new instance of LayoutInflater service
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

            // Inflate the custom layout/view
            View customView = inflater.inflate(R.layout.quick_action
                    , null);

                /*
                    public PopupWindow (View contentView, int width, int height)
                        Create a new non focusable popup window which can display the contentView.
                        The dimension of the window must be passed to this constructor.

                        The popup does not provide any background. This should be handled by
                        the content view.

                    Parameters
                        contentView : the popup's content
                        width : the popup's width
                        height : the popup's height
                */
            // Initialize a new instance of popup window
            quickAction = new PopupWindow(
                    customView,
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );


            quickAction.setElevation(5.0f);




            TextView quickText = customView.findViewById(R.id.quick_tv);
            quickText.setText("water "+Kender.getInstance().VV.getVÍZ_Mennyiség()
                    +"\nSugar "+Kender.getInstance().getCukor()+"\nStarch"+Kender.getInstance().getRost()
                    +"\nNatrium "+Kender.getInstance().nutes.N+"\nPhosphorus "+
                     Kender.getInstance().nutes.P
                    +"\nPotassium "+Kender.getInstance().nutes.K);
                /*
                    public void showAtLocation (View parent, int gravity, int x, int y)
                        Display the content view in a popup window at the specified location. If the
                        popup window cannot fit on screen, it will be clipped.
                        Learn WindowManager.LayoutParams for more information on how gravity and the x
                        and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
                        to specifying Gravity.LEFT | Gravity.TOP.

                    Parameters
                        parent : a parent view to get the getWindowToken() token from
                        gravity : the gravity which controls the placement of the popup window
                        x : the popup's x location offset
                        y : the popup's y location offset
                */

            quickAction.showAtLocation(cserép, Gravity.CENTER, 0, 0);
        }else {
            quickAction.dismiss();
            quickAction=null;
        }
    }



    private void clearCanvas() {
        thermoView.oo.run();
    }

    //
    public void cl(View v) {

        if(fátyol.getVisibility()==View.GONE){

            fátyol.setVisibility(View.VISIBLE);
            bulb.setBackground(getDrawable(R.drawable.feny60));
            vectorDrawable.start();
            findViewById(R.id.main).setBackgroundResource(R.drawable.httr_vil);
            Kender.getInstance().FF.beKapcs=true;
            polc.setElevation(5);
            polc.setCardElevation(60);
            Toast.makeText(this,"lámpa "+Kender.getInstance().FF.beKapcs,Toast.LENGTH_SHORT).show();
        }else if(fátyol.getVisibility()==View.VISIBLE&&!vectorDrawable.isRunning()){
            fátyol.setVisibility(View.GONE);
            vectorDrawable.reset();
            findViewById(R.id.main).setBackgroundResource(R.drawable.httr_stt);
            bulb.setBackgroundResource(outValue.resourceId);
            Kender.getInstance().FF.beKapcs=false;
            polc.setCardElevation(5);
            polc.setElevation(20);
            Toast.makeText(this,"lámpa "+Kender.getInstance().FF.beKapcs,Toast.LENGTH_SHORT).show();
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
    protected void onDestroy() {
        super.onDestroy();
        thermoView.handler.removeCallbacks(thermoView.oo);
        unbindService(kapcsolat);
        h.removeCallbacks(r);
        if(dialog.isShowing()) {

            dialog.dismiss();
        }

    }

    private ServiceConnection kapcsolat = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {


        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this, "onServiceConnected", Toast.LENGTH_SHORT).show();
            LService.Binderem myBinder = (LService.Binderem) service;
            lService = myBinder.getService();
            r.run();
            if(lService.isOOrunning)
            seed.setVisibility(View.GONE);
        }
    };

    Handler h = new Handler(Looper.myLooper());
    Runnable r = new Runnable() {
        @Override
        public void run() {

            canvasView.told(lService.al, lService.ism);
            napTV.setText(String.valueOf(lService.ism / 6));
            if(Kender.getInstance().flowering)ollo.setVisibility(View.VISIBLE);


            if (lService.stopIt) {

                if(lService.halott){
                    TextView text = dialog.findViewById(R.id.text);
                    text.setText("Your Plant Died!" +Kender.getInstance().causeofdeath+
                            "\n Raw Yield Salvaged: "+lService.hányGrammLett()+" gr");
                    ImageView image = dialog.findViewById(R.id.image);
                    image.setImageResource(R.drawable.koponyacsont);
                    h.removeCallbacks(r);

                    dialog.show();

                }else{
                    TextView text = dialog.findViewById(R.id.text);
                    text.setText("Produce Harvested!\n Raw Yield Collected: "+lService.hányGrammLett()+" gr");
                    ImageView image = dialog.findViewById(R.id.image);
                    image.setImageResource(R.drawable.koszoru);
                    h.removeCallbacks(r);

                    dialog.show();
                }

            }else  h.postDelayed(this, 600);

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


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(data, mShadow, null, 0);
                } else {
                    view.startDrag(data, mShadow, null, 0);
                }

                break;

            case R.id.locsol:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(data, mShadow, null, 0);
                } else {
                    view.startDrag(data, mShadow, null, 0);
                }
                break;

            case R.id.tap1:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(data, mShadow, null, 0);
                } else {
                    view.startDrag(data, mShadow, null, 0);
                }
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

                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                if(event.getClipDescription().getLabel().toString().equals("locsol")) {

                    loccs.start();
                    if(Kender.getInstance().VV.getVÍZ_Mennyiség()>=Kender.getInstance().CC.potSize/2) {
                        Kender.getInstance().CC.föld.flush();
                        alsFiok.flushIt();
                    }else
                        Kender.getInstance().VV.locsol();
                }
                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                ((ImageView) v).clearColorFilter();
                ((ImageView) v).setColorFilter(Color.YELLOW);

                v.invalidate();
                return true;

            case DragEvent.ACTION_DROP:
                String clipData = event.getClipDescription().getLabel().toString();
                if(clipData.equals("daseed")) {
                    lService.startThread();
                    gong.start();
                }
                else if(clipData.equals("tap1")){
                    nutriGoo.start();
                            Kender.getInstance().CC.föld.Nátrium+=Kender.getInstance().nutes.iN;
                            Kender.getInstance().CC.föld.Foszfor+=Kender.getInstance().nutes.iP;
                            Kender.getInstance().CC.föld.Kálium+=Kender.getInstance().nutes.iK;
                }

                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_ENDED:


                ((ImageView) v).clearColorFilter();
                if (event.getResult()) {
                    if(!lService.isOOrunning) seed.setVisibility(View.VISIBLE);
                    kanna.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Awesome!", Toast.LENGTH_SHORT).show();

                } else {
                    if(!lService.isOOrunning)
                    seed.setVisibility(View.VISIBLE);
                    kanna.setVisibility(View.VISIBLE);

                }
                return true;

            default:
                return false;
        }
    }

    public void szüret(View view){
        lService.szüretelve=true;
    }

    public void nyissMenut(View v){
        drawerLayout.openDrawer(Gravity.START);
    }
}


