package com.wpf.app.quick28.viewmodel;

import android.annotation.SuppressLint;

import com.wpf.app.quick.base.helper.binddatahelper.Text2TextView;
import com.wpf.app.quick.base.viewmodel.BindingViewModel;
import com.wpf.app.quick28.R;
import com.wpf.app.quick28.databinding.ActivityMainBinding;
import com.wpf.app.quickbind.annotations.BindData2View;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
public class MainViewModel extends BindingViewModel<ActivityMainBinding> {

    @SuppressLint("NonConstantResourceId")
    @BindData2View(id = R.id.spTextView1, helper = Text2TextView.class)
    String title = "测试BindData2View";
}
