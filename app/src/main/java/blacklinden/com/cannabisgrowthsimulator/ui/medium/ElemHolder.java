package blacklinden.com.cannabisgrowthsimulator.ui.medium;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Locale;
import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.pojo.Lamps;

public class ElemHolder extends RecyclerView.ViewHolder {
    private TextView txtName, txtDistance, txtGravity, txtDiameter;
    private ImageView imageView;
    private ItemClickListener listener;

    public ElemHolder(View itemView, ItemClickListener listener) {
        super(itemView);
        this.listener=listener;
        imageView = itemView.findViewById(R.id.cardImage);
        txtName = itemView.findViewById(R.id.txtName);
        txtDistance = itemView.findViewById(R.id.txtDistance);
        txtGravity = itemView.findViewById(R.id.txtGravity);
        txtDiameter = itemView.findViewById(R.id.txtDiameter);

    }

    void setDetails(Elem planet) {
        imageView.setImageDrawable(planet.getDrawable());
        txtName.setText(planet.getName());
        txtDistance.setText(String.format(Locale.US, "Consumption : %d W/h", planet.getConsumption()));
        txtGravity.setText(String.format(Locale.US, "Light Spectrum : %d Kelvin", planet.getSpectrum()));
        txtDiameter.setText(String.format(Locale.US, "Intensity : %d Lumens", planet.getLumen()));
        final Elem elem = planet;
        itemView.setOnClickListener(view -> {
            Lamps lamps = new Lamps(elem.getName(),elem.getType(),elem.getConsumption(),elem.getSpectrum(),
                    elem.getLumen(),elem.getAnimDrawCode(),elem.getFatyolDrawCode());

            String s = Mentés.getInstance().gsonra(lamps);

            Mentés.getInstance().put(Mentés.Key.TESZT_OBJ,s);
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