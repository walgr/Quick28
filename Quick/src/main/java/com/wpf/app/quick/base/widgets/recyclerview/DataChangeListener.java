package com.wpf.app.quick.base.widgets.recyclerview;

import com.wpf.app.quick.base.utils.Callback;


/**
 * Created by 王朋飞 on 2022/7/8.
 */
public interface DataChangeListener<Request extends RequestData, Data extends QuickItemData> {

    /**
     * 下拉刷新
     */
    void onRefresh(Request requestData, Callback<Data> callback);

    /**
     * 上拉加载
     */
    void onLoadMore(Request requestData, Callback<Data> callback);

    /**
     * 刷新结束
     */
    default void refreshFinish() {

    }

    /**
     * 加载结束
     */
    default void loadMoreFinish() {

    }
}
