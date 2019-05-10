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

import androidx.annotation.Nullable;
import blacklinden.com.cannabisgrowthsimulator.R;

public class Kolibri implements KolibriState, Runnable, View.OnClickListener {
    private float screenWidth;

    private Handler handler;
    private View kolibri;
    private int kattintas=0;
    private int tmbIndicator=0;
    private KolibriState state;
    private View[] tutorialTmb=null;


    public Kolibri(float screenWidth, View kolibri){

        handler = new Handler(Looper.myLooper());
        this.screenWidth=screenWidth;
        this.kolibri=kolibri;
        //state = Tutorial.getInstance(this);

        kolibri.setOnClickListener(this);
        //((TextView)kolibri).setText("szíjjá má mivan má more máá");

    }

    public void dispose(){
        stopIt();

        state.dispose();
    }


    @Override
    public void flyTo(View view) {
        if(!view.getTag().toString().equals("smoke weed"))
        state.flyTo(view);
        else{
            setState("smoke",null);
            state.flyTo(view);
        }
    }

    @Override
    public void csirip(View view) {

    }

    @Override
    public void run() {
        //if(!tutorial_e) repdes();

        if(state instanceof Repdes) {
            tmbIndicator=0;
            tutorialTmb=null;
            state.flyTo(null);
            handler.postDelayed(this, 12000);

        }else if(state instanceof Tutorial&&tutorialTmb!=null){

            if(tmbIndicator<tutorialTmb.length-1)
                state.flyTo(tutorialTmb[tmbIndicator]);
            else
            state = Repdes.getInstance(this);

            if(tutorialTmb!=null)
            tmbIndicator++;

            handler.postDelayed(this, 5000);
        }else {
            tutorialTmb=null;
            tmbIndicator = 0;
        }

        if(kattintas>=3){
            state=Repdes.getInstance(this);
            state.flyTo(null);
            kattintas=0;
        }

    }

    public void setTutorial_e(View[] tutorialTmb) {
        this.tutorialTmb = tutorialTmb;
        state=Tutorial.getInstance(this);
    }

    @Override
    public void onClick(View view) {

        kattintas++;

    }

    private void stopIt(){
        kattintas=0;
    }

    public View getKolibri(){
        return kolibri;
    }

    float getScreenWidth() {
        return screenWidth;
    }

    void setText(String text){
        ((TextView)kolibri).setText(text);
    }

    public KolibriState getState() {
        return state;
    }

    public void setState(String currentState, @Nullable View view){
        switch (currentState) {
            case "tutorial":
            state = Tutorial.getInstance(this);
            if(view!=null)
            state.flyTo(view);
            break;
            case "idle":
            state = Idle.getInstance(this);
            state.flyTo(view);
            break;
            case "repdes":
            state = Repdes.getInstance(this);
            this.run();
            break;
            case "smoke":
            state = Smoke.getInstance(this);
            if(view!=null)state.flyTo(view);
            break;
        }
    }

}
