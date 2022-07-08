package com.wpf.app.quick.base.helper.binddatahelper;


import android.support.annotation.NonNull;
import android.widget.CheckBox;

import com.wpf.app.quick.annotations.BindD2VHelper;

/**
 * Created by 王朋飞 on 2022/7/8.
 */
public class Select2CheckBox implements BindD2VHelper<CheckBox, Boolean> {

    @Override
    public void initView(@NonNull CheckBox view, @NonNull Boolean aBoolean) {
        view.setChecked(aBoolean);
    }
}
