package blacklinden.com.cannabisgrowthsimulator.serv;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;

import android.os.Handler;

import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;

import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;



import blacklinden.com.cannabisgrowthsimulator.MainActivity;
import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;

import blacklinden.com.cannabisgrowthsimulator.nov.C;
import blacklinden.com.cannabisgrowthsimulator.nov.F;
import blacklinden.com.cannabisgrowthsimulator.nov.Gy;
import blacklinden.com.cannabisgrowthsimulator.nov.H;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;

import blacklinden.com.cannabisgrowthsimulator.nov.Növény;

import blacklinden.com.cannabisgrowthsimulator.nov.menttolt.M;
import blacklinden.com.cannabisgrowthsimulator.nov.menttolt.T;
import blacklinden.com.cannabisgrowthsimulator.pojo.Termény;

public class LService extends Service {


    public int ism = 6;
    private static final int vég =800;
    public boolean stopIt = false;
    public volatile ArrayList<Növény> al = new ArrayList<>();
    private IBinder binderem = new Binderem();
    public static volatile boolean IS_SERVICE_RUNNING = false;
    private Notif notif;

    public volatile boolean szüretelve=false;
    public volatile boolean halott;
    private Thread lthread;
    private C bC,jC;
    private ArrayList<Növény> a;

    public LService() {

        if (!IS_SERVICE_RUNNING) {
            Kender.getInstance();
            Kender.getInstance().initFény();
            Kender.getInstance().initRost();
            Kender.getInstance().initVíz();
            Kender.getInstance().initCukor();
            Kender.getInstance().initCO2();
            a = new ArrayList<>();

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


            al.add(new Gy());
            al.add(new F().init(0));
            al.add(new M());
            al.add(new H());
            al.add(new T());

        bC = new C(true);
        jC = new C(false);

        lthread = new Thread(oo);
        //lthread.setDaemon(true);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Mentés.getInstance(this);




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
        IS_SERVICE_RUNNING = true;
        lthread.start();

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

        handler.removeCallbacks(oo);
        ism=6;

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


                ism();
                System.out.println("ISM " + ism);

                A();

                handler.postDelayed(oo, 1800);
        }
    };

    private void ism() {
        System.out.println("ISM " + ism);
        ism++;

    }


    private void A() {
       a.clear();
        Kender.getInstance().update(ism);

        if (!Kender.getInstance().halott_e&&!szüretelve&&ism<vég) {

            for(Növény x:al) {


                        //oldalhajtás
                if(Objects.equals(x.n,"X")&&x.fejl()==20&&x.szint()>=2&&x.szint()<8
                        &&!Kender.getInstance().verem.f.empty()&&!Kender.getInstance().verem.üreseMT()){

                        a.add(Kender.getInstance().verem.m.pop());
                        a.add(Kender.getInstance().verem.f.pop().init(x.szint(),x.szög()));
                        a.add(Kender.getInstance().verem.t.pop());

                    a.add(x);


                    }

                    else if (Objects.equals(x.n,"H")&&x.fejl()==10){
                           a.add(Kender.getInstance().verem.m.pop());
                           a.add(bC);
                           a.add(Kender.getInstance().verem.t.pop());
                           a.add(Kender.getInstance().verem.m.pop());
                           a.add(jC);
                           a.add(Kender.getInstance().verem.t.pop());
                           a.add(Kender.getInstance().verem.a.pop().init(0));
                        }

                    else if (Objects.equals(x.n, "A")&& x.fejl()==x.szint()&&
                        !Kender.getInstance().flowering&&
                                !Kender.getInstance().verem.üreseValami()) {

                        Kender.getInstance().levonas(x.szint()*10);
                    if(x.hossz()==1) {
                        a.add(Kender.getInstance().verem.m.pop());
                        a.add(Kender.getInstance().verem.f.pop().init(x.szint()));
                        a.add(Kender.getInstance().verem.t.pop());

                        a.add(Kender.getInstance().verem.m.pop());
                        a.add(Kender.getInstance().verem.x.pop().init(true,x.szint()));
                        a.add(Kender.getInstance().verem.l.pop().init(true, x.szint()));
                        a.add(Kender.getInstance().verem.t.pop());
                        a.add(Kender.getInstance().verem.m.pop());
                        a.add(Kender.getInstance().verem.x.pop().init(false,x.szint()));
                        a.add(Kender.getInstance().verem.l.pop().init(false, x.szint()));
                        a.add(Kender.getInstance().verem.t.pop());
                    }else{
                        a.add(Kender.getInstance().verem.m.pop());
                        a.add(Kender.getInstance().verem.f.pop().init(x.szint(),x.szög()));
                        a.add(Kender.getInstance().verem.t.pop());

                        a.add(Kender.getInstance().verem.m.pop());
                        a.add(Kender.getInstance().verem.l.pop().init(true, x.szint()));
                        a.add(Kender.getInstance().verem.t.pop());
                        a.add(Kender.getInstance().verem.m.pop());
                        a.add(Kender.getInstance().verem.l.pop().init(false, x.szint()));
                        a.add(Kender.getInstance().verem.t.pop());

                    }

                }

                    else if (Objects.equals(x.n, "F") && x.szint()>1 &&

                         Kender.getInstance().flowering
                                &&!Kender.getInstance().verem.av.empty()) {

                            a.add(x);
                            a.add(Kender.getInstance().verem.av.pop().init(x.szint()));

                        }


                        else if (Objects.equals(x.n, "F") && x.fejl() == 30 && x.szint()>0&&
                                !Kender.getInstance().verem.a.empty()) {

                            a.add(x);
                            x.vízigény();
                            if(x.légz()==0)
                            a.add(Kender.getInstance().verem.a.pop().init(x.szint()));
                            else if(x.légz()==1&&x.szint()<8)
                            a.add(Kender.getInstance().verem.a.pop().init(x.szint(),x.szög()));

                }


                    else if (Objects.equals(x.n, "AV") && x.fejl() > 50&&
                                !Kender.getInstance().verem.v.empty()) {
                            a.add(Kender.getInstance().verem.v.pop());
                        } else if (Objects.equals(x.n, "V") && x.fejl() > 100&&Kender.getInstance().Szintet()<100
                                &&!Kender.getInstance().verem.v.empty()) {
                            a.add(x);
                            a.add(Kender.getInstance().verem.v.pop());
                        } else {
                            a.add(x);
                        }

                x.élet();

            }
            if(!a.isEmpty()) {
                al.clear();
                al.addAll(a);
            }
        }

        else{

             vége();

                //Toast.makeText(this, hányGrammLett(al) + " Gramm", Toast.LENGTH_SHORT).show();

            }

        }

        private void vége(){
            handler.removeCallbacks(oo);


            System.out.println("<<<<<<||::.VÉGE.::||>>>>>>");

            halott=Kender.getInstance().halott_e;
            //saveWeed();
            stopIt=true;
            stopForeground(false);
            stopSelf();

        }

        public void saveWeed(){
            Gson gson = new Gson();
            Type tList = new TypeToken<ArrayList<Termény>>(){}.getType();
            List<Termény> termenyList = gson.fromJson(Mentés.getInstance().getString(Mentés.Key.TRMS_LST),tList);
            termenyList.add(new Termény(hányGrammLett(),Kender.getInstance().getFajta(),15,1));
            String ment = Mentés.getInstance().gsonra(termenyList);
            Mentés.getInstance().put(Mentés.Key.TRMS_LST,ment);
        }


        public float hányGrammLett () {
            float gramm=0;
            for (Növény x : al) {
                if (Objects.equals(x.n, "V")) {
                    gramm += x.vastagság();
                }

            }
            if(gramm==0) return gramm;
            else
            return gramm/100;
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
