package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;

public class L extends Növény {

    public float x;
    private boolean balra;
    private int szint;
    private int p;
    private float ép=1;
    private float hjl=60;

    public L(boolean balra) {
        super("L");
        x=0.05f;
        this.balra=balra;

        vízigény();
    }
    public L(boolean balra,int szint){
        super("L");
        x=0.05f;
        this.balra=balra;
        this.szint=szint;
        p = Color.GREEN;
        vízigény();

    }

    @Override
    public void élet() {
        ép+=Kender.getInstance().cukrozó(1);
        if(Kender.getInstance().flowering) {
            if (Kender.getInstance().getRost() <= 0 || Kender.getInstance().nutes.N > Kender.getInstance().nutes.P ||
                    Kender.getInstance().nutes.K > 9 || Kender.getInstance().nutes.P > 17)
                ép--;
        }else{
            if(Kender.getInstance().getRost()<=0||
                    Kender.getInstance().nutes.P>Kender.getInstance().nutes.N
                    ||Kender.getInstance().nutes.K>9||Kender.getInstance().nutes.N>12){
                ép--;
            }
        }
        if(ép==0)
            Kender.getInstance().halottRészek++;
        szín();
        //szög();
        xHossz();
        légz();
        fényFelvétel();
    }

    @Override
    public float vastagság() {
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


    private void xHossz(){
        if(x<110-szint&&ép>0)
            x++;
    }
    @Override
    public float hossz() {
        //ép-=0.1f;
        System.out.println("L "+x);

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

        else if(Kender.getInstance().flowering&&Kender.getInstance().nutes.P>12){
            p=(Color.YELLOW);
            ép--;
        }

        return p;
    }

    private boolean fényFelvétel(){
        if(Kender.getInstance().FF.beKapcs&&(Kender.getInstance().FF.watt-szint)>=0&&p==Color.GREEN) {
            Kender.getInstance().fény++;
            return true;
        }else return false;
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