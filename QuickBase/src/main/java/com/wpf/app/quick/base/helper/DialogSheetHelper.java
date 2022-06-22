package com.wpf.app.quick.base.helper;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;
import android.widget.FrameLayout;

import com.wpf.app.quick.base.R;
import com.wpf.app.quick.base.widgets.dialog.DialogSize;
import com.wpf.app.quick.base.widgets.dialog.QuickBottomSheetDialog;
import com.wpf.app.quick.base.widgets.dialog.QuickBottomSheetDialogFragment;

/**
 * Created by 王朋飞 on 2022/6/21.
 */
public class DialogSheetHelper {

    public static BottomSheetBehavior<?> dealSheet(QuickBottomSheetDialog dialog) {
        View bottomSheet = dialog.getWindow().findViewById(R.id.design_bottom_sheet);
        bottomSheet.setBackground(new ColorDrawable(Color.TRANSPARENT));
        BottomSheetBehavior<?> behavior = BottomSheetBehavior.from(bottomSheet);
        if (dialog.initPeekHeight() != DialogSize.NO_SET) {
            behavior.setPeekHeight(dialog.initPeekHeight());
        }
        if (dialog.initSheetState() != DialogSize.NO_SET) {
            behavior.setState(dialog.initSheetState());
        }
        behavior.setHideable(dialog.hideAble());
        return behavior;
    }

    public static BottomSheetBehavior<?> dealSheet(QuickBottomSheetDialogFragment dialog) {
        View bottomSheet = dialog.getWindow().findViewById(R.id.design_bottom_sheet);
        bottomSheet.setBackground(new ColorDrawable(Color.TRANSPARENT));
        BottomSheetBehavior<?> behavior = BottomSheetBehavior.from(bottomSheet);
        if (dialog.initPeekHeight() != DialogSize.NO_SET) {
            behavior.setPeekHeight(dialog.initPeekHeight());
        }
        if (dialog.initSheetState() != DialogSize.NO_SET) {
            behavior.setState(dialog.initSheetState());
        }
        behavior.setHideable(dialog.hideAble());
        return behavior;
    }
}
