package blacklinden.com.cannabisgrowthsimulator.canvas.kor;


import android.graphics.drawable.Drawable;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.pojo.Lamps;
import blacklinden.com.cannabisgrowthsimulator.ui.medium.Elem;

public class Fény {
    public float irány;
    private int lux;
    public int watt;
    private final float hőSötétben=25;
    private int kelvin;
    private float táv;


    private Égő égő;
    public int fatyolCode;
    public boolean beKapcs = false;
    public int drawCode;

    public Fény(String fajta){



     this.irány=0;
     setDrawCode("");


    }


    public int setDrawCode(String teszt){
        Lamps T = (Lamps)Mentés.getInstance().javara(Mentés.getInstance().getString(Mentés.Key.TESZT_OBJ),Lamps.class);

                égő=Égő.valueOf(T.getType());
                watt = T.getConsumption();
                kelvin = T.getSpectrum();
                lux = T.getLumen();
                drawCode = T.getAnimDrawCode();
                fatyolCode = T.getFatyolDrawCode();

        return drawCode;
    }

    public int setDrawCode(int i){
        Lamps T = (Lamps)Mentés.getInstance().javara(Mentés.getInstance().getString(Mentés.Key.TESZT_OBJ),Lamps.class);

        égő=Égő.valueOf(T.getType());
        watt = T.getConsumption();
        kelvin = T.getSpectrum();
        lux = T.getLumen();
        drawCode = T.getAnimDrawCode();
        fatyolCode = T.getFatyolDrawCode();


        return fatyolCode;
    }

    public void setWatt(int watt){
        this.watt=watt;
    }

    public void setKelvin(int kelvin){
        this.kelvin=kelvin;
    }

    public float hőmérséklet(){
        if(beKapcs)
        return (hőSötétben+(watt*égő.getHőszor_()));
        else
        return hőSötétben;
    }



    public void setIrány(){
        this.irány++;
    }


    public int getLux(){
        return lux;
    }

    public int getKelvin() { return kelvin;}



}