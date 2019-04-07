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

public class Kolibri implements KolibriState, Runnable, View.OnClickListener {
    private float screenWidth;
    private boolean tutorial_e=false;
    private Handler handler;
    private View kolibri;
    private int kattintas=0;
    private BubbleLayout bubbleLayout;
    private PopupWindow popupWindow;
    private KolibriState state;


    public Kolibri(float screenWidth, View kolibri){

        handler = new Handler(Looper.myLooper());
        this.screenWidth=screenWidth;
        this.kolibri=kolibri;
        //state = Tutorial.getInstance(this);
        kolibri.setOnClickListener(this);
        bubbleLayout = (BubbleLayout) LayoutInflater.from(kolibri.getContext()).inflate(R.layout.bubble_layout, null);
        popupWindow = BubblePopupHelper.create(kolibri.getContext(), bubbleLayout);
    }

    public void dispose(){
        stopIt();
        popupWindow.dismiss();
        state.dispose();
    }


    @Override
    public void flyTo(View view) {
        state.flyTo(view);
    }

    @Override
    public void csirip(View view) {

    }

    @Override
    public void run() {
        //if(!tutorial_e) repdes();

        if(state instanceof Repdes) {
            state.flyTo(null);
            handler.postDelayed(this, 12000);
        }

    }

    public void setTutorial_e(boolean tutorial_e) {
        this.tutorial_e = tutorial_e;
    }

    @Override
    public void onClick(View view) {
        kattintas++;
        //repdes();
    }

    public void stopIt(){
        kattintas=0;
    }

    public View getKolibri(){
        return kolibri;
    }

    float getScreenWidth() {
        return screenWidth;
    }

    BubbleLayout getBubbleLayout() {
        return bubbleLayout;
    }
    PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public KolibriState getState() {
        return state;
    }

    public void setState(String currentState){
        switch (currentState) {
            case "tutorial":
            state = Tutorial.getInstance(this);
            break;
            case "idle":
            state = Idle.getInstance(this);
            break;
            case "repdes":
            state = Repdes.getInstance(this);
            break;
        }
    }

}
