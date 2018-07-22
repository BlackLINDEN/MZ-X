package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class V extends Növény {

    private int szint;
    private Paint p;
    private float ép;
    private float szög;
    public V() {
        super("V");
        p = new Paint();
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setStrokeWidth(2);
        vízigény();
    }

    @Override
    public void élet() {
        ép+=Kender.getInstance().cukrozó(1);
        szög();
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
        Random rndm = new Random();
        szög=rndm.nextInt(20-(-20))+(-20);

        return szög;
    }

    @Override
    public Paint szín() {

        p.setColor(Color.WHITE);
        return p;
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
        return 0;
    }

    @Override
    public int szint() {
        return szint;
    }
}
