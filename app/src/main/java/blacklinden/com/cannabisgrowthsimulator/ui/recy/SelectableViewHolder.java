package blacklinden.com.cannabisgrowthsimulator.ui.recy;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;

import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.pojo.SelectableStashItem;

public class SelectableViewHolder  extends RecyclerView.ViewHolder {

        public static final int MULTI_SELECTION = 2;
        public static final int SINGLE_SELECTION = 1;
        CheckedTextView textView;
        SelectableStashItem stashItem;
        OnItemSelectedListener itemSelectedListener;


        public SelectableViewHolder(View view, OnItemSelectedListener listener) {
            super(view);
            itemSelectedListener = listener;
            textView = view.findViewById(R.id.checked_text_item);
            textView.setOnClickListener(view1 -> {


                if (stashItem.isSelected() && getItemViewType() == MULTI_SELECTION) {
                    setChecked(false);
                } else {
                    setChecked(true);
                }
                itemSelectedListener.onItemSelected(stashItem);

            });
        }

        public void setChecked(boolean value) {
            if (value) {
                textView.setBackgroundColor(Color.LTGRAY);
            } else {
                textView.setBackground(null);
            }
            stashItem.setSelected(value);
            textView.setChecked(value);
        }

        public interface OnItemSelectedListener {

            void onItemSelected(SelectableStashItem stash);
        }

    }

