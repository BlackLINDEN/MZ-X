package blacklinden.com.cannabisgrowthsimulator.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import blacklinden.com.cannabisgrowthsimulator.Main2Activity;
import blacklinden.com.cannabisgrowthsimulator.R;
import blacklinden.com.cannabisgrowthsimulator.eszk.Mentés;
import blacklinden.com.cannabisgrowthsimulator.pojo.MagEntity;
import blacklinden.com.cannabisgrowthsimulator.sql.MagVM;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;

    public CardPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    private void addCardItem(CardItem item) {
        mViews.add(null);
        mData.add(item);
    }

    public void addLiveData(List<MagEntity> magvak){
        mData.clear();
        for(MagEntity mag:magvak) {
            switch (mag.getFajta()){
                case "a":
                    addCardItem(new CardItem(R.string.title_1, mag.getMennyi(),"15-17%","150gr/m2","11 weeks","Auto Hybrid","Feminised",R.drawable.gambi24));
                    break;

                case "b":
                    addCardItem(new CardItem(R.string.title_2, mag.getMennyi(),"17-20%","140gr/m2","14 weeks","Auto Hybrid","Feminised",R.drawable.dpam));
                    break;

                case "c":
                    addCardItem(new CardItem(R.string.shit,mag.getMennyi(),"8-12%","200gr/m2","13 weeks","Auto Crap","Feminised",R.drawable.budbud));
                    break;

                case "d":
                    addCardItem(new CardItem(R.string.blueb,mag.getMennyi(),"12-18%","200gr/m2","13 weeks","Auto Crap","Feminised",R.drawable.budbud));
                    break;

                case "e":
                    addCardItem(new CardItem(R.string.title_northernlight,mag.getMennyi(),"13%","170gr/m2","15 weeks","Auto Indica","Feminised",R.drawable.budbud));
                    break;

                case "f":
                    addCardItem(new CardItem(R.string.grapeape,mag.getMennyi(),"14%","180gr/m2","15 weeks","Auto Indica","Feminised",R.drawable.budbud));
                    break;
            }
        }

    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.adapter, container, false);
        container.addView(view);
        bind(mData.get(position), view,position);
        CardView cardView = view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);

        view.setTag(position);



        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(CardItem item, View view,int position) {
        TextView titleTextView =  view.findViewById(R.id.titleTextView);
        TextView contentTextView =  view.findViewById(R.id.thc);
        TextView yield = view.findViewById(R.id.yield);
        TextView gender = view.findViewById(R.id.gender);
        TextView strainType = view.findViewById(R.id.strainType);
        TextView flower = view.findViewById(R.id.flower);
        ImageView imageView = view.findViewById(R.id.titleImage);
        TextView darab = view.findViewById(R.id.seedsLeft);
        titleTextView.setText(item.getTitle());
        contentTextView.setText(item.getThc());
        yield.setText(item.getYield());
        gender.setText(item.getGender());
        strainType.setText(item.getStrainType());
        flower.setText(item.getFlower());
        imageView.setImageResource(item.getPic());

        darab.setText(Integer.toString(item.getDb()));
        darab.setTag(Integer.toString(item.getDb()));
        //darab.setText(Integer.toString(Mentés.getInstance().getInt(Mentés.Key.valueOf(Main2Activity.sss[position]),0)));

    }



}