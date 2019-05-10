package blacklinden.com.cannabisgrowthsimulator.canvas.kor;


import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.pojo.Acc;

public class Cserép{

    public float potSize;
    public float waterRunoff;
    public Föld föld;

    public Cserép(){

  setDrawableCode();
    föld = new Föld("Dirt from outside");
}

public void setFöld(String soilType){
    föld.setFöld(soilType);
}

public int setDrawableCode(){
    String s = Mentés.getInstance().getString(Mentés.Key.SAMPLE_POT,"Quadra Pot");
    int drawableCode;
    if(s.equals("Quadra Pot")){
        drawableCode =R.drawable.ic_grandmas_remembrance;
        potSize=800;
        waterRunoff=-1.2f;
    }else{
        Acc acc = (Acc)Mentés.getInstance().javara(s,Acc.class);
        drawableCode =acc.getDrawCode();
        potSize=acc.getVol();
        waterRunoff=-1.2f;
    }



    return drawableCode;
}
public class Föld{
    public float PH;
    public float drainage;
    //100vízből mennyit tart meg ( százalék szorzó). ebből veszi le a calvinkör
    public float waterRetention;
    public int Nitrogén;
    public int Foszfor;
    public int Kálium;
    public String soilName;



                /*Nutritionally, coco is also an excellent choice.
                Depending on the source, it is rich in potassium, iron, manganese, copper and zinc.
                If you are growing hydroponically, this needs to be taken into consideration so you can provide the correct balance of nutrients.
                Coir has a high cation exchange rate that allows it to store nutrients and release them as needed.
                On the flip side, coir tends to hold on to calcium and magnesium,
                so you may need to adjust your nutrient mix accordingly.
                */
    private Föld(String type){
       setFöld(type);
    }

    void setFöld(String soilType){
        soilName = soilType;
        switch(soilName){
            case "coco":
                PH=5.5f;
                drainage=-1.2f;
                waterRetention=0.7f;
                Nitrogén = 1;
                Foszfor=1;
                Kálium=3;
                break;

            case "super":
                PH=5.5f;
                drainage=-1;
                waterRetention=1;
                Nitrogén=3;
                Foszfor=3;
                Kálium=3;
                break;

            default:
                PH=5.5f;
                drainage=-0.4f;
                waterRetention=2;
                Nitrogén=0;
                Foszfor=0;
                Kálium=1;
        }

    }

    public void flush(){
        Nitrogén/=2;
        Foszfor/=2;
        Kálium/=3;
    }

    }
}