package blacklinden.com.cannabisgrowthsimulator.ui;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import blacklinden.com.cannabisgrowthsimulator.R;


public class EszkAdapter extends RecyclerView.Adapter<EszkAdapter.MyViewHolder> {

    private List<Eszk> eszkList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, prop, type;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            type = view.findViewById(R.id.type);
            prop = view.findViewById(R.id.prop);
        }
    }


    public EszkAdapter(List<Eszk> eszkList) {
        this.eszkList = eszkList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.eszk_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Eszk eszk = eszkList.get(position);
        holder.name.setText(eszk.getTitle());
        holder.type.setText(eszk.getType());
        holder.prop.setText(eszk.getProp());
    }

    @Override
    public int getItemCount() {
        return eszkList.size();
    }
}
