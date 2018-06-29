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
        Context context;
        Teknős t;

        float delta_theta=0f;
        public static int ism=1;
        Paint paint;
        boolean stopIt=false;

        ArrayList<Növény> al = new ArrayList<>();


        public CanvasView(Context c, AttributeSet attrs) {
            super(c, attrs);
            context = c;


            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setTextSize(40);
            new Kender();
            F ff = new F();
            al.add(ff);
            al.add(new M());
            al.add(new L(true,1));
            al.add(new T());
            al.add(new M());
            al.add(new T());
            al.add(new M());
            al.add(new L(false,1));
            al.add(new T());

        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        }
        @Override
        protected void onDraw(final Canvas canvas) {
            super.onDraw(canvas);
            if(!stopIt) {
                T(canvas, al);
                canvas.drawText("Aqua "+Kender.getH2o()+" Azúcar "+Kender.getCukor()+" Almidón "+Kender.getRost(),0,canvas.getHeight()*0.1f,paint);
            }
            else
            canvas.drawText("Cosecha Fracasada!",canvas.getWidth()/2,canvas.getHeight()/2,paint);




        }
        Handler handler = new Handler(Looper.myLooper());
        public Runnable oo = new Runnable() {
            @Override
            public void run() {

                invalidate();
                if(ism==1000) {
                    handler.removeCallbacks(this);


                }
                else if(!Kender.Halott_e()){
                    ism();
                    A(al,ism);
                    handler.postDelayed(this, 500);
                }
            }
        };
        private void ism(){
            ism++;

        }

        private ArrayList<Növény> A(ArrayList<Növény> aa, int sm){
            ArrayList<Növény> a = new ArrayList<>();

            if(ism<800){
                for(Növény x:aa){
                    Kender.update();
                    x.élet();
                    if(Objects.equals(x.n, "F")&&x.fejl()==50){
                        a.add(x);
                        a.add(new A(x.szint()));
                    }
                    else if(Objects.equals(x.n,"A")&&x.szint()<500&&x.fejl()>5){

                        a.add(new M());
                        a.add(new X(true,x.szint()));
                        a.add(new L(true,x.szint()));
                        a.add(new T());
                        a.add(new M());
                        a.add(new T());
                        a.add(new M());
                        a.add(new X(false,x.szint()));
                        a.add(new L(false,x.szint()));
                        a.add(new T());
                        a.add(new F(x.szint()));


                    }else if(Objects.equals(x.n,"X")&&x.szint()==5){
                        a.add(new F(x.szint()));


                    }else if(Objects.equals(x.n,"X")&&(int)x.fejl()==300){

                        a.add(new X(x.szög()<0,301,x.hossz()/2,x.szög(),3));
                        a.add(new L(x.szög()<0,4));
                        a.add(new X(x.szög()<0,301,x.hossz()/2,x.szög(),3));
                    }

                    else {
                        a.add(x);
                    }
                } al.clear();
                al.addAll(a); }

                else {
                for (int i = aa.size()-1; i >=0; i--) {


                    Kender.update();
                    Növény y = aa.get(i);
                    y.élet();

                    if (Objects.equals(y.n, "F")&&y.szint()>2) {

                        a.add(new Av(Kender.Szintet()%y.szint()));
                        a.add(y);
                    } else if (Objects.equals(y.n, "AV")&&y.fejl()>1) {

                        a.add(new V());
                    } else {
                        a.add(y);
                    }

                }

                al.clear();
                Collections.reverse(a);
                al.addAll(a);

            }


            return a;
        }

        public void nulláz(){
            stopIt=false;
            ism=1;
            al.clear();
            al.add(new F());
            al.add(new M());
            al.add(new L(true,1));
            al.add(new T());
            al.add(new M());
            al.add(new T());
            al.add(new M());
            al.add(new L(false,1));
            al.add(new T());
            invalidate();
        }


        public void T(Canvas c,ArrayList<Növény> a){


            t = new Teknős(c.getWidth()/2,c.getHeight()-200,90);



            for (Növény ch : a) {

                switch (ch.n) {
                    case "F":
                    case "X":
                        c.rotate(ch.szög(), (float) t.x, (float) t.y);
                        t.előreRajz(ch.vastagság(),-ch.hossz(), c, ch.szín(), ism);

                        break;

                    case "L":
                        if(ch.szint()<2) {
                            c.rotate(ch.szög(), (float) t.x, (float) t.y);
                            t.levElRajz(-ch.hossz(), c, ch.szín());
                        }else if(ch.szint()==2){
                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög()-65, (float) t.x, (float) t.y);
                            t.levElRajz(-ch.hossz()/3,c,ch.szín());
                            t.betöltés(c);
                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög(), (float) t.x, (float) t.y);
                            t.levElRajz(-ch.hossz(), c, ch.szín());
                            t.betöltés(c);
                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög()+65, (float) t.x, (float) t.y);
                            t.levElRajz(-ch.hossz()/3, c, ch.szín());
                            t.betöltés(c);

                        }else {
                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög()-130, (float) t.x, (float) t.y);
                            t.levElRajz(-ch.hossz()/6,c,ch.szín());
                            t.betöltés(c);
                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög()-65, (float) t.x, (float) t.y);
                            t.levElRajz(-ch.hossz()/3, c, ch.szín());
                            t.betöltés(c);
                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög(), (float) t.x, (float) t.y);
                            t.levElRajz(-ch.hossz(), c, ch.szín());
                            t.betöltés(c);
                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög()+65, (float) t.x, (float) t.y);
                            t.levElRajz(-ch.hossz()/3,c,ch.szín());
                            t.betöltés(c);
                            t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                            c.rotate(ch.szög()+130, (float) t.x, (float) t.y);
                            t.levElRajz(-ch.hossz()/6, c, ch.szín());
                            t.betöltés(c);
                        }
                        break;

                    case "V":
                        t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                        c.rotate(ch.szög(), (float) t.x, (float) t.y);
                        t.virágzás(c,ch.szín());
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




}
