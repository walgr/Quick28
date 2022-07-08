package com.wpf.app.quick.base.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.wpf.app.quick.base.activity.QuickView;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class BaseViewModel<T extends QuickView> extends ViewModel {

    public T baseView;

    public void onResume() {

    }

    public void onPause() {

    }

    public void onStop() {

    }

    public void onDestroy() {

    }

    public void onViewCreated(T baseView) {

    }
}
