package blacklinden.com.cannabisgrowthsimulator.nov;


import java.util.concurrent.ThreadLocalRandom;

public class Gy extends Növény {

    private int counter;
    private int fullad,szomj;
    public Gy() {
        super("Gy");
        szomj=0;
        counter=0;
    }

    @Override
    public void élet() {
        if(fullad>40) {
            if(ThreadLocalRandom.current().nextInt(1, 100)>80) {
                Kender.getInstance().causeofdeath += "\n ROOT ROT";
                Kender.getInstance().halott_e = true;
            }
        }else if(szomj>15&&!Kender.getInstance().causeofdeath.contains("\n DEHYDRATION")) {
            Kender.getInstance().causeofdeath += "\n DEHYDRATION";
            Kender.getInstance().halott_e=true;
        }
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
                Kender.getInstance().VV.fogyaszt(
                        (int)(szigmoid(Kender.getInstance().Szintet())*(5))
                )
        );

        if(Kender.getInstance().VV.getVÍZ_Mennyiség()<=0) {
            szomj++;
            //Kender.getInstance().cukrozó(10*Kender.getInstance().Szintet());
            Kender.getInstance().FF.setIrány();
        }
        else if(Kender.getInstance().VV.getVÍZ_Mennyiség()>20) {
            szomj = 0;
            Kender.getInstance().FF.resetIrány();
        }
        return szomj;
    }

    private double szigmoid(double x) {
        return (1/( 1 + Math.pow(Math.E,(-1*x))));
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
            Kender.getInstance().nutes.N=Kender.getInstance().CC.föld.Nitrogén;
            if(Kender.getInstance().CC.föld.Nitrogén>0)Kender.getInstance().CC.föld.Nitrogén--;
            Kender.getInstance().nutes.P=Kender.getInstance().CC.föld.Foszfor;
            if(Kender.getInstance().CC.föld.Foszfor>0)Kender.getInstance().CC.föld.Foszfor--;
            Kender.getInstance().nutes.K=Kender.getInstance().CC.föld.Kálium;
            if(Kender.getInstance().CC.föld.Kálium>0)Kender.getInstance().CC.föld.Kálium--;

        }
        return 0;
    }

    @Override
    public float légz() {
        if(Kender.getInstance().VV.getVÍZ_Mennyiség()+Kender.getInstance().CC.waterRunoff<100*(
                Kender.getInstance().Szintet()/2
                ))
        Kender.getInstance().CO2(Kender.getInstance().LL.getCO2()/2);

        else {
            fullad++;
            //Kender.getInstance().cukrozó(10);
        }
        return 0;
    }

    @Override
    public int szint() {
        return 0;
    }
}
