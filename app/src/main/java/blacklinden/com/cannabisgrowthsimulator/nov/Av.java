package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Paint;

public class Av extends Növény {
    private int szint;
    private float ép;
    public Av(int szint) {
        super("AV");
        this.szint=szint+1;
        vízigény();
    }

    @Override
    public void élet() {
        ép+=Kender.getInstance().cukrozó(1);
        fejl();
    }

    @Override
    public float vastagság() {
        return 0;
    }

    @Override
    public float hossz() {
        return 0;
    }

    @Override
    public float szög() {
        return 0;
    }

    @Override
    public int szín() {
        return 0;
    }

    @Override
    public float fejl() {
        ép+=szint;

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
