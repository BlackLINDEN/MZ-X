package blacklinden.com.cannabisgrowthsimulator.nov;


import java.util.concurrent.ThreadLocalRandom;

public class Gy extends Növény {

    private int counter=0;
    private int fullad,szomj=0;
    public Gy() {
        super("Gy");

    }

    @Override
    public void élet() {
        if(fullad>400) {
            if(ThreadLocalRandom.current().nextInt(1, 100)==50) {
                Kender.getInstance().causeofdeath += "\n ROOT ROT";
                Kender.getInstance().halott_e = true;
            }
        }else if(szomj>12)
            Kender.getInstance().causeofdeath += "\n DEHYDRATION";
        vízigény();
        légz();
        counter++;
        tápigény();
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
                Kender.getInstance().VV.fogyaszt(Kender.getInstance().Szintet())
        );
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
        if(counter%2==0&&Kender.getInstance().VV.getVÍZ_Mennyiség()>10&&Math.abs(Kender.getInstance().VV.getPH()-Kender.getInstance().CC.föld.PH)<1) {
            Kender.getInstance().nutes.N=Kender.getInstance().CC.föld.Nátrium;
            Kender.getInstance().nutes.P=Kender.getInstance().CC.föld.Foszfor;
            Kender.getInstance().nutes.K=Kender.getInstance().CC.föld.Kálium;
        }
        return 0;
    }

    @Override
    public float légz() {
        if(Kender.getInstance().VV.getVÍZ_Mennyiség()+Kender.getInstance().CC.waterRunoff<100)
        Kender.getInstance().CO2(Kender.getInstance().LL.getCO2()/2);
        else if(Kender.getInstance().VV.getVÍZ_Mennyiség()+Kender.getInstance().getH2o()<=0)
            szomj++;
        else
            fullad++;
        return 0;
    }

    @Override
    public int szint() {
        return 0;
    }
}
