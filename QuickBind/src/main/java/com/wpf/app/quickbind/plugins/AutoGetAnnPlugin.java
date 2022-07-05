package com.wpf.app.quickbind.plugins;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.wpf.app.quick.annotations.AutoGet;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
public class AutoGetAnnPlugin implements FieldAnnBasePlugin {

    @Override
    public void dealField(Object obj, ViewModel viewModel, Field field) {
        setFieldData(obj, viewModel, field);
    }

    private void setFieldData(Object obj, ViewModel viewModel, Field field) {
        AutoGet autoGet = field.getAnnotation(AutoGet.class);
        if (autoGet == null) return;
        Bundle bundle = null;
        if (obj instanceof Activity) {
            bundle = ((Activity) obj).getIntent().getExtras();
        } else if (obj instanceof Fragment) {
            bundle = ((Fragment) obj).getArguments();
        }
        if (bundle == null) return;
        if (viewModel != null) {
            obj = viewModel;
        }
        field.setAccessible(true);
        String key = field.getName();
        if (!autoGet.key().isEmpty()) {
            key = autoGet.key();
        }
        if (arrayContains(field.getType().getInterfaces(), Parcelable.class)) {
            try {
                //Parcelable
                Parcelable value = bundle.getParcelable(key);
                if (value != null) {
                    field.set(obj, value);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            //Serializable
            Serializable value = bundle.getSerializable(key);
            if (value != null) {
                if (value.getClass().isArray()) {
                    if (((Object[]) value).length != 0 && ((Object[]) value)[0] instanceof Parcelable) {
                        //TODO 暂不支持Parcelable[]
                    } else {
                        field.set(obj, value);
                    }
                } else {
                    field.set(obj, value);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (getFieldType(field) == 2) {
                //ArrayList
                ArrayList<Parcelable> value =
                        bundle.getParcelableArrayList(key);
                if (value != null) {
                    field.set(obj, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private int getFieldType(@NonNull Field field) {
        Type type = field.getGenericType();
        if (type instanceof Class<?> && ((Class<?>) type).isArray()) {
            return 1;
        } else if (type instanceof ParameterizedType) {
            return 2;
        }
        return 0;
    }

    private boolean arrayContains(Class<?>[] classes, Class<?> cls) {
        if (classes == null) return false;
        for (Class<?> c : classes) {
            if (c == cls) return true;
        }
        return false;
    }
}
