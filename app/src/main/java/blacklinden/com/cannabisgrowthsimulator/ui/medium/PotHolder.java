package blacklinden.com.cannabisgrowthsimulator.ui.medium;

import android.support.v7.widget.RecyclerView;
import android.view.View;
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
    public PotHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.cardImage);
        txtName = itemView.findViewById(R.id.txtName);
        txtDistance = itemView.findViewById(R.id.txtDistance);
        txtGravity = itemView.findViewById(R.id.txtGravity);
        txtDiameter = itemView.findViewById(R.id.txtDiameter);

    }

    void setDetails(Elem planet) {
        imageView.setImageDrawable(planet.getDrawable());
        txtName.setText(planet.getName());
        txtDistance.setText(String.format(Locale.US, "Volume : %s L", planet.getVolume()));
        txtGravity.setText(String.format(Locale.US, "Drain : %s", planet.getDrain()));
        txtDiameter.setText(String.format(Locale.US, "Class: %s", planet.getRarity()));
        final Elem elem = planet;
        itemView.setOnClickListener(view -> {
            if(elem.getVolume()>600) {
                String s = Mentés.getInstance().gsonra(new Acc(elem.getName() ,elem.getVolume(),elem.getDrain(),elem.getRarity(),elem.getAnimDrawCode()));
                Mentés.getInstance().put(Mentés.Key.SAMPLE_POT, s);
                Toast.makeText(itemView.getContext(),"Selected: "+elem.getName(),Toast.LENGTH_SHORT).show();
            }else {
                String s = Mentés.getInstance().gsonra(new Acc(elem.getName() ,elem.getVolume(),elem.getDrain(),elem.getRarity(),elem.getAnimDrawCode()));
                Mentés.getInstance().put(Mentés.Key.SAMPLE_CAN, s);
                Toast.makeText(itemView.getContext(),"Selected: "+elem.getName(),Toast.LENGTH_SHORT).show();
            }

        });
    }
}