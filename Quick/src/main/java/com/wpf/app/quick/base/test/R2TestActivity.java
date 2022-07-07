package com.wpf.app.quick.base.test;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.wpf.app.quick.annotations.BindData2View;
import com.wpf.app.quick.annotations.BindView;
import com.wpf.app.quick.annotations.GroupView;
import com.wpf.app.quick.base.R;
import com.wpf.app.quick.base.R2;
import com.wpf.app.quick.base.activity.BaseActivity;
import com.wpf.app.quick.base.helper.binddatahelper.Text2TextView;
import com.wpf.app.quickbind.utils.GroupViews;

/**
 * Created by 王朋飞 on 2022/6/16.
 */
public class R2TestActivity extends BaseActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R2.id.recyclerView)
    RecyclerView mRecyclerView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R2.id.info)
    TextView info;

    @GroupView(idList = {R2.id.recyclerView, R2.id.info})
    GroupViews mGroupViews;

    @SuppressLint("NonConstantResourceId")
    @BindData2View(id = R2.id.info, helper = Text2TextView.class)
    String title = "测试R2";

    public R2TestActivity() {
        super(R.layout.fragment_recyclerview, "测试R2");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initView() {
        super.initView();

        info.postDelayed(() -> mGroupViews.goneAll(), 1000);
    }
}
