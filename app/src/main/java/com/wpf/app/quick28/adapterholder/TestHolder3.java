package com.wpf.app.quick28.adapterholder;

import android.view.View;
import android.view.ViewGroup;

import com.wpf.app.quick.base.widgets.recyclerview.QuickViewBindingHolder;
import com.wpf.app.quick28.R;
import com.wpf.app.quick28.databinding.HolderTest3Binding;
import com.wpf.app.quick28.model.TestModel3;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
public class TestHolder3 extends QuickViewBindingHolder<TestModel3, HolderTest3Binding> {

    public TestHolder3(ViewGroup mParent) {
        super(mParent, R.layout.holder_test3);
    }

    @Override
    public void onCreateHolderEnd(View itemView) {
        super.onCreateHolderEnd(itemView);
        itemView.setOnClickListener(v-> {
            if (getAdapterClickListener() != null) {
                getAdapterClickListener().onItemClick(itemView, getViewData(), getAdapterPosition());
            }
        });
    }
}
