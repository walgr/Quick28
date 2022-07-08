package com.wpf.app.quickbind.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by 王朋飞 on 2022/7/8.
 */
public interface RunOnHolder<Data> {

    Data run(RecyclerView.ViewHolder viewHolder);
}
