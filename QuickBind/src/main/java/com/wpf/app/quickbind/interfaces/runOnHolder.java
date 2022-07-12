package com.wpf.app.quickbind.interfaces;

import android.view.View;

/**
 * Created by 王朋飞 on 2022/7/8.
 */
public interface runOnHolder<V extends View, Data> {

    Data run(V view);
}