package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Paint;

public class A extends Növény {
    private int szint;
    private float ép;

    public A(int szint) {
        super("A");
        this.szint=szint+1;
        ép=0;

    }

    @Override
    public void élet() {
        ép+=Kender.getInstance().cukrozó(1);
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
