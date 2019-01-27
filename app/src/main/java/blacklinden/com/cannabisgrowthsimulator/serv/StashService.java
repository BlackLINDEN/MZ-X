package blacklinden.com.cannabisgrowthsimulator.serv;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.StashActivity;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.pojo.Termény;

public class StashService extends JobService {
    Random random;
    public StashService() {
        random = new Random();
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        String rawList = Mentés.getInstance().getString(Mentés.Key.ERllT_LST,"0");
        int n=-1;
        if(!rawList.equals("0")){
            Gson gson = new Gson();
            Type tList = new TypeToken<ArrayList<Termény>>(){}.getType();
            List<Termény> termenyList = gson.fromJson(rawList,tList);
            ArrayList<Termény> tt = new ArrayList<>();
            n=termenyList.size();
            for(Termény t : termenyList){
                t.update();
                if(t.getStatus().equals("smelly")&&random.nextInt(10)+1==8)
                    t.setStatus("molded");

                tt.add(t);
            }
            rawList = Mentés.getInstance().gsonra(tt);
            System.out.println(rawList);
            Mentés.getInstance().put(Mentés.Key.ERllT_LST,rawList);
        }else
            jobFinished(jobParameters,true);


        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (this, 0, new Intent(this, StashActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle("stash")
                .setContentText(Integer.toString(n))
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.drawable.date_simbol)
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.FLAG_LOCAL_ONLY)
                .setAutoCancel(true);

        manager.notify(0, builder.build());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }


}
