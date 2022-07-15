package com.wpf.app.quickbind.plugins;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wpf.app.quickbind.interfaces.BindBaseFragment;
import com.wpf.app.quickbind.interfaces.BindViewModel;
import com.wpf.app.quickbind.annotations.BindFragment;
import com.wpf.app.quickbind.viewpager.ViewPagerSize;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
public class BindFragmentAnnPlugin implements BasePlugin {

    @Override
    public boolean dealField(@NonNull Object obj, @Nullable ViewModel viewModel, @NonNull Field field) {
        try {
            BindFragment bindFragmentAnn = field.getAnnotation(BindFragment.class);
            if (bindFragmentAnn == null) return false;
                field.setAccessible(true);
                Object viewPagerObj = field.get(getRealObj(obj, viewModel));
                if (viewPagerObj instanceof ViewPager) {
                    ViewPager viewPager = (ViewPager) viewPagerObj;
                    if (bindFragmentAnn.limit() > 0) {
                        viewPager.setOffscreenPageLimit(bindFragmentAnn.limit());
                    }
                    FragmentManager fragmentManager = null;
                    if (obj instanceof AppCompatActivity) {
                        fragmentManager = ((AppCompatActivity) obj).getSupportFragmentManager();
                    } else if (obj instanceof Fragment) {
                        fragmentManager = ((Fragment) obj).getChildFragmentManager();
                    }
                    if (fragmentManager == null) return true;
                    if (bindFragmentAnn.withState()) {
                        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
                            private final HashMap<Integer, BindBaseFragment> mBaseFragmentList = new HashMap<>();

                            @Override
                            public void notifyDataSetChanged() {
                                super.notifyDataSetChanged();
                                mBaseFragmentList.clear();
                            }

                            @Override
                            public Fragment getItem(int i) {
                                try {
                                    BindBaseFragment baseFragment = (BindBaseFragment) bindFragmentAnn.fragment().newInstance();
                                    if (viewModel != null) {
                                        baseFragment.setArguments(baseFragment.getInitBundle(
                                                (BindViewModel) obj, i));
                                    } else {
                                        if (obj instanceof Activity) {
                                            baseFragment.setArguments(baseFragment.getInitBundle((Activity) obj, i));
                                        }
                                        if (obj instanceof Fragment) {
                                            baseFragment.setArguments(baseFragment.getInitBundle((Fragment) obj, i));
                                        }
                                    }
                                    mBaseFragmentList.put(i, baseFragment);
                                    return baseFragment;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }

                            @Override
                            public int getCount() {
                                if (viewPager instanceof ViewPagerSize) {
                                    return ((ViewPagerSize) viewPager).getPageSize();
                                }
                                return 0;
                            }

                            @Override
                            public int getItemPosition(@NonNull Object object) {
                                return POSITION_NONE;
                            }

                            @Nullable
                            @Override
                            public CharSequence getPageTitle(int position) {
                                if (mBaseFragmentList.get(position) != null) {
                                    return mBaseFragmentList.get(position).getTitle();
                                }
                                return null;
                            }
                        });
                    } else {
                        viewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
                            private final HashMap<Integer, BindBaseFragment> mBaseFragmentList = new HashMap<>();

                            @Override
                            public void notifyDataSetChanged() {
                                super.notifyDataSetChanged();
                                mBaseFragmentList.clear();
                            }

                            @Override
                            public Fragment getItem(int i) {
                                try {
                                    BindBaseFragment baseFragment = (BindBaseFragment) bindFragmentAnn.fragment().newInstance();
                                    if (viewModel != null) {
                                        baseFragment.setArguments(baseFragment.getInitBundle(
                                                (BindViewModel) obj, i));
                                    } else {
                                        if (obj instanceof Activity) {
                                            baseFragment.setArguments(baseFragment.getInitBundle((Activity) obj, i));
                                        }
                                        if (obj instanceof Fragment) {
                                            baseFragment.setArguments(baseFragment.getInitBundle((Fragment) obj, i));
                                        }
                                    }
                                    mBaseFragmentList.put(i, baseFragment);
                                    return baseFragment;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }

                            @Override
                            public int getCount() {
                                if (viewPager instanceof ViewPagerSize) {
                                    return ((ViewPagerSize) viewPager).getPageSize();
                                }
                                return 0;
                            }

                            @Override
                            public int getItemPosition(@NonNull Object object) {
                                return POSITION_NONE;
                            }

                            @Nullable
                            @Override
                            public CharSequence getPageTitle(int position) {
                                if (mBaseFragmentList.get(position) != null) {
                                    return mBaseFragmentList.get(position).getTitle();
                                }
                                return null;
                            }
                        });
                    }
                }
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
