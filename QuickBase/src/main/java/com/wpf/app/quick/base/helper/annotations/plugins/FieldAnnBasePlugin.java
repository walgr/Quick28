package com.wpf.app.quick.base.helper.annotations.plugins;

import android.arch.lifecycle.ViewModel;

import java.lang.reflect.Field;

/**
 * Created by 王朋飞 on 2022/6/15.
 * 处理有注解的属性
 */
public interface FieldAnnBasePlugin {

    void dealField(Object obj, ViewModel viewModel, Field field);
}
