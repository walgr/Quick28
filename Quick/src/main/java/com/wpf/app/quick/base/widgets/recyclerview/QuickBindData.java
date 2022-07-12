package com.wpf.app.quick.base.widgets.recyclerview;

import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.wpf.app.quickbind.QuickBind;

/**
 * Created by 王朋飞 on 2022/7/6.
 */
public class QuickBindData extends QuickItemData {

    protected @LayoutRes
    int layoutId;

    protected boolean dealBind = true;

    public QuickBindData() {
    }

    public QuickBindData(int layoutId) {
        this.layoutId = layoutId;
    }

    protected QuickViewHolder<? extends QuickBindData> mViewHolder;

    @CallSuper
    public void onBindViewHolder(QuickAdapter adapter, @NonNull QuickViewHolder<? extends QuickBindData> viewHolder, int position) {
        this.mViewHolder = viewHolder;
        if (dealBind) {
            QuickBind.dealInPlugins(this, null, QuickBind.bindPlugin);
        }
    }

    public void noBind() {
        this.dealBind = false;
    }

    public QuickViewHolder<? extends QuickBindData> getViewHolder() {
        return mViewHolder;
    }
}
