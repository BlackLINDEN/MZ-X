package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Fény;


public class X extends Növény {



    private boolean bvj;
    private float szög;
    private int szint;
    private Random rndm;
    private float ép;
    public float x;
    private int p;

    public X(boolean bvj, int szint){
        super("X");
        x=10;
        ép=0;
        szög=0;
        this.bvj=bvj;
        this.szint=szint;

        rndm=new Random();
        vízigény();
    }
    public X(boolean bvj,float ép,float x,float szög, int szint){
        super("X");
        this.ép=ép;
        this.x=x;
        this.szög=szög;
        this.bvj=bvj;
        this.szint=szint;

        rndm=new Random();

    }


    @Override
    public void élet() {
        ép+=Kender.getInstance().cukrozó(1);
        xHossz();
        //szög();
    }

    @Override
    public float vastagság() {
        return hossz()*0.00009f;
    }

    private void xHossz(){
        //ép-=0.002f;
        if(szint>5)
            x+=80f;
        else
            x+=100;
    }
    @Override
    public float hossz() {


        return x;
    }

    @Override
    public float szög() {
        int g=rndm.nextInt(10-(-10))+(-10);
        if(ép<11) {
            if (bvj)
                szög = Kender.getInstance().FF.irány - (vastagság()*230)+g;
            else
                szög = Kender.getInstance().FF.irány + (vastagság()*230)+g;
        }

        return szög;
    }

    @Override
    public int szín() {
        if(szint>10)
            p=(Color.GREEN);
        else
            p=(Color.argb(255,133,79,0));
        return p;
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
        ép-=lvns;
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


    public boolean isBvj() {
        return bvj;
    }
}