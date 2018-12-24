package blacklinden.com.cannabisgrowthsimulator.ui;

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

import blacklinden.com.cannabisgrowthsimulator.R;

public class CardPagerAdapter2 extends PagerAdapter implements CardAdapter {

        private List<CardView> mViews;
        private List<CardItem2> mData;
        private float mBaseElevation;

        public CardPagerAdapter2() {
            mData = new ArrayList<>();
            mViews = new ArrayList<>();
        }

        public void addCardItem(CardItem2 item) {
            mViews.add(null);
            mData.add(item);
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
                    .inflate(R.layout.adapter2, container, false);
            container.addView(view);
            bind(mData.get(position), view);
            CardView cardView = view.findViewById(R.id.cardView);

            if (mBaseElevation == 0) {
                mBaseElevation = cardView.getCardElevation();
            }

            cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
            mViews.set(position, cardView);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
            mViews.set(position, null);
        }

        private void bind(CardItem2 item, View view) {
            TextView titleTextView =  view.findViewById(R.id.titleTextView);

            TextView type = view.findViewById(R.id.typeTV);
            ImageView imageView = view.findViewById(R.id.titleImage);
            titleTextView.setText(item.getTitleRes());
            type.setText(item.getType());
            imageView.setImageResource(item.getPic());
        }



    }
