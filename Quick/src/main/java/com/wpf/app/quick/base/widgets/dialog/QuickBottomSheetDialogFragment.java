package com.wpf.app.quick.base.widgets.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.wpf.app.quick.base.helper.DialogSheetHelper;
import com.wpf.app.quick.base.helper.DialogSizeHelper;

/**
 * Created by 王朋飞 on 2022/6/21.
 */
@SuppressLint("ValidFragment")
public class QuickBottomSheetDialogFragment extends BottomSheetDialogFragment implements DialogSize, DialogLifecycle, SheetInit {

    protected @LayoutRes
    int layoutId;
    protected View layoutView;

    protected Context mContext;

    protected BottomSheetBehavior<?> mBehavior;

    @NonNull
    public Context getRealContext() {
        return mContext;
    }

    public QuickBottomSheetDialogFragment(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public QuickBottomSheetDialogFragment(View layoutView) {
        this.layoutView = layoutView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mBehavior = DialogSheetHelper.dealSheet(this);
        DialogSizeHelper.dealSize(this, initDialogWidth(), initDialogHeight());
    }

    public BottomSheetBehavior<?> getBehavior() {
        return mBehavior;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        BottomSheetDialog dialog = new BottomSheetDialog(this.getRealContext(), initDialogStyle() == NO_SET ? this.getTheme() : initDialogStyle());
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
        dealSize();
        initView(view);
        onDialogOpen();
    }

    protected int screenWidth, screenHeight;

    protected void dealSize() {
        Point size = getScreenSize();
        screenWidth = size.x;
        screenHeight = size.y;
    }

    public void initView(View view) {

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
        onDialogPrepare();
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
