package com.wpf.app.quick.base.helper;

import android.databinding.BindingAdapter;
import android.support.annotation.Nullable;

import com.wpf.app.quick.base.widgets.recyclerview.QuickItemData;
import com.wpf.app.quick.base.widgets.recyclerview.QuickRecyclerView;
import com.wpf.app.quick.base.widgets.recyclerview.QuickSelectData;

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
    public static void selectList(QuickRecyclerView list, @Nullable ArrayList<QuickSelectData> selectList) {
        if (list.getQuickAdapter().getRealTypeData() == null || selectList == null) return;
        for (QuickItemData listData : list.getQuickAdapter().getRealTypeData()) {
            if (!(listData instanceof QuickSelectData)) continue;
            QuickSelectData listItem = (QuickSelectData) listData;
            QuickSelectData selectItem = null;
            for (QuickSelectData select : selectList) {
                if (select.getId().equals(listItem.getId())) {
                    selectItem = listItem;
                    break;
                }
            }
            if (selectItem != null) {
                selectItem.setSelect(true);
            }
        }
    }

    /**
     * 根据id设置选中
     */
    @BindingAdapter("selectIdList")
    public static void selectIdList(QuickRecyclerView list, List<String> selectList) {
        if (list.getQuickAdapter().getData() == null) return;
        ArrayList<QuickItemData> adapterList = list.getQuickAdapter().getData();
        for (QuickItemData listData : adapterList) {
            if (!(listData instanceof QuickSelectData)) return;
            QuickSelectData selectData = (QuickSelectData) listData;
            if (selectList.contains(selectData.getId())) {
                selectData.setSelect(true);
            }
        }
    }
}
