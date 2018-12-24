package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;

import java.util.Random;

public class V extends Növény {

    private int szint;
    private int p;
    private float ép;
    private float szög;
    private float x;
    private int fajta;
    public V(int fajta) {
        super("V");
        p=Color.WHITE;
        this.fajta=fajta;
        vízigény();
        x=1f;
    }


    @Override
    public void élet() {
        ép+=Kender.getInstance().cukrozó(500);
        szög();
        szín();
        xVast();
    }

    private void xVast(){
        if(x<5)
        x+=ép/1000+Kender.getInstance().nutes.P/10;

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
        switch (Kender.getInstance().getFajta()) {
            case 1:
            if (ép > 1500)
                p = Color.rgb(205, 133, 63);
            break;
            case 2:
                if (ép > 3000)
                    p = Color.rgb(205, 133, 63);
            break;
        }
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
