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
    float x;
    private Paint p;

    public X(boolean bvj, int szint){
        super("X");
        x=10;
        ép=0;
        szög=0;
        this.bvj=bvj;
        this.szint=szint;
        p = new Paint();
        p.setStyle(Paint.Style.FILL);
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
        p = new Paint();
        p.setStyle(Paint.Style.FILL);
        rndm=new Random();
        vízigény();
    }


    @Override
    public void élet() {
        ép+=Kender.cukrozó(1);
        hossz();
        //szög();
    }

    @Override
    public float vastagság() {
        return hossz()*0.00005f;
    }

    @Override
    public float hossz() {

        ép-=0.002f;
        if(szint>5)
            x+=64.5f;
        else if(szint>2)
            x+=72f;
        else
            x+=20;
        return x;
    }

    @Override
    public float szög() {
        int g=rndm.nextInt(10-(-10))+(-10);
        if(ép<11) {
            if (bvj)
                szög = Fény.irány - (vastagság()*210)+g;
            else
                szög = Fény.irány + (vastagság()*210)+g;
        }

        return szög;
    }

    @Override
    public Paint szín() {
        if(szint>10)
            p.setColor(Color.GREEN);
        else
            p.setColor(Color.argb(255,133,79,0));

        return p;
    }

    @Override
    public float fejl() {
        return ép;
    }

    @Override
    public float vízigény() {
        Kender.Szint();
        return 0;
    }

    @Override
    public float fényigény() {
        return 0;
    }

    @Override
    public float hőigény() {
        float hi=22.5f;
        float lvns=(Math.abs(hi)-Math.abs(Fény.hőmérséklet()))/10;
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