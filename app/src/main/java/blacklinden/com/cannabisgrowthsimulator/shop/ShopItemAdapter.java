package blacklinden.com.cannabisgrowthsimulator.shop;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import blacklinden.com.cannabisgrowthsimulator.databinding.ShopItemBinding;


public class ShopItemAdapter extends RecyclerView.Adapter
        <ShopItemAdapter
                .ShopItemHolder> {


    private List<ShopItem> shopItems;
    private final OnItemClickListener listener;
    private static int coins;


    ShopItemAdapter(List<ShopItem> shopItems, OnItemClickListener listener) {

        this.shopItems = shopItems;
        this.listener=listener;
    }


    @NonNull
    @Override
    public ShopItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ShopItemBinding binding = ShopItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ShopItemHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ShopItemHolder holder, int position) {
        ShopItem shopItem = shopItems.get(position);
        holder.bind(shopItem,listener);
    }

    @Override
    public int getItemCount() {
        return shopItems.size();
    }

    void setShopItems(List<ShopItem> shopItems) {
        this.shopItems = shopItems;
        notifyDataSetChanged();
    }

    void setScore(int coins){
        ShopItemAdapter.coins =coins;
        notifyDataSetChanged();
    }

    void setEachItem(ShopItem item){
        shopItems.add(item);
    }

    static class ShopItemHolder extends RecyclerView.ViewHolder {

        ShopItemBinding shopItemBinding;

        ShopItemHolder(ShopItemBinding shopItemBinding) {
            super(shopItemBinding.getRoot());
            this.shopItemBinding = shopItemBinding;
        }

        void bind(ShopItem shopItem, final OnItemClickListener listener) {
            shopItemBinding.shopitemTv.setText(shopItem.getName());
            shopItemBinding.shopitemIv.setImageResource(shopItem.getImageCode());
            if(coins<shopItem.getPrice()) shopItemBinding.shopitemBuy.setTextColor(Color.RED);
            else shopItemBinding.shopitemBuy.setTextColor(Color.GREEN);

            if(!shopItem.getLocked()) {
                shopItemBinding.shopitemBuy.setBackgroundColor(Color.WHITE);
                shopItemBinding.shopitemBuy.setText(Integer.toString(shopItem.getPrice()));
                shopItemBinding.shopitemBuy.setOnClickListener(view -> {
                    if (coins >= shopItem.getPrice())
                        listener.onItemClick(new ShopItem(shopItem.getName(), coins - shopItem.getPrice(), shopItem.getImageCode()));

                });
            }
            else{
                shopItemBinding.shopitemBuy.setBackgroundColor(Color.DKGRAY);
                shopItemBinding.shopitemBuy.setText("Locked");
            }

        }


    }

    interface OnItemClickListener{
        void onItemClick(ShopItem item);
    }
}