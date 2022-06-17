package com.wpf.app.quick.base.activity;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.wpf.app.quick.base.R;

/**
 * Created by 王朋飞 on 2022/6/16.
 */
public class TestActivity extends BaseActivity {

    RecyclerView mRecyclerView;

    public TestActivity() {
        super(R.layout.fragment_recyclerview, "测试R2");
    }

    @Override
    public void initView() {
        super.initView();

        Log.e("TestActivity" , " "+ mRecyclerView);
    }
}
