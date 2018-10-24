package blacklinden.com.cannabisgrowthsimulator.nov;





import android.content.Context;

import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Cserép;
import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Fény;
import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Lég;
import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Verem;
import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Víz;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.eszk.Nutes;

public final class Kender {

    private static volatile Kender instance = null;

    private int co2;

    private int fajta;

    public Fény FF;
    public Lég LL;
    public Víz VV;
    public Cserép CC;
    private float metrix;
    public Nutes nutes;
    public int halottRészek=0;

    private boolean auto_e;

    public boolean flowering;

    private float h2o;

    private int hő;

    private int szint;

    public boolean halott_e;

    public float getRost() {
        return rost;
    }

    public void initRost(){this.rost=10;}

    private float rost;
    private Context context;
    public Verem verem;
    private float cukor;
    public float fény=0;//százalék
    public volatile String causeofdeath="";

    private Kender() {
        //1000000;
        verem = new Verem();
        initRost();
        initCO2();
        this.flowering=false;
        this.halott_e=false;
        this.szint=1;
        this.LL = new Lég();
        this.VV = new Víz();
        this.CC = new Cserép(1.5f,3,"super");
        initFény();
        initVíz();

        nutes = new Nutes();

    }

    public void clear(){
        instance = null;
    }

    public void initFény(){

        this.FF=new Fény(Mentés.getInstance().getString(Mentés.Key.SAMPLE_STR,"Advanced Star PRO STAR DUAL"));
    }
    public void initCukor(){
        this.cukor=1;
    }
    public void initCO2(){
        this.co2=1;
    }
    public void initVíz(){
        this.h2o=1;
    }


    public void update(int ism){
        System.out.println("Szint: "+szint);
        calvinKör();
        rostbanCukorTárolás();
        switch (fajta) {
            case 1:
                if (ism == 400) flowering = true;
                break;
            case 2:
                if (ism == 380) flowering = true;
                break;
        }
        if(cukor<=0&&rost>=1){
            cukor++;
            rost--;
        }else if(cukor<=0&&rost<=0) {
            cukor = 0;}

         if(halottRészek>2)
             halott_e=true;

        }

    public static Kender getInstance() {
        if (instance == null) {
            synchronized(Kender.class) {
                if (instance == null) {
                    instance = new Kender();
                }
            }
        }
        return instance;
    }

    public void fajta(int fajta){
        this.fajta=fajta;
    }

    public int getFajta(){
        return fajta;
    }

    private void calvinKör(){

        if(co2>0&&h2o>1&&h2o<200&&fény>0&&FF.beKapcs) {
            int nutri=(nutes.N+ nutes.P+ nutes.K);
            cukor +=  nutri+fény+co2;

                if(nutes.N>0) nutes.N--;
                if(nutes.P>0) nutes.P--;
                if(nutes.K>0) nutes.K--;

            co2--;
            h2o--;
            fény--;
        }

        if(FF.hőmérséklet()>27&&h2o>0)
            h2o-=2f;
        else
            h2o-=1f;


    }

    private void rostbanCukorTárolás(){
        if(cukor>1000){
            rost+=10;
            cukor-=10;
        }
    }

    public float cukrozó(int levonás){

        if(cukor<levonás)
            return 0;
        else{
            cukor-=levonás;
            return levonás;
        }
    }

    public void CO2(float co2PPM){
        co2+=co2PPM;
    }
    public void setH2o(float xx) {

        h2o+=xx;
    }

    public float getH2o() {
        return h2o;
    }
    public float getCukor() {
        return cukor;
    }
    public void Szint(){
        szint++;
    }
    public int Szintet(){return szint;}

    public void metrix(float metrix){
        this.metrix=metrix;
    }
    public boolean Halott_e() {
        return halott_e;
    }
    public void setContext(Context c){context=c;}
    public Context getContext(){return context;}





}