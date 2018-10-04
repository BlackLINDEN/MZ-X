package blacklinden.com.cannabisgrowthsimulator.eszk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Calendar;

public class ClockView extends View {
     private int h,w=0;
     private int padding=0;
     private int fontSize=0;
     private int numeralSpacing=0;
     private int armTrunc,harmTrunc=0;
     private int radius=0;
     private Paint paint;
     private boolean init_e;
     private int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12};
     private Rect rect = new Rect();

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        h=getHeight();
        w=getWidth();
        padding=numeralSpacing+50;
        fontSize=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                13,getResources().getDisplayMetrics());
        int min=Math.min(h,w);
        radius=min/2-padding;
        armTrunc=min/20;
        harmTrunc=min/7;
        paint=new Paint();
        init_e=true;
    }

    @Override
    protected void onDraw(Canvas c){
        if(!init_e) init();
        c.drawColor(Color.BLACK);
        drawCircle(c);
        drawCenter(c);
        drawNumeral(c);
        drawArms(c);
        postInvalidateDelayed(500);
    }

    private void drawArm(Canvas c,double loc,boolean hour_e){
        double angle=Math.PI*loc/30-Math.PI/2;
        int armRadius=hour_e?radius-armTrunc-harmTrunc:radius-armTrunc;
        c.drawLine(w/2,h/2,
                (float)(w/2+Math.cos(angle)*armRadius),
                (float)(h/2+Math.sin(angle)*armRadius),
                 paint);

    }
    private void drawArms(Canvas c){
        Calendar cal=Calendar.getInstance();
        float hour=cal.get(Calendar.HOUR_OF_DAY);
        hour=hour>12?hour-12:hour;
        drawArm(c,
                (hour+cal.get(Calendar.MINUTE/60)*5f),
                true);
        drawArm(c,
                cal.get(Calendar.MINUTE),false);
        drawArm(c,
                cal.get(Calendar.SECOND),false);
    }

    private void drawNumeral(Canvas c) {
        paint.setTextSize(fontSize);
        for (int i : numbers) {
            String temp = String.valueOf(i);
            paint.getTextBounds(temp, 0, temp.length(), rect);
            double angle = Math.PI / 6 * (i - 3);
            int x = (int) (w / 2 + Math.cos(angle) * radius - rect.width() / 2);
            int y = (int) (h / 2 + Math.sin(angle) * radius + rect.height() / 2);
            c.drawText(temp, x, y, paint);

        }
    }

    private void drawCenter(Canvas c){
        paint.setStyle(Paint.Style.FILL);
        c.drawCircle(w/2,h/2,12,paint);
        }

    private void drawCircle(Canvas c){
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        c.drawCircle(w/2,h/2,radius+padding-10,paint);

    }

}
