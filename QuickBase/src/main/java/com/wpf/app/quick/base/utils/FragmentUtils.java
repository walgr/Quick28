package com.wpf.app.quick.base.utils;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by 王朋飞 on 2022/6/21.
 */
public class FragmentUtils {

    public static Fragment loadData(@NonNull Fragment fragment, @NonNull HashMap<String, Object> data) {
        Bundle bundle = new Bundle();
        Set<String> keys = data.keySet();
        for (String key : keys) {
            Object value = data.get(key);
            if (value instanceof ArrayList<?> && !((ArrayList<?>)value).isEmpty() && (((ArrayList)value).get(0) instanceof Parcelable)) {
                bundle.putParcelableArrayList(key, (ArrayList<? extends Parcelable>) value);
            } else if (value instanceof Parcelable) {
                bundle.putParcelable(key, (Parcelable) value);
            } else if (value instanceof Serializable) {
                bundle.putSerializable(key, (Serializable) value);
            }
        }
        fragment.setArguments(bundle);
        return fragment;
    }
}
