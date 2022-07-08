package com.wpf.app.quick.annotations;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by 王朋飞 on 2022/7/6.
 */
public interface BindD2VBaseHelper<V extends View, Data> {

    void initView(@NonNull V view, @NonNull Data data);
}
