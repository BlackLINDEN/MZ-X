package blacklinden.com.cannabisgrowthsimulator.ui.medium;

import android.graphics.drawable.Drawable;

public class Elem {

    private String name;
    private int consumption;
    private float volume;
    private int spectrum;
    private String drain;
    private String type;
    private String rarity;
    private int lumen;
    private Drawable drawable;
    private int animDrawCode;
    private int fatyolDrawCode;

    public Elem(String name,String type, int consumption, int spectrum, int lumen,int animDrawCode, int fatyolDrawCode, Drawable drawable) {
        this.drawable = drawable;
        this.name = name;
        this.animDrawCode = animDrawCode;
        this.fatyolDrawCode = fatyolDrawCode;
        this.type=type;
        this.consumption = consumption;
        this.spectrum = spectrum;
        this.lumen = lumen;
    }

    public Elem(String name, float volume, String drain, String rarity,int animDrawCode,Drawable drawable) {
        this.drawable = drawable;
        this.name = name;
        this.volume = volume;
        this.drain = drain;
        this.rarity = rarity;
        this.animDrawCode=animDrawCode;
    }

    public String getName() {
        return name;
    }

    public Drawable getDrawable(){
        return drawable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public int getSpectrum() {
        return spectrum;
    }

    public int getAnimDrawCode(){return animDrawCode;}

    public int getFatyolDrawCode(){return fatyolDrawCode;}

    public void setSpectrum(int spectrum) {
        this.spectrum = spectrum;
    }

    public int getLumen() {
        return lumen;
    }

    public void setLumen(int lumen) {
        this.lumen = lumen;
    }

    public String getType(){
        return type;
    }

    public float getVolume() {
        return volume;
    }

    public String getDrain() {
        return drain;
    }

    public String getRarity() {
        return rarity;
    }

}