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
import android.support.annotation.StyleRes;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wpf.app.quick.base.helper.DialogSizeHelper;

/**
 * Created by 王朋飞 on 2022/6/16.
 */
@SuppressLint("ValidFragment")
public class QuickDialog extends Dialog implements DialogSize {

    protected @LayoutRes
    int layoutId;
    protected View layoutView;

    protected View mView;

    public View getView() {
        return mView;
    }

    protected Context mContext;

    @NonNull
    public Context getRealContext() {
        return mContext;
    }

    public @Nullable Activity getActivity() {
        if (getRealContext() instanceof Activity) {
            return (Activity) getRealContext();
        } else if (getRealContext() instanceof ContextThemeWrapper) {
            if (((ContextThemeWrapper)getRealContext()).getBaseContext() instanceof Activity) {
                return (Activity) ((ContextThemeWrapper) getRealContext()).getBaseContext();
            }
        }
        return null;
    }

    public QuickDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public QuickDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public QuickDialog(@NonNull Context context, @LayoutRes int layoutId) {
        super(context);
        this.layoutId = layoutId;
        this.mContext = context;
    }

    public QuickDialog(@NonNull Context context, int themeResId, @LayoutRes int layoutId) {
        super(context, themeResId);
        this.layoutId = layoutId;
        this.mContext = context;
    }

    public QuickDialog(@NonNull Context context, View layoutView) {
        super(context);
        this.layoutView = layoutView;
        this.mContext = context;
    }

    public QuickDialog(@NonNull Context context, int themeResId, View layoutView) {
        super(context, themeResId);
        this.layoutView = layoutView;
        this.mContext = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        DialogSizeHelper.dealSize(this, initDialogWidth(), initDialogHeight());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getScreenSize();
        if (layoutView != null) {
            mView = layoutView;
        } else {
            mView = View.inflate(getContext(), layoutId, null);
        }
        setContentView(mView);
        Window window = getWindow();
        if (window != null) {
            if (initDialogAnim() != NO_SET) {
                window.setWindowAnimations(initDialogAnim());
            }
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        initView(mView);
    }

    protected int screenWidth, screenHeight;

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    private void getScreenSize() {
        DisplayMetrics localDisplayMetrics = getRealContext().getResources().getDisplayMetrics();
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

    public int getNewWidth() {
        return newWidth;
    }

    public int getNewHeight() {
        return newHeight;
    }

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

    @Override
    public void show() {
        super.show();
        onDialogOpen();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        onDialogClose();
    }
}
