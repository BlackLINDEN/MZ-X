package blacklinden.com.cannabisgrowthsimulator.canvas.kor;

import java.util.Stack;

import blacklinden.com.cannabisgrowthsimulator.nov.A;
import blacklinden.com.cannabisgrowthsimulator.nov.Av;
import blacklinden.com.cannabisgrowthsimulator.nov.C;
import blacklinden.com.cannabisgrowthsimulator.nov.F;
import blacklinden.com.cannabisgrowthsimulator.nov.L;
import blacklinden.com.cannabisgrowthsimulator.nov.V;
import blacklinden.com.cannabisgrowthsimulator.nov.X;
import blacklinden.com.cannabisgrowthsimulator.nov.menttolt.M;
import blacklinden.com.cannabisgrowthsimulator.nov.menttolt.T;

public class Verem {

    public Stack<V> v;
    public Stack<X> x;
    public Stack<F> f;
    public Stack<L> l;
    public Stack<M> m;
    public Stack<T> t;
    public Stack<A> a;
    public Stack<Av> av;

    public Verem(){

    v = new Stack<>();
    x = new Stack<>();
    f = new Stack<>();
    l = new Stack<>();

    m = new Stack<>();
    t = new Stack<>();

    a = new Stack<>();
    av = new Stack<>();


    for(int i=0;i<800;i++) {
        m.push(new M());
        t.push(new T());
    }

    for(int i=0;i<500;i++){
        av.push(new Av());
        v.push(new V());

        a.push(new A());
        x.push(new X());
        f.push(new F());
        l.push(new L());
    }

    }

    public boolean üreseValami(){
        return v.empty()||x.empty()||f.empty()||l.empty()||m.empty()||t.empty()||a.empty()||av.empty();
    }

    public boolean üreseMT(){
        return m.empty()||t.empty();
    }
}
