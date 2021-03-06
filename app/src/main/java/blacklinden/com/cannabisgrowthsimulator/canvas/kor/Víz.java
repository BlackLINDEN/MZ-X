package blacklinden.com.cannabisgrowthsimulator.canvas.kor;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.pojo.Acc;

public class Víz {

    private float PH;
    private int drawCode;

    public float getVÍZ_Mennyiség() {
        return VÍZ_Mennyiség;
    }

    private float VÍZ_Mennyiség;

    public Víz(){
        PH=5.5f;
        VÍZ_Mennyiség=0;
        //setDrawCode();

    }

    public int setDrawCode(){
        //Mentés.getInst Context

        String s = Mentés.getInstance().getString(Mentés.Key.SAMPLE_CAN,"0");
        if(s.equals("0")){
            drawCode=R.drawable.kanna2;

        }else{
            Acc acc = (Acc)Mentés.getInstance().javara(s,Acc.class);
            drawCode=acc.getDrawCode();

        }
        /*
        switch (Mentés.getInstance().getString(Mentés.Key.SAMPLE_CAN,"Tinman")){
            case "Hipster Pipe":drawCode= R.drawable.kanna1;
                break;
            case "Army Can": drawCode = R.drawable.kanna2;
                break;
            case "Classic Green": drawCode = R.drawable.kanna3;
                break;
            case "Tinman": drawCode = R.drawable.kanna4;
                break;
            case "Fine Can": drawCode = R.drawable.kanna5;
                break;
            case "Mini": drawCode=R.drawable.kanna6;
                break;
        }*/

        return drawCode;
    }

    public float fogyaszt(int i){
        float er;
        if(VÍZ_Mennyiség>=i) {
            VÍZ_Mennyiség -= i;

           er=i;
        }else {
            er = VÍZ_Mennyiség;
            VÍZ_Mennyiség = 0;
        }
        return er;
    }

    public void locsol(){

        VÍZ_Mennyiség+=100;
    }
    public float getPH(){
        return PH;
    }

}