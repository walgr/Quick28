package com.wpf.app.quick.base.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wpf.app.quick.annotations.AutoGet;
import com.wpf.app.quickbind.interfaces.BindBaseFragment;
import com.wpf.app.quickbind.QuickBind;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
@SuppressLint("ValidFragment")
public class BaseFragment extends BindBaseFragment implements BaseView {

    protected @LayoutRes
    int layoutId;
    protected View layoutView;
    @AutoGet(key = titleKey)
    String title = this.getClass().getName();

    private final static String titleKey = "title";

    public BaseFragment(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public BaseFragment(@NonNull View layoutView) {
        this.layoutView = layoutView;
    }

    public BaseFragment(@LayoutRes int layoutId, String title) {
        this.layoutId = layoutId;
        this.title = title;
        Bundle bundle = new Bundle();
        bundle.putString(titleKey, title);
        setArguments(bundle);
    }

    public BaseFragment(@NonNull View layoutView, String title) {
        this.layoutView = layoutView;
        this.title = title;
        Bundle bundle = new Bundle();
        bundle.putString(titleKey, title);
        setArguments(bundle);
    }

    @CallSuper
    public void initView(View view) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layoutId != 0) {
            return inflater.inflate(layoutId, null);
        } else if (layoutView != null) {
            return layoutView;
        } else {
            return null;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewCreated(view);
    }

    public void viewCreated(View view) {
        QuickBind.bind(this);
        initView(view);
        initView();
    }

    @Override
    @CallSuper
    public void initView() {

    }

    public @Nullable
    String getTitle() {
        return title;
    }
}
