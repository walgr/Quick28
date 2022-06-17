package com.wpf.app.quick.base.helper.annotations.plugins;

import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.lang.reflect.Field;

/**
 * Created by 王朋飞 on 2022/6/15.
 * 处理有注解的属性
 */
public interface FieldAnnBasePlugin {

    default @Nullable Context getContext(@Nullable Object obj) {
        if (obj == null) return null;
        Context context = null;
        if (obj instanceof Activity) {
            context = (Context) obj;
        } else if (obj instanceof Fragment) {
            context = ((Fragment) obj).getContext();
        } else if (obj instanceof Dialog) {
            context = ((Dialog) obj).getContext();
        }
        return context;
    }

    void dealField(@NonNull Object obj, @Nullable ViewModel viewModel, @NonNull Field field);
}
