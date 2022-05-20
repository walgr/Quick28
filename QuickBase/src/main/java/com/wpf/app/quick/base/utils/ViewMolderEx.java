package com.wpf.app.quick.base.utils;

import android.support.annotation.NonNull;

import java.lang.reflect.ParameterizedType;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class ViewMolderEx {

    public static <VM> VM getVm0Clazz(@NonNull Object obj) {
        return (VM) ((ParameterizedType) obj.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public static <VB> VB getVm1Clazz(@NonNull Object obj) {
        return (VB) ((ParameterizedType) obj.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
}
