package blacklinden.com.cannabisgrowthsimulator.nov;





import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Cserép;
import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Fény;
import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Lég;
import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Verem;
import blacklinden.com.cannabisgrowthsimulator.canvas.kor.Víz;
import blacklinden.com.cannabisgrowthsimulator.eszk.Nutes;

public final class Kender {

    private static volatile Kender instance = null;

    private int co2;

    private int fajta;


    public Fény FF;
    Lég LL;
    public Víz VV;
    public Cserép CC;
    private float metrix;
    public Nutes nutes;
    int halottRészek=0;
    private int box;

    private boolean auto_e;

    public boolean flowering;

    private float h2o;
    private int szint;

    public boolean halott_e;
    private float rost;

    public Verem verem;
    private float cukor;
    float fény=0;
    public volatile String causeofdeath="";
    private String föld="0";
    private int levonasElottiMagSzam;
    private String strFajta;
    public int fajtaDrawCode;

    private Kender() {

        initFény();
        initVíz();
        initRost();
        initCO2();
        this.flowering=false;
        this.halott_e=false;
        this.szint=1;
        this.LL = new Lég();
        this.VV = new Víz();
        CC = new Cserép();


        nutes = new Nutes();

    }

    public void clear(){
        instance = null;
    }

    public void initFény(){

        this.FF=new Fény();
    }
    public void initCukor(){
        this.cukor=1;
    }
    public void initCO2(){
        this.co2=1;
    }
    public void initVíz(){
        this.h2o=1;
    }


    public void update(int ism){

        //if(cukor<100000)
            calvinKör();
        rostbanCukorTárolás();
        switch (fajta) {
            case 1:
            case 7:
            case 18:
                if (ism == 400) flowering = true;
                break;
            case 2:
            case 8:
            case 17:
                if (ism == 380) flowering = true;
                break;
            case 9:
            case 3:
            case 16:
                if (ism == 380) flowering = true;
                break;
            case 10:
            case 4:
            case 13:
                if (ism == 380) flowering = true;
                break;
            case 11:
            case 5:
            case 14:
                if (ism == 300) flowering = true;
                break;
            case 12:
            case 6:
            case 15:
                if (ism == 325) flowering = true;
                break;
        }

        if(cukor<=0&&rost<=0) {
            cukor = 0;}

        if(cukor<rost&&rost>0){
            cukor+=rost/3;
            rost-=rost/3;

        }

         if(halottRészek>2)
             halott_e=true;

        }

    public static Kender getInstance() {
        if (instance == null) {
            synchronized(Kender.class) {
                if (instance == null) {
                    instance = new Kender();
                }
            }
        }
        return instance;
    }

    public void fajta( String strFajta, int levonasElottiMagSzam ){

        switch (strFajta) {
            case "a":
                fajta=1;
                this.strFajta="Skunk";
                break;
            case "b":
                fajta=2;
                this.strFajta="Afghani";
                break;
            case "c":
              fajta=3;
              this.strFajta="Balkan Rose";
                break;
            case "d":
                fajta=4;
                this.strFajta="BlueBerry";
                break;
            case "e":
                fajta=5;
                this.strFajta="Northern Light";
                break;
            case "f":
                fajta=6;
                this.strFajta="Grape Ape";
                break;
            case "g":
                fajta=7;
                this.strFajta="AK 47";
                break;
            case "h":
                fajta=8;
                this.strFajta="Cheese";
                break;
            case "i":
                fajta=9;
                this.strFajta="Amnesia";
                break;
            case "j":
                fajta=10;
                this.strFajta="Super Lemon Haze";
                break;
            case "k":
                fajta=11;
                this.strFajta="White Widow";
                break;
            case "l":
                fajta=12;
                this.strFajta="Gelato";
                break;
            case "m":
                fajta=13;
                this.strFajta="Ghost OG";
                break;
            case "n":
                fajta=14;
                this.strFajta="Cherry Diesel";
                break;
            case "o":
                fajta=15;
                this.strFajta="Permafrost";
                break;
            case "p":
                fajta=16;
                this.strFajta="Pink Mango";
                break;
            case "q":
                fajta=17;
                this.strFajta="Pineapple";
                break;
            case "r":
                fajta=18;
                this.strFajta="Great White Shark";
                break;
        }

        this.levonasElottiMagSzam=levonasElottiMagSzam;
        verem = new Verem(fajta);
    }

    public void setFöld(String föld){
        CC.setFöld(föld);

    }


    public int getFajta(){
        return fajta;
    }

    private void calvinKör(){

        if(co2>0&&h2o>6&&fény>0&&FF.beKapcs) {
            int nutri=(nutes.N+ nutes.P+ nutes.K)*100;
            cukor +=  (fény+co2+h2o+nutri)*szint;

                if(nutes.N>0) nutes.N--;
                if(nutes.P>0) nutes.P--;
                if(nutes.K>0) nutes.K--;

            co2--;
            h2o=0;
            fény=0;
        }

        if(FF.hőmérséklet()>27&&h2o>0)
            h2o-=2f;
        else
            h2o-=1f;
    }

    private void rostbanCukorTárolás(){
        if(cukor>(szint*10*(nutes.N++)*6)){
            rost+=cukrozó(szint*10*(nutes.N++));
        }
    }

    float cukrozó(float levonás){

        if(cukor<levonás)
            return 0;
        else{
            cukor-=levonás;
            return levonás;
        }
    }

    void CO2(float co2PPM){
        co2+=co2PPM;
    }
    void setH2o(float xx) {
        h2o+=xx;
    }

    public float getH2o() {
        return h2o;
    }
    public float getCukor() {
        return cukor;
    }
    void Szint(){
        szint++;
    }
    public int Szintet(){return szint;}
    public void levonas(int szint){ if (rost>szint)rost-=szint; }
    void levonas(float fszint){ if(cukor>fszint)cukor-=fszint; }
    public void metrix(float metrix){
        this.metrix=metrix;
    }
    public float getRost() {
        return rost;
    }
    public void initRost(){this.rost=100;}
    void levonH2o(){if(h2o>0) h2o-= 1;}
    public String getFöld(){return föld;}
    public String getStrFajta(){return strFajta;}
    public int getLevonasElottiMagSzam(){return levonasElottiMagSzam;}









}