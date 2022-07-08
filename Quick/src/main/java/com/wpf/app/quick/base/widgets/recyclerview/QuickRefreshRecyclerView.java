package com.wpf.app.quick.base.widgets.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

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

    protected DataChangeListener<? extends RequestData, ? extends QuickItemData> mDataChangeListener;

    @Override
    protected void init() {
        super.init();

    }

    public void onRefresh() {
        mRequestData.refresh();
        if (mDataChangeListener != null) {
            ((DataChangeListener<RequestData, ? extends QuickItemData>)mDataChangeListener).onRefresh(mRequestData, list -> {
                getQuickAdapter().setNewData(list);
                getQuickAdapter().notifyDataSetChanged();
            });
            mDataChangeListener.refreshFinish();
        }
    }

    public void onLoadMore() {
        mRequestData.loadMore();
        if (mDataChangeListener != null) {
            ((DataChangeListener<RequestData, ? extends QuickItemData>)mDataChangeListener).onLoadMore(mRequestData, list -> {
                getQuickAdapter().appendList(list);
                getQuickAdapter().notifyItemRangeInserted(getQuickAdapter().getItemCount() - list.size(), list.size());
            });
            mDataChangeListener.loadMoreFinish();
        }
    }

    public DataChangeListener<? extends RequestData, ? extends QuickItemData> getDataChangeListener() {
        return mDataChangeListener;
    }

    public <Request extends RequestData, Data extends QuickItemData> void
    setDataChangeListener(DataChangeListener<Request, Data> dataChangeListener) {
        mDataChangeListener = dataChangeListener;
    }

    public <Request extends RequestData> Request getRequestData() {
        return (Request) mRequestData;
    }

    public <Request extends RequestData> void setRequestData(Request requestData) {
        mRequestData = requestData;
    }
}
