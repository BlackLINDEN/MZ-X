package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Paint;

public class A extends Növény {
    private int szint;
    private float ép;
    private float szg=0;
    private float oldhajt;

    public A() {
        super("A");

        ép=0;

    }

    public A init(int szint){
        this.szint=szint+1;
        oldhajt=1;
        return this;
    }

    public A init(int szint,float szg){
        this.szint=szint+1;
        this.szg=szg;
        oldhajt=0;
        return this;
    }

    @Override
    public void élet() {
        ép+=Kender.getInstance().cukrozó(1);
    }

    @Override
    public float vastagság() {
        return 0;
    }

    @Override
    public float hossz() {
        return oldhajt;
    }

    @Override
    public float szög() {
        return szg;
    }

    @Override
    public int szín() {
        return 0;
    }

    @Override
    public float fejl() {
        return ép;
    }

    @Override
    public float vízigény() {


        return 0;
    }

    @Override
    public float fényigény() {
        return 0;
    }

    @Override
    public float hőigény() {
        return 0;
    }

    @Override
    public float tápigény() {
        return 0;
    }

    @Override
    public float légz() {
        return 0;
    }

    @Override
    public int szint() {
        return szint;
    }
}
