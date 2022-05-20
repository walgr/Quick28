package com.wpf.app.quick.base.widgets.recyclerview;

import android.arch.lifecycle.LifecycleObserver;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class QuickItemDataBinding<VB extends ViewDataBinding> extends QuickItemData implements LifecycleObserver {

    protected @LayoutRes int layoutId;

    QuickItemDataBinding(@LayoutRes int layoutId) {
        super();
        this.layoutId = layoutId;
    }

    public void onCreateHolderEnd(QuickViewBindingHolder<? extends QuickItemDataBinding<VB>, VB > mViewHolder) {

    }
}
