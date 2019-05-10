package blacklinden.com.cannabisgrowthsimulator.ui.medium;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;

class SkinHolder extends RecyclerView.ViewHolder {
    private TextView txtName;
    private ImageView mid;
    private ItemClickListener listener;

    SkinHolder(View itemView, ItemClickListener listener) {
        super(itemView);
        txtName = itemView.findViewById(R.id.txtName);
        this.listener = listener;
        mid = itemView.findViewById(R.id.mid);
    }

    void setDetails(Skin skin) {

        mid.setImageResource(skin.getMiddleCode());
    switch(skin.getName()){
        case "a":
            txtName.setText("Marleys");
            break;
        case "b":
            txtName.setText("Goa");
            break;
        case "c":
            txtName.setText("Hermit");
            break;
        case "d":
            txtName.setText("HiTech");
            break;
    }


        final Skin skn = skin;
        itemView.setOnClickListener(view -> {

            Mentés.getInstance().put(Mentés.Key.SKN,skn.getName());

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