package com.wpf.app.quick.base.widgets.dialog;

import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by 王朋飞 on 2022/6/17.
 */
public interface DialogSize {

    int NO_SET = 0;

    default float initDialogHeightPercent() {
        return NO_SET;
    }

    //指定初始高度 不能低于最低 不能超过最高
    default int initDialogHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    //最高高度
    default int initDialogMaxHeight() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    //最低高度
    default int initDialogMinHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    //高度自适应
    default boolean initDialogAdaptiveHeight() {
        return false;
    }

    default float initDialogWidthPercent() {
        return NO_SET;
    }

    //指定初始高度  不能低于最低 不能超过最高
    default int initDialogWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    //最高高度
    default int initDialogMaxWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    //最低高度
    default int initDialogMinWidth() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    //高度自适应
    default boolean initDialogAdaptiveWidth() {
        return false;
    }

    //dialog背景下是否可点击
    default boolean canDialogBackgroundClick() {
        return false;
    }

    default float initDialogAlpha() {
        return 0.35f;
    }

    default int initDialogGravity() {
        return Gravity.CENTER;
    }

    public @StyleRes
    default int initDialogStyle() {
        return NO_SET;
    }

    public @StyleRes
    default int initDialogAnim() {
        return NO_SET;
    }

    public View getView();
    public Window getWindow();
    public int getScreenWidth();
    public int getScreenHeight();
    public int getNewWidth();
    public int getNewHeight();
}
