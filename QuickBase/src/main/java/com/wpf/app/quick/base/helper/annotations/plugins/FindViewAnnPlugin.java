package com.wpf.app.quick.base.helper.annotations.plugins;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.wpf.app.quick.annotations.GroupView;
import com.wpf.app.quick.base.helper.annotations.BindSp2View;
import com.wpf.app.quick.base.helper.annotations.GroupViews;
import com.wpf.app.quick.base.helper.annotations.QuickBindHelper;

import java.lang.reflect.Field;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
public class FindViewAnnPlugin implements FieldAnnBasePlugin {

    @Override
    public void dealField(@NonNull Object obj, @Nullable ViewModel viewModel, @NonNull Field field) {
        setFieldView(obj, viewModel, field);
    }

    private void setFieldView(Object obj, @Nullable ViewModel viewModel, @NonNull Field field) {
        try {
            BindSp2View findViewA = field.getAnnotation(BindSp2View.class);
            if (findViewA != null) {
                field.setAccessible(true);
                View findView = findView(obj, findViewA.id());
                if (findView instanceof TextView) {
                    setTextViewValue(
                            (TextView) findView,
                            QuickBindHelper.getBindSpFileName(),
                            findViewA.bindSp(),
                            findViewA.setSp(),
                            findViewA.getSp(),
                            findViewA.defaultValue());
                }
                if (viewModel != null) {
                    field.set(viewModel, findView);
                } else {
                    field.set(obj, findView);
                }
            }
            GroupView groupViewA = field.getAnnotation(GroupView.class);
            if (groupViewA != null) {
                field.setAccessible(true);
                GroupViews groupViews = new GroupViews();
                for (int id : groupViewA.idList()) {
                    View findView = findView(obj, id);
                    if (findView != null) {
                        groupViews.viewList.add(findView);
                    }
                }
                if (viewModel != null) {
                    field.set(viewModel, groupViews);
                } else {
                    field.set(obj, groupViews);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTextViewValue(TextView textView, String spFileName, @NonNull String bindSpKey, @NonNull String setSpKey, @NonNull String getSpKey, String defaultValue) {
        String key = "";
        if (!bindSpKey.isEmpty()) {
            key = bindSpKey;
        } else if (!setSpKey.isEmpty()) {
            key = setSpKey;
        } else if (!getSpKey.isEmpty()) {
            key = getSpKey;
        }
        if (key.isEmpty()) return;
        String value = textView.getContext().getSharedPreferences(spFileName, Context.MODE_PRIVATE).getString(key, defaultValue);
        if (!bindSpKey.isEmpty() || !setSpKey.isEmpty()) {
            String finalKey = key;
            textView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s != null) {
                        if (!s.toString().equals(value)) {
                            textView.getContext().getSharedPreferences(spFileName, Context.MODE_PRIVATE)
                                    .edit()
                                    .putString(finalKey, s.toString()).apply();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if (!bindSpKey.isEmpty() || !getSpKey.isEmpty()) {
                textView.setText(value);
            }
        }
    }

    private View findView(Object obj, int id) {
        View findView = null;
        if (obj instanceof Activity) {
            findView = ((Activity) obj).findViewById(id);
        }
        if (obj instanceof Fragment) {
            View fragmentView = ((Fragment) obj).getView();
            if (fragmentView != null) {
                findView = fragmentView.findViewById(id);
            }
        }
        if (obj instanceof RecyclerView.ViewHolder) {
            findView = ((RecyclerView.ViewHolder) obj).itemView.findViewById(id);
        }
        return findView;
    }
}
