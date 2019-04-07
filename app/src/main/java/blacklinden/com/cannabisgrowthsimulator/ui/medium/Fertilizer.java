package blacklinden.com.cannabisgrowthsimulator.ui.medium;

import android.graphics.drawable.Drawable;

public class Fertilizer {

    private Drawable drawable;
    private int drawCode;
    private String name;
    private int N;
    private int P;
    private int K;


    public Fertilizer(String name, int drawCode, Drawable drawable, int N, int P, int K){
        this.drawable=drawable;
        this.drawCode=drawCode;
        this.name=name;
        this.K=K;
        this.N=N;
        this.P=P;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public int getP() {
        return P;
    }

    public void setP(int p) {
        P = p;
    }

    public int getK() {
        return K;
    }

    public void setK(int k) {
        K = k;
    }

    public int getDrawCode(){return drawCode;}


}
