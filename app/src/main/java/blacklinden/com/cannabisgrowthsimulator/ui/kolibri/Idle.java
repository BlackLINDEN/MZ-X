package blacklinden.com.cannabisgrowthsimulator.ui.kolibri;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import androidx.annotation.Nullable;

public class Idle implements KolibriState{

    private static Idle instance = null;
    private Kolibri kolibri;

    private Idle(Kolibri kolibri){
        this.kolibri=kolibri;
    }

    public static Idle getInstance(@Nullable Kolibri kolibri){
        if(instance==null){
            synchronized (Idle.class){
                if(instance==null){
                    instance = new Idle(kolibri);
                }
            }
        }
        return instance;
    }



    @Override
    public void flyTo(View view) {


    }

    @Override
    public void csirip(View view) {

    }

    @Override
    public void dispose() {
        kolibri = null;
        instance = null;
    }
}
