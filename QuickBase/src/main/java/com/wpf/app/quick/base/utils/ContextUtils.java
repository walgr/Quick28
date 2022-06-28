package com.wpf.app.quick.base.utils;

import android.app.Activity;
import android.content.Context;
import android.view.ContextThemeWrapper;

/**
 * Created by 王朋飞 on 2022/6/24.
 */
public class ContextUtils {

    public static Activity getContext(Context context) {
        if (context instanceof Activity) return (Activity) context;
        Context newContext = context;
        while (newContext instanceof ContextThemeWrapper) {
            if (newContext instanceof Activity) return (Activity) newContext;
            newContext = ((ContextThemeWrapper) newContext).getBaseContext();
        }
        return (Activity) newContext;
    }
}
