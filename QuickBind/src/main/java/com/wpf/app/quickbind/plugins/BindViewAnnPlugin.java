package com.wpf.app.quickbind.plugins;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.wpf.app.quick.annotations.BindView;

import java.lang.reflect.Field;

/**
 * Created by 王朋飞 on 2022/6/15.
 * 给主工程R使用
 */
public class BindViewAnnPlugin implements FieldAnnBasePlugin {

    @Override
    public void dealField(@NonNull Object obj, @Nullable ViewModel viewModel, @NonNull Field field) {
        setFieldView(obj, viewModel, field);
    }

    private void setFieldView(Object obj, @Nullable ViewModel viewModel, @NonNull Field field) {
        try {
            BindView findViewA = field.getAnnotation(BindView.class);
            if (findViewA != null) {
                field.setAccessible(true);
                if (field.get(getRealObj(obj, viewModel)) != null) {
                    return;
                }
                View findView = findView(obj, findViewA.value());
                if (viewModel != null) {
                    field.set(viewModel, findView);
                } else {
                    field.set(obj, findView);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
