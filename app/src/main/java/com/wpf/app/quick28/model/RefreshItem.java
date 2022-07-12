package com.wpf.app.quick28.model;

import android.annotation.SuppressLint;
import android.widget.Toast;

import com.wpf.app.quick.annotations.BindData2View;
import com.wpf.app.quick.base.helper.binddatahelper.ItemClick;
import com.wpf.app.quick.base.helper.binddatahelper.Text2TextView;
import com.wpf.app.quick.base.widgets.recyclerview.QuickBindData;
import com.wpf.app.quickbind.interfaces.runItemClick;
import com.wpf.app.quick28.R;
import com.wpf.app.quickbind.interfaces.runOnView;

/**
 * Created by 王朋飞 on 2022/7/8.
 */
public class RefreshItem extends QuickBindData {

    public RefreshItem() {
        super(R.layout.holder_refresh_item);
    }

    @SuppressLint("NonConstantResourceId")
    @BindData2View(id = R.id.title, helper = Text2TextView.class)
    CharSequence title = "Title";

    @SuppressLint("NonConstantResourceId")
    @BindData2View(id = R.id.title, helper = Text2TextView.class)
    runOnView<CharSequence> title1 = (title) -> "Title " + getViewHolder().getAdapterPosition();

    @BindData2View(helper = ItemClick.class)
    runItemClick itemClick = () -> v ->
            Toast.makeText(v.getContext(), "点击" + getViewHolder().getAdapterPosition(), Toast.LENGTH_SHORT).show();
}
