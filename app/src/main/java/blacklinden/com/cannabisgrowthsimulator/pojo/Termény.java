package blacklinden.com.cannabisgrowthsimulator.pojo;

public class Termény {
    private float mennyi;
    private float suly;
    private int fajta;
    private int thc,cbd;
    private int napok;

    public Termény(float mennyi,int fajta,int thc,int cbd){
        this.mennyi=mennyi;
        this.fajta=fajta;
        this.cbd=cbd;
        this.thc=thc;
        suly=mennyi;
        napok=1;
    }

    public void update(){
        if(mennyi/2>suly)
            suly--;

        napok++;
    }
    public float getSuly(){
        return suly;
    }

    public int getThc(){return thc;}

    public int getCbd(){return cbd;}

    public int getFajta(){return fajta;}

    public int getNapok(){
        return napok;
    }
}
