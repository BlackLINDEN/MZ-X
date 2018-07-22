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

    public boolean auto_E;
    public String fajta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();

    }

    private void init(){


        checkBox = findViewById(R.id.auto_e);
        checkBox.setChecked(false);
        this.auto_E=checkBox.isChecked();
        checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                auto_E=isChecked;
            }
        });


        final RadioGroup radio1 =  findViewById(R.id.b1);
        //int checkedRadioButtonID = radGrp.getCheckedRadioButtonId();
        radio1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int id) {
                switch (id) {
                    case R.id.Sativa:
                        fajta="Sativa";
                        break;
                    case R.id.Indica:
                        fajta="Indica";
                        break;
                    case R.id.Hybrid:
                        fajta="Hybrid";
                        break;
                }
            }
        });



    }


    public void startSim(View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
