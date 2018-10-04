package blacklinden.com.cannabisgrowthsimulator.eszk;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;


import blacklinden.com.cannabisgrowthsimulator.R;

public class AlsFiok extends LinearLayout {

    StarchView starchView;

    public AlsFiok(Context context) {
        super(context);
        init(context);
    }

    public AlsFiok(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AlsFiok(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(Color.argb(255,130,82,1));
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.als_fiok, this);
        starchView = findViewById(R.id.starchView);

    }

    public void flushIt(){
        starchView.setPaint();
    }
}
