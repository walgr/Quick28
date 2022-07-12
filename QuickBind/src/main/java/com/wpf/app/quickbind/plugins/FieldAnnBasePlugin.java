package com.wpf.app.quickbind.plugins;

import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wpf.app.quick.runtime.Databinder;
import com.wpf.app.quick.runtime.internal.Utils;
import com.wpf.app.quickbind.QuickBind;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by 王朋飞 on 2022/6/15.
 * 处理有注解的属性
 */
public interface FieldAnnBasePlugin {

    default @NonNull Object getRealObj(@NonNull Object obj, @Nullable ViewModel viewModel) {
        if (viewModel != null) return viewModel;
        return obj;
    }

    default int getSaveId(@NonNull Object obj, @Nullable ViewModel viewModel, @NonNull Field field, int id) {
        Databinder databinder = QuickBind.BINDEDMAP.get(getRealObj(obj, viewModel).getClass());
        if (databinder == null) return id;
        Object value = databinder.getFieldValue(field.getName() + "BindViewId");
        if (value instanceof Integer) {
            return (int) value;
        }
        return id;
    }

    default @Nullable ArrayList<Integer> getSaveIdList(@NonNull Object obj, @Nullable ViewModel viewModel, @NonNull Field field) {
        Databinder databinder = QuickBind.BINDEDMAP.get(getRealObj(obj, viewModel).getClass());
        if (databinder == null) return null;
        Object value = databinder.getFieldValue(field.getName());
        if (value instanceof ArrayList) {
            return (ArrayList<Integer>) value;
        }
        return null;
    }

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

    default View getRootView(Object obj) {
        View rootView = null;
        if (obj instanceof Activity) {
            rootView = ((Activity) obj).getWindow().getDecorView();
        }
        if (obj instanceof Fragment) {
            rootView = ((Fragment) obj).getView();
        }
        if (obj instanceof Dialog) {
            rootView = ((Dialog) obj).getWindow().getDecorView();
        }
        if (obj instanceof RecyclerView.ViewHolder) {
            rootView = ((RecyclerView.ViewHolder) obj).itemView;
        }
        return rootView;
    }

    default View findView(Object obj, int id) {
        View findView = null;
        View rootView = getRootView(obj);
        if (rootView != null) {
            findView = rootView.findViewById(id);
        }
        return findView;
    }

    /**
     * @return 是否已经处理过
     */
    boolean dealField(@NonNull Object obj, @Nullable ViewModel viewModel, @NonNull Field field);
}
