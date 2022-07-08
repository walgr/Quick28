package com.wpf.app.quick.base.utils;

import com.wpf.app.quick.base.widgets.recyclerview.QuickItemData;

import java.util.List;

/**
 * Created by 王朋飞 on 2022/7/8.
 */
public interface Callback<Data extends QuickItemData> {

    void callback(List<Data> data);
}
