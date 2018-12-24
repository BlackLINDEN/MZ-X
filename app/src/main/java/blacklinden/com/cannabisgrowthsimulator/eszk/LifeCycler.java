package blacklinden.com.cannabisgrowthsimulator.eszk;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Objects;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;

public class LifeCycler extends LinearLayout {


    public LifeCycler(Context context) {
        super(context);
        init(context);
    }

    public LifeCycler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LifeCycler(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){

        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Objects.requireNonNull(inflater).inflate(R.layout.lifecycle_view, this);

    }



}
