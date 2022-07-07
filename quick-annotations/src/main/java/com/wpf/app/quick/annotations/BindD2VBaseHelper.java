package com.wpf.app.quick.annotations;

import android.support.annotation.NonNull;

/**
 * Created by 王朋飞 on 2022/7/6.
 */
public interface BindD2VBaseHelper<T, Data> {

    void initView(@NonNull T t, @NonNull Data data);
}
