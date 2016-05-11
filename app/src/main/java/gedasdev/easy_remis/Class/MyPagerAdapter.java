package gedasdev.easy_remis.Class;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import gedasdev.easy_remis.Fragment.FirstFragment;
import gedasdev.easy_remis.Fragment.SecondFragment;
import gedasdev.easy_remis.Fragment.ThirdFragment;

/**
 * Created by Colorado on 29/04/2016.
 */
public class MyPagerAdapter  extends FragmentPagerAdapter {
    private final SparseArray<WeakReference<Fragment>> instantiatedFragments = new SparseArray<>();

    private static final int NUMBER_OF_PAGES = 3;
    /**
     * Constructor
     * @param fm
     */
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final Fragment fragment = (Fragment) super.instantiateItem(container, position);
        instantiatedFragments.put(position, new WeakReference<>(fragment));
        return fragment;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        instantiatedFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    /**
     * Return fragment based on the position.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            case 2:
                return new ThirdFragment();
            default:
                return new FirstFragment();
        }
    }

    @Nullable
    public Fragment getFragment(final int position) {
        final WeakReference<Fragment> wr = instantiatedFragments.get(position);
        if (wr != null) {
            return wr.get();
        } else {
            return null;
        }
    }

    /**
     * Return the number of pages.
     * @return
     */
    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }




}
