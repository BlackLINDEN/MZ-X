package blacklinden.com.cannabisgrowthsimulator.ui.medium;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import blacklinden.com.cannabisgrowthsimulator.R;

public class PotAdapter extends RecyclerView.Adapter<PotHolder>{

    private Context context;
    private ArrayList<Elem> elemek;
    public PotAdapter(Context context, ArrayList<Elem> elemek) {
        this.context = context;
        this.elemek = elemek;
    }

    @NonNull
    @Override
    public PotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row,parent, false);
        return new PotHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PotHolder holder, int position) {
        Elem planet = elemek.get(position);
        holder.setDetails(planet);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return elemek.size();
    }
}
