package blacklinden.com.cannabisgrowthsimulator.pojo;

public class Lamps {
    private String name;
    private int consumption;

    private int spectrum;

    private String type;

    private int lumen;
    private int animDrawCode;
    private int fatyolDrawCode;

    public Lamps(String name,String type, int consumption, int spectrum, int lumen,int animDrawCode, int fatyolDrawCode) {
        this.name = name;
        this.animDrawCode = animDrawCode;
        this.fatyolDrawCode = fatyolDrawCode;
        this.type=type;
        this.consumption = consumption;
        this.spectrum = spectrum;
        this.lumen = lumen;
    }

    public String getName() {
        return name;
    }



    public int getConsumption(){return consumption;}

    public int getSpectrum() {
        return spectrum;
    }

    public int getAnimDrawCode(){return animDrawCode;}

    public int getFatyolDrawCode(){return fatyolDrawCode;}



    public int getLumen() {
        return lumen;
    }


    public String getType(){
        return type;
    }

}
