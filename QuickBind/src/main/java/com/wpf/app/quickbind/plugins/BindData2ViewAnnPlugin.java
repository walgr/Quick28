package com.wpf.app.quickbind.plugins;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wpf.app.quickbind.BindBaseHelper;
import com.wpf.app.quickbind.annotations.BindData2View;

import java.lang.reflect.Field;

/**
 * Created by 王朋飞 on 2022/7/6.
 */
public class BindData2ViewAnnPlugin implements FieldAnnBasePlugin {

    @Override
    public void dealField(@NonNull Object obj, @Nullable ViewModel viewModel, @NonNull Field field) {
        try {
            BindData2View bindData2View = field.getAnnotation(BindData2View.class);
            if (bindData2View == null) return;
            int id = bindData2View.id();
            Class<BindBaseHelper> helper = (Class<BindBaseHelper>) bindData2View.helper();
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) obj.getClass().getMethod("getViewHolder").invoke(obj);
            View findView = findView(viewHolder, id);
            field.setAccessible(true);
            Object value = field.get(obj);
            if (findView == null || value == null) return;
            BindBaseHelper<View, Object> bindBaseHelper = helper.newInstance();
            bindBaseHelper.initView(findView, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
