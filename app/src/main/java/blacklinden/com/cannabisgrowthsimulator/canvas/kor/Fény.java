package blacklinden.com.cannabisgrowthsimulator.canvas.kor;

import blacklinden.com.cannabisgrowthsimulator.Main2Activity;

public class Fény {
    public static float irány=0f;
    private static int lux;
    public static int watt=500;
    private static final float hőSötétben=25;
    private static int kelvin;
    private static float táv;
    private static String fajta;
    private static Égő égő;
    private static final float wattVáltóSzám=0.00052656506684073f;

    public Fény(){

     égő = Égő.CFL;

     Fény.irány=0;

    }

    public static float hőmérséklet(){
        Fény.fajta=Main2Activity.fajta;

        switch(Fény.fajta){
            case "led":Fény.égő=Égő.LED;
                break;
            case "cfl":Fény.égő=Égő.CFL;
                break;
            case "hps":Fény.égő=Égő.HALOGEN;
                break;
            case "inc":Fény.égő=Égő.INCANDESCENT;
                break;
        }
        return (hőSötétben+(((watt*wattVáltóSzám)*Fény.égő.getHőszor_())));
    }

    public static void setIrány(float d){
        irány=d;
    }

    public static void setIrány(){
        irány++;
    }


    public int getLux(){
        return lux;
    }


    public static void setFajta(String fajta) {
        Fény.fajta = fajta;
    }

}