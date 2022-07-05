package com.wpf.app.quick.base.activity;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.wpf.app.quick.annotations.BindView;
import com.wpf.app.quick.base.R;
import com.wpf.app.quick.base.R2;

import butterknife.ButterKnife;

/**
 * Created by 王朋飞 on 2022/6/16.
 */
public class TestActivity extends BaseActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R2.id.recyclerView)
    RecyclerView mRecyclerView;

    public TestActivity() {
        super(R.layout.fragment_recyclerview, "测试R2");
    }

    @Override
    public void initView() {
//        ButterKnife.bind(this);
        super.initView();
        Log.e("TestActivity", "id=" + mRecyclerView + " R.id=" + R.id.recyclerView +" R2.id=" + R2.id.recyclerView);
    }
}
