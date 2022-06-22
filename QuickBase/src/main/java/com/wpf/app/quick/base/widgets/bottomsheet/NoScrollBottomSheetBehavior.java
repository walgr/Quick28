package com.wpf.app.quick.base.widgets.bottomsheet;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 王朋飞 on 2022/6/22.
 * 内部有列表的可以尝试用这去除滑动冲突
 */
public class NoScrollBottomSheetBehavior<V extends View> extends BottomSheetBehavior<V> {

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent event) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return false;
    }
}
