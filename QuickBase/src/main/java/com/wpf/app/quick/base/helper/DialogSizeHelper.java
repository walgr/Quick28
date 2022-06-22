package com.wpf.app.quick.base.helper;

import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;

import com.wpf.app.quick.base.widgets.dialog.DialogSize;

/**
 * Created by 王朋飞 on 2022/6/17.
 */
public class DialogSizeHelper {

    public static void dealSize(@NonNull DialogSize dialog, int dialogWidth, int dialogHeight) {
        if (dialog.getWindow() == null) return;
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        if (dialog.getNewWidth() != DialogSize.NO_SET || dialog.getNewHeight() != DialogSize.NO_SET) {
            if (dialog.getNewWidth() != DialogSize.NO_SET) {
                windowParams.width = dealDialogWidth(dialog, dialog.initDialogWidthPercent() != DialogSize.NO_SET ? (int) (dialog.getNewWidth() * dialog.initDialogWidthPercent()) : dialogWidth);
            }
            if (dialog.getNewHeight() != DialogSize.NO_SET) {
                windowParams.height = dealDialogHeight(dialog, dialog.initDialogHeightPercent() != DialogSize.NO_SET ? (int) (dialog.getScreenHeight() * dialog.initDialogHeightPercent()) : dialogHeight);
            }
        } else {
            windowParams.gravity = dialog.initDialogGravity();
            windowParams.dimAmount = dialog.initDialogAlpha();
            if (dialog.canDialogBackgroundClick()) {
                //背景点击透传
                windowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            }
            if ((dialog.initDialogAdaptiveWidth() || dialog.initDialogWidth() == WindowManager.LayoutParams.WRAP_CONTENT) && dialog.getView() != null && dialog.initDialogWidthPercent() == DialogSize.NO_SET) {
                dialog.getView().post(() -> windowParams.width = dealDialogWidth(dialog, dialog.getView().getWidth()));
            } else {
                windowParams.width = dealDialogWidth(dialog, dialog.initDialogWidthPercent() != DialogSize.NO_SET ? (int) (dialog.getScreenWidth() * dialog.initDialogWidthPercent()) : dialogWidth);
            }
            if ((dialog.initDialogAdaptiveHeight() || dialog.initDialogHeight() == WindowManager.LayoutParams.WRAP_CONTENT) && dialog.getView() != null && dialog.initDialogHeightPercent() == DialogSize.NO_SET) {
                dialog.getView().post(() -> windowParams.height = dealDialogHeight(dialog, dialog.getView().getHeight()));
            } else {
                windowParams.height = dealDialogHeight(dialog, dialog.initDialogHeightPercent() != DialogSize.NO_SET ? (int) (dialog.getScreenHeight() * dialog.initDialogHeightPercent()) : dialogHeight);
            }
        }
        window.setAttributes(windowParams);
    }


    protected static int dealDialogHeight(DialogSize dialog, int curHeight) {
        if (curHeight == WindowManager.LayoutParams.MATCH_PARENT) {
            curHeight = dialog.initDialogMaxHeight();
            if (curHeight == WindowManager.LayoutParams.MATCH_PARENT) {
                curHeight = dialog.initDialogHeight();
            }
            if (curHeight > 0) {
                return dealDialogHeight(dialog, curHeight);
            }
            return curHeight;
        }
        if (curHeight == WindowManager.LayoutParams.WRAP_CONTENT) return curHeight;
        if (dialog.initDialogMaxHeight() == WindowManager.LayoutParams.MATCH_PARENT
                && dialog.initDialogMinHeight() == WindowManager.LayoutParams.WRAP_CONTENT)
            return curHeight;
        int maxHeight = dialog.initDialogMaxHeight();
        if (maxHeight == WindowManager.LayoutParams.MATCH_PARENT) {
            maxHeight = dialog.getScreenHeight();
        }
        if (curHeight > maxHeight) {
            return maxHeight;
        } else return Math.max(curHeight, dialog.initDialogMinHeight());
    }

    protected static int dealDialogWidth(DialogSize dialog, int curWidth) {
        if (curWidth == WindowManager.LayoutParams.MATCH_PARENT) return curWidth;
        if (curWidth == WindowManager.LayoutParams.WRAP_CONTENT) return curWidth;
        if (dialog.initDialogMaxWidth() == WindowManager.LayoutParams.MATCH_PARENT
                && dialog.initDialogMinWidth() == WindowManager.LayoutParams.WRAP_CONTENT)
            return curWidth;
        int maxWidth = dialog.initDialogMaxWidth();
        if (maxWidth == WindowManager.LayoutParams.MATCH_PARENT) {
            maxWidth = dialog.getScreenWidth();
        }
        if (curWidth > maxWidth) {
            return maxWidth;
        } else return Math.max(curWidth, dialog.initDialogMinWidth());
    }
}
