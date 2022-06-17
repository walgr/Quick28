package com.wpf.app.quick.base.widgets.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
public class QuickViewPager extends ViewPager implements ViewPagerSize {

    int pageSize = 0;

    public QuickViewPager(@NonNull Context context) {
        super(context);
    }

    public QuickViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    public void notifyPagerSize(int size) {
        this.pageSize = size;
        PagerAdapter fragmentsAdapter = getAdapter();
        if (fragmentsAdapter == null) return;
        fragmentsAdapter.notifyDataSetChanged();
    }
}
