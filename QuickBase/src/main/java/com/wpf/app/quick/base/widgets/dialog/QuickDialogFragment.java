package com.wpf.app.quick.base.widgets.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.wpf.app.quick.base.helper.DialogSizeHelper;

/**
 * Created by 王朋飞 on 2022/6/16.
 */
@SuppressLint("ValidFragment")
public class QuickDialogFragment extends DialogFragment implements DialogSize {

    protected @LayoutRes
    int layoutId;
    protected View layoutView;

    protected Context mContext;

    public Context getRealContext() {
        return mContext;
    }

    public QuickDialogFragment(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public QuickDialogFragment(View layoutView) {
        this.layoutView = layoutView;
    }

    @Override
    public void onStart() {
        super.onStart();
        DialogSizeHelper.dealSize(this, initDialogWidth(), initDialogHeight());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        Dialog dialog = new Dialog(this.getActivity(), initDialogStyle() == NO_SET ? this.getTheme() : initDialogStyle());
        Window window = dialog.getWindow();
        if (window != null) {
            if (initDialogAnim() != NO_SET) {
                window.setWindowAnimations(initDialogAnim());
            }
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layoutView != null) {
            return layoutView;
        } else {
            return inflater.inflate(layoutId, container, false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getScreenSize();
        initView(view);
        onDialogOpen();
    }

    protected int screenWidth, screenHeight;

    private void getScreenSize() {
        if (getRealContext() == null) return;
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((Activity) getRealContext()).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        screenWidth = localDisplayMetrics.widthPixels;
        screenHeight = localDisplayMetrics.heightPixels;
    }

    public void initView(View view) {

    }

    public void onDialogOpen() {

    }

    public void onDialogClose() {

    }

    protected int newWidth = NO_SET, newHeight = NO_SET;

    /**
     * 重新设置高度
     */
    public void newHeight(int newHeight) {
        this.newHeight = newHeight;
        DialogSizeHelper.dealSize(this, newWidth == 0 ? initDialogWidth() : newWidth, newHeight);
    }

    /**
     * 重新设置宽度
     */
    public void newWidth(int newWidth) {
        this.newWidth = newWidth;
        DialogSizeHelper.dealSize(this, newWidth, newHeight == 0 ? initDialogHeight() : newHeight);
    }

    public void show(Object context) {
        if (context instanceof AppCompatActivity) {
            show(((AppCompatActivity) context).getSupportFragmentManager(), getClass().getName() + System.currentTimeMillis());
        } else if (context instanceof Fragment) {
            show(((Fragment) context).getChildFragmentManager(), getClass().getName() + System.currentTimeMillis());
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        onDialogClose();
    }

    @Override
    public void dismissAllowingStateLoss() {
        super.dismissAllowingStateLoss();
        onDialogClose();
    }

    @Override
    public Window getWindow() {
        return getDialog().getWindow();
    }

    @Override
    public int getScreenWidth() {
        return screenWidth;
    }

    @Override
    public int getScreenHeight() {
        return screenHeight;
    }

    @Override
    public int getNewWidth() {
        return newWidth;
    }

    @Override
    public int getNewHeight() {
        return newHeight;
    }
}
