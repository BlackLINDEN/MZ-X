package blacklinden.com.cannabisgrowthsimulator.eszk;

import blacklinden.com.cannabisgrowthsimulator.R;

public class Nutes {

    public int N;
    public int P;
    public int K;

    public int iN,iP,iK;

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
        switch (Mentés.getInstance().getString(Mentés.Key.SAMPLE_NUT,"BioGrow")){
            case "Ionic Bloom":drawCode= R.drawable.tapszer4;
                    iN=2;iP=6;iK=2;
                break;
            case "Piranha Grow": drawCode = R.drawable.tapszer6;
                    iN=0;iP=8;iK=1;
                break;
            case "BioGrow": drawCode = R.drawable.tapszer2;
                    iN=4;iP=3;iK=6;
                break;
            case "FloraGrow Flowering": drawCode = R.drawable.nutri_flower;
                    iN=0;iP=5;iK=4;
                break;
        }
        return drawCode;
    }


}
