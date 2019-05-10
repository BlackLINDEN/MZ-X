package blacklinden.com.cannabisgrowthsimulator.canvas.kor;

public enum Égő {
    INCANDESCENT(0.0041f),HALOGEN(0.1f),HPS(1.3f),LED(0.005f),CFL(0.003f);

    public float getHőszor_() {
        return hőszor_;
    }

    private float hőszor_;

    Égő(float hőszor_){
        this.hőszor_=hőszor_;
    }
}