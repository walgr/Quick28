package com.wpf.app.quick.base.widgets.recyclerview;

import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wpf.app.quick.base.helper.annotations.QuickBindHelper;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public abstract class QuickViewHolder<T extends QuickItemData> extends RecyclerView.ViewHolder {

    boolean dealBindView = false;

    public QuickViewHolder(ViewGroup mParent, @LayoutRes int layoutId) {
        super(LayoutInflater.from(mParent.getContext()).inflate(layoutId, mParent, false));
    }

    public QuickViewHolder(ViewGroup mParent, @LayoutRes int layoutId, boolean dealBindView) {
        super(LayoutInflater.from(mParent.getContext()).inflate(layoutId, mParent, false));
        this.dealBindView = dealBindView;
    }

    protected QuickAdapterListener<T> mQuickAdapterListener;

    @CallSuper
    public void onCreateViewHolder(View itemView) {
        if (dealBindView) {
            QuickBindHelper.bind(this);
        }
    }

    public abstract void onBindViewHolder(QuickAdapter adapter, T data, int position);

    public @Nullable QuickAdapterListener<QuickItemData> getAdapterClickListener() {
        if (mQuickAdapterListener == null) return null;
        return (QuickAdapterListener<QuickItemData>)mQuickAdapterListener;
    }

    public void setQuickAdapterListener(@NonNull QuickAdapterListener<T> quickAdapterListener) {
        mQuickAdapterListener = quickAdapterListener;
    }

    public View getItemView() {
        return itemView;
    }
}
