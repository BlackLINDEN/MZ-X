package blacklinden.com.cannabisgrowthsimulator.ui;


public class CardItem {

    private int mTextResource;
    private int mTitleResource;
    private int mPic;

    public CardItem(int title, int text, int pic) {
        mTitleResource = title;
        mTextResource = text;
        mPic = pic;
    }

    public int getText() {
        return mTextResource;
    }

    public int getTitle() {
        return mTitleResource;
    }

    public int getPic() { return mPic; }
}
