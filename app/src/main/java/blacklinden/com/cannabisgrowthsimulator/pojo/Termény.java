package blacklinden.com.cannabisgrowthsimulator.pojo;

public class Termény {
    private float mennyi;
    private float suly,eredetiSuly;
    private int fajta;
    private int thc,cbd;
    private float t,c;
    private int napok;
    private int burp,gas;
    private boolean isCuring;
    private String status ="good",fajtaString;
    private float vapor;
    private String sorszám;


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
        t=(thc*(db/10));
        c=(cbd*(db/10));
        suly=mennyi;
        eredetiSuly=suly;
        napok=1;

    }

    public void update(){
        if(vapor>=40&&vapor<60) {
            if (t < thc) t+=2f;
            if (c < cbd) c+=0.5f;
            if(suly>(eredetiSuly*0.4f)) suly--;
        }else if(vapor>60) {
            if (c < cbd) c+=0.1f;
            status="smelly";
        }else if(vapor<40){
            if (t < thc) t+=0.1f;
            if(suly>(eredetiSuly*0.4f)) suly-=1.2f;
        }
        if(isCuring){
            if(gas<10)
            t++;
            else
                status="smelly";
            gas++;
        }else{
            if(goldilocks())status="goldilocks";
        }



        napok++;
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
    private boolean goldilocks(){
        return suly==eredetiSuly*0.5f;
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

    public void setSorszám(String i){
        sorszám=i;
    }

    public String getSorszám(){
        return sorszám;
    }
}
