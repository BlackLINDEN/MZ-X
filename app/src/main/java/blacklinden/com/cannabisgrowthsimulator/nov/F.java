package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;


public class F extends Növény {


    public float x=10f;
    private float ép;
    private float szg;
    private boolean oldHajt=false;
    private int szint;
    private int szín;
    private final float hosszSzabályzó=12500;


    public F(){
        super("F");



        System.out.println("szint");
    }

    public F init(float szg,int i){
        this.szg=szg;
        ép=1;
        szint=i;
        this.oldHajt=true;
        return this;
    }

    public F init(int i){
        ép=i;
        this.szint=i;
        Kender.getInstance().Szint();
        return this;
    }


    @Override
    public void élet() {

        ép+=Kender.getInstance().cukrozó(1);

        //if(Kender.getInstance().getRost()<=0&&Kender.getInstance().getCukor()<=0) ép--;
        //if(Kender.getInstance().nutrition.N>16&&Kender.getInstance().flowering) ép --;

        if(ép==0)
            Kender.getInstance().halottRészek++;
        xHossz();//
        fejl();
    }

    @Override
    public float vastagság() {
        if((x*0.0003f)-szint<5)
            return 5;
        else
        return (x*0.0003f)-szint;

    }



    private void xHossz(){

                if (Kender.getInstance().Szintet()<30||x < hosszSzabályzó - szint && ép > 0&&ép<200) {
                    x += ép*2+Kender.getInstance().nutes.N;
                }
    }

    @Override
    public float hossz() {

        return x;
    }

    @Override
    public float szög() {
        if(!oldHajt)
            return Kender.getInstance().FF.irány;
        else
            return Kender.getInstance().FF.irány+szg/2;


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
        //ép-=lvns;
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