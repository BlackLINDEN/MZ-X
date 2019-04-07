package blacklinden.com.cannabisgrowthsimulator.shop;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import blacklinden.com.cannabisgrowthsimulator.R;

class ShopItemHolder extends RecyclerView.ViewHolder {

    private ImageView item_iv;
    private TextView item_tv;
    private Button item_buy;

    ShopItemHolder(View itemView) {
        super(itemView);

        item_iv = itemView.findViewById(R.id.shopitem_iv);
        item_tv = itemView.findViewById(R.id.shopitem_tv);
        item_buy = itemView.findViewById(R.id.shopitem_buy);



    }

    @SuppressLint("SetTextI18n")
    void setDetails(ShopItem item){
        item_iv.setImageResource(item.getImageCode());
        item_tv.setText(item.getName());
        item_buy.setText(Integer.toString(item.getPrice()));
        item_buy.setOnClickListener(view -> {

        });
    }
}
