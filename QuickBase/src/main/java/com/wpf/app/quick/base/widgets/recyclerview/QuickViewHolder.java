package com.wpf.app.quick.base.widgets.recyclerview;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public abstract class QuickViewHolder<T extends QuickItemData> extends RecyclerView.ViewHolder {

    public QuickViewHolder(ViewGroup mParent, @LayoutRes int layoutId) {
        super(LayoutInflater.from(mParent.getContext()).inflate(layoutId, mParent, false));
    }

    protected QuickAdapterListener<T> mQuickAdapterListener;

    public void onCreateViewHolder(View itemView) {

    }

    public abstract void onBindViewHolder(QuickAdapter adapter, T data, int position);

    public @Nullable QuickAdapterListener<QuickItemData> getQuickAdapterListener() {
        if (mQuickAdapterListener == null) return null;
        return (QuickAdapterListener<QuickItemData>)mQuickAdapterListener;
    }

    public void setQuickAdapterListener(@NonNull QuickAdapterListener<T> quickAdapterListener) {
        mQuickAdapterListener = quickAdapterListener;
    }
}
