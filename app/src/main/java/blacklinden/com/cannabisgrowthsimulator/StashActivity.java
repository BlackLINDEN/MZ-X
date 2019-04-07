package blacklinden.com.cannabisgrowthsimulator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.support.animation.DynamicAnimation;
import android.support.animation.FloatPropertyCompat;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.circularprogressbar.CircularProgressBar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.plattysoft.leonids.ParticleSystem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.UUID;

import blacklinden.com.cannabisgrowthsimulator.canvas.CsuporCanvas;
import blacklinden.com.cannabisgrowthsimulator.canvas.KicsiCanvas;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.eszk.Ventil;
import blacklinden.com.cannabisgrowthsimulator.pojo.Stash;
import blacklinden.com.cannabisgrowthsimulator.pojo.Termény;
import blacklinden.com.cannabisgrowthsimulator.serv.StashService;

public class StashActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {


    private float dX;
    private float dY;
    private CsuporCanvas csupi,cs1,cs2,cs3;
    private  KicsiCanvas kicsiCanvas, kc2, kc3;
    private float humidity;
    private TextView paramerő;
    private CircularProgressBar cpb;
    private boolean ventilBE;
    private Ventil ventilObj;
    private ImageView kuka,pakk;
    private LinearLayout ll;
    private TextView ll2tv,ll3tv,ll4tv,ll5tv,
    cstv,cstv1,cstv2,cstv3;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stash);
        paramerő = findViewById(R.id.para);
        cpb = findViewById(R.id.progress_bar);
        cpb.setProgressAnimationInterpolator(new DecelerateInterpolator());
        ll = findViewById(R.id.csupisor);
        Typeface plain = Typeface.createFromAsset(getAssets(), "font/digitaldream.ttf");
        paramerő.setTypeface(plain);


        humidity = Mentés.getInstance().getFloat(Mentés.Key.PARATART,0.3f);

        ventilObj = findViewById(R.id.ventil);
        ventilObj.setLayerType(View.LAYER_TYPE_HARDWARE,null);

        kicsiCanvas = findViewById(R.id.stashCanvas);
        kc2 = findViewById(R.id.stashCanvas2);
        kc3 = findViewById(R.id.stashCanvas3);
        final ImageView iv = findViewById(R.id.h1);
        kuka = findViewById(R.id.kuka);
        pakk = findViewById(R.id.pakk);

        kicsiCanvas.setOnTouchListener(this);
        kc2.setOnTouchListener(this);
        kc3.setOnTouchListener(this);
        pakk.setOnTouchListener(this);



        chainedSpringAnimation(iv,kicsiCanvas,kc2,kc3);

        Stack<KicsiCanvas> kicsiCanvasStack = new Stack<>();
        kicsiCanvasStack.push(kicsiCanvas);
        kicsiCanvasStack.push(kc2);
        kicsiCanvasStack.push(kc3);





        csupi = findViewById(R.id.csupi);
        cs1 = findViewById(R.id.csupi1);
        cs2 = findViewById(R.id.csupi2);
        cs3 = findViewById(R.id.csupi3);

        csupi.setOnDragListener(this);
        cs1.setOnDragListener(this);
        cs2.setOnDragListener(this);
        cs3.setOnDragListener(this);
        //kuka.setOnDragListener(this);
        Stack<CsuporCanvas> csuporCanvasStack = new Stack<>();

        csuporCanvasStack.add(csupi);
        csuporCanvasStack.add(cs1);
        csuporCanvasStack.add(cs2);
        csuporCanvasStack.add(cs3);


        ll2tv = findViewById(R.id.ll2tv);
        ll2tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ll3tv = findViewById(R.id.ll3tv);
        ll3tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ll4tv = findViewById(R.id.ll4tv);
        ll4tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ll5tv = findViewById(R.id.ll5tv);
        ll5tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        cstv = findViewById(R.id.cstv);
        cstv1 = findViewById(R.id.cstv1);
        cstv2 = findViewById(R.id.cstv2);
        cstv3 = findViewById(R.id.cstv3);


        String rawList = Mentés.getInstance().getString(Mentés.Key.TRMS_LST,"0");
        if(!rawList.equals("0")) {
            Gson gson = new Gson();
            Type tList = new TypeToken<ArrayList<Termény>>() {
            }.getType();
            List<Termény> termenyList = gson.fromJson(rawList, tList);
            if(!termenyList.isEmpty()) {
                for (Termény t : termenyList) {

                    if (kicsiCanvasStack.peek().isEmpty()) {
                        t.setSorszám(createSalt());
                        Toast.makeText(this,"oncreate \n"+t.getSorszám(),Toast.LENGTH_SHORT).show();
                        kicsiCanvasStack.pop().init(t);
                    }
                }

                Mentés.getInstance().put(Mentés.Key.TRMS_LST,gson.toJson(termenyList));
            }


        }

        String rawListErllt = Mentés.getInstance().getString(Mentés.Key.ERllT_LST,"0");
        if(!rawList.equals("0")) {
            Gson gson = new Gson();
            Type tList = new TypeToken<ArrayList<Termény>>() {
            }.getType();
            List<Termény> erleltlist = gson.fromJson(rawListErllt, tList);
            if(!erleltlist.isEmpty()) {
                for (Termény t : erleltlist) {

                    if (csuporCanvasStack.peek().isEmpty()) {

                        csuporCanvasStack.pop().fillUp(t);
                    }
                }
            }
        }



        JobScheduler jobScheduler = (JobScheduler)getApplicationContext()
                .getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName componentName = new ComponentName(this,
                StashService.class);

        long scheduler_Interval = 15 * DateUtils.MINUTE_IN_MILLIS;
        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setPeriodic(scheduler_Interval)
                .setPersisted(false).build();
        Objects.requireNonNull(jobScheduler).schedule(jobInfo);

        paramerő.setText(Integer.toString((int) (humidity*100)));
        cpb.setProgress(humidity*100);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setCanceledOnTouchOutside(true);

        oo.run();
        aa.run();

    }

    private String createSalt() {

        return (UUID.randomUUID().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Mentés.getInstance().put(Mentés.Key.PARATART,humidity);
        if(dialog.isShowing())dialog.dismiss();
    }


    public SpringAnimation createSpringAnimation(View view,
                                                 DynamicAnimation.ViewProperty property,
                                                 float stiffness,
                                                 float dampingRatio) {
        SpringAnimation animation = new SpringAnimation(view, property);
        SpringForce springForce = new SpringForce();
        springForce.setStiffness(stiffness);
        springForce.setDampingRatio(dampingRatio);
        animation.setSpring(springForce);
        return animation;
    }



    private void chainedSpringAnimation( final View c1, final View c2, final View c3,final View c4) {


        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        final float w = point.x;
        final float h = point.y;

        final FloatPropertyCompat<View> scale =
                new FloatPropertyCompat<View>("scale") {
                    @Override
                    public float getValue(View view) {
                        // return the value of any one property
                        return view.getScaleX();
                    }

                    @Override
                    public void setValue(View view, float value) {
                        // Apply the same value to two properties
                        view.setScaleX(value);
                        view.setScaleY(value);
                    }
                };
        /*final FlingAnimation fling = new FlingAnimation(c1,DynamicAnimation.Y);
        fling.setStartVelocity(500f);
        fling.setFriction(0.5f);
        final SpringAnimation stretchAnimation = new SpringAnimation(findViewById(R.id.h1), DynamicAnimation.SCALE_Y, SpringForce.STIFFNESS_LOW);
        */

        View bKtl = findViewById(R.id.balKtl);
        View jKtl = findViewById(R.id.jbKtl);

        final SpringAnimation ktlXAnim = createSpringAnimation(bKtl, DynamicAnimation.X, SpringForce.STIFFNESS_HIGH, SpringForce.DAMPING_RATIO_LOW_BOUNCY);
        final SpringAnimation ktlYAnim = createSpringAnimation(bKtl, DynamicAnimation.Y, SpringForce.STIFFNESS_HIGH, SpringForce.DAMPING_RATIO_LOW_BOUNCY);

        final SpringAnimation jktlXAnim = createSpringAnimation(jKtl, DynamicAnimation.X, SpringForce.STIFFNESS_HIGH, SpringForce.DAMPING_RATIO_LOW_BOUNCY);
        final SpringAnimation jktlYAnim = createSpringAnimation(jKtl, DynamicAnimation.Y, SpringForce.STIFFNESS_HIGH, SpringForce.DAMPING_RATIO_LOW_BOUNCY);

        final SpringAnimation firstXAnim = createSpringAnimation(c2, DynamicAnimation.X, SpringForce.STIFFNESS_HIGH, SpringForce.DAMPING_RATIO_LOW_BOUNCY);
        final SpringAnimation firstYAnim = createSpringAnimation(c2, DynamicAnimation.Y, SpringForce.STIFFNESS_HIGH, SpringForce.DAMPING_RATIO_LOW_BOUNCY);

        final SpringAnimation secondXAnim = createSpringAnimation(c3, DynamicAnimation.X, SpringForce.STIFFNESS_HIGH, SpringForce.DAMPING_RATIO_LOW_BOUNCY);
        final SpringAnimation secondYAnim = createSpringAnimation(c3, DynamicAnimation.Y, SpringForce.STIFFNESS_HIGH, SpringForce.DAMPING_RATIO_LOW_BOUNCY);
        final SpringAnimation thirdXAnim = createSpringAnimation(c4, DynamicAnimation.X, SpringForce.STIFFNESS_HIGH, SpringForce.DAMPING_RATIO_LOW_BOUNCY);
        final SpringAnimation thirdYAnim = createSpringAnimation(c4, DynamicAnimation.Y, SpringForce.STIFFNESS_HIGH, SpringForce.DAMPING_RATIO_LOW_BOUNCY);

        final ViewGroup.MarginLayoutParams fab5Params = (ViewGroup.MarginLayoutParams) c2.getLayoutParams();
        final ViewGroup.MarginLayoutParams fab6Params = (ViewGroup.MarginLayoutParams) c3.getLayoutParams();
        final ViewGroup.MarginLayoutParams fab7Params = (ViewGroup.MarginLayoutParams) c4.getLayoutParams();
        final ViewGroup.MarginLayoutParams bParams = (ViewGroup.MarginLayoutParams) bKtl.getLayoutParams();
        final ViewGroup.MarginLayoutParams jParams = (ViewGroup.MarginLayoutParams) jKtl.getLayoutParams();

        firstXAnim.addUpdateListener((animation, v, vl) -> {
            secondXAnim.animateToFinalPosition(v + ((c2.getWidth() -
                    c3.getWidth()) / 2));
            ktlXAnim.animateToFinalPosition(v + ((
                    bKtl.getWidth()) / 2)+bParams.leftMargin);
            jktlXAnim.animateToFinalPosition(v + (c2.getWidth() -
                   jParams.rightMargin-jKtl.getWidth()));

        });

        firstYAnim.addUpdateListener((animation, v, vl) -> {
            secondYAnim.animateToFinalPosition(v + c2.getHeight() +
                    fab6Params.topMargin);
            ktlYAnim.animateToFinalPosition(v + c2.getHeight() -
                    bParams.topMargin/2);
            jktlYAnim.animateToFinalPosition(v + c2.getHeight() -
                    jParams.topMargin/2);
        });

        secondYAnim.addUpdateListener((animation, value, velocity) -> thirdYAnim.animateToFinalPosition(value + c2.getHeight() +
                fab7Params.topMargin));

        secondXAnim.addUpdateListener((animation, value, velocity) -> thirdXAnim.animateToFinalPosition(value + ((c2.getWidth() -
                c3.getWidth()) / 2)));



        c1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        dX = view.getX() - motionEvent.getRawX();
                        dY = view.getY() - motionEvent.getRawY();


                        break;
                    case MotionEvent.ACTION_MOVE:

                        float newX = motionEvent.getRawX() + dX;
                        float newY = motionEvent.getRawY() + dY;

                        view.animate().x(newX).y(newY).setDuration(0).start();

                        firstXAnim.animateToFinalPosition(newX + ((c1.getWidth() -
                                c2.getWidth()) / 2));
                        firstYAnim.animateToFinalPosition(newY + c1.getHeight() +
                                fab5Params.topMargin);






                        break;

                    case MotionEvent.ACTION_UP:
                        view.animate().translationX(0).translationY(0).setDuration(100).start();
                        float x=view.getX()-view.getTranslationX();
                        float y=view.getY()-view.getTranslationY();


                        firstXAnim.animateToFinalPosition(x + ((c1.getWidth() -
                                c2.getWidth()) / 2));
                        firstYAnim.animateToFinalPosition(y + c1.getHeight() +
                                fab5Params.topMargin);




                       break;
                }
                return true;
            }
        });

    }

    public void kcMenu(View view) {
        switch (view.getId()){
            case R.id.stashCanvas:

                break;
            case R.id.stashCanvas2:

                break;
            case R.id.stashCanvas3:

                break;
        }
    }

    public void nyissCsupi(View view) {
        ((CsuporCanvas) view).setNyitva();
        if(!((CsuporCanvas) view).isEmpty())
        ((CsuporCanvas) view).getTermény().burpJar();
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:

                //((CsuporCanvas) v).setColorFilter(Color.YELLOW);

                    if (((CsuporCanvas) v).isEmpty())
                        ((CsuporCanvas) v).hilite();

                return true;

            case DragEvent.ACTION_DRAG_ENTERED:



                //if(event.getClipDescription().getLabel().toString().equals("Give some water")

                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:

                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                v.invalidate();
                return true;

            case DragEvent.ACTION_DROP:

                ((CsuporCanvas) v).hilite();
                String clipData = event.getClipDescription().getLabel().toString();
                switch (clipData) {
                    case "kc1":
                            if (!kicsiCanvas.isEmpty() && ((CsuporCanvas) v).isEmpty()) {
                                ((CsuporCanvas) v).fillUp(kicsiCanvas.getTermény());
                                dryToCure(kicsiCanvas.getTermény().getSorszám());
                                kicsiCanvas.setEmpty();
                            }

                  break;

                    case "kc2":

                            if (!kc2.isEmpty() && ((CsuporCanvas) v).isEmpty()) {
                                ((CsuporCanvas) v).fillUp(kc2.getTermény());
                                dryToCure(kc2.getTermény().getSorszám());
                                kc2.setEmpty();
                            }

                        break;

                    case "kc3":

                            if (!kc3.isEmpty() && ((CsuporCanvas) v).isEmpty()) {
                                ((CsuporCanvas) v).fillUp(kc3.getTermény());
                                dryToCure(kc3.getTermény().getSorszám());
                                kc3.setEmpty();
                            }

                    break;
                    case "pakk":
                        if(!((CsuporCanvas)v).isEmpty()){

                            cureToStash(v);
                        }



                    //Mentés.getInstance().put(Mentés.Key.valueOf(Main2Activity.sss[Kender.getInstance().getFajta()-1]),i);
                }

                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                if(((CsuporCanvas) v).isEmpty())
                    ((CsuporCanvas) v).hilite();

                return true;

            default:
                return false;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        View.DragShadowBuilder mShadow = new View.DragShadowBuilder(view);
        ClipData.Item item = new ClipData.Item(view.getTag().toString());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
        switch (view.getId()) {
            case R.id.stashCanvas:
                if(!((KicsiCanvas)view).isEmpty())
                    view.startDragAndDrop(data, mShadow, null, 0);


                break;

            case R.id.stashCanvas2:

                if(!((KicsiCanvas)view).isEmpty())
                    view.startDragAndDrop(data, mShadow, null, 0);

                break;

            case R.id.stashCanvas3:
                if(!((KicsiCanvas)view).isEmpty())
                    view.startDragAndDrop(data, mShadow, null, 0);

                break;

            case R.id.pakk:
                view.startDragAndDrop(data,mShadow,null,0);
                break;
        }

        return false;
    }
    //012
    private void dryToCure(String sorszám){
        Gson gson = new GsonBuilder().create();
        Type tList = new TypeToken<ArrayList<Termény>>(){}.getType();
        List<Termény> termenyList = gson.fromJson(Mentés.getInstance().getString(Mentés.Key.TRMS_LST),tList);
        List<Termény> erlltList = gson.fromJson(Mentés.getInstance().getString(Mentés.Key.ERllT_LST),tList);
        Toast.makeText(this,sorszám,Toast.LENGTH_SHORT).show();
        for(Termény item:termenyList) {

            System.out.println(item.getSorszám());
            if (sorszám.equals(item.getSorszám())) {
                Toast.makeText(this,"added",Toast.LENGTH_SHORT).show();
                item.setCuring(true);
                erlltList.add(item);

                String ment2 = Mentés.getInstance().gsonra(erlltList);
                Mentés.getInstance().put(Mentés.Key.ERllT_LST,ment2);
                break;
            }
        }

        termenyList.removeIf(i -> i.getSorszám().equals(sorszám));
        String ment = Mentés.getInstance().gsonra(termenyList);
        Mentés.getInstance().put(Mentés.Key.TRMS_LST,ment);


    }

    private void cureToStash(View v){
        Gson gson = new GsonBuilder().create();
        Type tList = new TypeToken<ArrayList<Termény>>(){}.getType();
        Type vList = new TypeToken<ArrayList<Stash>>(){}.getType();
        List<Termény> erlltList = gson.fromJson(Mentés.getInstance().getString(Mentés.Key.ERllT_LST),tList);
        List<Stash> vgtrmkList = gson.fromJson(Mentés.getInstance().getString(Mentés.Key.VGTRMK_LST),vList);

        Termény t=((CsuporCanvas)v).getTermény();
        vgtrmkList.add(new Stash(t.getSuly(),t.getThc(),
                t.getCbd(),t.getNapok(),t.getFajtaString()));

        erlltList.removeIf(i -> i.getSorszám().equals(t.getSorszám()));
        ((CsuporCanvas)v).empty();
        String ment = Mentés.getInstance().gsonra(erlltList);
        Mentés.getInstance().put(Mentés.Key.ERllT_LST,ment);
        String ment2 = Mentés.getInstance().gsonra(vgtrmkList);
        Mentés.getInstance().put(Mentés.Key.VGTRMK_LST,ment2);

        ((TextView)dialog.findViewById(R.id.text)).setText("Stashed: \n"+t.getSuly()+"\n"+t.getThc()+"\n"+t.getNapok());
        dialog.show();

    }


    public void humidify(View v){
        if(humidity<0.7f)
        humidity+=0.03f;

                new ParticleSystem(StashActivity.this, 10, R.drawable.smokepart, 600)
                        .setFadeOut(500)
                        .setRotationSpeed(15)
                        .setSpeedModuleAndAngleRange(0.09f,0.1f,225,320)
                        .oneShot(v,50);

            }

    Handler handler = new Handler(Looper.myLooper());
    Runnable oo = new Runnable() {
        @Override
        public void run() {

            paramerő.setText(Integer.toString((int) (humidity*100)));
            cpb.setProgress(humidity*100);
            if(humidity*100>40&&humidity*100<=60) cpb.setForegroundStrokeColor(Color.GREEN);
            else if(humidity*100>60) cpb.setForegroundStrokeColor(Color.RED);
            else if(humidity*100<40) cpb.setForegroundStrokeColor(Color.YELLOW);
            if(ventilBE&&humidity>0.28f)humidity-=0.01f;

            if(!csupi.isEmpty())csupi.getTermény().setVapor(humidity);
            if(!cs1.isEmpty())cs1.getTermény().setVapor(humidity);
            if(!cs2.isEmpty())cs2.getTermény().setVapor(humidity);
            if(!cs3.isEmpty())cs3.getTermény().setVapor(humidity);


            handler.postDelayed(this,10000);

        }
    };


    Runnable aa = new Runnable() {
        @Override
        public void run() {
            if(findViewById(R.id.ll2tv).getVisibility()==View.VISIBLE) {

                if (csupi.isEmpty()) ll2tv.setText("empty");

                else ll2tv.setText(csupi.getTermény().getFajtaString()+"\n" +
                        csupi.getTermény().getNapok()+"\n" +
                        csupi.getTermény().getThc()+"\n" +
                        csupi.getTermény().getSuly());


                if (cs1.isEmpty()) ll3tv.setText("empty");

                else ll3tv.setText(cs1.getTermény().getFajtaString()+"\n" +
                        cs1.getTermény().getNapok()+"\n" +
                        cs1.getTermény().getThc()+"\n" +
                        cs1.getTermény().getSuly());


                if (cs2.isEmpty()) ll4tv.setText("empty");

                else ll4tv.setText(cs2.getTermény().getFajtaString()+"\n" +
                        cs2.getTermény().getNapok()+"\n" +
                        cs2.getTermény().getThc()+"\n" +
                        cs2.getTermény().getSuly());


                if (cs3.isEmpty()) ll5tv.setText("empty");

                else ll5tv.setText(cs3.getTermény().getFajtaString()+"\n" +
                        cs3.getTermény().getNapok()+"\n" +
                        cs3.getTermény().getThc()+"\n" +
                        cs3.getTermény().getSuly());
            }







            if (csupi.isEmpty()) cstv.setVisibility(View.GONE);

            else {
                cstv.setVisibility(View.VISIBLE);
                cstv.setText(csupi.getTermény().getFajtaString());
            }

            if (cs1.isEmpty()) cstv1.setVisibility(View.GONE);

            else {
                cstv1.setVisibility(View.VISIBLE);
                cstv1.setText(cs1.getTermény().getFajtaString());
            }

            if (cs2.isEmpty()) cstv2.setVisibility(View.GONE);

            else {
                cstv2.setVisibility(View.VISIBLE);
                cstv2.setText(cs2.getTermény().getFajtaString());
            }

            if (cs3.isEmpty()) cstv3.setVisibility(View.GONE);

            else {
                cstv3.setVisibility(View.VISIBLE);
                cstv3.setText(cs3.getTermény().getFajtaString());
            }



            handler.postDelayed(aa,500);
            }

    };


    public void ventil(View view) {
        if (!ventilBE) {
            ventilObj.indit();
            ventilBE = true;

        } else {

            ventilObj.stop();
            ventilBE = false;
        }
    }

    public void csuporMenu(View view) {

        if(findViewById(R.id.ll2).getVisibility()==View.GONE) {


            int cx = csupi.getRight();
            int cy = csupi.getBottom();
            int finalRadius = (int) Math.hypot(ll.getWidth(),
                    ll.getHeight());
            Animator a = ViewAnimationUtils.createCircularReveal(findViewById(R.id.ll2), cx,
                    cy, 0, finalRadius);
            a.setDuration(500);
            a.setInterpolator(new DecelerateInterpolator());
            findViewById(R.id.ll2).setVisibility(View.VISIBLE);
            a.start();
        }else
            findViewById(R.id.ll2).setVisibility(View.GONE);

    }



}
