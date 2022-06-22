package com.wpf.app.quick.base.widgets.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.Window;

import com.wpf.app.quick.base.helper.DialogSizeHelper;
import com.wpf.app.quick.base.helper.DialogSheetHelper;

/**
 * Created by 王朋飞 on 2022/6/21.
 */
public class QuickBottomSheetDialog extends BottomSheetDialog implements DialogSize, DialogLifecycle, SheetInit {

    protected @LayoutRes
    int layoutId;
    protected View layoutView;

    protected View mView;

    public View getView() {
        return mView;
    }

    protected Context mContext;

    protected BottomSheetBehavior<View> mBehavior;

    public QuickBottomSheetDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public QuickBottomSheetDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public QuickBottomSheetDialog(@NonNull Context context, @LayoutRes int layoutId) {
        super(context);
        this.layoutId = layoutId;
        this.mContext = context;
    }

    public QuickBottomSheetDialog(@NonNull Context context, int themeResId, @LayoutRes int layoutId) {
        super(context, themeResId);
        this.layoutId = layoutId;
        this.mContext = context;
    }

    public QuickBottomSheetDialog(@NonNull Context context, View layoutView) {
        super(context);
        this.layoutView = layoutView;
        this.mContext = context;
    }

    public QuickBottomSheetDialog(@NonNull Context context, int themeResId, View layoutView) {
        super(context, themeResId);
        this.layoutView = layoutView;
        this.mContext = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        mBehavior = DialogSheetHelper.dealSheet(this);
        DialogSizeHelper.dealSize(this, initDialogWidth(), initDialogHeight());
    }

    public BottomSheetBehavior<View> getBehavior() {
        return mBehavior;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dealSize();
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

    private int screenWidth, screenHeight;

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    protected void dealSize() {
        Point size = getScreenSize();
        screenWidth = size.x;
        screenHeight = size.y;
    }

    public void initView(View view) {

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
        DialogSizeHelper.dealSize(this, newWidth == NO_SET ? initDialogWidth() : newWidth, newHeight);
    }

    /**
     * 重新设置宽度
     */
    public void newWidth(int newWidth) {
        this.newWidth = newWidth;
        DialogSizeHelper.dealSize(this, newWidth, newHeight == NO_SET ? initDialogHeight() : newHeight);
    }

    @Override
    public void show() {
        onDialogPrepare();
        super.show();
        onDialogOpen();
    }

    public void show(Object context) {
        show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        onDialogClose();
    }
}
