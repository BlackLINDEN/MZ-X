package blacklinden.com.cannabisgrowthsimulator.canvas.kor;


import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;

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
    switch (Mentés.getInstance().getString(Mentés.Key.SAMPLE_POT,"Quadra Pot")){
        case "Stiegl":drawableCode=R.drawable.cserep10;
            potSize=800;
            waterRunoff=-1.2f;
            break;
        case "Geranium Pot":drawableCode=R.drawable.cserepes;
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

    }

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
             this.PH=5.5f;
             this.drainage=-1.2f;
             this.waterRetention=0.7f;
             this.Nátrium = 1;
             this.Foszfor=1;
             this.Kálium=5;
             break;

            case "super":
             this.PH=5.5f;
             this.drainage=-1;
             this.waterRetention=1;
             this.Nátrium=3;
             this.Foszfor=3;
             this.Kálium=3;
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