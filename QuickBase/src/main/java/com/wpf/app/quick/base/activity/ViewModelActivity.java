package com.wpf.app.quick.base.activity;


import android.arch.lifecycle.ViewModelProvider;
import android.view.View;

import com.wpf.app.quick.base.helper.annotations.QuickBindHelper;
import com.wpf.app.quick.base.utils.ViewMolderEx;
import com.wpf.app.quick.base.viewmodel.BaseViewModel;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class ViewModelActivity<VM extends BaseViewModel<H>, H extends BaseView> extends BaseActivity {

    private VM mViewModel;

    public ViewModelActivity(int layoutId) {
        super(layoutId);
    }

    public ViewModelActivity(View layoutView) {
        super(layoutView);
    }

    public ViewModelActivity(int layoutId, String activityTitle) {
        super(layoutId, activityTitle);
    }

    public ViewModelActivity(View layoutView, String activityTitle) {
        super(layoutView, activityTitle);
    }

    @Override
    protected void dealContentView() {
        Class<VM> vmClass = ViewMolderEx.getVm0Clazz(this);
        if (vmClass != null) {
            mViewModel = new ViewModelProvider(this,
                    new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                    .get(vmClass);
            QuickBindHelper.bind(this, mViewModel);
            mViewModel.onViewCreated((H) this);
        }
    }

    public VM getViewModel() {
        return mViewModel;
    }
}
