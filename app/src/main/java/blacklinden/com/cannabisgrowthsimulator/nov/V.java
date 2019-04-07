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

        Random rndm = new Random();
        szög=rndm.nextInt(20-(-20))+(-20);
    }


    @Override
    public void élet() {
        ép+=Kender.getInstance().cukrozó(1);
        szög();
        szín();
        xVast();
        ép+=tápigény();
        ép+=fényigény();
        ép+=hőigény();
    }

    private void xVast(){
        x++;

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


        return szög;
    }

    @Override
    public int szín() {

            if (ép > 1500)
                p = Color.rgb(205, 133, 63);


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
        if(Kender.getInstance().FF.getKelvin()<3000)
        return Kender.getInstance().FF.getLux()/100;
        else
        return 1.5f;
    }

    @Override
    public float hőigény() {
        float temp=Kender.getInstance().FF.hőmérséklet();
        if(temp>28)
            return(temp-28);
        else
        return 0;
    }

    @Override
    public float tápigény() {
        if(Kender.getInstance().nutes.P>0) Kender.getInstance().nutes.P--;
        if(Kender.getInstance().nutes.K>0) Kender.getInstance().nutes.K--;
        return (Kender.getInstance().nutes.P+Kender.getInstance().nutes.K);
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
