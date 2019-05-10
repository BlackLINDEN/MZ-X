package blacklinden.com.cannabisgrowthsimulator.ui.medium;

import android.graphics.drawable.Drawable;

public class Skin {

    private int middleCode;
    private String name;

    public Skin(String name,int middleCode){

        this.middleCode=middleCode;

        this.name=name;
    }



    public int getMiddleCode() {
        return middleCode;
    }



    public String getName(){
        return name;
    }
}
