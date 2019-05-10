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
    private ItemClickListener listener;

    public PotAdapter(Context context, ArrayList<Elem> elemek, ItemClickListener listener) {
        this.context = context;
        this.elemek = elemek;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row,parent, false);
        return new PotHolder(view,listener);
    }


    @Override
    public void onBindViewHolder(@NonNull PotHolder holder, int position) {
        Elem planet = elemek.get(position);
        holder.setDetails(planet);
    }

    @Override
    public int getItemCount() {
        return elemek.size();
    }
}
