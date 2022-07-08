package com.wpf.app.quick.base.activity;


import android.arch.lifecycle.ViewModelProvider;
import android.view.View;

import com.wpf.app.quickbind.interfaces.BindViewModel;
import com.wpf.app.quickbind.QuickBind;
import com.wpf.app.quick.base.utils.ViewMolderEx;
import com.wpf.app.quick.base.viewmodel.BaseViewModel;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public abstract class ViewModelActivity<VM extends BaseViewModel<H>, H extends QuickView> extends QuickActivity implements BindViewModel<VM> {

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
    protected void onResume() {
        super.onResume();
        if (mViewModel != null) {
            mViewModel.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mViewModel != null) {
            mViewModel.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mViewModel != null) {
            mViewModel.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mViewModel != null) {
            mViewModel.onDestroy();
        }
    }

    @Override
    protected void dealContentView() {
        Class<VM> vmClass = ViewMolderEx.getVm0Clazz(this);
        if (vmClass != null) {
            mViewModel = new ViewModelProvider(this,
                    new ViewModelProvider.AndroidViewModelFactory(getApplication()))
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
