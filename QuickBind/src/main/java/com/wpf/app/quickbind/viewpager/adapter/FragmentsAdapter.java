package com.wpf.app.quickbind.viewpager.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wpf.app.quickbind.interfaces.BindBaseFragment;
import java.util.List;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
public class FragmentsAdapter extends FragmentPagerAdapter {

    private final List<? extends BindBaseFragment> mFragments;

    public FragmentsAdapter(FragmentManager fm, List<? extends BindBaseFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (mFragments != null && mFragments.get(position) != null) {
            return mFragments.get(position).getTitle();
        }
        return null;
    }
}
