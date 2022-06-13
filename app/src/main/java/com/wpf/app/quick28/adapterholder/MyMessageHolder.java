package com.wpf.app.quick28.adapterholder;

import android.view.ViewGroup;

import com.wpf.app.quick.base.widgets.recyclerview.QuickAdapter;
import com.wpf.app.quick.base.widgets.recyclerview.QuickViewBindingHolder;
import com.wpf.app.quick28.R;
import com.wpf.app.quick28.databinding.HolderMessageMyBinding;
import com.wpf.app.quick28.model.MyMessage;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
public class MyMessageHolder extends QuickViewBindingHolder<MyMessage, HolderMessageMyBinding> {

    public MyMessageHolder(ViewGroup mParent) {
        super(mParent, R.layout.holder_message_my);
    }

    @Override
    public void onBindViewHolder(QuickAdapter adapter, MyMessage data, int position) {

    }
}
