package blacklinden.com.cannabisgrowthsimulator;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Fény;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;
import blacklinden.com.cannabisgrowthsimulator.serv.Constants;
import blacklinden.com.cannabisgrowthsimulator.serv.LService;

public class Main2Activity extends AppCompatActivity {

    public CheckBox checkBox;

    public boolean auto_E;
    public String fajta;
    Intent service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();

    }

    private void init(){

        service = new Intent(Main2Activity.this, LService.class);


    }


    public void startSim(View v){

        Intent i = new Intent(this,MainActivity.class);
        if(!LService.IS_SERVICE_RUNNING) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                service.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
                startForegroundService(service);
            }else {
                service.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
                //LService.IS_SERVICE_RUNNING = true;
                startService(service);
            }
        }
        startActivity(i);
    }
}
