package blacklinden.com.cannabisgrowthsimulator.eszk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

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
        numeralSpacing=0;
        h=getHeight();
        w=getWidth();
        padding=numeralSpacing+50;
        fontSize=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                13,getResources().getDisplayMetrics());
        int min=Math.min(h,w);
        armTrunc=min/20;
        radius=(int)(min/2-padding);
        init_e=true;
    }

    @Override
    protected void onDraw(Canvas c){
        super.onDraw(c);
        if(!init_e)init();
        drawCircle(c);
        drawArms(c);
        postInvalidateDelayed(500);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public Handler handler = new Handler(Looper.myLooper());
    public Runnable oo = new Runnable() {
        @Override
        public void run() {

        }
    };

    private void drawCircle(Canvas c){
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        c.drawCircle(w/2,h/2,radius,paint);

    }

    private void drawArm(Canvas c,double loc){
        double angle=Math.PI*loc/30000+Math.PI/2;
        int armRadius=radius-armTrunc;
        c.drawLine(w/2,h/2,
                (float)(w/2+Math.cos(angle)*armRadius),
                (float)(h/2+Math.sin(angle)*armRadius),
                paint);
    }

    private void drawArms(Canvas c){

        drawArm(c, Kender.getInstance().getCukor());

    }

}
