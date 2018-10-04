package blacklinden.com.cannabisgrowthsimulator.nov;

public class H extends Növény {



    private int ép;
    public H() {
        super("H");
        this.ép=1;
    }

    @Override
    public void élet() {

        ép++;
    }

    @Override
    public float vastagság() {
        return 0;
    }

    @Override
    public float hossz() {
        return 0;
    }

    @Override
    public float szög() {
       return Kender.getInstance().FF.irány;

    }

    @Override
    public int szín() {
        return 0;
    }

    @Override
    public float fejl() {
        return ép;
    }

    @Override
    public float vízigény() {
        return 0;
    }

    @Override
    public float fényigény() {
        return 0;
    }

    @Override
    public float hőigény() {
        return 0;
    }

    @Override
    public float tápigény() {
        return 0;
    }

    @Override
    public float légz() {
        return 0;
    }

    @Override
    public int szint() {
        return 0;
    }
}
