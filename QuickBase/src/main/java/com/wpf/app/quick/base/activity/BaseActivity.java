package com.wpf.app.quick.base.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 王朋飞 on 2022/5/20.
 * layoutId、layoutView 必须有1个，同时存在时layoutView有效
 */
public class BaseActivity extends AppCompatActivity implements BaseView {

    protected @LayoutRes
    int layoutId;
    private View layoutView;

    BaseActivity() {

    }

    BaseActivity(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    BaseActivity(View layoutView) {
        this.layoutView = layoutView;
    }

    BaseActivity(@LayoutRes int layoutId, View layoutView) {
        this.layoutId = layoutId;
        this.layoutView = layoutView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dealContentView();
        initView();
    }

    @Override
    public void initView() {

    }

    protected void dealContentView() {
        if (layoutId != 0) {
            setContentView(layoutId);
        } else if (layoutView != null) {
            setContentView(layoutView);
        }
    }
}
