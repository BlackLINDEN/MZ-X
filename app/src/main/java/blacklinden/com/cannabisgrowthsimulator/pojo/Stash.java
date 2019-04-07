package blacklinden.com.cannabisgrowthsimulator.pojo;




public class Stash {
    private float mennyi;
    private float thc,cbd;
    private String minőség;
    private String fajta;
    private int napok;
    private int id;

    public Stash(float mennyi, float thc, float cbd, int napok, String fajta){
        this.fajta=fajta;
        this.mennyi=mennyi;
        this.napok=napok;
        this.thc=thc;
        this.cbd=cbd;
    }

    public Stash(int id,String fajta,float mennyi){
        this.fajta=fajta;
        this.mennyi=mennyi;
        this.id=id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        Stash stash = (Stash) obj;
        return stash.getFajta().equals(this.getFajta());

    }

    public float getMennyi(){
        return mennyi;
    }
    public float getThc(){
        return thc;
    }
    public float getCbd(){
        return cbd;
    }
    public String getMinőség(){
        return minőség;
    }
    public String getFajta(){
        return fajta;
    }
    public int getId(){ return id; }
}
