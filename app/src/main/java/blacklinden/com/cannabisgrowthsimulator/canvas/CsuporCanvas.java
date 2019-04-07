package blacklinden.com.cannabisgrowthsimulator.canvas;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Teknős;
import blacklinden.com.cannabisgrowthsimulator.pojo.Termény;

public class CsuporCanvas extends View {

    private Teknős t;
    private boolean empty;
    private Paint üveg,kupak,hilite;
    private Termény termény;
    private boolean nyitva,hilit;

    public CsuporCanvas(Context context) {
        super(context);
        innit();


    }

    public CsuporCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        innit();

    }

    private void innit(){
        empty=true;
        nyitva=false;
        t = new Teknős();
        hilite = new Paint();
        hilite.setColor(Color.MAGENTA);
        üveg = new Paint();

        üveg.setStyle(Paint.Style.FILL);
        Shader shader = new BitmapShader(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.glss_txtr)
                ,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        üveg.setShader(shader);

        kupak = new Paint();

        kupak.setStyle(Paint.Style.FILL);
        Shader shader1 = new BitmapShader(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.prfa_txtr)
                ,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        kupak.setShader(shader1);




    }

    public void fillUp(Termény termény){
        this.termény=termény;
        empty=false;
        postInvalidate();
    }
    public void empty(){
        this.termény=null;
        empty=true;
        nyitva=true;
        postInvalidate();
    }

    public boolean isEmpty(){
        return empty;
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        float w=getWidth();
        float h=getHeight();

        hiliter(canvas,w,h);

        if(termény!=null) {

            for (int i = 0; i < 100; i++)
                t.terményRajz(this.getContext(), canvas, (int)w, (int)h,(int)(h/4), termény.getFajta(), 10);
        }

        canvas.drawRoundRect(1,h/4,w,h,15,15,üveg);
        kupakRajz(canvas,w,h);
        canvas.drawRoundRect(w/6,h/4-w/10,(w-w/6),h/4,20,20,üveg);

    }

    private void kupakRajz(Canvas canvas,float w,float h){
        t.orient((w/6+w/10),(h/4-w/10),0);
        if(nyitva){
            t.mentés(canvas,(int)(w/6+w/10),(int)(h/4-w/10),0);
            canvas.rotate(-40,(w/6+w/10),(h/4));
            canvas.drawRoundRect(w/6+w/20,h/4-w/6,(w-w/6)-w/20,h/4,20,20,kupak);
            t.betöltés(canvas);
        }else

            canvas.drawRoundRect(w/6+w/20,h/4-w/5,(w-w/6)-w/20,h/4,20,20,kupak);


    }

    private void hiliter(Canvas canvas,float w,float h){
        if(hilit){
            canvas.drawRoundRect(0,h/4,w,h,20,20,hilite);
        }

    }

    public void setNyitva(){
        nyitva = !nyitva;
        postInvalidate();
    }
    public void hilite(){
        hilit = !hilit;
        postInvalidate();
    }

    public Termény getTermény(){
        return termény;
    }
}
