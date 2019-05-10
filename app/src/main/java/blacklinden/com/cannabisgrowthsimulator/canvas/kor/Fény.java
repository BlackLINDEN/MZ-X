package blacklinden.com.cannabisgrowthsimulator.canvas.kor;


import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;
import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.pojo.Lamps;
import blacklinden.com.cannabisgrowthsimulator.ui.medium.Elem;

public class Fény {
    public float irány;
    private int lux;
    public int watt;
    private float hőSötétben=25;
    private int kelvin;
    private float táv;


    private Égő égő;
    public int fatyolCode;
    public boolean beKapcs = false;
    public int drawCode;
    private Lamps defT;

    public Fény(){



     this.irány=0;
        defT =new Lamps("HPS Grow","HPS",600,2500,10200, R.drawable.avd_anim,R.drawable.narancs_csova);
     setDrawCode("");


    }


    public int setDrawCode(String teszt){
        String s = Mentés.getInstance().getString(Mentés.Key.TESZT_OBJ,"o");
        if(!s.equals("o")) {
            Lamps T = (Lamps) Mentés.getInstance().javara(s, Lamps.class);

            égő = Égő.valueOf(T.getType());
            watt = T.getConsumption();
            kelvin = T.getSpectrum();
            lux = T.getLumen();
            drawCode = T.getAnimDrawCode();
            fatyolCode = T.getFatyolDrawCode();

            return drawCode;
        }else{

            Mentés.getInstance().put(Mentés.Key.TESZT_OBJ,Mentés.getInstance().gsonra(
                    new Lamps("HPS Grow","HPS",600,2500,10200,
                    R.drawable.avd_anim,R.drawable.narancs_csova
                    )
            ));

            return setDrawCode("");
        }

    }

    public int setDrawCode(int i){
        //alapérték ide jön
        Lamps T;
        String s = Mentés.getInstance().getString(Mentés.Key.TESZT_OBJ,"0");
        if(s.equals("0"))
         T = defT;
        else
         T = (Lamps)Mentés.getInstance().javara(s,Lamps.class);

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
        if(Math.abs(irány)<5)irány++;
    }

    public void resetIrány(){if(irány>0)irány--;}

    public void setIrány(int i){
        irány=i;
    }
    public int getLux(){
        return lux;
    }

    public int getKelvin() { return kelvin;}
    public void setHő(){
        if(hőSötétben>22.5f)
        hőSötétben--;
    }


}