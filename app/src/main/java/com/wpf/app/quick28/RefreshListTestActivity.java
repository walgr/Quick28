package com.wpf.app.quick28;

import android.annotation.SuppressLint;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.wpf.app.quick.annotations.BindView;
import com.wpf.app.quick.base.activity.QuickActivity;
import com.wpf.app.quick.base.utils.Callback;
import com.wpf.app.quick.base.widgets.recyclerview.DataChangeListener;
import com.wpf.app.quick.base.widgets.recyclerview.QuickItemData;
import com.wpf.app.quick.base.widgets.recyclerview.QuickRefreshRecyclerView;
import com.wpf.app.quick.base.widgets.recyclerview.RequestData;
import com.wpf.app.quick28.model.RefreshItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王朋飞 on 2022/7/8.
 */
public class RefreshListTestActivity extends QuickActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.list)
    QuickRefreshRecyclerView mRecyclerView;

    public RefreshListTestActivity() {
        super(R.layout.activity_refresh_list, "列表刷新页");
    }

    @Override
    public void initView() {
        mRecyclerView.setDataChangeListener(new DataChangeListener<RequestData, RefreshItem>() {
            @Override
            public void onRefresh(RequestData requestData, Callback<RefreshItem> callback) {
                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mSmartRefreshLayout.post(() -> {
                        mSmartRefreshLayout.finishRefresh();
                        callback.callback(new ArrayList<RefreshItem>() {{
                            add(new RefreshItem());
                            add(new RefreshItem());
                        }});
                    });
                }).start();

            }

            @Override
            public void onLoadMore(RequestData requestData, Callback<RefreshItem> callback) {
                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mSmartRefreshLayout.post(() -> {
                        mSmartRefreshLayout.finishLoadMore();
                        callback.callback(new ArrayList<RefreshItem>() {{
                            add(new RefreshItem());
                            add(new RefreshItem());
                            add(new RefreshItem());
                        }});
                    });
                }).start();
            }
        });
        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> mRecyclerView.onRefresh());
        mSmartRefreshLayout.setOnLoadMoreListener(loadMore -> mRecyclerView.onLoadMore());
        mSmartRefreshLayout.autoRefresh();
    }
}
