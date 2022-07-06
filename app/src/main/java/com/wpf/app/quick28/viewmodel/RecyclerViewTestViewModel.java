package com.wpf.app.quick28.viewmodel;

import android.view.View;

import com.wpf.app.quick.base.viewmodel.BindingViewModel;
import com.wpf.app.quick28.databinding.ActivityRecyclerviewTestBinding;
import com.wpf.app.quick28.model.BindDataTestModel;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
public class RecyclerViewTestViewModel extends BindingViewModel<ActivityRecyclerviewTestBinding> {

    public void clean(View view) {
        getViewBinding().list.getQuickAdapter().cleanAll();
    }

    public void addMessage(View view) {
        getViewBinding().list.getQuickAdapter().addData(new BindDataTestModel());
        getViewBinding().list.getQuickAdapter().notifyDataSetChanged();
    }

    public void addOtherCome(View view) {

    }
}
