package com.wpf.app.quick.base.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wpf.app.quick.base.helper.annotations.AutoGet;
import com.wpf.app.quick.base.helper.annotations.QuickBindHelper;
import com.wpf.app.quick.base.widgets.viewpager.ViewPagerSize;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
@SuppressLint("ValidFragment")
public class BaseFragment extends Fragment implements BaseView {

    protected @LayoutRes
    int layoutId;
    private View layoutView;
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

    /**
     * 从ViewModelActivity获取数据
     */
    public @NonNull Bundle getInitBundle(ViewModelActivity viewModelActivity, int position) {
        return null;
    }

    /**
     * 从Activity获取数据
     */
    public @NonNull Bundle getInitBundle(Activity activity, int position) {
        return null;
    }

    /**
     * 从Fragment获取数据
     */
    public @NonNull Bundle getInitBundle(Fragment fragment, int position) {
        return null;
    }

    @CallSuper
    @Override
    public void initView() {

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
        QuickBindHelper.bind(this);
        initView();
    }

    public @Nullable
    String getTitle() {
        return title;
    }
}
