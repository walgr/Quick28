package com.wpf.app.quick.base.widgets.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 王朋飞 on 2022/6/21.
 */
public interface QuickContext {

    @NonNull
    Context getContext();

    default Context getRealContext() {
        Activity activity = getActivity();
        if (activity != null) return activity;
        return getContext();
    }

    @Nullable
    default Activity getActivity() {
        if (getContext() instanceof Activity) {
            return (Activity) getContext();
        } else if (getContext() instanceof ContextThemeWrapper) {
            if (((ContextThemeWrapper) getContext()).getBaseContext() instanceof Activity) {
                return (Activity) ((ContextThemeWrapper) getContext()).getBaseContext();
            } else if (((ContextThemeWrapper) getContext()).getBaseContext() instanceof ContextThemeWrapper) {
                if (((ContextThemeWrapper)((ContextThemeWrapper) getContext()).getBaseContext()).getBaseContext() instanceof Activity) {
                    return (Activity) ((ContextThemeWrapper)((ContextThemeWrapper) getContext()).getBaseContext()).getBaseContext();
                }
            }
        }
        return null;
    }

    @Nullable
    default AppCompatActivity getAppCompatActivity() {
        if (getContext() instanceof AppCompatActivity) {
            return (AppCompatActivity) getContext();
        } else if (getContext() instanceof ContextThemeWrapper) {
            if (((ContextThemeWrapper) getContext()).getBaseContext() instanceof AppCompatActivity) {
                return (AppCompatActivity) ((ContextThemeWrapper) getContext()).getBaseContext();
            } else if (((ContextThemeWrapper) getContext()).getBaseContext() instanceof ContextThemeWrapper) {
                if (((ContextThemeWrapper)((ContextThemeWrapper) getContext()).getBaseContext()).getBaseContext() instanceof AppCompatActivity) {
                    return (AppCompatActivity) ((ContextThemeWrapper)((ContextThemeWrapper) getContext()).getBaseContext()).getBaseContext();
                }
            }
        }
        return null;
    }

    @NonNull
    default Point getScreenSize() {
        if (getActivity() == null) return new Point();
        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        return size;
    }

    @NonNull
    default Point getScreenRealSize() {
        if (getActivity() == null) return new Point();
        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getRealSize(size);
        return size;
    }

    @NonNull
    default int getScreenWidth() {
        if (getActivity() == null) return 0;
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.widthPixels;
    }

    @NonNull
    default int getScreenHeight() {
        if (getActivity() == null) return 0;
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.heightPixels;
    }

    @NonNull
    default int getScreenRealHeight() {
        if (getActivity() == null) return 0;
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(localDisplayMetrics);
        return localDisplayMetrics.heightPixels;
    }

    //必须在post里执行
    @NonNull
    default int getViewRealHeight(@NonNull View curView) {
        return ((ViewGroup)curView.getParent()).getMeasuredHeight();
    }

    @SuppressLint("ResourceType")
    @NonNull
    default int getActivityRootViewHeight(int defaultHeight) {
        if (getActivity() == null) return defaultHeight;
        return getActivity().findViewById(16908290).getMeasuredHeight();
    }
}
