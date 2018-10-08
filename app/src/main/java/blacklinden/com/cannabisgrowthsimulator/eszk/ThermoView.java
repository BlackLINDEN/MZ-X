package blacklinden.com.cannabisgrowthsimulator.eszk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import blacklinden.com.cannabisgrowthsimulator.nov.Kender;

public class ThermoView extends View {

    Paint piros,fekete,fehér;
    private int fok,fh;


    public ThermoView(Context context) {
        super(context);
        init();
    }

    public ThermoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ThermoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        fh=0;
        fok=(int)(Kender.getInstance().FF.hőmérséklet()/0.5f);
        piros = new Paint();
        piros.setAntiAlias(true);
        piros.setStyle(Paint.Style.FILL);
        piros.setStrokeCap(Paint.Cap.ROUND);
        piros.setColor(Color.RED);
        piros.setStrokeWidth(10f);

        fekete = new Paint();
        fekete.setStyle(Paint.Style.STROKE);
        fekete.setColor(Color.BLACK);
        fekete.setStrokeWidth(10f);



    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);
               c.drawRoundRect(c.getWidth()/4,c.getHeight(),(c.getWidth()/4)*3,0,15,15,fekete);
        c.drawLine(((c.getWidth()/4)*2),c.getHeight()/2,(c.getWidth()/4)*3,c.getHeight()/2,fekete);
        c.drawLine(((c.getWidth()/4)*2),c.getHeight()/3,(c.getWidth()/4)*3,c.getHeight()/3,fekete);
        c.drawLine(((c.getWidth()/4)*2),(c.getHeight()/3)*2,(c.getWidth()/4)*3,(c.getHeight()/3)*2,fekete);
        c.drawCircle(c.getWidth()/2,c.getHeight()-25,18,piros);
        c.drawLine(
                c.getWidth()/2, c.getHeight()-25,
                c.getWidth()/2+(float)(Math.cos(Math.PI/2)*(c.getWidth()*0.008)*fok),
                (float)((c.getHeight()-25)-Math.sin(Math.PI/2)*(c.getHeight()*0.008)*fok), piros
        );

    }

    public Handler handler = new Handler(Looper.myLooper());
    public Runnable oo = new Runnable() {
        @Override
        public void run() {
            fh=(int)Kender.getInstance().FF.hőmérséklet();
            handler.postDelayed(this,1000);

            if(((int)(fh/0.5f))!=fok) {
                if(((int)(fh/0.5f))>fok)
                fok++;
                else
                fok--;
                invalidate();
            }

        }
    };




    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

}
