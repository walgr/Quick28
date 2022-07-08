package com.wpf.app.quick.base.activity;

import android.annotation.SuppressLint;
import android.support.annotation.CallSuper;
import android.view.View;

import com.wpf.app.quick.base.R;
import com.wpf.app.quick.base.widgets.recyclerview.QuickRecyclerView;

/**
 * Created by 王朋飞 on 2022/6/15.
 */
public class RFragment extends QuickFragment {

    @SuppressLint("NonConstantResourceId")
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
    public void initView(View view) {
        if (getView() != null) {
            mRecyclerView = getView().findViewById(R.id.recyclerView);
        }
    }

    public QuickRecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
