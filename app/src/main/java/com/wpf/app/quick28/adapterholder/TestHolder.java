package com.wpf.app.quick28.adapterholder;

import android.annotation.SuppressLint;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wpf.app.quick.base.helper.annotations.FindView;
import com.wpf.app.quick.base.widgets.recyclerview.QuickAdapter;
import com.wpf.app.quick.base.widgets.recyclerview.QuickViewHolder;
import com.wpf.app.quick28.R;
import com.wpf.app.quick28.model.TestModel;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
public class TestHolder extends QuickViewHolder<TestModel> {

    @SuppressLint("NonConstantResourceId")
    @FindView(id = R.id.txt)
    TextView txt= null;

    public TestHolder(ViewGroup mParent) {
        super(mParent, R.layout.holder_test, true);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(QuickAdapter adapter, TestModel data, int position) {
        txt.postDelayed(() -> {
            txt.setText(System.currentTimeMillis() + "");
        }, 1000);
    }
}
