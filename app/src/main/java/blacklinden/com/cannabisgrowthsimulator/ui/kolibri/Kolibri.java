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

import android.view.animation.OvershootInterpolator;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.daasuu.bl.ArrowDirection;
import com.daasuu.bl.BubbleLayout;
import com.daasuu.bl.BubblePopupHelper;

import java.util.concurrent.ThreadLocalRandom;

import blacklinden.com.cannabisgrowthsimulator.R;

public class Kolibri implements Runnable, View.OnClickListener {
    private Path path;
    private ObjectAnimator animator;
    private OvershootInterpolator tul;
    private float screenWidth;
    private boolean tutorial_e=false;
    private Handler handler;
    private View kolibri;
    private int kattintas=0;

    public Kolibri(float screenWidth,View kolibri){
        path = new Path();
        handler = new Handler(Looper.myLooper());
        tul = new OvershootInterpolator();
        this.screenWidth=screenWidth;
        this.kolibri=kolibri;
        kolibri.setOnClickListener(this);
    }

    public void flyTo(View v) {

        int[] hely = new int[2];
        int[] helyv = new int[2];
        kolibri.getLocationOnScreen(hely);
        v.getLocationOnScreen(helyv);
        int x1 = hely[0];
        int y1 = hely[1];

        int x0 = helyv[0];
        int y0 = helyv[1];


        float X = (x0 + x1) / 3;
        float Y = (y0 + y1) / 3;


        path.moveTo(x1, y1);
        path.quadTo(X, Y, x0, y0);
        animator = ObjectAnimator.ofFloat(kolibri, View.X, View.Y, path);
        animator.setDuration(2000);
        animator.setInterpolator(tul);
        animator.start();
        if (x0 < screenWidth / 2)
            kolibri.setScaleX(-1f);
        else kolibri.setScaleX(1f);

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

    private void csirip(View cv){

        BubbleLayout bubbleLayout = (BubbleLayout) LayoutInflater.from(kolibri.getContext()).inflate(R.layout.bubble_layout, null);
        PopupWindow popupWindow = BubblePopupHelper.create(kolibri.getContext(), bubbleLayout);

        int[] hely = new int[2];
        int[] helyv = new int[2];
        kolibri.getLocationOnScreen(hely);
        cv.getLocationOnScreen(helyv);
        bubbleLayout.setArrowDirection(ArrowDirection.LEFT);
        TextView tv = bubbleLayout.findViewById(R.id.vmiTV);
        tv.setText(cv.getTag().toString());
        popupWindow.showAtLocation(kolibri, Gravity.NO_GRAVITY, hely[0],  hely[1]-kolibri.getHeight()/2);


    }

    private void repdes(){
        int[] hely = new int[2];

        kolibri.getLocationOnScreen(hely);

        int x1 = hely[0];
        int y1 = hely[1];

        int x0 = ThreadLocalRandom.current().nextInt((int)screenWidth/4-kolibri.getWidth(), (int)screenWidth/2+kolibri.getWidth());
        int y0 = ThreadLocalRandom.current().nextInt((int)screenWidth/4-kolibri.getWidth(), (int)screenWidth+kolibri.getWidth());;


        float X = (x0 + x1) / 3;
        float Y = (y0 + y1) / 3;


        path.moveTo(x1, y1);
        path.quadTo(X, Y, x0, y0);
        animator = ObjectAnimator.ofFloat(kolibri, View.X, View.Y, path);
        animator.setDuration(2000);
        animator.setInterpolator(tul);
        animator.start();
        path.reset();
        if (x0 < screenWidth / 2)
            kolibri.setScaleX(-1f);
        else kolibri.setScaleX(1f);
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
        kolibri.setVisibility(View.GONE);
        kattintas=0;
    }
}
