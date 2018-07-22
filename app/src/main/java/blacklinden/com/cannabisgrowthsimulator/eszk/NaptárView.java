package blacklinden.com.cannabisgrowthsimulator.eszk;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import blacklinden.com.cannabisgrowthsimulator.R;

public class NaptárView extends LinearLayout {

    TextView napTV;

    public NaptárView(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
        init(context);
    }

    public NaptárView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        init(context);
    }

    public NaptárView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        napTV = findViewById(R.id.nap);
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.naptar_view, this);

    }

    public void setNap(int nap){
        napTV.setText(String.valueOf(nap));
    }

}
