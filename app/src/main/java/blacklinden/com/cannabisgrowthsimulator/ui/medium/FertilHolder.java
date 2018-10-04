package blacklinden.com.cannabisgrowthsimulator.ui.medium;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;

public class FertilHolder extends RecyclerView.ViewHolder {
    private TextView txtName, txtDistance, txtGravity, txtDiameter;
    private ImageView imageView;
    public FertilHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.cardImage);
        txtName = itemView.findViewById(R.id.txtName);
        txtDistance = itemView.findViewById(R.id.txtDistance);
        txtGravity = itemView.findViewById(R.id.txtGravity);
        txtDiameter = itemView.findViewById(R.id.txtDiameter);

    }

    public void setDetails(Fertilizer planet) {
        imageView.setImageDrawable(planet.getDrawable());
        txtName.setText(planet.getName());
        txtDistance.setText(String.format(Locale.US, "Natrium : %d", planet.getN()));
        txtGravity.setText(String.format(Locale.US, "Phosphorus : %d", planet.getP()));
        txtDiameter.setText(String.format(Locale.US, "Potassium : %d", planet.getK()));
        final Fertilizer elem = planet;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Mentés.getInstance().put(Mentés.Key.SAMPLE_NUT,elem.getName());

                Toast.makeText(itemView.getContext(),"Selected: "+elem.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}