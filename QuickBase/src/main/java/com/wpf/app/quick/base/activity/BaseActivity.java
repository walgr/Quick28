package com.wpf.app.quick.base.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wpf.app.quick.base.helper.annotations.QuickBindHelper;
import com.wpf.app.quick.base.utils.ActivityUtils;
import com.wpf.app.quick.base.widgets.viewpager.ViewPagerSize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by 王朋飞 on 2022/5/20.
 * layoutId、layoutView 必须有1个，同时存在时layoutView有效
 */
public class BaseActivity extends AppCompatActivity implements BaseView {

    protected @LayoutRes
    int layoutId;
    private View layoutView;
    String activityTitle = "";

    BaseActivity(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    BaseActivity(View layoutView) {
        this.layoutView = layoutView;
    }

    public BaseActivity(int layoutId, String activityTitle) {
        this.layoutId = layoutId;
        this.activityTitle = activityTitle;
    }

    public BaseActivity(View layoutView, String activityTitle) {
        this.layoutView = layoutView;
        this.activityTitle = activityTitle;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dealContentView();
        QuickBindHelper.bind(this);
        initView();
        setTitle();
    }

    @Override
    public void initView() {

    }

    private void setTitle() {
        if (activityTitle != null && !activityTitle.isEmpty()) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(activityTitle);
            }
        }
    }

    protected void dealContentView() {
        if (layoutId != 0) {
            setContentView(layoutId);
        } else if (layoutView != null) {
            setContentView(layoutView);
        }
    }

    public <T extends Activity> void startActivity(Class<T> activityCls) {
        startActivity(activityCls, null);
    }

    public <T extends Activity> void startActivity(Class<T> activityCls, Map<String, Object> data) {
        ActivityUtils.startActivity(this, activityCls, data);
    }
}
