package blacklinden.com.cannabisgrowthsimulator.eszk;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import blacklinden.com.cannabisgrowthsimulator.R;

public class FelsFiok extends LinearLayout {

    public FelsFiok(Context context) {
        super(context);
        init(context);
    }

    public FelsFiok(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FelsFiok(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


        private void init(Context context){
            setOrientation(LinearLayout.VERTICAL);
            setBackgroundColor(Color.argb(255,130,82,1));
            setBackgroundResource(R.drawable.graf_felsopolc);
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.fels_fiok, this);
        }

}
