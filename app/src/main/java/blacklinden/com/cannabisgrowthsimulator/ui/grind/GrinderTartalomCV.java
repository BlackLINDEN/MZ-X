package blacklinden.com.cannabisgrowthsimulator.ui.grind;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.github.alexjlockwood.kyrie.KyrieDrawable;

import java.lang.ref.WeakReference;
import java.util.concurrent.ThreadLocalRandom;

import blacklinden.com.cannabisgrowthsimulator.R;

public class GrinderTartalomCV extends View {

    WeakReference<Bitmap> bitmapWeakReference;
    private float[] points;
    private Paint paint,shaderPaint;
    private boolean isGrinded=false,isEmpty=true;
    private int db;
    private int levonando;
    private int stashID;
    private String fajta,mnsg;
    private float thc,cbd;
    private GrinderListener listener;


    public GrinderTartalomCV(Context context) {
        super(context);
        init();
    }

    public GrinderTartalomCV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){

        paint = new Paint();
        paint.setColor(Color.BLUE);

        shaderPaint = new Paint();

        points = new float[500];



        listener = null;

    }

    public interface GrinderListener{

        void onEmpty();

        void onFilled();

        void onGrinded();

    }

    public void setGrinderListener(GrinderListener listener){
        this.listener = listener;
    }

    public void dispose(){
        bitmapWeakReference.clear();
        isEmpty=true;
        isGrinded=false;

        if(listener!=null)
            listener.onEmpty();
        //listener=null;
    }

    public void fillUp(Bitmap bitmap,int[] db,int stashID,String fajta, String mnsg, float thc, float cbd){
        isEmpty=false;
        bitmapWeakReference = new WeakReference<>(bitmap);
        this.db=db[1];
        levonando=db[0];
        shaderPaint.setShader(new BitmapShader(bitmap,Shader.TileMode.REPEAT,Shader.TileMode.REPEAT));
        this.stashID=stashID;
        this.fajta=fajta;
        this.mnsg=mnsg;
        this.thc=thc;
        this.cbd=cbd;
        invalidate();
    }

    private void drawPoints(Canvas c){

        paint.reset();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);

        for(int i=0;i<points.length/2;i++) {
            int x = ThreadLocalRandom.current().nextInt(0, getWidth());
            int y = ThreadLocalRandom.current().nextInt(0, getHeight());

            points[i*2]=x;
            points[i*2+1]=y;
        }
        c.drawPoints(points, paint);

    }
    private void drawNuggets(Canvas c){


        for(int i=0;i<25;i++) {
            int tempW=getWidth()/10;
            int x = ThreadLocalRandom.current().nextInt(tempW, getWidth()-tempW);
            int y = ThreadLocalRandom.current().nextInt(tempW, getHeight()-tempW);
            int d = ThreadLocalRandom.current().nextInt(-tempW,tempW);

           c.drawOval(x,y,x+d,y+d,shaderPaint);
        }

        if(listener!=null)
            listener.onGrinded();
    }

    private void drawBobi(Canvas c) {


        for (int i = 0; i < db; i++) {
            int x = ThreadLocalRandom.current().nextInt(0, getWidth()-bitmapWeakReference.get().getWidth());
            int y = ThreadLocalRandom.current().nextInt(0, getHeight()-bitmapWeakReference.get().getHeight());

            c.drawBitmap(bitmapWeakReference.get(),x,y,null);
        }

        if(listener!=null)
            listener.onFilled();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if(!isEmpty) {
            if (isGrinded) {

                drawPoints(canvas);
                drawNuggets(canvas);
            } else
                drawBobi(canvas);
        }
    }


    public void setGrinded(boolean b){
        isGrinded=b;
        invalidate();
    }

    public boolean getIsEmpty(){
        return isEmpty;
    }

    public int getStashID(){return stashID;}

    public float getDB(){return db;}

    public float getThc(){return thc;}

    public float getCbd(){return cbd;}

    public String getFajta(){return fajta;}

    public String getMnsg(){return mnsg;}

    public int getLevonando(){return levonando;}

}
