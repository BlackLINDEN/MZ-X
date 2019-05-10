package blacklinden.com.cannabisgrowthsimulator.ui.medium;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import blacklinden.com.cannabisgrowthsimulator.R;

public class FertilAdapter extends RecyclerView.Adapter<FertilHolder>{

    private Context context;
    private ArrayList<Fertilizer> elemek;
    private ItemClickListener listener;

    public FertilAdapter(Context context, ArrayList<Fertilizer> elemek, ItemClickListener listener) {
        this.context = context;
        this.elemek = elemek;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FertilHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nut_row,parent, false);
        return new FertilHolder(view, listener);
    }


    @Override
    public void onBindViewHolder(@NonNull FertilHolder holder, int position) {
        Fertilizer fertil = elemek.get(position);
        holder.setDetails(fertil);
    }

    @Override
    public int getItemCount() {
        return elemek.size();
    }
}
