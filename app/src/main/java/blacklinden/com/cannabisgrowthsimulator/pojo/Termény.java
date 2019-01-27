package blacklinden.com.cannabisgrowthsimulator.pojo;

public class Termény {
    private float mennyi;
    private float suly,eredetiSuly;
    private int fajta;
    private int thc,cbd;
    private float t,c;
    private int napok;
    private float moisture;
    private int burp,gas;
    private boolean isCuring;
    private String status ="good",fajtaString;
    private float vapor;
    private int sorszám;


    public Termény(int db,float mennyi,int fajta,int thc,int cbd){
        this.mennyi=db;
        this.fajta=fajta;
        switch (fajta) {
            case 1:
            this.fajtaString ="weed1";
            break;
            case 2:
            this.fajtaString="weed2";
            break;
            case 3:
            this.fajtaString="weed3";
            break;
            case 4:
            this.fajtaString="weed4";
            break;
            case 5:
            this.fajtaString="weed5";
            break;
            case 6:
            this.fajtaString="weed6";
            break;
        }
        this.cbd=cbd;
        this.thc=thc;
        t=(thc);
        c=(cbd);
        suly=mennyi;
        moisture=suly*0.8f;
        eredetiSuly=suly;
        napok=1;

    }

    public void update(){
        suly=moisture+moisture/4;

        if(!status.equals("molded")&&vapor>=0.4f&&vapor<0.6f) {
            if (t <= thc + vigor()) {

                if (isCuring) {
                    t += vigor();
                } else
                    t += 0.1f;
            }
            if (c <= cbd + vigor()) {

                if (isCuring) {
                    c += vigor();
                } else
                    c += 0.1f;
            }

            if (isCuring && goldilocks()) t++;
        }

        if(vapor<0.4f||vapor>0.6f)
            moisture+=suly*vapor;

       if(moisture>eredetiSuly*0.2f&&moisture>2){
           if(isCuring) {
               moisture--;
               gas++;
           }
           else {
               moisture--;
           }
       }

       if(gas>moisture/2)status="smelly";


        napok++;
    }

    private float vigor(){
        return (int)eredetiSuly/10;
    }
    public float getSuly(){
        return suly;
    }

    public float getThc(){return (t);}

    public float getCbd(){return (c);}

    public int getFajta(){return fajta;}

    public String getFajtaString(){return fajtaString;}

    public float getDarab(){return mennyi;}

    public int getNapok(){
        return napok;
    }
    public boolean goldilocks(){
        return moisture>=eredetiSuly*0.3f&&moisture<=eredetiSuly*0.37f;
    }
    public void burpJar(){
        burp++;
        gas=0;
    }

    public boolean isCuring() {
        return isCuring;
    }

    public String getStatus(){return status;}

    public void setCuring(boolean curing) {
        isCuring = curing;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setVapor(float vapor){
        this.vapor=vapor;
    }

    public void setSorszám(int i){
        sorszám=i;
    }

    public int getSorszám(){
        return sorszám;
    }
}
