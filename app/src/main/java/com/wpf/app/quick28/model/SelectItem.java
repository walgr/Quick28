package com.wpf.app.quick28.model;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Toast;

import com.wpf.app.quick.annotations.BindData2View;
import com.wpf.app.quick.base.helper.binddatahelper.ItemClick;
import com.wpf.app.quick.base.helper.binddatahelper.Select2CheckBox;
import com.wpf.app.quick.base.helper.binddatahelper.Text2TextView;
import com.wpf.app.quick.base.widgets.recyclerview.QuickSelectData;
import com.wpf.app.quick28.R;
import com.wpf.app.quickbind.interfaces.RunItemClick;
import com.wpf.app.quickbind.interfaces.RunOnHolder;

/**
 * Created by 王朋飞 on 2022/7/8.
 */
public class SelectItem extends QuickSelectData {

    public SelectItem() {
        super(R.layout.holder_select_item);
    }

    @SuppressLint("NonConstantResourceId")
    @BindData2View(id = R.id.select, helper = Select2CheckBox.class)
    boolean isSelect = true;

    @SuppressLint("NonConstantResourceId")
    @BindData2View(id = R.id.title, helper = Text2TextView.class)
    RunOnHolder<CharSequence> title = () -> "Title " + getViewHolder().getAdapterPosition();

    @BindData2View(helper = ItemClick.class)
    RunItemClick itemClick = () -> v -> {
        isSelect = !isSelect;
        Toast.makeText(v.getContext(), "点击" + title.run().toString(), Toast.LENGTH_SHORT).show();
        getViewHolder().getQuickAdapter().notifyItemChanged(getViewHolder().getAdapterPosition());
    };
}
