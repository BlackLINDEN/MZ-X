package blacklinden.com.cannabisgrowthsimulator.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Teknős;

import blacklinden.com.cannabisgrowthsimulator.pojo.Termény;

public class KicsiCanvas extends View {

    private Teknős t;
    private Termény termény;
    private Paint paint,outline;
    private boolean empty = true;
    private Shader shader;
    private WeakReference<Bitmap> bitmap;




    public KicsiCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        t = new Teknős();
        setup();
    }



    private void setup(){

        paint = new Paint();
        outline = new Paint();
        outline.setColor(Color.DKGRAY);
        outline.setStyle(Paint.Style.STROKE);
        outline.setStrokeWidth(10);

        bitmap = new WeakReference<>(BitmapFactory.decodeResource(getResources(),R.drawable.blck_lnn));
        shader = new BitmapShader(bitmap.get()
        ,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        paint.setShader(shader);


    }

    public void init( Termény termény){

        this.termény=termény;
        empty = termény == null;
        postInvalidate();
    }

    public void dispose(){
        t=null;

            bitmap.get().recycle();
            bitmap.clear();
            bitmap= null;
            shader=null;

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawOval(0,getHeight()-getHeight()/3,getWidth(),getHeight(),paint);
        canvas.drawRoundRect(0,(getHeight()-getHeight()/7),getWidth(),getHeight(),50,50,paint);
        if(termény!=null) {

            for (int i = 0; i < 100; i++)
                t.terményRajz(this.getContext(), canvas, getWidth(), getHeight(), termény.getFajta(), 100);
        }
        canvas.drawRoundRect(0,getHeight(),getWidth(),0,15,15,paint);
        canvas.drawRoundRect(0,getHeight(),getWidth(),0,30,30,outline);
        canvas.drawOval(0,0,getWidth(),getHeight()/10,paint);
    }


    public boolean isEmpty(){
        return empty;
    }


    public String getFajta(){
        String fajta;
        switch(termény.getFajta()){
            case 1:
                fajta="yugoszláv";
                break;
            case 2:
                fajta="valami más";
                break;
            case 6:
                fajta ="grape ape";
                break;
            default:
                fajta="csumibogyó";
        }

        return fajta;
    }


    //ide majd valami számolás is kell
    public int getSzarazsag(){
        return termény.getNapok();
    }

    public Termény getTermény(){

        return termény;
    }

    public void setEmpty(){
        termény=null;
        empty=true;
        postInvalidate();
    }






}
