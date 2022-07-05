package com.wpf.app.quick.base.widgets.dialog;

import android.support.design.widget.BottomSheetBehavior;

/**
 * Created by 王朋飞 on 2022/6/21.
 */
public interface SheetInit {

    default int initSheetState() {
        return BottomSheetBehavior.STATE_HIDDEN;
    }

    default boolean hideAble() {
        return false;
    }

    default int initPeekHeight() {
        return DialogSize.NO_SET;
    }

    /**
     * 是否可以手动滑动
     * @return
     */
    default boolean canScroll() {
        return true;
    }
}
