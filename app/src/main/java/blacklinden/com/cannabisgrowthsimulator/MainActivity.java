package blacklinden.com.cannabisgrowthsimulator;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.race604.drawable.wave.WaveDrawable;

import java.lang.ref.WeakReference;

import blacklinden.com.cannabisgrowthsimulator.canvas.CanvasView;
import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Fény;
import blacklinden.com.cannabisgrowthsimulator.eszk.ThermoView;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;
import blacklinden.com.cannabisgrowthsimulator.nov.L;
import blacklinden.com.cannabisgrowthsimulator.serv.LService;

public class MainActivity extends AppCompatActivity {

    CanvasView canvasView;
    ThermoView thermoView;

    CardView cardView;
    RadioGroup rg;
    ImageButton bulb,ventil;
    Animation animation;
    ImageView wIV;
    private WaveDrawable hullám;
    SeekBar sb1,sb2;
    boolean ventilBE;
    TextView napTV;
    private LService lService;
    private boolean bounded_e =false;
    Intent service;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.densityDpi;

        napTV = findViewById(R.id.nap);
        ventilBE=false;
        canvasView = findViewById(R.id.canvas);
        canvasView.metrix((density/160)*50);
        thermoView = findViewById(R.id.thermo);
        cardView = findViewById(R.id.cv);
        rg = findViewById(R.id.b2);
        bulb = findViewById(R.id.gmb);
        ventil = findViewById(R.id.ventil);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_ventil);
        sb1 = findViewById(R.id.sb1watt);
        sb2 = findViewById(R.id.sb2kelvin);
        wIV = findViewById(R.id.waterIV);
        hullám = new WaveDrawable(getDrawable(R.drawable.gmb));
        hullám.setWaveAmplitude(3);
        hullám.setWaveSpeed(5);
        hullám.setWaveLength(100);
        hullám.setIndeterminate(false);



        wIV.setImageDrawable(hullám);

        service = new Intent(MainActivity.this, LService.class);
        bindService(service,kapcsolat, Context.BIND_AUTO_CREATE);

        watt_kelvin();
        clearCanvas();

    }


    @Override
    protected void onPause(){
        super.onPause();

        thermoView.handler.removeCallbacks(thermoView.oo);

    }

    @Override
    protected void onResume(){
        super.onResume();



    }

    public void h2oSzab(){
        lService.setFigyi(new LService.figyi() {
            @Override
            public void init(String title) {

            }

            @Override
            public void H2O(float h2o) {

            }

            @Override
            public void NAP(int nap) {

            }
        });
    }

    public void watt_kelvin(){
        //watt
        sb1.setBackgroundColor(Color.BLACK);
       // sb1.setMin(30);
        sb1.setMax(2000);
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //if(b) Kender.getInstance().FF.setWatt(i);
                sb1.setProgress(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //kelvin
        sb2.setBackgroundColor(Color.BLACK);
        sb2.setMax(7000);
        //sb2.setMin(2500);
        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //if(b) Kender.getInstance().FF.setKelvin(i);
                sb2.setProgress(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void clearCanvas() {
                thermoView.oo.run();
            }
            public void cl(View v) {
                cardView.setVisibility(View.VISIBLE);
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        RadioButton rb = radioGroup.findViewById(i);
                        if (null != rb && i != -1) {

                            switch (i) {
                                case R.id.Led:
                                    //Kender.getInstance().FF.fajtaVlt("led");
                                    bulb.setImageResource(R.drawable.ic_launcher_foreground);
                                    break;

                                case R.id.Cfl:
                                    //Kender.getInstance().FF.fajtaVlt("cfl");
                                    bulb.setImageResource(R.drawable.cflicon);
                                    break;

                                case R.id.Inc:
                                    //Kender.getInstance().FF.fajtaVlt("inc");
                                    bulb.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
                                    break;

                                case R.id.Hps:
                                    //Kender.getInstance().FF.fajtaVlt("hps");
                                    bulb.setImageResource(R.drawable.kendericon);
                                    break;
                            }
                        }
                    }
                });

            }
            public void exitCV(View v){
                cardView.setVisibility(View.GONE);
            }

            public void locsol(View v){
                Kender.getInstance().VV.locsol();
            }

            public void ventilator(View v) {
                if (!ventilBE) {
                    ventil.startAnimation(animation);
                    ventilBE=true;
                }else{
                    animation.reset();
                    ventil.clearAnimation();
                    ventilBE=false;
                }
            }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        thermoView.handler.removeCallbacks(thermoView.oo);
        h.removeCallbacks(r);
        unbindService(kapcsolat);
    }





    private ServiceConnection kapcsolat = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bounded_e = false;

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this,"onServiceConnected",Toast.LENGTH_SHORT).show();
            LService.Binderem myBinder = (LService.Binderem) service;
            lService = myBinder.getService();
            bounded_e = true;
            r.run();
        }
    };

    Handler h = new Handler(Looper.myLooper());
    Runnable r = new Runnable() {
        @Override
        public void run() {
            canvasView.told(lService.al,lService.ism);
            napTV.setText(String.valueOf(lService.ism/6));
            hullám.setLevel(lService.hullám());
            h.postDelayed(this,600);
            if(lService.stopIt)finish();
        }
    };




        }


