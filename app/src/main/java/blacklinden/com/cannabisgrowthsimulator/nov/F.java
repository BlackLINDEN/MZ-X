package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;


public class F extends Növény {


    public float x=10f;
    private float ép;

    private int szint;
    private int szín;
    private final int hosszSzabályzó=50000;

    public F() {
        super("F");

        ép=1;
        vízigény();
    }
    public F(int i){
        super("F");
        ép=i;
        this.szint=i;


        Kender.getInstance().Szint();
    }


    @Override
    public void élet() {
        System.out.println("élet");
        ép+=Kender.getInstance().cukrozó(1f);

        //if(Kender.getInstance().getRost()<=0&&Kender.getInstance().getCukor()<=0) ép--;
        //if(Kender.getInstance().nutrition.N>16&&Kender.getInstance().flowering) ép --;

        if(ép==0)
            Kender.getInstance().halottRészek++;
        xHossz();//
        fejl();
    }

    @Override
    public float vastagság() {
        return (x*0.0003f)-szint;

    }



    private void xHossz(){

                if (x < hosszSzabályzó - szint && ép > 0) {
                    x += ép+Kender.getInstance().nutes.N;
                }
    }

    @Override
    public float hossz() {

        return x;
    }

    @Override
    public float szög() {
        return Kender.getInstance().FF.irány;
    }

    @Override
    public int szín() {
            szín = Color.RED;
        return szín;
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
        float hi=22.5f;
        float lvns=(Math.abs(hi)-Math.abs(Kender.getInstance().FF.hőmérséklet()))/10;
        ép-=lvns;
        return hi;
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