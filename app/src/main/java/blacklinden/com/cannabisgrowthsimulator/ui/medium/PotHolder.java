package blacklinden.com.cannabisgrowthsimulator.ui.medium;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.pojo.Acc;

public class PotHolder extends RecyclerView.ViewHolder {
    private TextView txtName, txtDistance, txtGravity, txtDiameter;
    private ImageView imageView;
    private ItemClickListener listener;

    public PotHolder(View itemView, ItemClickListener listener) {
        super(itemView);
        imageView = itemView.findViewById(R.id.cardImage);
        txtName = itemView.findViewById(R.id.txtName);
        txtDistance = itemView.findViewById(R.id.txtDistance);
        txtGravity = itemView.findViewById(R.id.txtGravity);
        txtDiameter = itemView.findViewById(R.id.txtDiameter);
        this.listener = listener;
    }

    void setDetails(Elem planet) {
        imageView.setImageDrawable(planet.getDrawable());
        switch(planet.getName()) {
            case "a":
                txtName.setText("Black");
                break;
            case "b":
                txtName.setText("Brown");
                break;
            case "c":
                txtName.setText("Cann");
                break;
            case "d":
                txtName.setText("oderKan");
                break;
            default: txtName.setText("smthElse");
        }
            txtDistance.setText(String.format(Locale.US, "Volume : %s L", planet.getVolume()));
        txtGravity.setText(String.format(Locale.US, "Drain : %s", planet.getDrain()));
        txtDiameter.setText(String.format(Locale.US, "Class: %s", planet.getRarity()));
        final Elem elem = planet;
        itemView.setOnClickListener(view -> {
            if(elem.getVolume()>600) {
                String s = Mentés.getInstance().gsonra(new Acc(elem.getName() ,elem.getVolume(),elem.getDrain(),elem.getRarity(),elem.getAnimDrawCode()));
                Mentés.getInstance().put(Mentés.Key.SAMPLE_POT, s);
            }else {
                String s = Mentés.getInstance().gsonra(new Acc(elem.getName() ,elem.getVolume(),elem.getDrain(),elem.getRarity(),elem.getAnimDrawCode()));
                Mentés.getInstance().put(Mentés.Key.SAMPLE_CAN, s);
            }


            view.animate()
                    .translationX(100)
                    .setDuration(200)
                    .setInterpolator(new LinearInterpolator())
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            view.animate()
                                    .translationX(0)
                                    .setDuration(200)
                                    .setInterpolator(new LinearInterpolator())
                                    .start();

                        }
                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    }).start();

            listener.onItemClick();

        });
    }
}