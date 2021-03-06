package com.wpf.app.quick.base.helper.annotations.plugins;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.wpf.app.quick.base.helper.annotations.LoadSp;
import com.wpf.app.quick.base.helper.annotations.QuickBindHelper;

import java.lang.reflect.Field;

/**
 * Created by 王朋飞 on 2022/6/17.
 */
public class LoadSpPlugin implements FieldAnnBasePlugin {

    @Override
    public void dealField(@NonNull Object obj, @Nullable ViewModel viewModel, @NonNull Field field) {
        try {
            LoadSp loadSpAnn = field.getAnnotation(LoadSp.class);
            if (loadSpAnn == null) return;
            Context context = getContext(obj);
            if (context == null) return;
            String fileName = QuickBindHelper.getBindSpFileName();
            if (!TextUtils.isEmpty(loadSpAnn.fileName())) {
                fileName = loadSpAnn.fileName();
            }
            String value = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).getString(loadSpAnn.key(), loadSpAnn.defaultValue());
            field.setAccessible(true);
            if (field.getType() == String.class) {
                field.set(viewModel == null ? obj : viewModel, value);
            } else {
                //不是要String要数据类
                Object valueObj = new Gson().fromJson(value, field.getGenericType());
                field.set(viewModel == null ? obj : viewModel, valueObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
