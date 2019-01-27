package blacklinden.com.cannabisgrowthsimulator.parse;

import java.util.ArrayList;

import blacklinden.com.cannabisgrowthsimulator.nov.Növény;

public class ParseUtil {
    private StringBuilder stringBuilder;

    public ParseUtil(){
        stringBuilder = new StringBuilder();
    }

    public String[][] lsysToTmb(ArrayList<Növény> arrayList){
        if(!arrayList.isEmpty()){

            for(Növény x:arrayList){
                stringBuilder.append(x.n);
                stringBuilder.append("_");
                stringBuilder.append(x.vastagság());
                stringBuilder.append("_");
                stringBuilder.append(x.hossz());
                stringBuilder.append("_");
                stringBuilder.append(x.szín());
                stringBuilder.append("_");
                stringBuilder.append(x.szint());
                stringBuilder.append(" ");
            }

            String[] nyersTmb = stringBuilder.toString().split(" ");
            String[][] tmb = new String[nyersTmb.length][6];
            for(int i=0;i<nyersTmb.length;i++){
                for(int j=0;j<5;j++){
                   String[] mnkTmb = nyersTmb[i].split("_");
                    tmb[i][j]=mnkTmb[j];
                }
            }


        return tmb;


        }else
            return null;
    }


}
