package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;

import java.util.Random;


public class X extends Növény {



    private boolean bvj;
    private float szög;
    private int szint;
    private Random rndm;
    private float ép;
    public float x;
    private int p;

    public X(){
        super("X");
        x=10;
        ép=1;
        szög=0;


        rndm=new Random();

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

    public X init(boolean bvj,int szint){
        this.bvj=bvj;
        this.szint=szint;
        return this;
    }


    @Override
    public void élet() {
        ép+=Kender.getInstance().cukrozó(1);
        if(Kender.getInstance().getRost()<=0)
            ép--;
        xHossz();
        //szög();
    }

    @Override
    public float vastagság() {
        return hossz()*0.00009f;
    }

    private void xHossz() {
        //ép-=0.002f;
        switch (Kender.getInstance().getFajta()) {

            case 1:

            if(szint>1&&szint<5)
                x += 5;
            else x += 3;
                break;
            case 2:

                if (ép<szint&&szint > 8)
                    x += 8f;
                else
                    x += 8+Kender.getInstance().nutes.N;


                break;
        }
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
                szög = Kender.getInstance().FF.irány - (50)-g;
            else
                szög = Kender.getInstance().FF.irány + (50)+g;
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
        //if(ép>0)ép-=lvns;
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