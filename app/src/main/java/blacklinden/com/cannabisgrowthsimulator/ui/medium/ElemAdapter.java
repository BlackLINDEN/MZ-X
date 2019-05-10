package blacklinden.com.cannabisgrowthsimulator.ui.medium;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import blacklinden.com.cannabisgrowthsimulator.R;


public class ElemAdapter extends RecyclerView.Adapter<ElemHolder>{

    private Context context;
    private ArrayList<Elem> elemek;
    private ItemClickListener listener;

    public ElemAdapter(Context context, ArrayList<Elem> elemek, ItemClickListener listener) {
        this.context = context;
        this.elemek = elemek;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ElemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row,parent, false);
        return new ElemHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ElemHolder holder, int position) {
        Elem planet = elemek.get(position);
        holder.setDetails(planet);
    }

    @Override
    public int getItemCount() {
        return elemek.size();
    }
}
