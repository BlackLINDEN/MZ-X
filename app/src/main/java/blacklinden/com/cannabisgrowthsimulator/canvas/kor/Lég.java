package blacklinden.com.cannabisgrowthsimulator.canvas.kor;
public class Lég {

   private float CO2;

    public Lég(){
        CO2=3f;
    }

    public  void palack(boolean be){
        CO2 = (be)?30:15f;
    }

    public float getCO2() {
        return CO2;
    }

    public void addCO2(float i){
      CO2+=i;
    }

}