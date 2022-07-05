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


    default View findView(Object obj, int id) {
        View findView = null;
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
        if (rootView != null) {
            findView = rootView.findViewById(id);
        }
        return findView;
    }

    void dealField(@NonNull Object obj, @Nullable ViewModel viewModel, @NonNull Field field);
}
