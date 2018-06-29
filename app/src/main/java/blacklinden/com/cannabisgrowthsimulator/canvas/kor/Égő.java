package blacklinden.com.cannabisgrowthsimulator.canvas.kor;

public enum Égő {
    INCANDESCENT(0.41f),HALOGEN(0.13f),LED(0.05f),CFL(0.3f);

    public float getHőszor_() {
        return hőszor_;
    }

    private float hőszor_;

    Égő(float hőszor_){
        this.hőszor_=hőszor_;
    }
}