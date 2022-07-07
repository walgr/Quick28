package com.wpf.app.quick.base.helper.binddatahelper;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.wpf.app.quickbind.BindBaseHelper;

/**
 * Created by 王朋飞 on 2022/7/7.
 */
public class Text2TextView implements BindBaseHelper<TextView, String> {

    @Override
    public void initView(@NonNull TextView view, @NonNull String s) {
        view.setText(s);
    }
}
