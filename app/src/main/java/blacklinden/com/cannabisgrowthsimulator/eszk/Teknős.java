package blacklinden.com.cannabisgrowthsimulator.eszk;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
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
    private VectorDrawable vd;
    private KyrieDrawable kd;

    public Teknős(Context context,float metrix) {


        Bitmap bitmapA = BitmapFactory.decodeResource(
                context.getResources(), R.drawable.kndr_szr);
        kenderTeszt = BitmapFactory.decodeResource(context.getResources(),R.drawable.budbud1);
        mutableBitmap = kenderTeszt.copy(Bitmap.Config.ARGB_8888, true);
        //mutableBitmap.setHeight(10);



        mag = new Paint();
        mag.setColor(Color.rgb(222,184,135));
        mag.setStyle(Paint.Style.FILL);

        sSzr = new BitmapShader(bitmapA,
                Shader.TileMode.REPEAT
                , Shader.TileMode.MIRROR);

        switch (Kender.getInstance().getFajta()) {
            case 1:
                kd = KyrieDrawable.create(context, R.drawable.laci_levele);
                break;
            case 2:
                kd = KyrieDrawable.create(context, R.drawable.ic_haze_leaf);
                break;
            case 3:
                kd = KyrieDrawable.create(context, R.drawable.ic_yugo_skunk);
                break;
            case 4:
                kd = KyrieDrawable.create(context,R.drawable.ic_blue_berry);
                break;
            case 5:
                kd = KyrieDrawable.create(context,R.drawable.ic_northern_light);
                break;
            case 6:
                kd = KyrieDrawable.create(context,R.drawable.ic_grape_ape);
                break;
        }
        Path path = new Path();
        path.setFillType(Path.FillType.WINDING);


        Shader shader1 = new BitmapShader(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.lvlrezet),
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);

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