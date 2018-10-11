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

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;


import blacklinden.com.cannabisgrowthsimulator.BuildConfig;
import blacklinden.com.cannabisgrowthsimulator.MainActivity;
import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.nov.A;
import blacklinden.com.cannabisgrowthsimulator.nov.Av;
import blacklinden.com.cannabisgrowthsimulator.nov.C;
import blacklinden.com.cannabisgrowthsimulator.nov.F;
import blacklinden.com.cannabisgrowthsimulator.nov.Gy;
import blacklinden.com.cannabisgrowthsimulator.nov.H;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;
import blacklinden.com.cannabisgrowthsimulator.nov.L;
import blacklinden.com.cannabisgrowthsimulator.nov.Növény;
import blacklinden.com.cannabisgrowthsimulator.nov.V;
import blacklinden.com.cannabisgrowthsimulator.nov.X;
import blacklinden.com.cannabisgrowthsimulator.nov.menttolt.M;
import blacklinden.com.cannabisgrowthsimulator.nov.menttolt.T;

public class LService extends Service {


    public int ism = 6;
    private int vég =800;
    public boolean stopIt = false;
    public ArrayList<Növény> al = new ArrayList<>();
    private IBinder binderem = new Binderem();
    public static volatile boolean IS_SERVICE_RUNNING = false;
    private Notif notif;
    public volatile boolean isOOrunning=false;
    public boolean szüretelve=false;
    public boolean halott;

    public LService() {
        if (!IS_SERVICE_RUNNING) {
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
            Intent notificationIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);
            Notification.Builder nb = notif.
                    getAndroidChannelNotification("GROWBOX", "Grow Operation In-Progress");
            nb.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.kendericon));
            nb.setSmallIcon(R.mipmap.type_simbol);
            nb.setContentIntent(pendingIntent);
            notif.getManager().notify(101, nb.build());

            startForeground(101,
                    nb.build());

        }
        IS_SERVICE_RUNNING = true;

            al.add(new Gy());
            al.add(new F(0));
            al.add(new M());
            al.add(new H());
            al.add(new T());



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {





        if (intent != null) {
            switch ((Objects.requireNonNull(intent.getAction()))) {
                case Constants.ACTION.STARTFOREGROUND_ACTION:
                    Log.i("log ", "Received Start Foreground Intent ");
                    showNotification();


                    break;
                case Constants.ACTION.STOPFOREGROUND_ACTION:
                    Log.i("log ", "Received Stop Foreground Intent");
                    stopForeground(true);

                    break;

            }
        }

        return START_STICKY;
    }

    public void startThread(){
        oo.run();
    }

    private void showNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);


        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.kendericon);


        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {


            Notification notification = new Notification.Builder(this)
                    .setContentTitle("GROWBOX")
                    .setTicker("Grow Operation")
                    .setContentText("In-Progress")
                    .setSmallIcon(R.mipmap.type_simbol)
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
        al.clear();
        Kender.getInstance().clear();
        IS_SERVICE_RUNNING=false;
    }


    public Handler handler = new Handler(Looper.myLooper());
    public Runnable oo = new Runnable() {
        @Override
        public void run() {
            //figyi.H2O(Kender.getInstance().getH2o());
            //figyi.NAP(ism);

            isOOrunning=true;
            ism();
            System.out.println("ISM " + ism);
            Kender.getInstance().update(ism);
            A(al);
            handler.postDelayed(oo, 12000);

        }
    };

    private void ism() {
        System.out.println("ISM " + ism);
        ism++;

    }


    private void A(ArrayList<Növény> aa) {
        ArrayList<Növény> a = new ArrayList<>();


        if (!Kender.getInstance().halott_e&&!szüretelve&&ism<vég) {
            for (Növény x : aa) {

                x.élet();


                        if (Objects.equals(x.n, "F") && x.fejl() == 20 && x.szint()>0) {
                            a.add(x);
                            a.add(new A(x.szint()));
                        }else if(Objects.equals(x.n,"X")&&x.szint()>1&&x.fejl()==30){
                            a.add(new M());
                            a.add(new F(x.szög()));
                            a.add(new T());
                            a.add(x);

                        }else if (Objects.equals(x.n,"H")&&x.fejl()==10){
                           a.add(new M());
                           a.add(new C(true));
                           a.add(new T());
                           a.add(new M());
                           a.add(new C(false));
                           a.add(new T());
                           a.add(new A(0));
                        }else if (Objects.equals(x.n, "A")&& x.szint() < 500) {


                            a.add(new M());
                            a.add(new X(true, x.szint()));
                            a.add(new L(true, x.szint()));
                            a.add(new T());
                            a.add(new M());
                            a.add(new X(false, x.szint()));
                            a.add(new L(false, x.szint()));
                            a.add(new T());
                            a.add(new F(x.szint()));


                        }else if (Objects.equals(x.n, "F") && x.szint()>1 && Kender.getInstance().flowering) {
                            a.add(x);
                            a.add(new Av(x.szint()));

                        } else if (Objects.equals(x.n, "AV") && x.fejl() > 50) {
                            a.add(new V());
                        } else if (Objects.equals(x.n, "V") && x.fejl() > 100) {
                            a.add(x);
                            a.add(new V());
                        } else {
                            a.add(x);
                        }

            }

            al.clear();
            al.addAll(a);
        }

        else{

             vége();

                //Toast.makeText(this, hányGrammLett(al) + " Gramm", Toast.LENGTH_SHORT).show();

            }

        }

        private void vége(){
            handler.removeCallbacks(oo);
            halott=Kender.getInstance().halott_e;
            stopIt = true;
            stopForeground(true);
            stopSelf();
        }


        public float hányGrammLett () {
            float gramm=10;
            for (Növény x : al) {
                if (Objects.equals(x.n, "V")) {
                    gramm += x.vastagság();
                }
            }
            return gramm;
        }

        @Nullable
        @Override
        public IBinder onBind (Intent intent){
            return binderem;
        }

        @Override
        public boolean onUnbind (Intent intent){
            return true;
        }

        @Override
        public void onRebind (Intent intent){
            super.onRebind(intent);
        }



        public class Binderem extends Binder {
            public LService getService() {
                return LService.this;
            }
        }

        public int hullám () {
            return (int) Kender.getInstance().VV.getVÍZ_Mennyiség();
        }

    }
