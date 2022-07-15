package com.wpf.app.quick.base.widgets.bottomsheet;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wpf.app.quick.base.widgets.dialog.DialogSize;
import com.wpf.app.quick.base.widgets.dialog.QuickContext;
import com.wpf.app.quick.base.widgets.dialog.SheetInit;

/**
 * Created by 王朋飞 on 2022/6/21.
 */
public class QuickBottomSheetView extends LinearLayout implements SheetInit, QuickContext {

    public QuickBottomSheetView(Context context) {
        this(context, null);
    }

    public QuickBottomSheetView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickBottomSheetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @CallSuper
    protected void initView() {
        setOrientation(LinearLayout.VERTICAL);
        dealSize();
        setBackground(new ColorDrawable(Color.TRANSPARENT));
        post(() -> {
            initBottomSheet();
            setLayoutParam();
            setHeight(initViewMaxHeight());
            setVisibility(VISIBLE);
        });
        setVisibility(GONE);
    }

    protected void dealSize() {
        Point size = getScreenSize();
        screenWidth = size.x;
        screenHeight = size.y;
    }

    public void setLayoutParam() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.LayoutParams layoutParamsC = (CoordinatorLayout.LayoutParams) layoutParams;
            layoutParamsC.setBehavior(getBehavior());
        } else {
            layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getScreenHeight());
            if (getBehavior() != null) {
                ((CoordinatorLayout.LayoutParams) layoutParams).setBehavior(getBehavior());
            }
            setLayoutParams(layoutParams);
        }
    }

    public void setHeight(int height) {
        if (height < -1) return;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.LayoutParams layoutParamsC = (CoordinatorLayout.LayoutParams) layoutParams;
            layoutParamsC.height = height;
            setLayoutParams(layoutParamsC);
        } else {
            layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
            setLayoutParams(layoutParams);
        }
    }

    protected int screenWidth, screenHeight;
    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int initViewMaxHeight() {
        return DialogSize.NO_SET;
    }

    protected BottomSheetBehavior<View> mBehavior;
    protected void initBottomSheet() {
        if (canScroll()) {
            mBehavior = new BottomSheetBehavior<>();
        } else {
            mBehavior = new NoScrollBottomSheetBehavior<>();
        }
        setBottomSheet();
    }

    @CallSuper
    protected void setBottomSheet() {
        mBehavior.setHideable(hideAble());
        mBehavior.setState(initSheetState());
        mBehavior.setPeekHeight(initPeekHeight());
    }

    public BottomSheetBehavior<?> getBehavior() {
        return mBehavior;
    }
}
