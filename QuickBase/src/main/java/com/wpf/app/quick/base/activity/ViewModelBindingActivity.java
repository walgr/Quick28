package com.wpf.app.quick.base.activity;

import android.arch.lifecycle.ViewModelProvider;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.wpf.app.quick.base.constant.BRConstant;
import com.wpf.app.quick.base.helper.annotations.QuickBindHelper;
import com.wpf.app.quick.base.utils.ViewMolderEx;
import com.wpf.app.quick.base.viewmodel.BindingViewModel;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class ViewModelBindingActivity<VM extends BindingViewModel<VB>, VB extends ViewDataBinding> extends BaseActivity {

    protected VM mViewModel;

    public ViewModelBindingActivity(int layoutId) {
        this.layoutId = layoutId;
    }

    public ViewModelBindingActivity(int layoutId, String activityTitle) {
        super(layoutId, activityTitle);
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
        viewBinding = DataBindingUtil.setContentView(this, layoutId);
        viewBinding.setLifecycleOwner(this);
        viewBinding.setVariable(BRConstant.viewModel, mViewModel);
        viewBinding.executePendingBindings();
        if (mViewModel != null) {
            mViewModel.setViewBinding(viewBinding);
        }
    }

    @Override
    protected void dealContentView() {
        Class<VM> viewModelCls = ViewMolderEx.getVm0Clazz(this);
        if (viewModelCls != null) {
            setViewModel(new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(viewModelCls));
            QuickBindHelper.bind(this, mViewModel);
            if (mViewModel != null) {
                mViewModel.onBindingCreate(viewBinding);
            }
        } else {
            setViewBinding();
        }
    }

    @Override
    public void initView() {
        super.initView();
        initView(viewBinding);
    }

    public void initView(VB viewDataBinding) {

    }
}
