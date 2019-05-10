package blacklinden.com.cannabisgrowthsimulator.nov;

import android.graphics.Color;

public class L extends Növény {

    public float x,v;
    private boolean balra;
    private int szint;
    private int p;
    private float ép = 1;
    private float hjl = 5;
    private int sötétIdő = 0;
    private float hosszszab,vastszab;
    private String halál;



    public L(int fajta) {
        super("L");
        x = 5f;
        switch (fajta){
            case 1:
            case 7:
            case 15:
                hosszszab=30;
                vastszab=6;
            break;
            case 2:
            case 8:
            case 16:
                hosszszab=40;
                vastszab=5.6f;
                break;
            case 3:
            case 9:
            case 17:
                hosszszab=28;
                vastszab=6.8f;
                break;
            case 4:
            case 10:
            case 13:
            case 14:
                hosszszab=35;
                vastszab=5f;
                break;
            case 5:
            case 11:
            case 18:
                hosszszab=38;
                vastszab=6.2f;
                break;
            case 6:
            case 12:
                hosszszab=38;
                vastszab=6.2f;
                break;
        }

        p = Color.rgb(34,139,34);

    }

    public L init(boolean balra, int szint){

        this.balra = balra;
        this.szint = szint;
        return this;
    }

    public L init(boolean balra, int szint, int i){

        this.balra = balra;
        this.szint = szint;
        hosszszab-=10;
        return this;
    }



    @Override
    public void élet() {


        if(ép<10)ép += Kender.getInstance().cukrozó(0.5f);


        if (Kender.getInstance().getRost() <= 0 ){
            halál = "SUGAR STARVATION";
            ép--;
            }

        if(ép==0){

            p=(Color.YELLOW);

        }

        if(Kender.getInstance().nutes.N > 18){
            halál += "\nNITROGEN BURN";
            p=(Color.argb(255,200,100,40));
            ép--;
        }

        if(Kender.getInstance().nutes.K > 9 ){
            halál += "\nPOTASSIUM BURN";
            p=(Color.argb(255,100,105,40));
            ép--;
        }

        if (Kender.getInstance().nutes.P > 20){
            halál += "\nPHOSPHORUS BURN";
            p=(Color.argb(255,20,200,40));
            ép--;
        }

        if(sötétIdő>18){
            halál +="\nLIGHT DEPRIVATION";
            ép-=100;
        }
        if(ép<=0) {
            if(halál!=null&&!Kender.getInstance().causeofdeath.contains(halál))
                Kender.getInstance().causeofdeath+="\n "+halál;
                Kender.getInstance().halottRészek++;
        }
        szín();
        vízigény();
        xHossz();
        légz();
        fényFelvétel();
        //fényigény();
        tápigény();
        hőigény();
    }


    @Override
    public float vastagság() {
       v=x/vastszab;
        return v;
    }


    private void xHossz(){
        if(szint<2)hosszszab=10;

        if(szint<60 &&x<hosszszab-szint*2)
            x+= (ép+Kender.getInstance().nutes.N)*0.1f;;
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
        else if(!Kender.getInstance().FF.beKapcs&&hjl>68)
            hjl-=10;
        else if(hjl<61)
            hjl++;
        if(balra)
            return Kender.getInstance().FF.irány-hjl;
        else return Kender.getInstance().FF.irány+hjl;
    }

    @Override
    public int szín() {
        if(Kender.getInstance().Szintet()-szint<ép/100)
        p=Color.YELLOW;
        return p;
    }

    private boolean fényFelvétel(){
        if(Kender.getInstance().FF.beKapcs&&p==Color.rgb(34,139,34)) {
            if(Kender.getInstance().flowering)
                Kender.getInstance().fény+=((Kender.getInstance().FF.getLux()-Kender.getInstance().FF.getKelvin())/1000)*szint;
            else
                Kender.getInstance().fény+=((Kender.getInstance().FF.getLux()+Kender.getInstance().FF.getKelvin())/1000);//*szint;
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
        Kender.getInstance().levonH2o();
        return 0;
    }

    @Override
    public float fényigény() {
        if(Kender.getInstance().FF.beKapcs) {
            if (Kender.getInstance().flowering && Kender.getInstance().FF.getKelvin() > 3000 ||
                    !Kender.getInstance().flowering && Kender.getInstance().FF.getKelvin() < 3000)

                Kender.getInstance().levonas((float) szint);
        }


        return 0;
    }

    @Override
    public float hőigény() {

        if(Kender.getInstance().FF.hőmérséklet()>30||Kender.getInstance().FF.hőmérséklet()<23)
            Kender.getInstance().levonas(szint);
        return 0;
    }

    @Override
    public float tápigény() {
        if(Kender.getInstance().nutes.N>easeInOutSin(ép,1,1,100))
        Kender.getInstance().nutes.N-=easeInOutSin(ép,1,1,100);
        return 0;
    }

    private float  easeInOutSin(float t,float b , float c, float d) {
        return -c/2 * ((float)Math.cos(Math.PI*t/d) - 1) + b;
    }

    @Override
    public float légz() {
        Kender.getInstance().CO2(Kender.getInstance().LL.getCO2());//(Lég.getCO2()*fényFelvétel())
        return 0;
    }

    @Override
    public int szint() {
        return szint;
    }

    
}