package blacklinden.com.cannabisgrowthsimulator.ui.kolibri;



import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.view.Gravity;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.daasuu.bl.ArrowDirection;
import androidx.annotation.Nullable;
import blacklinden.com.cannabisgrowthsimulator.R;

public class Tutorial implements KolibriState{

    private static Tutorial instance = null;
    private Kolibri kolibri;
    private Path path;
    private OvershootInterpolator tul;


    private Tutorial(Kolibri kolibri){
        this.kolibri=kolibri;
        path = new Path();
        tul = new OvershootInterpolator();

    }

    public static Tutorial getInstance(@Nullable Kolibri kolibri){
        if(instance==null){
            synchronized (Tutorial.class){
                if(instance==null){
                    instance = new Tutorial(kolibri);
                }
            }
        }
        return instance;
    }



    @Override
    public void flyTo(View v) {
        if(kolibri!=null) {
            int[] hely = new int[2];
            int[] helyv = new int[2];
            kolibri.getKolibri().getLocationOnScreen(hely);
            v.getLocationOnScreen(helyv);
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
            animator.setInterpolator(tul);
            animator.start();
            if (x0 < kolibri.getScreenWidth() / 2)
                kolibri.getKolibri().setScaleX(-1f);
            else kolibri.getKolibri().setScaleX(1f);

            path.reset();

            final View cv = v;

            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    csirip(cv);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
        }
    }

    @Override
    public void csirip(View view) {

        int[] hely = new int[2];
        int[] helyv = new int[2];
        if(kolibri!=null) {
            kolibri.getKolibri().getLocationOnScreen(hely);
            view.getLocationOnScreen(helyv);
            kolibri.getBubbleLayout().setArrowDirection(ArrowDirection.LEFT);
            TextView tv = kolibri.getBubbleLayout().findViewById(R.id.vmiTV);
            tv.setText(view.getTag().toString());

            kolibri.getPopupWindow()
                    .showAtLocation(
                            kolibri.getKolibri(),
                            Gravity.NO_GRAVITY, hely[0],
                            hely[1] - kolibri.getKolibri().getHeight() / 2
                    );
        }
    }

    @Override
    public void dispose() {
        instance = null;
    }



}