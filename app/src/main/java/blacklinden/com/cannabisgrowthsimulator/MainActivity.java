package blacklinden.com.cannabisgrowthsimulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import blacklinden.com.cannabisgrowthsimulator.canvas.CanvasView;
import blacklinden.com.cannabisgrowthsimulator.nov.Kender;

public class MainActivity extends AppCompatActivity {

    CanvasView canvasView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        canvasView = findViewById(R.id.canvas);

    }

            public void clearCanvas(View v) {
                canvasView.oo.run();
            }
            public void cl(View v) {
                canvasView.nulláz();
            }

            public void locsol(View v){
                Kender.setH2o();
            }



        }


