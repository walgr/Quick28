package com.wpf.app.quick28.model;

import android.arch.lifecycle.MutableLiveData;

import com.wpf.app.quick.base.widgets.recyclerview.HolderBindingClass;
import com.wpf.app.quick.base.widgets.recyclerview.QuickViewDataBinding;
import com.wpf.app.quick28.adapterholder.TestHolder3;
import com.wpf.app.quick28.databinding.HolderTest3Binding;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
@HolderBindingClass(holderClass = TestHolder3.class)
public class TestModel3 extends QuickViewDataBinding<HolderTest3Binding> {

    MutableLiveData<String> text;

    public MutableLiveData<String> getText() {
        return text;
    }

    public TestModel3(MutableLiveData<String> text) {
        this.text = text;
    }
}
