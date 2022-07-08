package com.wpf.app.quick.base.helper.binddatahelper;

import android.support.annotation.NonNull;
import android.view.View;

import com.wpf.app.quick.annotations.BindD2VBaseHelper;

/**
 * Created by 王朋飞 on 2022/7/8.
 */
public class ItemClick implements BindD2VBaseHelper<View, View.OnClickListener> {

    @Override
    public void initView(@NonNull View view, @NonNull View.OnClickListener onClickListener) {
        view.setOnClickListener(onClickListener);
    }
}
