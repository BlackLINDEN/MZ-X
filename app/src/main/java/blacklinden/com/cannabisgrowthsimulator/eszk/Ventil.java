package blacklinden.com.cannabisgrowthsimulator.eszk;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import blacklinden.com.cannabisgrowthsimulator.R;

public class Ventil extends RelativeLayout  {

    Handler handler = new Handler();
    ImageView ventil;
    Animation animation;
    Thread animationThread;

    public Ventil(Context context) {
        super(context);
        innit(context);
    }

    public Ventil(Context context, AttributeSet attrs) {
        super(context, attrs);
        innit(context);
    }

    public Ventil(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        innit(context);
    }

    private void innit(Context context){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ventil_view, this);


        ventil = findViewById(R.id.prop);
        animation = AnimationUtils.loadAnimation(context, R.anim.rotate_ventil);
        animationThread = new Thread(new Runnable(){
            @Override public void run(){
                handler.post(animateView);
            }

        });

        //animationThread.start();

    }

    public void stop(){
        animation.reset();
        ventil.clearAnimation();

    }

    public void indit(){
        ventil.startAnimation(animation);


    }


    Runnable animateView = new Runnable(){
        @Override public void run(){

            ventil.startAnimation(animation);
        }

    };
}
