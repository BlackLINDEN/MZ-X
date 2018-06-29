package blacklinden.com.cannabisgrowthsimulator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Fény;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;

public class Main2Activity extends AppCompatActivity {

    public CheckBox checkBox;

    public static boolean auto_E;
    public static String fajta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();

    }

    private void init(){


        checkBox = findViewById(R.id.auto_e);
        checkBox.setChecked(false);
        Main2Activity.auto_E=checkBox.isChecked();
        checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Main2Activity.auto_E=isChecked;
            }
        });


        final RadioGroup radio1 =  findViewById(R.id.b1);
        //int checkedRadioButtonID = radGrp.getCheckedRadioButtonId();
        radio1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int id) {
                switch (id) {
                    case R.id.Sativa:
                        Main2Activity.fajta="Sativa";
                        break;
                    case R.id.Indica:
                        Main2Activity.fajta="Indica";
                        break;
                    case R.id.Hybrid:
                        Main2Activity.fajta="Hybrid";
                        break;
                }
            }
        });


        RadioGroup radio2 =  findViewById(R.id.b2);
        //int checkedRadioButtonID = radGrp.getCheckedRadioButtonId();
        radio2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int id) {
                switch (id) {
                    case R.id.Led:
                        Fény.setFajta("led");
                        break;
                    case R.id.Inc:
                        Fény.setFajta("inc");
                        break;
                    case R.id.Hps:
                        Fény.setFajta("hps");
                        break;
                    case R.id.Cfl:
                        Fény.setFajta("cfl");
                        break;
                }
            }
        });

        NP();

    }

    private void NP(){

        final String[] wattValues = new String[20];

        for (int i = 0; i < wattValues.length; i++) {
            String number = Integer.toString(i*50);
            wattValues[i] = number.length() < 2 ? "0" + number : number;
        }
        NumberPicker np = findViewById(R.id.watt);
        np.setEnabled(true);
        np.setDisplayedValues(wattValues);
        np.setWrapSelectorWheel(true);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
               //Fény.watt=Integer.parseInt(wattValues[newVal]);
            }
        });
    }

    public void startSim(View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
