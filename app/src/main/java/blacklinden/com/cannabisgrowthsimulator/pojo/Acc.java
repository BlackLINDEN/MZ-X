package blacklinden.com.cannabisgrowthsimulator.pojo;

public class Acc {

private String name;
private float vol;
private String drain;
private String rarity;
private int drawCode;

public Acc(String name, float vol, String drain, String rarity, int drawCode){
    this.name=name;
    this.vol=vol;
    this.drain=drain;
    this.rarity=rarity;
    this.drawCode=drawCode;
}

public String getName(){
    return name;
}

public float getVol(){
    return vol;
}

public String getDrain(){
    return drain;
}

public String getRarity(){
    return rarity;
}

public int getDrawCode(){
    return drawCode;
}
}
