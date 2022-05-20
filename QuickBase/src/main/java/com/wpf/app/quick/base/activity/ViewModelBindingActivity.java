package com.wpf.app.quick.base.activity;

import android.arch.lifecycle.ViewModelProvider;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;

import com.wpf.app.quick.base.constant.BRConstant;
import com.wpf.app.quick.base.utils.ViewMolderEx;
import com.wpf.app.quick.base.viewmodel.BindingViewModel;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class ViewModelBindingActivity<VM extends BindingViewModel<VB>, VB extends ViewDataBinding> extends BaseActivity {

    private VM mViewModel;
    private @LayoutRes int layoutId;

    public ViewModelBindingActivity(int layoutId) {
        this.layoutId = layoutId;
    }

    public void setViewModel(VM viewModel) {
        mViewModel = viewModel;
        setViewBinding();
    }

    public void setViewBinding() {
        mViewModel.setViewBinding(DataBindingUtil.setContentView(this, layoutId));
        mViewModel.getViewBinding().setLifecycleOwner(this);
        mViewModel.getViewBinding().setVariable(BRConstant.viewModel, mViewModel);
        mViewModel.getViewBinding().executePendingBindings();
    }

    @Override
    protected void dealContentView() {
        mViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(ViewMolderEx.getVm0Clazz(this));
        mViewModel.onBindingCreate(mViewModel.getViewBinding());
    }
}
