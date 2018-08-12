package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class V extends Növény {

    private int szint;
    private int p;
    private float ép;
    private float szög;
    private float x;
    public V() {
        super("V");

        vízigény();
        x=5f;
    }

    public V(float v) {
        super("V");

        vízigény();
        x=v;
    }

    @Override
    public void élet() {
        ép+=Kender.getInstance().cukrozó(1);
        szög();
        xVast();
    }

    private void xVast(){
        if(x<15.5f)
        x+=ép+szint;
    }

    @Override
    public float vastagság() {

        return x;
    }

    @Override
    public float hossz() {
        return 0;
    }

    @Override
    public float szög() {
        Random rndm = new Random();
        szög=rndm.nextInt(20-(-20))+(-20);

        return szög;
    }

    @Override
    public int szín() {

        p=(Color.WHITE);
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
