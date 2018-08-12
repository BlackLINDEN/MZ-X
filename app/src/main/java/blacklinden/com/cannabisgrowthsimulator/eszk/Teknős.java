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

import blacklinden.com.cannabisgrowthsimulator.R;

public class Teknős  {
    public double x, y;
    private double angle;
    private Stack<S> stack = new Stack<>();
    private int sz=0;
    private int számláló;
    private Paint virág,bibe;
    private Paint levélKör;
    private Paint levél,szár;
    private Path path;

    private Shader sSzr;
    private Shader shader1;

    public Teknős(Context context) {


        Bitmap bitmapA = BitmapFactory.decodeResource(
                context.getResources(), R.drawable.kndr_szr);


        sSzr = new BitmapShader(bitmapA,
                Shader.TileMode.REPEAT
                , Shader.TileMode.MIRROR);

        shader1 = new BitmapShader(
                BitmapFactory.decodeResource(context.getResources(),
                R.drawable.lvlrzt12),
                Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT);

        path = new Path();
        path.setFillType(Path.FillType.WINDING);

        virág = new Paint();

        virág.setStyle(Paint.Style.FILL);
        virág.setColor(Color.GREEN);

        bibe = new Paint();
        bibe.setStrokeWidth(1f);

        levélKör = new Paint();
        levélKör.setColor(Color.rgb(25,189,10));
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
        x += ((float)step / ism)* Math.cos(Math.toRadians(angle));
        y +=((float)step / ism)* Math.sin(Math.toRadians(angle));
        szár.reset();
        szár.setStrokeWidth(v);
        if(paint==Color.RED)
        szár.setShader(sSzr);
        else {
            szár.setColor(paint);
            szár.setStyle(Paint.Style.STROKE);
        }
        canvas.drawLine(oldx, oldy, (float)x,(float)y,szár);

    }

    public void virágzás(float v,Canvas canvas, int p){
        float oldx =(float) x;
        float oldy =(float) y;
        x += (5)* Math.cos(Math.toRadians(angle));
        y +=(5)* Math.sin(Math.toRadians(angle));

        bibe.setColor(p);
        canvas.drawCircle((float)x,(float)y,v, virág);
        canvas.drawLine((float)x, (float)y, oldx,oldy,bibe);

    }

    public void levElRajz(float vast, double step, Canvas canvas, Context context, int szín) {

        float oldx =(float) x;
        float oldy =(float) y;
        x += ((float)step)* Math.cos(Math.toRadians(angle));
        y +=((float)step)* Math.sin(Math.toRadians(angle));





        canvas.drawOval(oldx - vast, (float) y -50, (float)(x + vast), oldy, levél);
        canvas.drawOval(oldx - vast, (float) y - 50, (float)(x + vast), oldy, levélKör);

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


        canvas.drawOval(oldx - vast, (float) y -vast, (float)(x + vast), oldy, levél);
        canvas.drawOval(oldx - vast, (float) y - vast, (float)(x + vast), oldy, levélKör);

    }



    public void előre(float sz, double step, Canvas c){
        x += (float)step * Math.cos(Math.toRadians(sz));
        y +=(float) step * Math.sin(Math.toRadians(sz));

        c.translate((float)0,(float)step);
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
        String fname = "Image-"+ n +".jpg";
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