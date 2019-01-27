package blacklinden.com.cannabisgrowthsimulator.ui.kolibri;


import android.animation.Animator;
import android.animation.ObjectAnimator;

import android.graphics.Path;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.TooltipCompat;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.daasuu.bl.ArrowDirection;
import com.daasuu.bl.BubbleLayout;
import com.daasuu.bl.BubblePopupHelper;

import java.lang.ref.WeakReference;
import java.util.concurrent.ThreadLocalRandom;

import blacklinden.com.cannabisgrowthsimulator.R;

public class Kolibri implements Runnable, View.OnClickListener {
    private Path path;
    private ObjectAnimator animator;
    private OvershootInterpolator tul;
    private float screenWidth;
    private boolean tutorial_e=false;
    private Handler handler;
    private WeakReference<View> kolibri;
    private int kattintas=0;
    private BubbleLayout bubbleLayout;
    private PopupWindow popupWindow;


    public Kolibri(float screenWidth, View kolibri){
        path = new Path();
        handler = new Handler(Looper.myLooper());
        tul = new OvershootInterpolator();
        this.screenWidth=screenWidth;
        this.kolibri=new WeakReference<>(kolibri);
        kolibri.setOnClickListener(this);
        bubbleLayout = (BubbleLayout) LayoutInflater.from(kolibri.getContext()).inflate(R.layout.bubble_layout, null);
        popupWindow = BubblePopupHelper.create(kolibri.getContext(), bubbleLayout);
    }

    public void dispose(){
        kolibri.clear();
    }

    public void flyTo(View v) {
        if(tutorial_e) {
            int[] hely = new int[2];
            int[] helyv = new int[2];
            kolibri.get().getLocationOnScreen(hely);
            v.getLocationOnScreen(helyv);
            int x1 = hely[0];
            int y1 = hely[1];

            int x0 = helyv[0];
            int y0 = helyv[1];


            float X = (x0 + x1) / 3;
            float Y = (y0 + y1) / 3;


            path.moveTo(x1, y1);
            path.quadTo(X, Y, x0, y0);
            animator = ObjectAnimator.ofFloat(kolibri.get(), View.X, View.Y, path);
            animator.setDuration(2000);
            animator.setInterpolator(tul);
            animator.start();
            if (x0 < screenWidth / 2)
                kolibri.get().setScaleX(-1f);
            else kolibri.get().setScaleX(1f);

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

    public void flyTo(View v,String cs) {
        final String fcs = cs;
        if(tutorial_e) {
            int[] hely = new int[2];
            int[] helyv = new int[2];
            kolibri.get().getLocationOnScreen(hely);
            v.getLocationOnScreen(helyv);
            int x1 = hely[0];
            int y1 = hely[1];

            int x0 = helyv[0];
            int y0 = helyv[1];


            float X = (x0 + x1) / 3;
            float Y = (y0 + y1) / 3;


            path.moveTo(x1, y1);
            path.quadTo(X, Y, x0, y0);
            animator = ObjectAnimator.ofFloat(kolibri.get(), View.X, View.Y, path);
            animator.setDuration(2000);
            animator.setInterpolator(tul);
            animator.start();
            if (x0 < screenWidth / 2)
                kolibri.get().setScaleX(-1f);
            else kolibri.get().setScaleX(1f);

            path.reset();

            final View cv = v;

            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    csirip(cv,fcs);
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


    public void flyTo(View v,boolean i) {
        if(i) {
            int[] hely = new int[2];
            int[] helyv = new int[2];
            kolibri.get().getLocationOnScreen(hely);
            v.getLocationOnScreen(helyv);
            int x1 = hely[0];
            int y1 = hely[1];

            int x0 = helyv[0];
            int y0 = helyv[1];


            float X = (x0 + x1) / 3;
            float Y = (y0 + y1) / 3;


            path.moveTo(x1, y1);
            path.quadTo(X, Y, x0, y0);
            animator = ObjectAnimator.ofFloat(kolibri.get(), View.X, View.Y, path);
            animator.setDuration(2000);
            animator.setInterpolator(tul);
            animator.start();
            if (x0 < screenWidth / 2)
                kolibri.get().setScaleX(-1f);
            else kolibri.get().setScaleX(1f);

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

    private void csirip(View cv){



        int[] hely = new int[2];
        int[] helyv = new int[2];
        kolibri.get().getLocationOnScreen(hely);
        cv.getLocationOnScreen(helyv);
        bubbleLayout.setArrowDirection(ArrowDirection.LEFT);
        TextView tv = bubbleLayout.findViewById(R.id.vmiTV);
        tv.setText(cv.getTag().toString());
        if(this!=null)
        popupWindow.showAtLocation(kolibri.get(), Gravity.NO_GRAVITY, hely[0],  hely[1]-kolibri.get().getHeight()/2);


    }

    private void csirip(View cv,String cs){

        BubbleLayout bubbleLayout = (BubbleLayout) LayoutInflater.from(kolibri.get().getContext()).inflate(R.layout.bubble_layout, null);
        PopupWindow popupWindow = BubblePopupHelper.create(kolibri.get().getContext(), bubbleLayout);

        int[] hely = new int[2];
        int[] helyv = new int[2];
        kolibri.get().getLocationOnScreen(hely);
        cv.getLocationOnScreen(helyv);
        bubbleLayout.setArrowDirection(ArrowDirection.LEFT);
        TextView tv = bubbleLayout.findViewById(R.id.vmiTV);
        tv.setText(cs);
        popupWindow.showAtLocation(kolibri.get(), Gravity.NO_GRAVITY, hely[0],  hely[1]-kolibri.get().getHeight()/2);


    }

    private void repdes(){
        int[] hely = new int[2];

        kolibri.get().getLocationOnScreen(hely);

        int x1 = hely[0];
        int y1 = hely[1];

        int x0 = ThreadLocalRandom.current().nextInt((int)screenWidth/4-kolibri.get().getWidth(), (int)screenWidth/2+kolibri.get().getWidth());
        int y0 = ThreadLocalRandom.current().nextInt((int)screenWidth/4-kolibri.get().getWidth(), (int)screenWidth+kolibri.get().getWidth());;


        float X = (x0 + x1) / 3;
        float Y = (y0 + y1) / 3;


        path.moveTo(x1, y1);
        path.quadTo(X, Y, x0, y0);
        animator = ObjectAnimator.ofFloat(kolibri.get(), View.X, View.Y, path);
        animator.setDuration(2000);
        animator.setInterpolator(tul);
        animator.start();
        path.reset();
        if (x0 < screenWidth / 2)
            kolibri.get().setScaleX(-1f);
        else kolibri.get().setScaleX(1f);
    }


    @Override
    public void run() {
        if(!tutorial_e) repdes();


        if(kattintas>=5)
            stopIt();
        else
            handler.postDelayed(this, 12000);
    }


    public void setTutorial_e(boolean tutorial_e) {
        this.tutorial_e = tutorial_e;
    }

    @Override
    public void onClick(View view) {
        kattintas++;
        repdes();
    }

    private void stopIt(){
        handler.removeCallbacks(this);
        kolibri.get().setVisibility(View.GONE);
        kattintas=0;
    }


}
