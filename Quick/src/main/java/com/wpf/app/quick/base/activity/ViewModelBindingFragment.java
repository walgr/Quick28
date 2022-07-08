package com.wpf.app.quick.base.activity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.wpf.app.quick.base.constant.BRConstant;
import com.wpf.app.quickbind.QuickBind;
import com.wpf.app.quick.base.utils.ViewMolderEx;
import com.wpf.app.quick.base.viewmodel.BindingViewModel;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
@SuppressLint("ValidFragment")
public abstract class ViewModelBindingFragment<VM extends BindingViewModel<VB>, VB extends ViewDataBinding> extends QuickFragment {

    protected VM mViewModel;

    public ViewModelBindingFragment(int layoutId) {
        super(layoutId);
    }

    public ViewModelBindingFragment(@NonNull View layoutView) {
        super(layoutView);
    }

    public ViewModelBindingFragment(int layoutId, String title) {
        super(layoutId, title);
    }

    public ViewModelBindingFragment(@NonNull View layoutView, String title) {
        super(layoutView, title);
    }

    @Override
    public void initView(View view) {

    }

    public void setViewModel(VM viewModel) {
        mViewModel = viewModel;
        setViewBinding();
    }

    private VB viewBinding = null;

    public VB getViewBinding() {
        return viewBinding;
    }

    public void setViewBinding() {
        if (getView() != null) {
            viewBinding = DataBindingUtil.getBinding(getView());
            if (viewBinding != null) {
                viewBinding.setLifecycleOwner(this);
                viewBinding.setVariable(BRConstant.viewModel, mViewModel);
                viewBinding.executePendingBindings();
            }
            if (mViewModel != null) {
                mViewModel.setViewBinding(viewBinding);
            }
        }
    }

    @CallSuper
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViewModel();
        super.onViewCreated(view, savedInstanceState);
        initView(getViewBinding());
    }

    public void initViewModel() {
        Class<VM> viewModelCls = ViewMolderEx.getVm0Clazz(this);
        if (viewModelCls != null && getContext() != null) {
            setViewModel(new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory((Application) getContext().getApplicationContext())).get(viewModelCls));
            QuickBind.bind(this, mViewModel);
            if (mViewModel != null) {
                mViewModel.onBindingCreate(viewBinding);
            }
        } else {
            setViewBinding();
        }
    }

    public abstract void initView(@Nullable VB viewDataBinding);
}
