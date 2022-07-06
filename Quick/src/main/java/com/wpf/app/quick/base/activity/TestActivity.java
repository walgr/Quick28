package com.wpf.app.quick.base.activity;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.wpf.app.quick.annotations.BindView;
import com.wpf.app.quick.annotations.SaveId;
import com.wpf.app.quick.base.R;
import com.wpf.app.quick.base.R2;
import com.wpf.app.quick.runtime.internal.Utils;

/**
 * Created by 王朋飞 on 2022/6/16.
 */
public class TestActivity extends BaseActivity {

    @SuppressLint("NonConstantResourceId")
    @SaveId(R2.id.recyclerView)
    RecyclerView mRecyclerView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R2.id.info)
    TextView info;

    public TestActivity() {
        super(R.layout.fragment_recyclerview, "测试R2");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initView() {
        super.initView();
        info.setText("mRecyclerView:" + mRecyclerView);
        Log.e("TestActivity", "view=" + mRecyclerView + " R.id=" + R.id.recyclerView + " R2.id=" + R2.id.recyclerView + " name:" + Utils.getResourceEntryName(getWindow().getDecorView(), R.id.recyclerView));
    }
}
