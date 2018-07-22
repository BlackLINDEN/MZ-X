package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;
import android.graphics.Paint;

import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Fény;


public class F extends Növény {


    private float x=0.1f;
    private float ép;

    private int szint;
    private Paint p;
    private final int hosszSzabályzó=50000;

    public F() {
        super("F");
        p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        ép=0;
        vízigény();
    }
    public F(int i){
        super("F");
        ép=i;
        this.szint=i;
        p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        vízigény();
    }


    @Override
    public void élet() {

        ép+=Kender.getInstance().cukrozó(1f);
        hossz();//
        fejl();
    }

    @Override
    public float vastagság() {
        return ép>0?(rost()):-1;
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

    @Override
    public float hossz() {
        //ép-=0.01f;
        if(x<hosszSzabályzó)
        x+=ép;
        System.out.println("F hossza: "+x);
        return x;
    }

    @Override
    public float szög() {
       // if(Kender.getInstance().getRost()==0||vastagság()==-1) Kender.getInstance().FF.setIrány();
        return Kender.getInstance().FF.irány;
    }

    @Override
    public Paint szín() {
        if(ép<80)
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
        Kender.getInstance().Szint();
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