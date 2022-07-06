package com.wpf.app.quickbind;

import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by 王朋飞 on 2022/7/6.
 */
public interface BindBaseHelper<V extends View, Data> {

    void initView(@Nullable V view, @Nullable Data data);
}
