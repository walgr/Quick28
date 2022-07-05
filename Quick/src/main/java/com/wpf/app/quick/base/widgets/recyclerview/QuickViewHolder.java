package com.wpf.app.quick.base.widgets.recyclerview;

import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wpf.app.quickbind.QuickBind;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class QuickViewHolder<T extends QuickItemData> extends RecyclerView.ViewHolder {

    boolean dealBindView = false;
    boolean autoClick = false;

    protected QuickAdapter mQuickAdapter;

    public QuickViewHolder(ViewGroup mParent) {
        super(LayoutInflater.from(mParent.getContext()).inflate(0, mParent, false));
    }

    public QuickViewHolder(ViewGroup mParent, @LayoutRes int layoutId) {
        super(LayoutInflater.from(mParent.getContext()).inflate(layoutId, mParent, false));
    }

    public QuickViewHolder(ViewGroup mParent, @LayoutRes int layoutId, boolean dealBindView) {
        super(LayoutInflater.from(mParent.getContext()).inflate(layoutId, mParent, false));
        this.dealBindView = dealBindView;
    }

    public QuickViewHolder(ViewGroup mParent, @LayoutRes int layoutId, boolean dealBindView, boolean autoClick) {
        super(LayoutInflater.from(mParent.getContext()).inflate(layoutId, mParent, false));
        this.dealBindView = dealBindView;
        this.autoClick = autoClick;
    }

    @CallSuper
    public void onCreateViewHolder(View itemView) {
        if (dealBindView) {
            QuickBind.bind(this);
        }
        if (autoClick) {
            itemView.setOnClickListener(v -> {
                if (mQuickAdapter != null && mQuickAdapter.getQuickAdapterListener() != null) {
                    ((QuickAdapterListener<T>)mQuickAdapter.getQuickAdapterListener()).onItemClick(
                            v, getViewData(), getAdapterPosition()
                    );
                }
            });
        }
    }

    @CallSuper
    public void onBindViewHolder(QuickAdapter adapter, @NonNull T data, int position) {
        this.mQuickAdapter = adapter;
    }

    public QuickAdapter getQuickAdapter() {
        return mQuickAdapter;
    }

    public @Nullable
    QuickAdapterListener<QuickItemData> getAdapterClickListener() {
        if (this.mQuickAdapter == null || this.mQuickAdapter.getQuickAdapterListener() == null)
            return null;
        return (QuickAdapterListener<QuickItemData>) this.mQuickAdapter.getQuickAdapterListener();
    }

    public void itemViewClick(View clickView) {
        if (getAdapterClickListener() != null) {
            getAdapterClickListener().onItemClick(clickView, getViewData(), getAdapterPosition());
        }
    }

    public void setAutoClick(boolean autoClick) {
        this.autoClick = autoClick;
    }

    public boolean isAutoClick() {
        return autoClick;
    }

    public View getItemView() {
        return itemView;
    }

    public @Nullable
    T getViewData() {
        if (mQuickAdapter == null || mQuickAdapter.getData() == null) return null;
        return (T) mQuickAdapter.getData().get(getAdapterPosition());
    }
}
