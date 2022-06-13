package com.wpf.app.quick.base.helper.annotations;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by 王朋飞 on 2022/6/8.
 * 自动获取Activity、Fragment里Intent里的值到属性上
 * 支持 char、byte、int、float、long、short、double、String、array、list、map、Serializable、Parcelable
 * 暂不支持 Parcelable[]
 */
public class QuickBindHelper {

    private static String bindSpFileName = "QuickViewSpBindFile";

    public static void bind(Activity activity) {
        bind(activity, null);
    }

    public static void bind(Activity activity, ViewModel viewModel) {
        setDataByIntent(activity.getIntent().getExtras(), viewModel == null ? activity : viewModel);
        findView(activity, viewModel);
    }

    public static void bind(Fragment fragment) {
        bind(fragment, null);
    }

    public static void bind(Fragment fragment, ViewModel viewModel) {
        setDataByIntent(fragment.getArguments(), viewModel == null ? fragment : viewModel);
        findView(fragment, viewModel);
    }

    public static void bind(RecyclerView.ViewHolder viewHolder) {
        findView(viewHolder, null);
    }

    private static void setDataByIntent(@Nullable Bundle bundle, Object obj) {
        if (bundle == null) return;
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                setFieldData(bundle, obj, field);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void findView(Object obj, @Nullable ViewModel viewModel) {
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(obj) == null) {
                    setFieldView(obj, null, field);
                }
            }
            if (viewModel != null) {
                Field[] viewModelFields = viewModel.getClass().getDeclaredFields();
                for (Field field : viewModelFields) {
                    setFieldView(obj, viewModel, field);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setFieldView(Object obj, @Nullable ViewModel viewModel, @NonNull Field field) {
        try {
            FindView findViewA = field.getAnnotation(FindView.class);
            if (findViewA != null) {
                field.setAccessible(true);
                View findView = findView(obj, findViewA.id());
                if (findView instanceof TextView) {
                    setTextViewValue(
                            (TextView) findView,
                            bindSpFileName,
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

    private static void setTextViewValue(TextView textView, String spFileName, @NonNull String bindSpKey, @NonNull String setSpKey, @NonNull String getSpKey, String defaultValue) {
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

    private static View findView(Object obj, int id) {
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

    private static void setFieldData(Bundle bundle, Object obj, Field field) {
        AutoGet autoGet = field.getAnnotation(AutoGet.class);
        if (autoGet == null) return;
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

    private static int getFieldType(@NonNull Field field) {
        Type type = field.getGenericType();
        if (type instanceof Class<?> && ((Class<?>) type).isArray()) {
            return 1;
        } else if (type instanceof ParameterizedType) {
            return 2;
        }
        return 0;
    }

    private static boolean arrayContains(Class<?>[] classes, Class<?> cls) {
        if (classes == null) return false;
        for (Class<?> c : classes) {
            if (c == cls) return true;
        }
        return false;
    }

    public static void setBindSpFileName(String bindSpFileName) {
        QuickBindHelper.bindSpFileName = bindSpFileName;
    }
}
