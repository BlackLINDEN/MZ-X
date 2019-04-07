package blacklinden.com.cannabisgrowthsimulator.ui.medium;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.pojo.Nutri;

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

    public void setDetails(Fertilizer fertil) {
        imageView.setImageDrawable(fertil.getDrawable());
        txtName.setText(fertil.getName());
        txtDistance.setText(String.format(Locale.US, "Natrium : %d", fertil.getN()));
        txtGravity.setText(String.format(Locale.US, "Phosphorus : %d", fertil.getP()));
        txtDiameter.setText(String.format(Locale.US, "Potassium : %d", fertil.getK()));
        final Fertilizer elem = fertil;
        itemView.setOnClickListener(view -> {

            String s = Mentés.getInstance().gsonra(new Nutri(elem.getName() ,elem.getDrawCode(),elem.getN(),elem.getP(),elem.getK()));

            Mentés.getInstance().put(Mentés.Key.SAMPLE_NUT,s);

            Toast.makeText(itemView.getContext(),"Selected: "+elem.getName(),Toast.LENGTH_SHORT).show();
        });
    }
}