package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;
import android.graphics.Paint;

import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Fény;

public class L extends Növény {

    float x;
    private boolean balra;
    private int szint;
    private Paint p;
    private float ép;

    public L(boolean balra) {
        super("L");
        x=0.05f;
        this.balra=balra;
        p=new Paint();
        vízigény();
    }
    public L(boolean balra,int szint){
        super("L");
        this.balra=balra;
        this.szint=szint;
        p=new Paint();
        vízigény();
    }

    @Override
    public void élet() {
        ép+=Kender.getInstance().cukrozó(1);
        szín();
        szög();
        hossz();
        légz();
        fényFelvétel();
    }

    @Override
    public float vastagság() {
        return 9;
    }

    @Override
    public float hossz() {
        ép-=0.1f;
        if(x<80)
            x+=0.1f+(ép);

        return x;

    }

    @Override
    public float szög() {
        float hjl=60;
        if(p.getColor()== Color.YELLOW)
            hjl+=10;
        if(balra)
            return Kender.getInstance().FF.irány-hjl;
        else return Kender.getInstance().FF.irány+hjl;
    }

    @Override
    public Paint szín() {
        if(ép>300||!fényFelvétel()||ép<0||Kender.getInstance().getRost()==0){
            p.setStyle(Paint.Style.FILL);
            p.setAlpha(200);
            p.setColor(Color.YELLOW);
        }else{
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.GREEN);
        }
        return p;
    }

    private boolean fényFelvétel(){
        if((Kender.getInstance().FF.watt-szint)>=0||p.getColor()==Color.GREEN) {
            Kender.getInstance().fény++;
            return true;
        }else return false;
    }

    @Override
    public float fejl() {
        return 0;
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
        return 0;
    }

    @Override
    public float tápigény() {
        return 0;
    }

    @Override
    public float légz() {
        Kender.getInstance().CO2(10*szint);//(Lég.getCO2()*fényFelvétel())
        return 0;
    }

    @Override
    public int szint() {
        return szint;
    }
}