package blacklinden.com.cannabisgrowthsimulator.canvas.kor;


import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;

public class Fény {
    public float irány;
    private int lux;
    public int watt;
    private final float hőSötétben=25;
    private int kelvin;
    private float táv;
    private String fajta;
    private Égő égő;
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
                drawCode = R.drawable.ic_rrr;
                break;
            case "Feith Electric":
                drawCode = R.drawable.ic_jjj;
                égő=Égő.LED;
                watt = 250;
                kelvin = 3500;
                lux = 13500;
                break;
            case "CFL Grow":
                drawCode = R.drawable.ic_light_bulb_technology_svgrepo_com;
                égő=Égő.CFL;
                watt = 150;
                kelvin = 2700;
                lux = 7850;
                break;
            case "inc":
                égő=Égő.INCANDESCENT;
                watt = 200;
                kelvin = 6600;
                lux = 11200;
                break;
        }
        return drawCode;
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



}