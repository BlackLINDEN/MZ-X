package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;
import android.graphics.Paint;

import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Fény;

public class L extends Növény {

    public float x;
    private boolean balra;
    private int szint;
    private int p;
    private float ép;


    public L(boolean balra) {
        super("L");
        x=0.05f;
        this.balra=balra;

        vízigény();
    }
    public L(boolean balra,int szint){
        super("L");
        x=0.05f;
        this.balra=balra;
        this.szint=szint;

        vízigény();

    }

    @Override
    public void élet() {
        ép+=Kender.getInstance().cukrozó(1);
        szín();
        //szög();
        xHossz();
        légz();
        fényFelvétel();
    }

    @Override
    public float vastagság() {
        return 12;
    }


    private void xHossz(){
        if(x<110-szint)
            x+=ép/3;
    }
    @Override
    public float hossz() {
        //ép-=0.1f;
        System.out.println("L "+x);

        return x;

    }

    @Override
    public float szög() {
        float hjl=60;
        if(p== Color.YELLOW)
            hjl+=10;
        if(balra)
            return Kender.getInstance().FF.irány-hjl;
        else return Kender.getInstance().FF.irány+hjl;
    }

    @Override
    public int szín() {
        if(ép<0){

            p=(Color.YELLOW);
        }else{

            p=(Color.GREEN);
        }
        return p;
    }

    private boolean fényFelvétel(){
        if((Kender.getInstance().FF.watt-szint)>=0||p==Color.GREEN) {
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
        Kender.getInstance().CO2(Kender.getInstance().LL.getCO2()/2);//(Lég.getCO2()*fényFelvétel())
        return 0;
    }

    @Override
    public int szint() {
        return szint;
    }
}