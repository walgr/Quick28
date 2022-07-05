package com.wpf.app.quickbind;

import android.arch.lifecycle.ViewModel;

/**
 * Created by 王朋飞 on 2022/7/5.
 */
public interface BindViewModel<VM extends ViewModel> {

    VM getViewModel();
}
