package blacklinden.com.cannabisgrowthsimulator.shop;

import androidx.annotation.Nullable;

public class ShopItem {
    private String name;
    private int price;
    private int imageCode;
    private @Nullable boolean locked = false;

    public ShopItem(String name,int price,int imageCode){
        this.name=name;
        this.price=price;
        this.imageCode=imageCode;
    }

    public ShopItem(String name,int price,int imageCode,boolean locked){
        this.name=name;
        this.price=price;
        this.imageCode=imageCode;
        this.locked=locked;
    }

    public String getName(){return name;}
    public int getPrice(){return price;}
    public int getImageCode(){return imageCode;}
    public boolean getLocked(){return locked;}
}
