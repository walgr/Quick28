package com.wpf.app.quick.base.helper.bind.plugins;

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

import com.wpf.app.quick.base.helper.bind.annotations.BindFragment;
import com.wpf.app.quick.base.activity.BaseFragment;
import com.wpf.app.quick.base.activity.ViewModelActivity;
import com.wpf.app.quick.base.widgets.viewpager.ViewPagerSize;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
public class BindFragmentAnnPlugin implements FieldAnnBasePlugin {

    @Override
    public void dealField(@NonNull Object obj, @Nullable ViewModel viewModel, @NonNull Field field) {
        try {
            BindFragment bindFragmentAnn = field.getAnnotation(BindFragment.class);
            if (bindFragmentAnn != null) {
                field.setAccessible(true);
                Object viewPagerObj = field.get(viewModel == null ? obj : viewModel);
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
                    if (fragmentManager == null) return;
                    if (bindFragmentAnn.withState()) {
                        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
                            private final HashMap<Integer, BaseFragment> mBaseFragmentList = new HashMap<>();

                            @Override
                            public void notifyDataSetChanged() {
                                super.notifyDataSetChanged();
                                mBaseFragmentList.clear();
                            }

                            @Override
                            public Fragment getItem(int i) {
                                try {
                                    BaseFragment baseFragment = (BaseFragment) bindFragmentAnn.fragment().newInstance();
                                    if (viewModel != null) {
                                        baseFragment.setArguments(baseFragment.getInitBundle(
                                                (ViewModelActivity) obj, i));
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
                            private final HashMap<Integer, BaseFragment> mBaseFragmentList = new HashMap<>();

                            @Override
                            public void notifyDataSetChanged() {
                                super.notifyDataSetChanged();
                                mBaseFragmentList.clear();
                            }

                            @Override
                            public Fragment getItem(int i) {
                                try {
                                    BaseFragment baseFragment = (BaseFragment) bindFragmentAnn.fragment().newInstance();
                                    if (viewModel != null) {
                                        baseFragment.setArguments(baseFragment.getInitBundle(
                                                (ViewModelActivity) obj, i));
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
