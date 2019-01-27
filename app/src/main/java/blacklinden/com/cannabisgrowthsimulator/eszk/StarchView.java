package blacklinden.com.cannabisgrowthsimulator.eszk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;

public class StarchView extends View {
    private Paint paint;
    private int w,h;
    private int radius;
    private float padding;
    private int numeralSpacing;
    private int fontSize;
    private int armTrunc;
    private boolean init_e=false;
    private float humid;


    public StarchView(Context context) {
        super(context);
    }

    public StarchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StarchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        numeralSpacing=0;
        h=getHeight();
        w=getWidth();
        padding=numeralSpacing+50;
        fontSize=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                13,getResources().getDisplayMetrics());
        int min=Math.min(h,w);
        armTrunc=min/20;
        radius=(int)(min-padding);
        init_e=true;



    }

    @Override
    protected void onDraw(Canvas c){
        super.onDraw(c);
        if(!init_e)init();

        //drawCircle(c);
        drawArms(c);
       // postInvalidateDelayed(500);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void drawCircle(Canvas c){
        paint.reset();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        c.drawCircle(w/2,h/2,radius,paint);

    }

    private void drawArm(Canvas c,double loc){
        double angle=Math.PI*loc+Math.PI/2;
        int armRadius=radius-armTrunc;
        c.drawLine(w/2,h/2,
                (float)(w/2+Math.cos(angle)*armRadius),
                (float)(h/2+Math.sin(angle)*armRadius),
                paint);
    }

    private void drawArms(Canvas c){

        drawArm(c, humid);

    }

    public void loc(float humid){
        this.humid=humid*1000;
        postInvalidate();
    }
}
