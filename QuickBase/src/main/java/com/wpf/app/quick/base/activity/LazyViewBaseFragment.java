package com.wpf.app.quick.base.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wpf.app.quick.base.helper.bind.QuickBind;

/**
 * Created by 王朋飞 on 2022/6/28.
 */
@SuppressLint("ValidFragment")
public class LazyViewBaseFragment extends BaseFragment {

    public LazyViewBaseFragment(int layoutId) {
        super(layoutId);
    }

    public LazyViewBaseFragment(@NonNull View layoutView) {
        super(layoutView);
    }

    public LazyViewBaseFragment(int layoutId, String title) {
        super(layoutId, title);
    }

    public LazyViewBaseFragment(@NonNull View layoutView, String title) {
        super(layoutView, title);
    }

    protected View rootView = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            if (layoutView != null) {
                rootView = layoutView;
            } else if (layoutId != 0) {
                rootView = inflater.inflate(layoutId, null);
            }
            QuickBind.bind(this);
            initView(rootView);
            initView();
        }
        return rootView;
    }

    @Override
    public void viewCreated(View view) {

    }
}
