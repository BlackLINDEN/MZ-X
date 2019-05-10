package blacklinden.com.cannabisgrowthsimulator.ui.medium;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import blacklinden.com.cannabisgrowthsimulator.R;

public class SkinAdapter extends RecyclerView.Adapter<SkinHolder>{

    private Context context;
    private ArrayList<Skin> skins;
    private ItemClickListener listener;

    public SkinAdapter(Context context, ArrayList<Skin> skins, ItemClickListener listener) {
        this.context = context;
        this.skins = skins;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SkinHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.skin_row,parent, false);
        return new SkinHolder(view, listener);
    }


    @Override
    public void onBindViewHolder(@NonNull SkinHolder holder, int position) {
        Skin skin = skins.get(position);
        holder.setDetails(skin);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return skins.size();
    }
}
