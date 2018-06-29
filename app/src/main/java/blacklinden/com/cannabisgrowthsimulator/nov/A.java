package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Paint;

public class A extends Növény {
    private int szint;
    private float ép;
    public A(int szint) {
        super("A");
        this.szint=szint+1;
        ép=0;
        vízigény();
    }

    @Override
    public void élet() {
        ép+=Kender.cukrozó(1);
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
    public Paint szín() {
        return new Paint();
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
