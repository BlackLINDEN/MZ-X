package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;
import android.graphics.Paint;

import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Fény;


public class F extends Növény {


    public float x=1f;
    private float ép;

    private int szint;
    private int szín;
    private final int hosszSzabályzó=50000;

    public F() {
        super("F");

        ép=0;
        vízigény();
    }
    public F(int i){
        super("F");
        ép=i;
        this.szint=i;


        Kender.getInstance().Szint();
    }


    @Override
    public void élet() {
        System.out.println("élet");
        ép+=Kender.getInstance().cukrozó(1f);
        xHossz();//
        fejl();
    }

    @Override
    public float vastagság() {
        //nagyon kevés
        return (x*0.0003f)-szint;
    }

    private float rost() {
        float fix;
        if (szint < 3 && Kender.getInstance().getRost() < 500) {
            fix = 0.01f;
        } else
            fix = 0.0005f;
        if (Kender.getInstance().getRost() * fix > 10) {
            return 10;
        } else
            return Kender.getInstance().getRost() * fix;
    }

    private void xHossz(){
        if(x<hosszSzabályzó-szint) {
            x += ép*2;
            //ép -=1;
        }
        System.out.println("F hossza: "+x+" ÉP "+ép);
    }

    @Override
    public float hossz() {
        //ép-=0.01f;

        return x;
    }

    @Override
    public float szög() {
       // if(Kender.getInstance().getRost()==0||vastagság()==-1) Kender.getInstance().FF.setIrány();
        return Kender.getInstance().FF.irány;
    }

    @Override
    public int szín() {
            szín = Color.RED;
        return szín;
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


}