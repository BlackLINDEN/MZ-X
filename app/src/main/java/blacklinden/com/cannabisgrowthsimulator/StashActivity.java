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

import java.util.Objects;

import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.serv.StashService;

public class StashActivity extends AppCompatActivity {
    private static final int idozito = 90000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stash);

        TextView tv = findViewById(R.id.stashTV);
        tv.setText(Mentés.getInstance().getString(Mentés.Key.TRMS_LST,"üres")+"\n");

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
