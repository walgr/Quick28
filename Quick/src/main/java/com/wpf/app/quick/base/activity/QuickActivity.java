package com.wpf.app.quick.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wpf.app.quickbind.QuickBind;
import com.wpf.app.quick.base.utils.ActivityUtils;
import java.util.Map;

/**
 * Created by 王朋飞 on 2022/5/20.
 * layoutId、layoutView 必须有1个，同时存在时layoutView有效
 */
public abstract class QuickActivity extends AppCompatActivity implements QuickView {

    protected @LayoutRes
    int layoutId;
    private View layoutView;
    String activityTitle = "";

    public QuickActivity() {
    }

    public QuickActivity(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public QuickActivity(View layoutView) {
        this.layoutView = layoutView;
    }

    public QuickActivity(int layoutId, String activityTitle) {
        this.layoutId = layoutId;
        this.activityTitle = activityTitle;
    }

    public QuickActivity(View layoutView, String activityTitle) {
        this.layoutView = layoutView;
        this.activityTitle = activityTitle;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dealContentView();
        QuickBind.bind(this);
        initView();
        setTitle();
    }

    public abstract void initView();

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
