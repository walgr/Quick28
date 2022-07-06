package com.wpf.app.quick.base.viewmodel;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.ViewModel;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class BindingViewModel<T extends ViewDataBinding> extends ViewModel implements LifecycleObserver {

    private T mViewBinding;

    public void setViewBinding(T viewBinding) {
        mViewBinding = viewBinding;
    }

    public @NonNull
    T getViewBinding() {
        return mViewBinding;
    }

    public void onBindingCreate(@Nullable T mViewBinding) {

    }
}
