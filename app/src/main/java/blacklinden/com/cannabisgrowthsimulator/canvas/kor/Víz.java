package blacklinden.com.cannabisgrowthsimulator.canvas.kor;
public class Víz {

    private float PH;


    public float getVÍZ_Mennyiség() {
        return VÍZ_Mennyiség;
    }

    private float VÍZ_Mennyiség;

    public Víz(){
        PH=5.5f;
        VÍZ_Mennyiség=0;
    }
    public float setVÍZ_Mennyiség(int szint){
        if(VÍZ_Mennyiség>=szint/3) {
            VÍZ_Mennyiség -= szint / 3;
            return szint/3;
        }
        else {
            float wv=VÍZ_Mennyiség;
            VÍZ_Mennyiség=0;
            return wv;

        }

    }

    public void locsol(){
        VÍZ_Mennyiség+=10;
    }
    public float getPH(){
        return PH;
    }

}