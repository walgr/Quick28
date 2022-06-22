package com.wpf.app.quick.base.utils;

import android.support.v7.widget.RecyclerView;

/**
 * Created by 王朋飞 on 2022/6/22.
 */
public class RecyclerViewUtils {

    public static boolean isScrollBottom(RecyclerView recyclerView) {
        return !recyclerView.canScrollVertically(1);
    }

    public static boolean isScrollTop(RecyclerView recyclerView) {
        return !recyclerView.canScrollVertically(-1);
    }
}
