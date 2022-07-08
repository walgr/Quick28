package com.wpf.app.quick28.model;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.wpf.app.quick.annotations.BindData2View;
import com.wpf.app.quick.base.helper.binddatahelper.Text2TextView;
import com.wpf.app.quick.base.widgets.recyclerview.QuickAdapter;
import com.wpf.app.quick.base.widgets.recyclerview.QuickBindData;
import com.wpf.app.quick.base.widgets.recyclerview.QuickSelectData;
import com.wpf.app.quick.base.widgets.recyclerview.QuickViewHolder;
import com.wpf.app.quickbind.interfaces.RunOnHolder;
import com.wpf.app.quick28.R;

/**
 * Created by 王朋飞 on 2022/7/8.
 */
public class RefreshItem extends QuickSelectData {

    public RefreshItem() {
        super(R.layout.holder_refresh_item);
    }

    @SuppressLint("NonConstantResourceId")
    @BindData2View(id = R.id.title, helper = Text2TextView.class)
    CharSequence title = "Title";

    @SuppressLint("NonConstantResourceId")
    @BindData2View(id = R.id.title, helper = Text2TextView.class)
    RunOnHolder<CharSequence> title1 = viewHolder -> "Title " + getViewHolder().getAdapterPosition();

    boolean isSelect = false;
}
