package com.wpf.app.quick.base.widgets.recyclerview;

import android.databinding.BaseObservable;

/**
 * Created by 王朋飞 on 2022/5/20.
 * 快捷列表基础ItemData 有特殊要求时可指定viewType
 */
public class QuickItemData extends BaseObservable {
    private int viewType;

    public QuickItemData() {
    }

    public QuickItemData(int viewType) {
        this.viewType = viewType;
        initViewType();
    }

    public int getViewType() {
        return viewType;
    }

    private void initViewType() {
        if (viewType == 0) {
            viewType = Math.abs(getClass().getName().hashCode());
        }
    }
}
