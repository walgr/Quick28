package com.wpf.app.quick.base.widgets.dialog;

/**
 * Created by 王朋飞 on 2022/6/21.
 */
public interface DialogLifecycle {

    //dialog show之前执行
    default void onDialogPrepare() {

    }

    //dialog show之后执行
    default void onDialogOpen() {

    }

    //dialog dismiss后执行
    default void onDialogClose() {

    }
}
