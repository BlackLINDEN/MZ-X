package blacklinden.com.cannabisgrowthsimulator.ui;


public class CardItem {

    private String thc,yield,flower;
    private String strainType,gender;
    private int mTitleResource;
    private int mPic;

    public CardItem(int title, String thc, String yield, String flower, String strainType, String gender, int pic) {
        mTitleResource = title;
        this.thc = thc;
        this.yield = yield;
        this.flower = flower;
        this.strainType = strainType;
        this.gender = gender;
        mPic = pic;
    }

    public String getThc() {
        return thc;
    }

    public String getYield(){ return yield;}

    public String getFlower(){ return flower;}

    public String getStrainType(){return strainType;}

    public String getGender(){ return gender;}

    public int getTitle() {
        return mTitleResource;
    }

    public int getPic() { return mPic; }
}
