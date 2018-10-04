package blacklinden.com.cannabisgrowthsimulator.ui.medium;

import android.graphics.drawable.Drawable;

public class Elem {

    private String name;
    private int consumption;
    private float volume;
    private int spectrum;
    private String drain;
    private String rarity;
    private int lumen;
    private Drawable drawable;

    public Elem(String name, int consumption, int spectrum, int lumen,Drawable drawable) {
        this.drawable = drawable;
        this.name = name;
        this.consumption = consumption;
        this.spectrum = spectrum;
        this.lumen = lumen;
    }

    public Elem(String name, float volume, String drain, String rarity,Drawable drawable) {
        this.drawable = drawable;
        this.name = name;
        this.volume = volume;
        this.drain = drain;
        this.rarity = rarity;
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

    public void setSpectrum(int spectrum) {
        this.spectrum = spectrum;
    }

    public int getLumen() {
        return lumen;
    }

    public void setLumen(int lumen) {
        this.lumen = lumen;
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