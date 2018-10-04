package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;

public class C extends Növény {

    private int szint;
    private int p;
    private int ép;
    private float x;
    private boolean balra;

    public C(boolean balra) {
        super("C");
        p= Color.GREEN;
        x=0;
        ép=1;
        szint=1;
        this.balra=balra;

    }

    @Override
    public void élet() {
    xSzög();
    ép++;
    fényFelvétel();
    }

    @Override
    public float vastagság() {
        return 5;
    }

    @Override
    public float hossz() {
        return 0;
    }

    private void xSzög(){
        if(Math.abs(x)<95)
            x++;
    }
    @Override
    public float szög() {
        if(balra)
            return Kender.getInstance().FF.irány-x;
        else return Kender.getInstance().FF.irány+x;

    }

    @Override
    public int szín() {
        if(ép>10)
            p=Color.YELLOW;
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

    private boolean fényFelvétel(){
        if(Kender.getInstance().FF.beKapcs&&(Kender.getInstance().FF.watt-szint)>=0&&p== Color.GREEN) {
            Kender.getInstance().fény++;
            return true;
        }else return false;
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
        return 0;
    }

    @Override
    public int szint() {
        return szint;
    }
}
