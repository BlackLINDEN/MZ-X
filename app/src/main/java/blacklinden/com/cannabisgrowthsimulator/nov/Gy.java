package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Paint;

public class Gy extends Növény {
    public Gy() {
        super("Gy");

    }

    @Override
    public void élet() {
        vízigény();
        légz();
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
        return 0;
    }

    @Override
    public float vízigény() {

        Kender.getInstance().setH2o(
                Kender.getInstance().VV.setVÍZ_Mennyiség(Kender.getInstance().Szintet())
        );
        return 10;
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
        if(Kender.getInstance().VV.getPH()>4.9f&&Kender.getInstance().VV.getPH()<6f)
        return 1;
        else
        return 0;
    }

    @Override
    public float légz() {
        if(Kender.getInstance().VV.getVÍZ_Mennyiség()<100)
        Kender.getInstance().CO2(Kender.getInstance().LL.getCO2()/2);
        return 0;
    }

    @Override
    public int szint() {
        return 0;
    }
}
