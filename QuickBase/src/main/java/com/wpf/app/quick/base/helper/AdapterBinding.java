package com.wpf.app.quick.base.helper;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BindingAdapter;
import android.support.annotation.Nullable;

import com.wpf.app.quick.base.widgets.recyclerview.QuickItemData;
import com.wpf.app.quick.base.widgets.recyclerview.QuickRecyclerView;

import java.util.ArrayList;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class AdapterBinding {

    @BindingAdapter("setData")
    public static void setData(QuickRecyclerView mRecyclerView, @Nullable ArrayList<QuickItemData> data) {
        mRecyclerView.getQuickAdapter().setNewData(data);
    }


    @BindingAdapter("selectList")
    public static void selectList(QuickRecyclerView mRecyclerView, @Nullable ArrayList<QuickItemData> data) {

    }

    /**
     * 根据id设置选中
     */
    @BindingAdapter("selectIdList")
    public static void selectIdList(QuickRecyclerView mRecyclerView, MutableLiveData<ArrayList<String>> selectList) {

    }
}
