package blacklinden.com.cannabisgrowthsimulator.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import blacklinden.com.cannabisgrowthsimulator.eszk.Teknős;
import blacklinden.com.cannabisgrowthsimulator.nov.A;
import blacklinden.com.cannabisgrowthsimulator.nov.X;
import blacklinden.com.cannabisgrowthsimulator.nov.Av;
import blacklinden.com.cannabisgrowthsimulator.nov.F;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;
import blacklinden.com.cannabisgrowthsimulator.nov.L;
import blacklinden.com.cannabisgrowthsimulator.nov.Növény;
import blacklinden.com.cannabisgrowthsimulator.nov.V;
import blacklinden.com.cannabisgrowthsimulator.nov.menttolt.T;
import blacklinden.com.cannabisgrowthsimulator.nov.menttolt.M;



public class CanvasView extends View {



        private Bitmap mBitmap;
        Teknős t;
        private int ism;
    float delta_theta=0f;
    private float metrix;
    Paint paint;
        Context context;
        private ArrayList<Növény> AL;

        public CanvasView(Context c, AttributeSet attrs) {
            super(c, attrs);
            context = c;
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setTextSize(40);
            AL = new ArrayList<>();
            t = new Teknős(getContext());
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        }
        @Override
        protected void onDraw(final Canvas canvas) {
            super.onDraw(canvas);

                T(canvas, AL);

                //canvas.drawText("Aqua "+Kender.getInstance().getH2o()+" Azúcar "+Kender.getInstance().getCukor()+" Almidón "+Kender.getInstance().getRost(),0,canvas.getHeight()*0.1f,paint);

        }




        public void T(Canvas c,ArrayList<Növény> a){

            //

            t.orient(c.getWidth()/2,c.getHeight()-metrix,90);


            for (Növény ch : a) {

                switch (ch.n) {
                    case "F":
                    case "X":
                        c.rotate(ch.szög(), (float) t.x, (float) t.y);
                        t.előreRajz(ch.vastagság(),-ch.hossz(), c, ch.szín(), ism);
                        break;

                    case "H":
                        c.rotate(ch.szög());
                        t.magRajz(c);
                        break;

                    case "C":
                        c.rotate(ch.szög(), (float) t.x, (float) t.y);
                        t.levElRajz(ch.vastagság(),-ch.hossz()/5,c);
                        break;

                    case "L":
                      if(ch.szint()==1) {

                            c.rotate(ch.szög(), (float) t.x, (float) t.y);
                            t.levElRajz(ch.vastagság(),-ch.hossz(),c,ch.szín());

                        }else if(ch.szint()<=3&&ch.szint()>1){

                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög()-65, (float) t.x, (float) t.y);
                            t.levElRajz(ch.vastagság(),-ch.hossz()/2,c,ch.szín());
                            t.betöltés(c);

                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög()+65, (float) t.x, (float) t.y);
                            t.levElRajz(ch.vastagság(),-ch.hossz()/2, c, ch.szín());
                            t.betöltés(c);

                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög(), (float) t.x, (float) t.y);
                            t.levElRajz(ch.vastagság(),-ch.hossz(), c, ch.szín());
                            t.betöltés(c);

                        }else {
                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög()-130, (float) t.x, (float) t.y);
                            t.levElRajz(ch.vastagság(),-ch.hossz()/4,c,ch.szín());
                            t.betöltés(c);

                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög()+130, (float) t.x, (float) t.y);
                            t.levElRajz(ch.vastagság(),-ch.hossz()/4, c, ch.szín());
                            t.betöltés(c);

                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög()-65, (float) t.x, (float) t.y);
                            t.levElRajz(ch.vastagság(),-ch.hossz()/2, c, ch.szín());
                            t.betöltés(c);

                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög()+65, (float) t.x, (float) t.y);
                            t.levElRajz(ch.vastagság(),-ch.hossz()/2,c,ch.szín());
                            t.betöltés(c);

                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög(), (float) t.x, (float) t.y);
                            t.levElRajz(ch.vastagság(),-ch.hossz(), c, ch.szín());
                            t.betöltés(c);
                        }
                        break;

                    case "V":
                        t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                        //c.rotate(ch.szög(), (float) t.x, (float) t.y);

                        t.virágzás(ch.vastagság(),c,ch.szín());
                        t.betöltés(c);
                        break;
                    case "[":
                        t.mentés(c, (int) t.x, (int) t.y, (int) delta_theta);
                        break;
                    case "]":
                        t.betöltés(c);
                        break;
                }



            }
        }
    //adat toldó
    public void told(ArrayList<Növény> yyy, int ism){
        this.ism=ism;
        this.AL.clear();
        this.AL.addAll(yyy);
        invalidate();
    }

        public void metrix(float m){
            metrix=m;
        }


}
