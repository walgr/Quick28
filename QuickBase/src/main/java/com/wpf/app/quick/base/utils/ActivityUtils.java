package com.wpf.app.quick.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
public class ActivityUtils {

    public static <T extends Activity> void startActivity(Context activity, Class<T> activityCls) {
        startActivity(activity, activityCls, null);
    }

    public static <T extends Activity> void startActivity(Context activity, Class<T> activityCls, Map<String, Object> data) {
        Intent intent = new Intent(activity, activityCls);
        if (data != null) {
            Set<String> keys = data.keySet();
            for (String key : keys) {
                Object value = data.get(key);
                if (value instanceof ArrayList<?> && !((ArrayList<?>)value).isEmpty() && (((ArrayList)value).get(0) instanceof Parcelable)) {
                    intent.putParcelableArrayListExtra(key, (ArrayList<? extends Parcelable>) value);
                } else if (value instanceof Parcelable) {
                    intent.putExtra(key, (Parcelable) value);
                } else if (value instanceof Serializable) {
                    intent.putExtra(key, (Serializable) value);
                }
            }
        }
        activity.startActivity(intent);
    }
}
