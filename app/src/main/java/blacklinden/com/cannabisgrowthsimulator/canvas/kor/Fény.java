package blacklinden.com.cannabisgrowthsimulator.canvas.kor;


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
    private String fajta;

    private Égő égő;
    public int fatyolCode;
    public boolean beKapcs = false;
    public int drawCode;

    public Fény(String fajta){

    this.fajta= Mentés.getInstance().getString(Mentés.Key.SAMPLE_STR,"PRO STAR DUAL");

     this.irány=0;
     setDrawCode();


    }
    public int setDrawCode(){
        fajta= Mentés.getInstance().getString(Mentés.Key.SAMPLE_STR,"PRO STAR DUAL");
        switch(fajta){
            case "PRO STAR DUAL":
                égő=Égő.LED;
                watt = 200;
                kelvin = 6600;
                lux = 11200;
                drawCode = R.drawable.fullspec_led;
                fatyolCode = R.drawable.lila_csova;
                break;
            case "Feith Electric":
                drawCode = R.drawable.blue_led;
                fatyolCode = R.drawable.feher_csova;
                égő=Égő.LED;
                watt = 250;
                kelvin = 3500;
                lux = 13500;
                break;
            case "CFL Grow":
                drawCode = R.drawable.cfl_yellow;
                fatyolCode = R.drawable.httr_vill001;
                égő=Égő.CFL;
                watt = 150;
                kelvin = 2700;
                lux = 7850;
                break;
            case "HPS Grow":
                drawCode = R.drawable.yellow_hps;
                fatyolCode = R.drawable.narancs_csova;
                égő=Égő.HALOGEN;
                watt = 600;
                kelvin = 2500;
                lux = 10200;
                break;
        }
        return drawCode;
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
        fajta= Mentés.getInstance().getString(Mentés.Key.SAMPLE_STR,"PRO STAR DUAL");
        switch(fajta){
            case "PRO STAR DUAL":
                égő=Égő.LED;
                watt = 200;
                kelvin = 6600;
                lux = 11200;
                drawCode = R.drawable.fullspec_led;
                fatyolCode = R.drawable.lila_csova;
                break;
            case "Feith Electric":
                drawCode = R.drawable.blue_led;
                fatyolCode = R.drawable.feher_csova;
                égő=Égő.LED;
                watt = 250;
                kelvin = 3500;
                lux = 13500;
                break;
            case "CFL Grow":
                drawCode = R.drawable.cfl_yellow;
                fatyolCode = R.drawable.httr_vill001;
                égő=Égő.CFL;
                watt = 150;
                kelvin = 2700;
                lux = 7850;
                break;
            case "HPS Grow":
                drawCode = R.drawable.yellow_hps;
                fatyolCode = R.drawable.narancs_csova;
                égő=Égő.HALOGEN;
                watt = 600;
                kelvin = 2500;
                lux = 10200;
                break;
        }
        return fatyolCode;
    }

    public void setWatt(int watt){
        this.watt=watt;
    }

    public void setKelvin(int kelvin){
        this.kelvin=kelvin;
    }


    public void fajtaVlt(String t){
        this.fajta=t;
    }

    public float hőmérséklet(){
        return (hőSötétben+(watt*égő.getHőszor_()));
    }



    public void setIrány(){
        this.irány++;
    }


    public int getLux(){
        return lux;
    }

    public int getKelvin() { return kelvin;}



}