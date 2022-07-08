package com.wpf.app.quick.base.activity;

import android.annotation.SuppressLint;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;
import com.wpf.app.quick.base.R;
import com.wpf.app.quick.base.viewmodel.BaseViewModel;
import com.wpf.app.quick.base.widgets.recyclerview.QuickRecyclerView;

/**
 * Created by 王朋飞 on 2022/6/15.
 */

public class RModelFragment<VM extends BaseViewModel<H>, H extends QuickView> extends ViewModelFragment<VM, H> {

    protected QuickRecyclerView mRecyclerView;
    protected @IdRes
    int quickListId = 0;

    public RModelFragment() {
        super(R.layout.fragment_recyclerview);
    }

    @SuppressLint("ValidFragment")
    public RModelFragment(int layoutId, @IdRes int quickListId) {
        super(layoutId);
        this.quickListId = quickListId;
    }

    @SuppressLint("ValidFragment")
    public RModelFragment(String title) {
        super(R.layout.fragment_recyclerview, title);
    }

    @SuppressLint("ValidFragment")
    public RModelFragment(@LayoutRes int layoutId, @IdRes int quicckListId, String title) {
        super(layoutId, title);
        this.quickListId = quicckListId;
    }

    @CallSuper
    @Override
    public void initView(View view) {
        if (getView() != null) {
            mRecyclerView = getView().findViewById(quickListId == 0 ? R.id.recyclerView : quickListId);
        }
        super.initView(view);
    }

    public QuickRecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
