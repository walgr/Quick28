package com.wpf.app.quick28;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.wpf.app.quick.annotations.GroupView;
import com.wpf.app.quick.base.activity.ViewModelBindingActivity;
import com.wpf.app.quick.base.helper.bind.GroupViews;
import com.wpf.app.quick28.databinding.ActivityDataTestBinding;
import com.wpf.app.quick28.viewmodel.IntentDataTestViewModel;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
public class IntentDataTestActivity extends ViewModelBindingActivity<IntentDataTestViewModel, ActivityDataTestBinding> {

    @SuppressLint("NonConstantResourceId")
    TextView title = null;

    @GroupView(idList = {R.id.title, R.id.title1})
    GroupViews textGroup = null;

    public IntentDataTestActivity() {
        super(R.layout.activity_data_test, "传输测试页");
    }

    @Override
    public void initView(ActivityDataTestBinding viewDataBinding) {
        super.initView(viewDataBinding);
        title.setText("传输测试页Activity");
        title.postDelayed(() -> textGroup.goneAll(), 1000);
    }
}
