package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;

public class L extends Növény {

    public float x,v;
    private boolean balra;
    private int szint;
    private int p;
    private float ép = 1;
    private float hjl = 60;
    private int sötétIdő = 0;
    private String halál;

    public L(boolean balra) {
        super("L");
        x = 0.05f;
        this.balra = balra;

        vízigény();
    }

    public L(boolean balra, int szint) {
        super("L");
        x = 0.05f;
        v = 5f;
        this.balra = balra;
        this.szint = szint;
        p = Color.GREEN;
        vízigény();

    }

    private int vastSegéd(){
      int xxx;
        switch(Kender.getInstance().getFajta()){
            case 1:xxx=12;
                break;
            case 2:xxx=14;
                break;
            default:xxx=10;
        }
        return xxx;
    }

    @Override
    public void élet() {


        ép += Kender.getInstance().cukrozó(1);
        if (Kender.getInstance().flowering){

            if (Kender.getInstance().getRost() <= 0 ){
            halál = "SUGAR STARVATION";
            ép--;
            }
                else if(Kender.getInstance().nutes.N > Kender.getInstance().nutes.P){
                halál = "NATRIUM BURN";
                ép--;
            }

                else if(Kender.getInstance().nutes.K > 9 ){
                halál = "POTASSIUM BURN";
                ép--;
            }

                    else if (Kender.getInstance().nutes.P > 20){
                halál = "PHOSPHORUS BURN";
                ép--;
            }

         }


        if(sötétIdő>9){
            halál ="LIGHT DEPRIVATION";
            ép=0;
        }
        if(ép<=0) {
            if(halál!=null&&!Kender.getInstance().causeofdeath.contains(halál))
                Kender.getInstance().causeofdeath+="\n "+halál;
                Kender.getInstance().halottRészek++;
        }
        szín();
        //szög();
        xHossz();
        légz();
        fényFelvétel();
    }


    @Override
    public float vastagság() {
        if(v<vastSegéd())
            v+=0.01f;
        return v;
    }


    private void xHossz(){
        if(x<110-szint&&ép>0)
            x++;
    }
    @Override
    public float hossz() {
        //ép-=0.1f;


        return x;

    }

    @Override
    public float szög() {

        if(p== Color.YELLOW&&hjl<90)
            hjl+=10;
        else if(!Kender.getInstance().FF.beKapcs)
            hjl=60;
        if(balra)
            return Kender.getInstance().FF.irány-hjl;
        else return Kender.getInstance().FF.irány+hjl;
    }

    @Override
    public int szín() {
        if(ép==0){

            p=(Color.YELLOW);
        }else if(!Kender.getInstance().flowering
                &&
                Kender.getInstance().nutes.P>Kender.getInstance().nutes.N){
            p=(Color.BLACK);
            ép-=10;
        }

        else if(Kender.getInstance().flowering&&Kender.getInstance().nutes.P>20){
            p=(Color.YELLOW);
            ép--;
        }

        return p;
    }

    private boolean fényFelvétel(){
        if(Kender.getInstance().FF.beKapcs&&p==Color.GREEN) {
            if(Kender.getInstance().FF.getKelvin()>4500&&!Kender.getInstance().flowering)
            Kender.getInstance().fény+=Kender.getInstance().FF.getLux()/1000;
            else
            Kender.getInstance().fény++;
            if(Kender.getInstance().FF.getKelvin()<4000&&Kender.getInstance().flowering)
            Kender.getInstance().fény+=Kender.getInstance().FF.getLux()/1000;
            else
            Kender.getInstance().fény++;
                return true;
        }else {
            sötétIdő++;
            return false;
        }
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
        Kender.getInstance().CO2(Kender.getInstance().LL.getCO2()/2);//(Lég.getCO2()*fényFelvétel())
        return 0;
    }

    @Override
    public int szint() {
        return szint;
    }

    
}