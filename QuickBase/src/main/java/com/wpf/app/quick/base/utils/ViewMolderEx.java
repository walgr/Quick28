package com.wpf.app.quick.base.utils;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class ViewMolderEx {

    public @Nullable
    static <VM extends Class<? extends ViewModel>> VM getVm0Clazz(@NonNull Object obj) {
        Type superCls = obj.getClass().getGenericSuperclass();
        if (superCls instanceof ParameterizedType) {
            Type[] actualType = ((ParameterizedType) superCls).getActualTypeArguments();
            if (actualType.length != 0) {
                Type type = actualType[0];
                if (type instanceof ParameterizedType) {
                    return (VM) ((ParameterizedType) type).getRawType();
                } else {
                    return (VM) type;
                }
            }
        }
        return null;
    }

    public static <VB> VB getVm1Clazz(@NonNull Object obj) {
        return (VB) ((ParameterizedType) obj.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
}
