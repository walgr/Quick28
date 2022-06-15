package com.wpf.app.quick.base.activity;

import android.annotation.SuppressLint;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.wpf.app.quick.base.R;
import com.wpf.app.quick.base.widgets.recyclerview.QuickRecyclerView;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
public class RFragment extends BaseFragment {

    protected QuickRecyclerView mRecyclerView;

    public RFragment() {
        super(R.layout.fragment_recyclerview);
    }

    @SuppressLint("ValidFragment")
    public RFragment(String title) {
        super(R.layout.fragment_recyclerview, title);
    }

    @CallSuper
    @Override
    public void initView() {
        if (getView() != null) {
            mRecyclerView = getView().findViewById(R.id.recyclerView);
        }
        super.initView();
        addRefreshView();
    }

    private void addRefreshView() {
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
