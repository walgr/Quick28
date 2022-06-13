package com.wpf.app.quick28;

import android.app.Application;

import com.wpf.app.quick.base.constant.BRConstant;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BRConstant.viewModel = BR.viewModel;
        BRConstant.data = BR.data;
        BRConstant.adapter = BR.adapter;
        BRConstant.position = BR.position;
    }
}
