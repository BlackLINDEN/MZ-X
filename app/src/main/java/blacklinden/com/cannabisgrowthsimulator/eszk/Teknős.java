package blacklinden.com.cannabisgrowthsimulator.eszk;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;

public class Teknős  {
    public double x, y;
    private double angle;
    private Stack<S> stack = new Stack<>();
    private int sz=0;
    private int számláló;
    private Paint virág,bibe;
    private Paint levélKör;
    private Paint levél,szár;
    private Paint mag;
    private Path path;
    private float metrix;
    private Shader sSzr;
    private Shader shader1,shader2;

    public Teknős(Context context,float metrix) {

        this.metrix=metrix;
        Bitmap bitmapA = BitmapFactory.decodeResource(
                context.getResources(), R.drawable.kndr_szr);

        mag = new Paint();
        mag.setColor(Color.rgb(222,184,135));
        mag.setStyle(Paint.Style.FILL);

        sSzr = new BitmapShader(bitmapA,
                Shader.TileMode.REPEAT
                , Shader.TileMode.MIRROR);
        shader2 = new BitmapShader(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.mag_def_final_blur),
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
        );
        if(Kender.getInstance().getFajta()==1) {
            shader1 = new BitmapShader(
                    BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.slice5),
                    Shader.TileMode.MIRROR,
                    Shader.TileMode.MIRROR);
        }else{
            shader1 = new BitmapShader(
                    BitmapFactory.decodeResource(
                            context.getResources(),
                            R.drawable.psyfra),
                    Shader.TileMode.MIRROR,
                    Shader.TileMode.MIRROR);
        }
        path = new Path();
        path.setFillType(Path.FillType.WINDING);

        virág = new Paint();

        virág.setStyle(Paint.Style.FILL);
        virág.setShader(new BitmapShader(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.gambi24),
                Shader.TileMode.MIRROR,
                Shader.TileMode.MIRROR
                )
        );

        bibe = new Paint();
        bibe.setStrokeWidth(5f);
        bibe.setStyle(Paint.Style.STROKE);
        bibe.setStrokeCap(Paint.Cap.BUTT);
        bibe.setPathEffect(new PathDashPathEffect(
                getTriangle(8),
                20,
                1.0f,
                PathDashPathEffect.Style.ROTATE));


        levélKör = new Paint();

        levélKör.setStyle(Paint.Style.STROKE);
        levélKör.setStrokeCap(Paint.Cap.ROUND);
        levélKör.setStrokeWidth(2f);

       // lg = new LinearGradient(0,0,0,1,Color.BLACK,Color.WHITE, Shader.TileMode.MIRROR);

        levélKör.setPathEffect(new PathDashPathEffect(
                getTriangle(8),
                10,
                0.0f,
                PathDashPathEffect.Style.ROTATE));

        levél = new Paint();
        levél.setAntiAlias(true);
        levél.setStyle(Paint.Style.FILL_AND_STROKE);
        levél.setShader(shader1);
        szár = new Paint();

    }

    public void orient(double x0, double y0, double a0){
        x = x0;
        y = y0;
        angle = a0;
    }


    public void előreRajz(float v, double step, Canvas canvas, int paint, int ism) {
        float oldx =(float) x;
        float oldy =(float) y;
        if(ism<180) {
            x += ((float) step / ism) * Math.cos(Math.toRadians(angle));
            y += ((float) step / ism) * Math.sin(Math.toRadians(angle));
        }else{
            x += ((float) step / 180) * Math.cos(Math.toRadians(angle));
            y += ((float) step / 180) * Math.sin(Math.toRadians(angle));
        }
        szár.reset();
        szár.setStrokeWidth(v);
        if(paint==Color.RED)
        szár.setShader(sSzr);
        else {
            szár.setColor(paint);
            szár.setStyle(Paint.Style.STROKE);
        }
        if(y>canvas.getHeight()/4)
        canvas.drawLine(oldx, oldy, (float)x,(float)y,szár);

    }

    public void virágzás(float v,Canvas canvas, int p){
        float oldx =(float) x;
        float oldy =(float) y;
        int rand = ThreadLocalRandom.current().nextInt(10, 20);
        x += (rand)* Math.cos(Math.toRadians(angle));
        y +=(rand)* Math.sin(Math.toRadians(angle));

        bibe.setColor(p);

        canvas.drawCircle((float)x, (float)y, v, virág);
        canvas.drawCircle((float)x, (float)y, v, bibe);

    }

    public void levElRajz(float vast, double step, Canvas canvas) {

        float oldx =(float) x;
        float oldy =(float) y;
        x += ((float)step)* Math.cos(Math.toRadians(angle));
        y +=((float)step)* Math.sin(Math.toRadians(angle));

        canvas.drawOval(oldx - vast, (float) y - vast, (float)(x + vast), oldy, levél);


    }

    public void magRajz(Canvas canvas){

        x += ((float)10)* Math.cos(Math.toRadians(angle));
        y +=((float)10)* Math.sin(Math.toRadians(angle));

        canvas.drawCircle((float)x,(float)y,10, mag);;
    }

    private Path getTriangle(float size) {
        Path path = new Path();
        float half = size / 2;
        path.moveTo(-half, -half);
        path.lineTo(-half, half);
        path.lineTo(half, 0);
        path.close();
        return path;
    }


    public void levElRajz(float vast,double step, Canvas canvas, int szín) {
        float oldx =(float) x;
        float oldy =(float) y;
        x += ((float)step)* Math.cos(Math.toRadians(angle));
        y +=((float)step)* Math.sin(Math.toRadians(angle));

        levélKör.setColor(szín);
        if(szín==Color.BLACK||szín==Color.YELLOW) levél.setShader(shader2);

        canvas.drawOval(oldx - vast, (float) y -vast, (float)(x + vast), oldy, levél);
        canvas.drawOval(oldx - vast, (float) y - vast, (float)(x + vast), oldy, levélKör);

    }



    public void mentés(Canvas c, int ix, int iy, int t){
        sz++;
        c.save();
        számláló=c.save();

        stack.push(new S(c,ix,iy,számláló));

    }

    public void betöltés(Canvas c){
        sz++;

        c.restoreToCount(stack.peek().t);
        számláló--;
        //c.translate((float)(x-stack.peek().x),((float)y-stack.peek().y));
        x = stack.peek().x;
        y = stack.peek().y;
        stack.pop();

        //System.out.println(sz+" stack t"+stack.peek().t+" x"+(x-stack.peek().x)+" y"+(stack.peek().y));
    }






    public void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Plant-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}