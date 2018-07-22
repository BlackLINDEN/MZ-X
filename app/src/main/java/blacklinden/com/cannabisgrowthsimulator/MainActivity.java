package blacklinden.com.cannabisgrowthsimulator;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.race604.drawable.wave.WaveDrawable;

import blacklinden.com.cannabisgrowthsimulator.canvas.CanvasView;
import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Fény;
import blacklinden.com.cannabisgrowthsimulator.eszk.ThermoView;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.densityDpi;
        Kender.getInstance();
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

        h2oSzab();
        watt_kelvin();
        clearCanvas();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        canvasView.handler.removeCallbacks(canvasView.oo);
        thermoView.handler.removeCallbacks(thermoView.oo);
    }

    @Override
    protected void onPause(){
        super.onPause();
        canvasView.handler.removeCallbacks(canvasView.oo);
        thermoView.handler.removeCallbacks(thermoView.oo);

    }

    @Override
    protected void onResume(){
        super.onResume();

        Kender.getInstance().initFény();
        Kender.getInstance().initRost();
        Kender.getInstance().initVíz();
        Kender.getInstance().initCukor();
        Kender.getInstance().initCO2();
        canvasView.handler.post(canvasView.oo);
    }

    public void h2oSzab(){
        canvasView.setFigyi(new CanvasView.figyi() {
            @Override
            public void init(String title) {

            }

            @Override
            public void H2O(float h2o) {
                hullám.setLevel((int)h2o);
            }

            @Override
            public void NAP(int nap) { napTV.setText(String.valueOf(nap/6));}
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
                if(b) Kender.getInstance().FF.setWatt(i);
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
                if(b) Kender.getInstance().FF.setKelvin(i);
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
                canvasView.oo.run();
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
                                    Kender.getInstance().FF.fajtaVlt("led");
                                    bulb.setImageResource(R.drawable.ic_launcher_foreground);
                                    break;

                                case R.id.Cfl:
                                    Kender.getInstance().FF.fajtaVlt("cfl");
                                    bulb.setImageResource(R.drawable.cflicon);
                                    break;

                                case R.id.Inc:
                                    Kender.getInstance().FF.fajtaVlt("inc");
                                    bulb.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
                                    break;

                                case R.id.Hps:
                                    Kender.getInstance().FF.fajtaVlt("hps");
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
                Kender.getInstance().setH2o();
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


        }


