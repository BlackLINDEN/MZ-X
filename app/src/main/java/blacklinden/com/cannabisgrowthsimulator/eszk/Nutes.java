package blacklinden.com.cannabisgrowthsimulator.eszk;


import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.ui.medium.Fertilizer;

public class Nutes {

    public int N;
    public int P;
    public int K;

    public int iN,iP,iK;
    public String nuteName;

    public int getDrawCode() {
        return drawCode;
    }

    private int drawCode;


    public Nutes(){
        N=0;
        P=0;
        K=0;

    }


    public int setDrawCode(){
        String s = Mentés.getInstance().getString(Mentés.Key.SAMPLE_NUT,"0");
        if(!s.equals("0")) {
            Fertilizer f = (Fertilizer) Mentés.getInstance().javara(s, Fertilizer.class);
            drawCode = f.getDrawCode();
            iN = f.getN();
            iP = f.getP();
            iK = f.getK();
            nuteName = f.getName();
        }else{
            drawCode = R.drawable.tapszer2;
            iN = 6;
            iP = 1;
            iK = 1;
            nuteName ="BioGrow";
        }

        return drawCode;
    }


}
