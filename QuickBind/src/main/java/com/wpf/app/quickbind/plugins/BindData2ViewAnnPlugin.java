package com.wpf.app.quickbind.plugins;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wpf.app.quick.annotations.BindD2VBaseHelper;
import com.wpf.app.quick.annotations.BindData2View;
import com.wpf.app.quick.annotations.internal.Constants;
import com.wpf.app.quickbind.interfaces.RunOnHolder;
import com.wpf.app.quickbind.utils.ReflectHelper;

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
            int bindId = bindData2View.id();
            Class<BindD2VBaseHelper<View, Object>> helper = (Class<BindD2VBaseHelper<View, Object>>) bindData2View.helper();
            Object viewParent = obj;
            View findView = null;
            if (parentClassIs(obj.getClass(), "QuickBindData")) {
                viewParent = obj.getClass().getMethod("getViewHolder").invoke(obj);
                if (viewParent instanceof RecyclerView.ViewHolder) {
                    findView = ((RecyclerView.ViewHolder) viewParent).itemView;
                }
            }
            if (bindId != Constants.NO_RES_ID) {
                int id = getSaveId(obj, viewModel, field, bindId);
                findView = findView(viewParent, id);
            }
            field.setAccessible(true);
            Object value = field.get(getRealObj(obj, viewModel));
            if (findView == null || value == null) return;
            BindD2VBaseHelper<View, Object> bindBaseHelper = helper.newInstance();
            if (value instanceof RunOnHolder) {
                bindBaseHelper.initView(findView, ((RunOnHolder<?>) value).run((RecyclerView.ViewHolder) viewParent));
            } else {
                bindBaseHelper.initView(findView, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean parentClassIs(@NonNull Class<?> cur, @NonNull String parentName) {
        while (!parentName.equals(cur.getSimpleName())) {
            cur = cur.getSuperclass();
            if (ReflectHelper.canBreakScan(cur)) return false;
            if (cur.getSimpleName().equals(parentName)) {
                return true;
            }
        }
        return true;
    }
}
