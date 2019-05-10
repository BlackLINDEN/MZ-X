package blacklinden.com.cannabisgrowthsimulator.ui.recy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.pojo.SelectableStashItem;
import blacklinden.com.cannabisgrowthsimulator.pojo.Stash;
import blacklinden.com.cannabisgrowthsimulator.pojo.Vegtermek;

public class SelectableAdapter extends RecyclerView.Adapter implements SelectableViewHolder.OnItemSelectedListener {

private List<SelectableStashItem> mValues;

private boolean isMultiSelectionEnabled;
private SelectableViewHolder.OnItemSelectedListener listener;


public SelectableAdapter(SelectableViewHolder.OnItemSelectedListener listener, boolean isMultiSelectionEnabled) {
        this.listener = listener;
        this.isMultiSelectionEnabled = isMultiSelectionEnabled;

        }

 public void setLiveValues(List<Vegtermek> vegtermekek){
    List<SelectableStashItem> tempVal = new ArrayList<>();
        for(Vegtermek v:vegtermekek){
                tempVal.add(new SelectableStashItem(new Stash(v.id,v.getFajta(),v.getMennyi()),false));
        }
        mValues=tempVal;
        notifyDataSetChanged();
 }

@NonNull
@Override
public SelectableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.checked_item, parent, false);

        return new SelectableViewHolder(itemView, this);
        }

@Override
public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        SelectableViewHolder holder = (SelectableViewHolder) viewHolder;
        SelectableStashItem selectableItem = mValues.get(position);
        String name = selectableItem.getFajta();
        holder.textView.setText(name);
        if (isMultiSelectionEnabled) {
        TypedValue value = new TypedValue();
        holder.textView.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorMultiple, value, true);
        int checkMarkDrawableResId = value.resourceId;
        holder.textView.setCheckMarkDrawable(checkMarkDrawableResId);
        } else {
        TypedValue value = new TypedValue();
        holder.textView.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorSingle, value, true);
        int checkMarkDrawableResId = value.resourceId;
        holder.textView.setCheckMarkDrawable(checkMarkDrawableResId);
        }

        holder.stashItem = selectableItem;
        holder.setChecked(holder.stashItem.isSelected());
        }

@Override
public int getItemCount() {
        if(mValues!=null)return mValues.size();
        else return 0;
        }

public List<Stash> getSelectedItems() {

        List<Stash> selectedItems = new ArrayList<>();
        for (SelectableStashItem item : mValues) {
        if (item.isSelected()) {
        selectedItems.add(item);
        }
        }
        return selectedItems;
        }

public void resetSelectedItems(){
        for(SelectableStashItem item:mValues) {
         if(item.isSelected())
            item.setSelected(false);
        }
        notifyDataSetChanged();
}

@Override
public int getItemViewType(int position) {
        if(isMultiSelectionEnabled){
        return SelectableViewHolder.MULTI_SELECTION;
        }
        else{
        return SelectableViewHolder.SINGLE_SELECTION;
        }
        }

@Override
public void onItemSelected(SelectableStashItem item) {
        if (!isMultiSelectionEnabled) {

        for (SelectableStashItem selectableItem : mValues) {
        if (!selectableItem.equals(item)
        && selectableItem.isSelected()) {
        selectableItem.setSelected(false);
        } else if (selectableItem.equals(item)
        && item.isSelected()) {
        selectableItem.setSelected(true);
        }
        }
        notifyDataSetChanged();
        }
        listener.onItemSelected(item);
        }


}