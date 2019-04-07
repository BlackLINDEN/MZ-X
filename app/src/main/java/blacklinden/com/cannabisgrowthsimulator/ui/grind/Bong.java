package blacklinden.com.cannabisgrowthsimulator.ui.grind;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.github.alexjlockwood.kyrie.KyrieDrawable;
import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Teknős;

public class Bong extends View  {

    private Paint paint;
    private AnimatedVectorDrawableCompat kd;
    private float top;
    private BongListener listener;
    private boolean filled;
    private int counter;


    public Bong(Context context) {
        super(context);
        init();
    }

    public Bong(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){

        listener = null;
        paint = new Paint();
        Bitmap bitmap = Teknős.flowerStrain(getContext(),"weed1");
        paint.setShader(new BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.REPEAT));
        kd = AnimatedVectorDrawableCompat.create(getContext(), R.drawable.avd_sziv);
        top=1000;



    }

    private Handler handler = new Handler();
    Runnable loggingThread =  new Runnable(){
        public void run(){

                if (filled&&top < getHeight()/2) {
                    counter++;
                    top+=(getHeight()/2)/5;
                    invalidate();
                    handler.postDelayed(loggingThread,500);

                }else{
                    filled=false;
                    top=getHeight()/2;
                    handler.removeCallbacks(loggingThread);


                }
            }

    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //long totalDuration = kd.getTotalDuration();
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            handler.postDelayed(loggingThread,300);
            if(listener!=null&&top < getHeight()/2){
                listener.inhale();
                kd.start();
            }
        }
        if(event.getAction()==MotionEvent.ACTION_UP){
                handler.removeCallbacks(loggingThread);
                if(listener!=null){
                listener.exhale(counter*10);
                counter=0;
                kd.stop();
                }
        }

        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        kd.setBounds(0,0,getWidth(),getHeight());
        kd.draw(canvas);
        if(top<=getHeight()/2)
        canvas.drawRect(0,top,getWidth()/10,getHeight()/2,paint);


    }

    public void fillUp(){

        filled=true;
       top = 0;
       invalidate();

    }

    public interface BongListener{

        void inhale();

        void exhale(int counter);


    }

    public void setListener(BongListener listener){this.listener=listener;}


}
