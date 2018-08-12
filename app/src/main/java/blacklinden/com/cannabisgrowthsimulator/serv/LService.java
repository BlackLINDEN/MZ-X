package blacklinden.com.cannabisgrowthsimulator.serv;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;


import blacklinden.com.cannabisgrowthsimulator.BuildConfig;
import blacklinden.com.cannabisgrowthsimulator.MainActivity;
import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.nov.A;
import blacklinden.com.cannabisgrowthsimulator.nov.Av;
import blacklinden.com.cannabisgrowthsimulator.nov.F;
import blacklinden.com.cannabisgrowthsimulator.nov.Gy;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;
import blacklinden.com.cannabisgrowthsimulator.nov.L;
import blacklinden.com.cannabisgrowthsimulator.nov.Növény;
import blacklinden.com.cannabisgrowthsimulator.nov.V;
import blacklinden.com.cannabisgrowthsimulator.nov.X;
import blacklinden.com.cannabisgrowthsimulator.nov.menttolt.M;
import blacklinden.com.cannabisgrowthsimulator.nov.menttolt.T;

public class LService extends Service {



    public int ism=6;

    public figyi figyi;
    public boolean stopIt=false;
    public ArrayList<Növény> al = new ArrayList<>();
    private IBinder binderem = new Binderem();
    public static boolean IS_SERVICE_RUNNING = false;
    private Notif notif;

    public LService(){
        if(!IS_SERVICE_RUNNING) {
            Kender.getInstance();
            Kender.getInstance().initFény();
            Kender.getInstance().initRost();
            Kender.getInstance().initVíz();
            Kender.getInstance().initCukor();
            Kender.getInstance().initCO2();
        }
    }



    @Override
    public void onCreate() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notif = new Notif(this);
            Toast.makeText(getApplicationContext(),"Ay Ricky dont Eat Them Oreos",Toast.LENGTH_SHORT).show();

            Notification.Builder nb = notif.
                    getAndroidChannelNotification("GROWBOX","chuppie");

            notif.getManager().notify(101, nb.build());

            startForeground(101,
                    nb.build());

        }
        IS_SERVICE_RUNNING = true;
        if(al.isEmpty()) {
            F ff = new F(1);
            al.add(new Gy());
            al.add(ff);
            al.add(new M());
            al.add(new L(true, 1));
            al.add(new T());
            al.add(new M());
            al.add(new T());
            al.add(new M());
            al.add(new L(false, 1));
            al.add(new T());

        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(LService.this, "ON", Toast.LENGTH_SHORT).show();

            oo.run();

if(intent!=null) {
    switch ((Objects.requireNonNull(intent.getAction()))) {
        case Constants.ACTION.STARTFOREGROUND_ACTION:
            Log.i("log ", "Received Start Foreground Intent ");
            showNotification();
            Toast.makeText(this, "Service Started!", Toast.LENGTH_SHORT).show();

            break;
        case Constants.ACTION.STOPFOREGROUND_ACTION:
            Log.i("log ", "Received Stop Foreground Intent");
            stopForeground(true);
            //stopSelf();
            break;

    }
}

        return START_STICKY;
    }

    private void showNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);


        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher_foreground);


        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {


            Notification notification = new Notification.Builder(this)
                    .setContentTitle("FOrgroundTeszt")
                    .setTicker("teszt")
                    .setContentText("sor001")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setLargeIcon(icon)
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .setPriority(Notification.PRIORITY_MAX)
                    .build();

            startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,
                    notification);
        }









    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(LService.this,"Critical Overload. Battery Faileur Imminent!!!", Toast.LENGTH_SHORT).show();
        Toast.makeText(LService.this,"Turn Off Device to prevent Core Melt-down.", Toast.LENGTH_SHORT).show();
        Toast.makeText(LService.this,":D", Toast.LENGTH_LONG).show();
        handler.removeCallbacks(oo);
    }




    public Handler handler = new Handler(Looper.myLooper());
    public Runnable oo = new Runnable() {
        @Override
        public void run() {
            //figyi.H2O(Kender.getInstance().getH2o());
            //figyi.NAP(ism);


                ism();
                System.out.println("ISM "+ism);
                A(al,ism);
                handler.postDelayed(oo, 600);

        }
    };
    private void ism(){
        System.out.println("ISM "+ism);
        ism++;

    }



    private void A(ArrayList<Növény> aa, int sm){
        ArrayList<Növény> a = new ArrayList<>();
        Kender.getInstance().update();
        if(ism<500){
            for(Növény x:aa){

                x.élet();
                if(Objects.equals(x.n, "F")&&x.fejl()==50){
                    Toast.makeText(this,x.n,Toast.LENGTH_SHORT).show();
                    a.add(x);
                    a.add(new A(x.szint()));
                }
                else if(Objects.equals(x.n,"A")&&x.szint()<500){

                    a.add(new M());
                    a.add(new X(true,x.szint()));
                    a.add(new L(true,x.szint()));
                    a.add(new T());
                    a.add(new M());
                    a.add(new T());
                    a.add(new M());
                    a.add(new X(false,x.szint()));
                    a.add(new L(false,x.szint()));
                    a.add(new T());
                    a.add(new F(x.szint()));


                }else if(Objects.equals(x.n,"X")&&x.szint()==5){
                    a.add(new F(x.szint()));


                }else if(Objects.equals(x.n,"X")&&(int)x.fejl()==300){

                    a.add(new X(x.szög()<0,301,x.hossz()/2,x.szög(),3));
                    a.add(new L(x.szög()<0,4));
                    a.add(new X(x.szög()<0,301,x.hossz()/2,x.szög(),3));
                }else if (Objects.equals(x.n, "F")&&x.szint()>8&&ism>300) {

                    a.add(new Av(Kender.getInstance().Szintet()%x.szint()));
                    a.add(x);
                } else if (Objects.equals(x.n, "AV")&&x.fejl()>50) {

                    a.add(new V());
                } else if(Objects.equals(x.n,"V")&&x.fejl()>100){
                    a.add(x);
                    a.add(new V());
                }
                else {
                    a.add(x);
                }
            } al.clear();
            al.addAll(a); }

        else {
            stopSelf();
            stopForeground(true);
            Toast.makeText(this,hányGrammLett(al)+" Gramm",Toast.LENGTH_SHORT).show();
            handler.removeCallbacks(oo);
            stopIt=true;
        }

    }

    private float hányGrammLett(ArrayList<Növény> aaa){
        float gramm=0;
        for(Növény x:aaa){
            if(Objects.equals(x.n,"V")){
                gramm+=x.vastagság();
            }
        }
        return gramm/100;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binderem;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }



    public void setFigyi(figyi figyi) {
        this.figyi=figyi;
    }

    public interface figyi{
        void init(String title);
        void H2O(float h2o);
        void NAP(int nap);
    }

    public class Binderem extends Binder {
        public LService getService() {
            return LService.this;
        }
    }

    public int hullám(){
        return (int) Kender.getInstance().VV.getVÍZ_Mennyiség();
    }

}
