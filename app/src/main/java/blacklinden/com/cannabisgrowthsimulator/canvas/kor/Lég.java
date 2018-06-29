package blacklinden.com.cannabisgrowthsimulator.canvas.kor;
public class Lég {
    //ppm??
    private static float CO2=4f;
    private static float O2=21f;



    public static void palack(){}

    public static void légCsere(){}


    public static float getCO2() {
        return CO2;
    }

    public static void setO2(float o2) {
        O2 = o2;
    }

    public static void _O2() {
        O2 += 0.06f;
        CO2 -= 0.06f;
    }


}