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

public class Bong extends View implements Runnable  {

    private Paint paint;
    private AnimatedVectorDrawableCompat kd;
    private float top;
    private BongListener listener;
    private boolean filled;
    private int counter;
    private String fajta,mnsg="";
    private float thc,cbd;
    private Handler handler;


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
        handler = new Handler();


    }






    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN&&filled)
                this.run();

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

    public void fillUp(String fajta,float thc,float cbd,String mnsg){
       filled=true;
       top = 0;
       this.mnsg=mnsg;
       this.fajta=fajta;
       this.thc=thc;
       this.cbd=cbd;
       if(listener!=null)listener.inhale();
       invalidate();
    }

    @Override
    public void run() {

        if (filled&&top < getHeight()/2) {

            if(listener!=null){
                listener.exhale(counter*10);
            }

            counter++;
            top+=(getHeight()/2)/5;
            invalidate();
            handler.postDelayed(this,3000);

        }else if(filled&&top>=getHeight()/2){
            filled=false;
            top=getHeight()/2;
            handler.removeCallbacks(this);
            if(listener!=null) listener.end(calculateXp());

        }
    }

    private int calculateXp(){

        float f = XPutil.f(1);
        int q = XPutil.q(mnsg);
        float t = XPutil.thc(thc);
        return (int)((q*(f*f)-1)/(4-t));
    }

    public interface BongListener{

        void inhale();

        void exhale(int counter);

        void end(int xp);

    }

    public void setListener(BongListener listener){this.listener=listener;}


}

class XPutil {
    static int q(String mnsg) {
        int i;
        switch ("good") {
            case "molded":
                i = 1;
                break;
            case "smelly":
                i = 3;
                break;
            case "good":
                i = 5;
                break;
            case "goldilocks":
                i = 7;
                break;
            default:
                i = 0;
        }

        return i;
    }


    static float thc(float thc) {
        if (thc >= 1 && thc < 6)
            return 1.5f;
        else if (thc >= 6 && thc < 11)
            return 2;
        else if (thc >= 11 && thc < 16)
            return 2.5f;
        else if (thc >= 16 && thc < 21)
            return 3;
        else if (thc >= 21 && thc <= 25)
            return 4;
        else
            return 1;
    }

    static int f(int fajta) {
        if (fajta >= 1 && fajta < 5)
            return 2;
        else if (fajta >= 5 && fajta < 9)
            return 3;
        else if (fajta >= 9 && fajta < 13)
            return 4;
        else if (fajta >= 13 && fajta < 17)
            return 5;
        else if (fajta >= 17 && fajta <= 20)
            return 7;
        else return 1;
    }
}

