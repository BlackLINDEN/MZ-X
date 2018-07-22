package blacklinden.com.cannabisgrowthsimulator.canvas.kor;

import blacklinden.com.cannabisgrowthsimulator.Main2Activity;

public class Fény {
    public float irány;
    private int lux;
    public int watt=500;
    private final float hőSötétben=25;
    private int kelvin;
    private float táv;
    private String fajta;
    private Égő égő;

    public Fény(String fajta){

    this.fajta=fajta;

     this.irány=0;

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


        switch(fajta){
            case "led":égő=Égő.LED;
                break;
            case "cfl":égő=Égő.CFL;
                break;
            case "hps":égő=Égő.HALOGEN;
                break;
            case "inc":égő=Égő.INCANDESCENT;
                break;
        }
        return (hőSötétben+(watt*égő.getHőszor_()));
    }



    public void setIrány(){
        this.irány++;
    }


    public int getLux(){
        return lux;
    }



}