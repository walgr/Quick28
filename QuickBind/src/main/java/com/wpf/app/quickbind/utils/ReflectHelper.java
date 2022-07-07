package com.wpf.app.quickbind.utils;

import android.app.Dialog;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 王朋飞 on 2022/7/7.
 */
public class ReflectHelper {

    public static List<Field> getFieldWithParent(@NonNull Object obj) {
        ArrayList<Field> result = new ArrayList<>();
        Class<?> curCls = obj.getClass();
        while (curCls != null) {
            result.addAll(Arrays.asList(curCls.getDeclaredFields()));
            curCls = curCls.getSuperclass();
            if (curCls == AppCompatActivity.class
                    || curCls == Fragment.class
                    || curCls == Dialog.class
                    || curCls == RecyclerView.ViewHolder.class
                    || curCls == View.class
                    || curCls == ViewModel.class) {
                break;
            }
        }
        return result;
    }

    /**
     * 向上扫描是否可以停止
     */
    public static boolean canBreakScan(Class<?> curCls) {
        if (curCls == null) return true;
        String clsName = curCls.getName();
        return clsName.startsWith("android.") || clsName.startsWith("androidx.") || clsName.startsWith("java.");
    }
}
