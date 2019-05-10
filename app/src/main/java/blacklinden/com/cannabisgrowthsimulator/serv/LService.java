package blacklinden.com.cannabisgrowthsimulator.serv;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import blacklinden.com.cannabisgrowthsimulator.Main2Activity;
import blacklinden.com.cannabisgrowthsimulator.MainActivity;
import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.nov.C;
import blacklinden.com.cannabisgrowthsimulator.nov.F;
import blacklinden.com.cannabisgrowthsimulator.nov.Gy;
import blacklinden.com.cannabisgrowthsimulator.nov.H;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;
import blacklinden.com.cannabisgrowthsimulator.nov.L;
import blacklinden.com.cannabisgrowthsimulator.nov.Növény;
import blacklinden.com.cannabisgrowthsimulator.nov.menttolt.M;
import blacklinden.com.cannabisgrowthsimulator.nov.menttolt.T;
import blacklinden.com.cannabisgrowthsimulator.pojo.Termény;

@SuppressWarnings("ALL")
public class LService extends Service {


    public int ism = 6;
    private static final int vég =800;
    public boolean stopIt = false;
    public volatile ArrayList<Növény> al = new ArrayList<>();
    private IBinder binderem = new Binderem();
    public static volatile boolean IS_SERVICE_RUNNING = false;
    private Notif notif;
    private Notification notification;

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

        notificationForO("In Progress");

            al.add(new Gy());
            al.add(new F(Kender.getInstance().getFajta()).init(0));
            al.add(new M());
            al.add(new H());
            al.add(new T());

        bC = new C(true);
        jC = new C(false);

        lthread = new Thread(oo);
        lthread.setPriority(Thread.MAX_PRIORITY);
        //lthread.setDaemon(true);




    }

    private void notificationForO(String uzenet){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notif = new Notif(this);
            Intent notificationIntent = new Intent(this, Main2Activity.class);
            PendingIntent pendingIntent = TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(notificationIntent)
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification.Builder nb = notif.
                    getAndroidChannelNotification("GROWBOX", uzenet);
            nb.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.kendericon));
            nb.setSmallIcon(R.mipmap.type_simbol);
            nb.setContentIntent(pendingIntent);
            nb.setOnlyAlertOnce(true);

            notif.getManager().notify(101, nb.build());

            startForeground(101,
                    nb.build());

        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Mentés.getInstance(this);




        if (intent != null) {
            switch ((Objects.requireNonNull(intent.getAction()))) {
                case Constants.ACTION.STARTFOREGROUND_ACTION:
                    Log.i("log ", "Received Start Foreground Intent ");
                    showNotification("In Progress");


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

    private void showNotification(String uzenet) {
        Intent notificationIntent = new Intent(this, Main2Activity.class);
        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        //notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(notificationIntent)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.kendericon);


        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {


            notification = new Notification.Builder(this)
                    .setContentTitle("GROWBOX")

                    .setTicker("Grow Operation")
                    .setContentText(uzenet)
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
                ism();
                A();
                handler.postDelayed(oo, 2000);
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
                if(Objects.equals(x.n,"X")&&x.fejl()==20&&x.szint()>=2&&x.szint()<12
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

                    else if (Objects.equals(x.n, "A")&& x.fejl()==1&&
                        !Kender.getInstance().flowering&&

                                !Kender.getInstance().verem.üreseValami()) {



                        //oldalhajtás-e
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
                        a.add(Kender.getInstance().verem.l.pop().init(true, x.szint(),1));
                        a.add(Kender.getInstance().verem.t.pop());
                        a.add(Kender.getInstance().verem.m.pop());
                        a.add(Kender.getInstance().verem.l.pop().init(false, x.szint(), 1));
                        a.add(Kender.getInstance().verem.t.pop());

                    }

                }

                    else if (Objects.equals(x.n, "F") && Kender.getInstance().Szintet()-x.szint()<7 &&
                         Kender.getInstance().flowering
                                &&!Kender.getInstance().verem.av.empty()) {

                            a.add(x);
                            a.add(Kender.getInstance().verem.av.pop().init(x.szint()));

                        }


                        else if (Objects.equals(x.n, "F") && x.fejl() == 30 && x.szint()>0) {

                            if(Kender.getInstance().getRost()>=x.szint()&&
                                    Kender.getInstance().verem.a.size()>=1) {
                                //légz() nem légzés itt
                                a.add(x);
                                x.vízigény();
                                if (x.légz() == 0)
                                    a.add(Kender.getInstance().verem.a.pop().init(x.szint()));
                                else if (x.légz() == 1 && x.szint() < 8)
                                    a.add(Kender.getInstance().verem.a.pop().init(x.szint(), x.szög()));
                                Kender.getInstance().levonas(x.szint());
                            }else x.vízigény();

                }


                    else if (Objects.equals(x.n, "AV") && x.fejl() > Kender.getInstance().Szintet()-x.szint()*200&&
                                !Kender.getInstance().verem.v.empty()) {
                            a.add(Kender.getInstance().verem.v.pop());
                        } else if (Objects.equals(x.n, "V") && x.fejl() > Kender.getInstance().Szintet()-x.szint()*1000&&Kender.getInstance().Szintet()<100
                                &&!Kender.getInstance().verem.av.empty()) {
                            a.add(x);
                            a.add(Kender.getInstance().verem.av.pop());
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

            }

        }

        public void vége(){
            handler.removeCallbacks(oo);

            halott=Kender.getInstance().halott_e;

            if(halott){
                showNotification("Your Plant Died");
                notificationForO("Your Plant Died");
            }else{
                showNotification("Auto Harvest");
                notificationForO("Auto Harvest");
            }
            //saveWeed();
            stopIt=true;
            stopForeground(false);
            stopSelf();

        }

        public void saveWeed(){
            if(hányGrammLett()>=1) {
                Gson gson = new GsonBuilder().create();
                Type tList = new TypeToken<ArrayList<Termény>>() {
                }.getType();
                List<Termény> termenyList = gson.fromJson(Mentés.getInstance().getString(Mentés.Key.TRMS_LST), tList);

                termenyList.add(new Termény(darabraMennyi(), hányGrammLett(), Kender.getInstance().getFajta(), 15, 1));
                String ment = Mentés.getInstance().gsonra(termenyList);
                Mentés.getInstance().put(Mentés.Key.TRMS_LST, ment);
            }
        }


        public int hányGrammLett () {
            float gramm=0;
            for (Növény x : al) {
                if (Objects.equals(x.n, "V")) {
                    gramm += x.vastagság();
                }

            }
            if(gramm==0) return 0;
            else
            return (int)gramm/1000;
        }

        private int darabraMennyi(){
        int i=0;
        for(Növény x:al){
            if (Objects.equals(x.n, "V")) {
                i++;
            }
        }
        return i;
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

        public void harvest(){
        szüretelve=true;
        handler.post(oo);

        }

        public void trim(){
        handler.post(oo);
            al.remove(L.class);
        }

    }
