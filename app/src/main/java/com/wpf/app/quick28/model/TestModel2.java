package com.wpf.app.quick28.model;

import android.annotation.SuppressLint;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.wpf.app.quick.base.widgets.recyclerview.HolderBindingLayout;
import com.wpf.app.quick.base.widgets.recyclerview.QuickBindingData;
import com.wpf.app.quick.base.widgets.recyclerview.QuickViewBindingHolder;
import com.wpf.app.quick28.BR;
import com.wpf.app.quick28.R;
import com.wpf.app.quick28.databinding.HolderTest2Binding;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
@SuppressLint("NonConstantResourceId")
@HolderBindingLayout(layout = R.layout.holder_test2)
public class TestModel2 extends QuickBindingData<HolderTest2Binding> {

    @Bindable
    Boolean select2 = false;

    public void setSelect2(Boolean select2) {
        this.select2 = select2;
        notifyPropertyChanged(BR.select2);
    }
    @Bindable
    String title = "";

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }


    @Override
    public void onCreateHolderEnd(@NonNull  QuickViewBindingHolder<? extends QuickBindingData<HolderTest2Binding>, HolderTest2Binding> viewHolder) {
        super.onCreateHolderEnd(viewHolder);
        viewHolder.getItemView().setOnClickListener(v -> {
            if (viewHolder.getAdapterClickListener() == null) return;
            viewHolder.getAdapterClickListener().onItemClick(v, viewHolder.getViewData(), viewHolder.getAdapterPosition());
        });
    }

    @Override
    public String toString() {
        return "TestModel2{" +
                "select2=" + select2 +
                ", title='" + title + '\'' +
                '}';
    }
}
