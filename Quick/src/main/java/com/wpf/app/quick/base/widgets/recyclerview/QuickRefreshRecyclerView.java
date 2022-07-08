package com.wpf.app.quick.base.widgets.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import com.wpf.app.quick.base.utils.Callback;

import java.util.List;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class QuickRefreshRecyclerView extends QuickRecyclerView {

    RequestData mRequestData = new RequestData(0);

    public QuickRefreshRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public QuickRefreshRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickRefreshRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected DataChangeListener mDataChangeListener;

    @Override
    protected void init() {
        super.init();

    }

    public void onRefresh() {
        mRequestData.refresh();
        if (mDataChangeListener != null) {
            mDataChangeListener.onRefresh(mRequestData, list -> {
                getQuickAdapter().setNewData(list);
                getQuickAdapter().notifyDataSetChanged();
            });
        }
    }

    public void onLoadMore() {
        mRequestData.loadMore();
        if (mDataChangeListener != null) {
            mDataChangeListener.onLoadMore(mRequestData, list -> {
                getQuickAdapter().appendList(list);
                getQuickAdapter().notifyItemRangeInserted(getQuickAdapter().getItemCount() - list.size(), list.size());
            });
        }
    }

    public DataChangeListener getDataChangeListener() {
        return mDataChangeListener;
    }

    public void setDataChangeListener(DataChangeListener dataChangeListener) {
        mDataChangeListener = dataChangeListener;
    }

    public RequestData getRequestData() {
        return mRequestData;
    }

    public void setRequestData(RequestData requestData) {
        mRequestData = requestData;
    }
}
