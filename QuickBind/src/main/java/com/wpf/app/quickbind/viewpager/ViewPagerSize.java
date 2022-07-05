package com.wpf.app.quickbind.viewpager;

import android.support.v4.view.PagerAdapter;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
public interface ViewPagerSize {

    int getPageSize();

    void setPageSize(int size);

    PagerAdapter getAdapter();

    default void notifyPagerSize(ViewPagerSize viewPager, int size) {
        if (viewPager == null) return;
        PagerAdapter fragmentsAdapter = viewPager.getAdapter();
        if (fragmentsAdapter == null) return;
        setPageSize(size);
        fragmentsAdapter.notifyDataSetChanged();
    }
}
