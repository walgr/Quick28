package com.wpf.app.quick.base.activity;


import android.arch.lifecycle.ViewModelProvider;

import com.wpf.app.quick.base.utils.ViewMolderEx;
import com.wpf.app.quick.base.viewmodel.BaseViewModel;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class ViewModelActivity<VM extends BaseViewModel<H>, H extends BaseView> extends BaseActivity {

    private VM mViewModel;

    @Override
    protected void dealContentView() {
        mViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(ViewMolderEx.getVm0Clazz(this));
        mViewModel.onModelCreate((H) this);
    }
}
