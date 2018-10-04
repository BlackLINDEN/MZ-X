package blacklinden.com.cannabisgrowthsimulator.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments;

    public FragAdapter(FragmentManager fm) {
        super(fm);

        fragments = new Fragment[]{
                new Lights(),
                new Nutrition(),
                new Accessories()
        };
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position){
        String cím  = getItem(position).getClass().getName();
        return cím.subSequence(cím.lastIndexOf(".")+1,cím.length());
    }
}
