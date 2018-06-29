package blacklinden.com.cannabisgrowthsimulator.eszk;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.Stack;

public class Teknős  {
    public double x, y;
    private double angle;
    private Stack<S> stack = new Stack<>();
    private int sz=0;
    private int számláló;
    private Paint levél;
    private Paint levélKör;
    private Path path;



    public Teknős(double x0, double y0, double a0) {
        x = x0;
        y = y0;
        angle = a0;
        path = new Path();
        path.setFillType(Path.FillType.WINDING);

        levél = new Paint();
        levél.setARGB(255,133,235,93);
        levél.setStyle(Paint.Style.FILL);

        levélKör = new Paint();
        levélKör.setColor(Color.BLACK);
        levélKör.setStyle(Paint.Style.STROKE);
        levélKör.setStrokeWidth(7f);

    }

    public void előreRajz(float v, double step, Canvas canvas, Paint paint, int ism) {
        float oldx =(float) x;
        float oldy =(float) y;
        x += ((float)step / ism)* Math.cos(Math.toRadians(angle));
        y +=((float)step / ism)* Math.sin(Math.toRadians(angle));
        paint.setStrokeWidth(v);
        canvas.drawLine(oldx, oldy, (float)x,(float)y,paint);

    }

    public void virágzás(Canvas canvas, Paint p){
        float oldx =(float) x;
        float oldy =(float) y;
        x += (20)* Math.cos(Math.toRadians(angle));
        y +=(20)* Math.sin(Math.toRadians(angle));


        canvas.drawCircle((float)x,(float)y,20,levél);
        canvas.drawLine((float)x, (float)y, oldx,oldy,p);

    }

    public void levElRajz(double step, Canvas canvas, Paint szín) {
        float oldx =(float) x;
        float oldy =(float) y;
        x += ((float)step)* Math.cos(Math.toRadians(angle));
        y +=((float)step)* Math.sin(Math.toRadians(angle));

        canvas.drawOval(oldx - 10, (float) y - 50, (oldx + 10), oldy + 10, szín);
        canvas.drawOval(oldx - 10, (float) y - 50, (oldx + 10), oldy + 10, levélKör);

    }

    public void el(double step, Canvas canvas,Paint paint, int ism) {
        float oldx =(float) x;
        float oldy =(float) y;
        x += (float)step * Math.cos(Math.toRadians(angle));
        y +=(float) step * Math.sin(Math.toRadians(angle));
        levél.setStrokeWidth(ism*2);

        canvas.drawArc(oldx,oldy,(float)(x+step),(float)(y+step),6f,12f,true,paint);




    }
    public void előre(double step, Canvas c){
        x += (float)step * Math.cos(Math.toRadians(angle));
        y +=(float) step * Math.sin(Math.toRadians(angle));

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