package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;


public class F extends Növény {


    public float x=1f;
    private float ép;
    private float szg;
    private boolean oldHajt=false;
    private int szint;
    private int szín;
    private float hosszSzabályzó;


    public F(){
        super("F");

        System.out.println("szint");
    }

    public F init(int i,float szg){

        //this.szg=szg>Kender.getInstance().FF.irány?szg-1:szg+1;
        if(szg!=Kender.getInstance().FF.irány){
            this.szg=szg/2;

        }
        ép=1;
        szint=i;
        this.oldHajt=true;
        hosszSzabályzó=15;
        //Kender.getInstance().Szint();
        return this;
    }

    public F init(int i){
        ép=i;
        //if(szg!=Kender.getInstance().FF.irány) szg=szg>0?szg-10:szg+10;
        this.szint=i;
        Kender.getInstance().Szint();
        hosszSzabályzó=20;
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


    }

    @Override
    public float vastagság() {
        if((x*0.08f)-szint/5>1)
        return (x*0.08f)-szint/5;
        else
        return 1;
    }



    private void xHossz(){

                if (x < hosszSzabályzó - szint && ép > 20) {
                    x += (ép+Kender.getInstance().nutes.N)*0.01f;
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
        else {

            return Kender.getInstance().FF.irány + szg ;
        }

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
        Kender.getInstance().levonH2o(1);

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
        if(oldHajt)
            return 1;
        else
            return 0;
    }

    @Override
    public int szint() {
        return szint;
    }


}