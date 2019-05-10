package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;

import java.util.Random;


public class X extends Növény {



    private boolean bvj;
    private float szög;
    private int szint;
    private Random rndm;
    private float ép;
    private float hosszszab,vastszab,szögszab;
    public float x;
    private int p,g;


    public X(int fajta){
        super("X");
        x=1;
        ép=1;
        szög=0;
        switch(fajta){
            case 1 :
            case 16:
                hosszszab=15;
                vastszab=0.04f;
                szögszab=50;
                break;
            case 2:
            case 11:
            case 15:
                hosszszab=25;
                vastszab=0.07f;
                szögszab=60;
                break;
            case 3:
            case 10:
            case 17:
                hosszszab=12;
                vastszab=0.05f;
                szögszab=20;
                break;
            case 4:
            case 9:
            case 18:
                hosszszab=10;
                vastszab=0.06f;
                szögszab=55;
                break;
            case 5:
            case 8:
            case 13:
                hosszszab=13;
                vastszab=0.05f;
                szögszab=61;
                break;
            case 6:
            case 7:
            case 12:
            case 14:
                hosszszab=9;
                vastszab=0.08f;
                szögszab=47;
                break;
        }
        rndm=new Random();
        g=rndm.nextInt((30-(-30))+(-30));
    }


    public X init(boolean bvj,int szint){
        this.bvj=bvj;
        this.szint=szint;
        return this;
    }


    @Override
    public void élet() {
        if(ép<40)ép+=Kender.getInstance().cukrozó(1);

        xHossz();
        //szög();
        //hőigény();
    }

    @Override
    public float vastagság() {
        return hossz()*vastszab;
    }

    private void xHossz() {
        //ép-=0.002f;
       if(szint>1&&x<hosszszab-szint*2&&ép>10&&ép<40)
           x+= (ép+Kender.getInstance().nutes.N)*0.1f;
    }
    @Override
    public float hossz() {


        return x;
    }

    @Override
    public float szög() {


            if (bvj)
                szög = Kender.getInstance().FF.irány - (szögszab)-g;
            else
                szög = Kender.getInstance().FF.irány + (szögszab)+g;


        return szög;
    }

    @Override
    public int szín() {
        if(szint>10)
            p=(Color.GREEN);
        else
            p=(Color.argb(255,133,79,0));
        return Color.GREEN;
    }

    @Override
    public float fejl() {
        return ép;
    }

    @Override
    public float vízigény() {

        return 0;
    }

    @Override
    public float fényigény() {
        return 0;
    }

    @Override
    public float hőigény() {
        float hi=22.5f;
        float lvns=(Math.abs(hi)-Math.abs(Kender.getInstance().FF.hőmérséklet()))/10;
        if(ép>0)ép-=lvns;
        return hi;
    }

    @Override
    public float tápigény() {
        return 0;
    }

    @Override
    public float légz() {
        return 0;
    }

    @Override
    public int szint() {
        return szint;
    }


}