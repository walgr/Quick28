package com.wpf.app.quick.base.helper.annotations.plugins;

import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wpf.app.quick.base.activity.BaseFragment;
import com.wpf.app.quick.base.helper.annotations.BindFragments;
import com.wpf.app.quick.base.widgets.viewpager.FragmentsAdapter;
import com.wpf.app.quick.base.widgets.viewpager.FragmentsStateAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
public class BindFragmentsAnnPlugin implements FieldAnnBasePlugin {

    @Override
    public void dealField(@NonNull Object obj, @Nullable ViewModel viewModel, @NonNull Field field) {
        try {
            BindFragments bindFragmentsAnn = field.getAnnotation(BindFragments.class);
            if (bindFragmentsAnn != null) {
                field.setAccessible(true);
                Object viewPagerObj = field.get(viewModel == null ? obj : viewModel);
                if (viewPagerObj instanceof ViewPager) {
                    ViewPager viewPager = (ViewPager) viewPagerObj;
                    if (bindFragmentsAnn.limit() > 0) {
                        viewPager.setOffscreenPageLimit(bindFragmentsAnn.limit());
                    }
                    FragmentManager fragmentManager = null;
                    if (obj instanceof AppCompatActivity) {
                        fragmentManager = ((AppCompatActivity) obj).getSupportFragmentManager();
                    } else if (obj instanceof Fragment) {
                        fragmentManager = ((Fragment) obj).getChildFragmentManager();
                    }
                    if (fragmentManager == null) return;
                    if (bindFragmentsAnn.withState()) {
                        viewPager.setAdapter(new FragmentsStateAdapter(fragmentManager, getFragment(obj, bindFragmentsAnn.fragments())));
                    } else {
                        viewPager.setAdapter(new FragmentsAdapter(fragmentManager, getFragment(obj, bindFragmentsAnn.fragments())));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<? extends BaseFragment> getFragment(Object obj, Class<? extends BaseFragment>[] fragmentClsArray) {
        if (fragmentClsArray == null) return null;
        List<BaseFragment> fragments = new ArrayList<>();
        int position = 0;
        for (Class<? extends BaseFragment> fragmentCls : fragmentClsArray) {
            try {
                BaseFragment baseFragment = fragmentCls.newInstance();
                if (obj instanceof Activity) {
                    baseFragment.setArguments(baseFragment.getInitBundle((Activity) obj, position));
                } else if (obj instanceof Fragment) {
                    baseFragment.setArguments(baseFragment.getInitBundle((Fragment) obj, position));
                }
                fragments.add(baseFragment);
            } catch (Exception e) {
                e.printStackTrace();
            }
            position++;
        }
        return fragments;
    }
}
