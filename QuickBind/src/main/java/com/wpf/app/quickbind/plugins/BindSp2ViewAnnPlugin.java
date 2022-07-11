package com.wpf.app.quickbind.plugins;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.wpf.app.quickbind.annotations.BindSp2View;
import com.wpf.app.quickbind.QuickBind;

import java.lang.reflect.Field;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
public class BindSp2ViewAnnPlugin implements FieldAnnBasePlugin {

    @Override
    public boolean dealField(@NonNull Object obj, @Nullable ViewModel viewModel, @NonNull Field field) {
        try {
            BindSp2View findViewA = field.getAnnotation(BindSp2View.class);
            if (findViewA == null) return false;
            field.setAccessible(true);
            View findView = (View) field.get(obj);
            if (findView instanceof TextView) {
                setTextViewValue(
                        (TextView) findView,
                        QuickBind.getBindSpFileName(),
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
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
}
