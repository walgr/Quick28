package com.wpf.app.quick.base.activity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.wpf.app.quickbind.QuickBind;
import com.wpf.app.quick.base.utils.ViewMolderEx;
import com.wpf.app.quick.base.viewmodel.BaseViewModel;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
@SuppressLint("ValidFragment")
public class ViewModelFragment<VM extends BaseViewModel<H>, H extends BaseView> extends BaseFragment {

    protected VM mViewModel;

    public ViewModelFragment(@LayoutRes int layoutId) {
        super(layoutId);
    }

    public ViewModelFragment(@NonNull View layoutView) {
        super(layoutView);
    }

    public ViewModelFragment(@LayoutRes int layoutId, String title) {
        super(layoutId, title);
    }

    public ViewModelFragment(@NonNull View layoutView, String title) {
        super(layoutView, title);
    }

    @CallSuper
    @Override
    public void initView(View view) {
        initViewModel();
        super.initView(view);
    }

    public void initViewModel() {
        Class<VM> vmClass = ViewMolderEx.getVm0Clazz(this);
        if (vmClass != null && getContext() != null) {
            mViewModel = new ViewModelProvider(this,
                    new ViewModelProvider.AndroidViewModelFactory((Application) getContext().getApplicationContext()))
                    .get(vmClass);
            QuickBind.bind(this, mViewModel);
            mViewModel.baseView = (H) this;
            mViewModel.onViewCreated((H) this);
        }
    }

    public VM getViewModel() {
        return mViewModel;
    }
}
