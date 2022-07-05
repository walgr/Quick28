package com.wpf.app.quick.base.widgets.recyclerview;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class QuickItemData extends BaseObservable {
    private String id;
    private MutableLiveData<Boolean> isSelect;
    private int viewType;

    public QuickItemData() {
    }

    public QuickItemData(int viewType) {
        this("", new MutableLiveData<>(), viewType);
    }

    public QuickItemData(String id, MutableLiveData<Boolean> isSelect, int viewType) {
        this.id = id;
        this.isSelect = isSelect;
        this.viewType = viewType;
        initViewType();
    }

    public String getId() {
        return id;
    }

    public MutableLiveData<Boolean> getIsSelect() {
        return isSelect;
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
