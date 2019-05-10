package blacklinden.com.cannabisgrowthsimulator.ui.kolibri;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.Nullable;

public class Idle implements KolibriState{

    private static Idle instance = null;
    private Kolibri kolibri;
    private Path path;

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
        if(kolibri!=null) {
            kolibri.setText("");
            int[] hely = new int[2];
            int[] helyv = new int[2];
            kolibri.getKolibri().getLocationOnScreen(hely);
            view.getLocationOnScreen(helyv);
            int x1 = hely[0];
            int y1 = hely[1];

            int x0 = helyv[0];
            int y0 = helyv[1];

            float X = (x0 + x1) / 3;
            float Y = (y0 + y1) / 3;

            path.moveTo(x1, y1);
            path.quadTo(X, Y, x0, y0);
            Animator animator = ObjectAnimator.ofFloat(kolibri.getKolibri(), View.X, View.Y, path);
            animator.setDuration(2000);
            animator.setInterpolator(new OvershootInterpolator());
            animator.start();

            path.reset();
            if (kolibri.getKolibri().getScaleX()==-1f)
                kolibri.getKolibri().setScaleX(1f);


        }

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
