package com.wpf.app.quick.base.widgets.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class QuickRecyclerView extends RecyclerView {

    public QuickRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public QuickRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    protected QuickAdapter mQuickAdapter;

    private void init() {
        mQuickAdapter = new QuickAdapter();
        setAdapter(mQuickAdapter);
    }

    public int size() {
        return mQuickAdapter.getItemCount();
    }

    public QuickAdapter getQuickAdapter() {
        return mQuickAdapter;
    }
}
