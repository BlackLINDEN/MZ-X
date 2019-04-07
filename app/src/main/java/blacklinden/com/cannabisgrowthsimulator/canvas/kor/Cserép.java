package blacklinden.com.cannabisgrowthsimulator.canvas.kor;


import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.pojo.Acc;

public class Cserép{

    public float potSize;
    public float waterRunoff;
    public Föld föld;
    public int drawableCode;

public Cserép(float potSize,float waterRunoff,String soilType){
    föld = new Föld(soilType);
  setDrawableCode();

}

public int setDrawableCode(){
    String s = Mentés.getInstance().getString(Mentés.Key.SAMPLE_POT,"Quadra Pot");
    if(s.equals("Quadra Pot")){
        drawableCode=R.drawable.black_pot;
        potSize=800;
        waterRunoff=-1.2f;
    }else{
        Acc acc = (Acc)Mentés.getInstance().javara(s,Acc.class);
        drawableCode=acc.getDrawCode();
        potSize=acc.getVol();
        waterRunoff=-1.2f;
    }

    /*
    switch (Mentés.getInstance().getString(Mentés.Key.SAMPLE_POT,"Quadra Pot")){
        case "Stiegl":
            drawableCode=R.drawable.cserep10;
            potSize=800;
            waterRunoff=-1.2f;
            break;


        case "Air Pot":  drawableCode=R.drawable.air_pot;
            potSize=800;
            waterRunoff=-1.2f;
            break;

        case "Quadra Pot": drawableCode=R.drawable.black_pot;
            potSize=800;
            waterRunoff=-1.2f;
            break;

        case "Molded Rolled Rim Pot": drawableCode=R.drawable.molded_rolled_rim_pot;
            potSize=800;
            waterRunoff=-1.2f;
            break;

        case "Plain Ceramic Pot": drawableCode=R.drawable.plain_ceramic_pot;
            potSize=800;
            waterRunoff=-1.2f;
            break;

        case "Cast Bronze Pot": drawableCode=R.drawable.cserep6;
            potSize=800;
            waterRunoff=-1.2f;
            break;

        case "Blumentopf": drawableCode=R.drawable.cserep8;
            potSize=700;
            waterRunoff=-1;
            break;

        case "Seghettata": drawableCode=R.drawable.cserep9;
            potSize=500;
            waterRunoff=-1;
            break;

        case "Concentric Geranium": drawableCode=R.drawable.cserep7;
            potSize=600;
            waterRunoff=-2;
            break;

        case "Dodeca Pot": drawableCode=R.drawable.dodeca_pot;
            potSize=600;
            break;

    }*/

    return drawableCode;
}
public class Föld{
    public float PH;
    public float drainage;
    //100vízből mennyit tart meg ( százalék szorzó). ebből veszi le a calvinkör
    public float waterRetention;
    public int Nátrium;
    public int Foszfor;
    public int Kálium;


                /*Nutritionally, coco is also an excellent choice.
                Depending on the source, it is rich in potassium, iron, manganese, copper and zinc.
                If you are growing hydroponically, this needs to be taken into consideration so you can provide the correct balance of nutrients.
                Coir has a high cation exchange rate that allows it to store nutrients and release them as needed.
                On the flip side, coir tends to hold on to calcium and magnesium,
                so you may need to adjust your nutrient mix accordingly.
                */
    private Föld(String type){
        switch(type){
            case "coco":
                PH=5.5f;

                drainage=-1.2f;

                waterRetention=0.7f;

                Nátrium = 0;

                Foszfor=1;

                Kálium=2;
             break;

            case "super":
             PH=5.5f;
             drainage=-1;
             waterRetention=1;
             Nátrium=1;
             Foszfor=1;
             Kálium=1;
             break;
        }
    }

    public void flush(){
        Nátrium/=2;
        Foszfor/=2;
        Kálium/=3;
    }

    }
}