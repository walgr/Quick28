package com.wpf.app.quick.base.activity;

import android.annotation.SuppressLint;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.wpf.app.quick.base.R;
import com.wpf.app.quick.base.viewmodel.BaseViewModel;
import com.wpf.app.quick.base.widgets.recyclerview.QuickRecyclerView;

/**
 * Created by 王朋飞 on 2022/6/15.
 */

public class RModelFragment<VM extends BaseViewModel<H>, H extends BaseView> extends ViewModelFragment<VM, H> {

    protected QuickRecyclerView mRecyclerView;
    protected @IdRes
    int viewId = 0;

    public RModelFragment() {
        super(R.layout.fragment_recyclerview);
    }

    @SuppressLint("ValidFragment")
    public RModelFragment(int layoutId, @IdRes int viewId) {
        super(layoutId);
        this.viewId = viewId;
    }

    @SuppressLint("ValidFragment")
    public RModelFragment(String title) {
        super(R.layout.fragment_recyclerview, title);
    }

    @SuppressLint("ValidFragment")
    public RModelFragment(int layoutId, @IdRes int viewId, String title) {
        super(layoutId, title);
        this.viewId = viewId;
    }

    @CallSuper
    @Override
    public void initView() {
        if (getView() != null) {
            mRecyclerView = getView().findViewById(viewId == 0 ? R.id.recyclerView : viewId);
        }
        super.initView();
        addRefreshView();
    }

    private void addRefreshView() {
        if (viewId != 0) return;
        ViewGroup refreshView = addParentRefreshView();
        if (refreshView != null) {
            if (mRecyclerView != null && mRecyclerView.getParent() instanceof ViewGroup) {
                ViewGroup parentView = (ViewGroup) mRecyclerView.getParent();
                parentView.removeView(mRecyclerView);
                refreshView.addView(mRecyclerView);
                parentView.addView(refreshView);
            }
        }
    }

    protected @NonNull
    ViewGroup addParentRefreshView() {
        return null;
    }

    public QuickRecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
