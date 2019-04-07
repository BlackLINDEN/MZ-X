package blacklinden.com.cannabisgrowthsimulator.pojo;

public class Nutri {

    private int drawCode;
    private String name;
    private int N;
    private int P;
    private int K;


    public Nutri(String name, int drawCode, int N, int P, int K){
        this.drawCode=drawCode;
        this.name=name;
        this.K=K;
        this.N=N;
        this.P=P;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public int getP() {
        return P;
    }

    public void setP(int p) {
        P = p;
    }

    public int getK() {
        return K;
    }

    public void setK(int k) {
        K = k;
    }

    public int getDrawCode(){return drawCode;}


}
