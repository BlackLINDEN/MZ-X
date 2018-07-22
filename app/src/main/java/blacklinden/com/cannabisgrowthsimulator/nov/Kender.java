package blacklinden.com.cannabisgrowthsimulator.nov;


import blacklinden.com.cannabisgrowthsimulator.Main2Activity;
import blacklinden.com.cannabisgrowthsimulator.MainActivity;
import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Fény;
import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Lég;

public final class Kender {

    private static volatile Kender instance = null;

    private float co2;

    private String fajta;

    public Fény FF;
    public Lég LL;


    private boolean auto_e;



    private float h2o;

    private int hő;

    private int szint;


    private boolean halott_e=false;



    public float getRost() {
        return rost;
    }

    public void initRost(){this.rost=100;}

    private float rost;

    private float cukor;
    public float fény=0;//százalék

    private Kender() {
        //1000000;
        initRost();
        initCO2();

        this.szint=1;
        this.LL = new Lég();
        initFény();
        initVíz();
    }

    public void initFény(){
        this.FF=new Fény("led");
    }
    public void initCukor(){
        this.cukor=0;
    }
    public void initCO2(){
        this.co2=10;
    }
    public void initVíz(){
        this.h2o=1;
    }
    public void update(){

        calvinKör();
        rostbanCukorTárolás();


        if(cukor<=0&&rost>=1){
            cukor++;
            rost--;
        }else if(cukor<=0&&rost<=0) {
            cukor = 0;}
        else if(FF.irány<-5){
            halott_e=true;
            }

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

    private void calvinKör(){

        if (co2 > 6 && h2o > 6&&fény>0) {

            h2o-=szint*0.01;
            cukor+=10;
            LL._O2();
        }

        if(h2o>5000)
            co2=5;
        else
            co2=10;

        if(FF.hőmérséklet()>27&&h2o>0)
            h2o--;
        else if(h2o>0)
            h2o-=0.1;
        else
            h2o=0;

    }

    private void rostbanCukorTárolás(){
        if(cukor>1000){
            rost++;
            cukor--;
        }
    }

    public float cukrozó(float levonás){

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
    public void setH2o() {

        h2o+=10000;
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
    public boolean Halott_e() {
        return halott_e;
    }



}