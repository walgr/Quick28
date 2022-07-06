package com.wpf.app.quick.base.widgets.recyclerview;

import android.arch.lifecycle.LifecycleObserver;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class QuickViewDataBinding<VB extends ViewDataBinding> extends QuickItemData implements LifecycleObserver {

    protected @LayoutRes int layoutId;

    public QuickViewDataBinding() {
    }

    QuickViewDataBinding(@LayoutRes int layoutId) {
        super();
        this.layoutId = layoutId;
    }

    public void onHolderCreated(QuickViewBindingHolder<? extends QuickViewDataBinding<VB>, VB> viewHolder) {

    }
}
