package com.wpf.app.quick28;

import com.wpf.app.quick.base.activity.ViewModelBindingActivity;
import com.wpf.app.quick28.databinding.ActivityRecyclerviewTestBinding;
import com.wpf.app.quick28.viewmodel.RecyclerViewTestViewModel;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
public class RecyclerViewTestActivity extends ViewModelBindingActivity<RecyclerViewTestViewModel, ActivityRecyclerviewTestBinding> {

    public RecyclerViewTestActivity() {
        super(R.layout.activity_recyclerview_test, "列表测试页");
    }
}
