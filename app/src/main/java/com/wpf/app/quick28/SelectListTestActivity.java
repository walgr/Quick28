package com.wpf.app.quick28;

import android.annotation.SuppressLint;
import android.view.View;

import com.wpf.app.quick.annotations.BindData2View;
import com.wpf.app.quick.annotations.BindView;
import com.wpf.app.quick.base.activity.QuickActivity;
import com.wpf.app.quick.base.helper.binddatahelper.ItemClick;
import com.wpf.app.quick.base.widgets.recyclerview.QuickRecyclerView;
import com.wpf.app.quick28.model.SelectItem;
import com.wpf.app.quickbind.interfaces.RunItemClick;

/**
 * Created by 王朋飞 on 2022/7/8.
 */
public class SelectListTestActivity extends QuickActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.list)
    QuickRecyclerView list;

    @BindData2View(id = R.id.btnClean, helper = ItemClick.class)
    RunItemClick btnClean = () -> this::clean;

    @BindData2View(id = R.id.btnAdd, helper = ItemClick.class)
    RunItemClick btnAdd = () -> this::addMessage;

    public SelectListTestActivity() {
        super(R.layout.activity_recyclerview_test, "选择列表页");
    }

    @Override
    public void initView() {

    }

    public void clean(View view) {
        list.getQuickAdapter().cleanAll();
    }

    public void addMessage(View view) {
        list.getQuickAdapter().addData(new SelectItem());
        list.getQuickAdapter().notifyDataSetChanged();
    }
}
