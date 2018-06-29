package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Paint;

public abstract class Növény {
    public String n;

    public Növény(String n){
        this.n=n;
    }

    public abstract void élet();

    public abstract float vastagság();

    public abstract float hossz();

    public abstract float szög();

    public abstract Paint szín();

    public abstract float fejl();

    public abstract float vízigény();

    public abstract float fényigény();

    public abstract float hőigény();

    public abstract float tápigény();

    public abstract float légz();

    public abstract int szint();



}