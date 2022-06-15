package com.wpf.app.quick.base.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.wpf.app.quick.base.activity.BaseView;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class BaseViewModel<T extends BaseView> extends ViewModel {

    public void onViewCreated(T view) {

    }
}
