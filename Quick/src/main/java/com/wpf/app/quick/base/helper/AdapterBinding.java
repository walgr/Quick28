package com.wpf.app.quick.base.helper;

import android.databinding.BindingAdapter;
import android.support.annotation.Nullable;

import com.wpf.app.quick.base.widgets.recyclerview.QuickItemData;
import com.wpf.app.quick.base.widgets.recyclerview.QuickRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class AdapterBinding {

    @BindingAdapter("setData")
    public static void setData(QuickRecyclerView list, @Nullable ArrayList<QuickItemData> data) {
        list.getQuickAdapter().setNewData(data);
    }


    @BindingAdapter("selectList")
    public static void selectList(QuickRecyclerView list, @Nullable ArrayList<QuickItemData> selectList) {
        if (list.getQuickAdapter().getData() != null) {
            for (QuickItemData listData : list.getQuickAdapter().getData()) {
                QuickItemData selectItem = null;
                for (QuickItemData select: selectList) {
                    if (select.getId().equals(listData.getId())) {
                        selectItem = listData;
                        break;
                    }
                }
                if (selectItem != null) {
                    selectItem.getIsSelect().postValue(true);
                }
            }
        }
    }

    /**
     * 根据id设置选中
     */
    @BindingAdapter("selectIdList")
    public static void selectIdList(QuickRecyclerView list, List<String> selectList) {
        if (list.getQuickAdapter().getData() != null) {
            for (QuickItemData listData : list.getQuickAdapter().getData()) {
                if (selectList.contains(listData.getId())) {
                    listData.getIsSelect().postValue(true);
                }
            }
        }
    }
}
