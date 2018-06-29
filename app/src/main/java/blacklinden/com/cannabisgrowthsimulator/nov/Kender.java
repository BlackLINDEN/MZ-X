package blacklinden.com.cannabisgrowthsimulator.nov;


import blacklinden.com.cannabisgrowthsimulator.Main2Activity;
import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Fény;
import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Lég;

public class Kender {

    private static float co2;

    private static String fajta;



    private static boolean auto_e;



    private static float h2o;

    private static int hő;

    private static int szint;

    public static void Szint(){
        Kender.szint++;
    }
    public static int Szintet(){return szint;}
    public static boolean Halott_e() {
        return halott_e;
    }

    public static void setHalott_e(boolean halott_e) {
        Kender.halott_e = halott_e;
    }

    private static boolean halott_e=false;


    public static void setRost(float rost) {
        Kender.rost = rost;
    }

    public static float getRost() {
        return rost;
    }

    private static float rost;

    private static float cukor;
    public static float fény=0;//százalék

    public Kender() {
        Kender.cukor=0;//1000000;
        Kender.rost=100;
        Kender.co2=10;
        Kender.h2o=1;
        Kender.szint=1;
        Kender.auto_e=Main2Activity.auto_E;
        Kender.fajta=Main2Activity.fajta;
        new Fény();

    }
    public static void update(){
        calvinKör();
        rostbanCukorTárolás();


        if(cukor<=0&&rost>=1){
            cukor++;
            rost--;
        }else if(cukor<=0&&rost<=0) {
            cukor = 0;}
        else if(Fény.irány<-5){
            halott_e=true;
            }

        }





    private static void calvinKör(){

        if (co2 > 6 && h2o > 6&&fény>0) {
            co2 -= 0.006f;
            //bődületes faszság
            h2o=(h2o>=0)?h2o-=(Fény.hőmérséklet()+szint):0;
            cukor+=10;
            Lég._O2();
        }

        if(h2o>5000)
            co2=5;
        else
            co2=10;

    }

    private static void rostbanCukorTárolás(){
        if(cukor>1000){
            rost++;
            cukor--;
        }
    }

    public static float cukrozó(float levonás){

        if(cukor<levonás)
            return 0;
        else{
            cukor-=levonás;
            return levonás;
        }
    }

    public static void CO2(float co2PPM){
        Kender.co2+=co2PPM;
    }
    public static void setH2o() {

        Kender.h2o+=10000;
    }

    public static float getH2o() {
        return h2o;
    }

    public static float getCukor() {
        return cukor;
    }


}