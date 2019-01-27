package blacklinden.com.cannabisgrowthsimulator.eszk;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Environment;

import com.github.alexjlockwood.kyrie.KyrieDrawable;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import blacklinden.com.cannabisgrowthsimulator.MyApp;
import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;

public class Teknős  {
    public double x, y;
    private double angle;
    private Stack<S> stack = new Stack<>();
    private int számláló;
    private int sz=0;

    private Paint levél,szár;
    private Paint mag;
    private Shader sSzr;
    private Bitmap kenderTeszt,mutableBitmap;

    private KyrieDrawable kd;

    public Teknős(Context context) {


        //Bitmap bitmapA = BitmapFactory.decodeResource(context.getResources(), R.drawable.kndr_szr);


        //mutableBitmap.setHeight(10);



        mag = new Paint();
        mag.setColor(Color.rgb(222,184,135));
        mag.setStyle(Paint.Style.FILL);

        sSzr = new BitmapShader(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.kndr_szr),
                Shader.TileMode.REPEAT
                , Shader.TileMode.MIRROR);


        switch (Kender.getInstance().getFajta()) {
            case 1:
                kd = KyrieDrawable.create(context, R.drawable.laci_levele);
                kenderTeszt = BitmapFactory.decodeResource(context.getResources(),R.drawable.budbud1);
                break;
            case 2:
                kd = KyrieDrawable.create(context, R.drawable.ic_haze_leaf);
                kenderTeszt = BitmapFactory.decodeResource(context.getResources(),R.drawable.budbud);
                break;
            case 3:
                kd = KyrieDrawable.create(context, R.drawable.ic_yugo_skunk);
                kenderTeszt = BitmapFactory.decodeResource(context.getResources(),R.drawable.budbud1);
                break;
            case 4:
                kd = KyrieDrawable.create(context,R.drawable.ic_blue_berry);
                kenderTeszt = BitmapFactory.decodeResource(context.getResources(),R.drawable.budbud1);
                break;
            case 5:
                kd = KyrieDrawable.create(context,R.drawable.ic_northern_light);
                kenderTeszt = BitmapFactory.decodeResource(context.getResources(),R.drawable.budbud1);
                break;
            case 6:
                kd = KyrieDrawable.create(context,R.drawable.ic_grape_ape);
                kenderTeszt = BitmapFactory.decodeResource(context.getResources(),R.drawable.budbud1);
                break;
        }



        mutableBitmap = kenderTeszt.copy(Bitmap.Config.ARGB_8888, true);

        levél = new Paint();
        levél.setAntiAlias(true);
        levél.setStyle(Paint.Style.FILL_AND_STROKE);
        levél.setColor(Color.GREEN);
        szár = new Paint();

    }

    public Teknős(){
       /* kenderTeszt = BitmapFactory.decodeResource(context.getResources(),R.drawable.budbud1);
        mutableBitmap = kenderTeszt.copy(Bitmap.Config.ARGB_8888, true);
        */

    }

    public void orient(double x0, double y0, double a0){
        x = x0;
        y = y0;
        angle = a0;
    }


    public void előreRajz(float v, double step, Canvas canvas, int paint, int ism) {
        float oldx =(float) x;
        float oldy =(float) y;

            x += ((float) step ) * Math.cos(Math.toRadians(angle));
            y += ((float) step ) * Math.sin(Math.toRadians(angle));

        szár.reset();
        szár.setStrokeWidth(v);

        szár.setShader(sSzr);

        if(y>canvas.getHeight()/10)
        canvas.drawLine(oldx, oldy, (float)x,(float)y,szár);

    }



    public void virágzás(float v,Canvas canvas, int p){

        int rand = ThreadLocalRandom.current().nextInt(10, 20);
        x += (rand)* Math.cos(Math.toRadians(angle));
        y +=(rand)* Math.sin(Math.toRadians(angle));

      
        canvas.drawBitmap(mutableBitmap,(float)x-mutableBitmap.getWidth()/2,(float)y-mutableBitmap.getHeight(),null);


    }
    //kicsicanvas
    public void terményRajz(Context context, Canvas c,int w, int h, int fajta,int mennyi){
        Bitmap bitmap = rotateBitmap(flowerStrain(context,fajta),ThreadLocalRandom.current().nextInt(1,270));
        int height_bounds_controller = mennyi<50?(h/2):h-bitmap.getHeight();
        int x = ThreadLocalRandom.current().nextInt(0, w-bitmap.getWidth());
        int y = ThreadLocalRandom.current().nextInt(3, height_bounds_controller);

        c.drawBitmap(bitmap,x,y,null);

    }

    public void terményRajz(Context context, Canvas c,int w, int h,int hCont, int fajta,int mennyi){
        Bitmap bitmap = rotateBitmap(flowerStrain(context,fajta),ThreadLocalRandom.current().nextInt(1,270));
        int height_bounds_controller = h-bitmap.getHeight();
        int x = ThreadLocalRandom.current().nextInt(0, w-bitmap.getWidth());
        int y = ThreadLocalRandom.current().nextInt(hCont, height_bounds_controller);

        c.drawBitmap(bitmap,x,y,null);

    }

    private Bitmap flowerStrain(Context context,int fajta){
        switch (fajta){
            case 1: return BitmapFactory.decodeResource(context.getResources(),R.drawable.budbud1);
            default: return BitmapFactory.decodeResource(context.getResources(),R.drawable.budbud);
        }
    }

    private Bitmap rotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
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

        //vd.setBounds((int)(oldx - vast), (int)( y -vast), (int) (x + vast), (int)oldy);
        //vd.setColorFilter(szín,PorterDuff.Mode.MULTIPLY);

        //vd.draw(canvas);

        kd.setBounds((int)(oldx - vast), (int)( y -vast), (int) (x + vast), (int)oldy);
        if(szín!=Color.rgb(34,139,34))
        kd.setTint(szín);
        //kd.setColorFilter(szín,PorterDuff.Mode.MULTIPLY);
        kd.draw(canvas);
    }



    public void mentés(Canvas c, int ix, int iy, int t){
        sz++;
        c.save();
        számláló=c.save();

        stack.push(new S(ix,iy,számláló));

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