package com.wpf.app.quick.base.helper.binddatahelper;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.wpf.app.quick.annotations.BindD2VBaseHelper;

/**
 * Created by 王朋飞 on 2022/7/7.
 */
public class Text2TextView implements BindD2VBaseHelper<TextView, String> {

    @Override
    public void initView(@NonNull TextView view, @NonNull String s) {
        view.setText(s);
    }
}
