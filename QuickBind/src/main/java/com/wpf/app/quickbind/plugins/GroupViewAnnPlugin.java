package com.wpf.app.quickbind.plugins;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.wpf.app.quick.annotations.GroupView;
import com.wpf.app.quickbind.GroupViews;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by 王朋飞 on 2022/7/6.
 */
public class GroupViewAnnPlugin implements FieldAnnBasePlugin {

    @Override
    public void dealField(@NonNull Object obj, @Nullable ViewModel viewModel, @NonNull Field field) {
        GroupView groupViewA = field.getAnnotation(GroupView.class);
        if (groupViewA == null) return;
        field.setAccessible(true);
        GroupViews groupViews = new GroupViews();
        ArrayList<Integer> R2IdList = getSaveIdList(obj, viewModel, field);
        if (R2IdList != null) {
            for (int id : R2IdList) {
                View findView = findView(obj, id);
                if (findView != null) {
                    groupViews.viewList.add(findView);
                }
            }
        } else {
            for (int id : groupViewA.idList()) {
                View findView = findView(obj, id);
                if (findView != null) {
                    groupViews.viewList.add(findView);
                }
            }
        }
        try {
            if (viewModel != null) {
                field.set(viewModel, groupViews);
            } else {
                field.set(obj, groupViews);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
