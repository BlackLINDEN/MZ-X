package blacklinden.com.cannabisgrowthsimulator;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.pojo.Termény;
import blacklinden.com.cannabisgrowthsimulator.serv.StashService;

public class StashActivity extends AppCompatActivity {
    private static final int idozito = 90000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stash);

        TextView tv = findViewById(R.id.stashTV);
        tv.setText(Mentés.getInstance().getString(Mentés.Key.TRMS_LST,"üres")+"\n");
        //tételenként új custom view-t adok a recy-hez vagy a layout-hoz ( majd kiderül )
        /*String rawList = Mentés.getInstance().getString(Mentés.Key.TRMS_LST,"0");
        if(!rawList.equals("0")) {
            Gson gson = new Gson();
            Type tList = new TypeToken<ArrayList<Termény>>() {
            }.getType();
            List<Termény> termenyList = gson.fromJson(rawList, tList);
            for (Termény t : termenyList) {
                // t.update();

            }
        }*/

        JobScheduler jobScheduler = (JobScheduler)getApplicationContext()
                .getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName componentName = new ComponentName(this,
                StashService.class);

        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setOverrideDeadline(idozito).setRequiredNetworkType(
                        JobInfo.NETWORK_TYPE_NONE)
                .setPersisted(false).build();
        Objects.requireNonNull(jobScheduler).schedule(jobInfo);
    }
}
