package com.wpf.app.quick.base.widgets.recyclerview;

import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public interface QuickAdapterListener<T> {
    void onItemClick(View view, @Nullable T data, int position);
}
