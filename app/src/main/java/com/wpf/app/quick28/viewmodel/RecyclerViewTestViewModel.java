package com.wpf.app.quick28.viewmodel;

import android.support.annotation.Nullable;
import android.view.View;

import com.wpf.app.quick.base.viewmodel.BindingViewModel;
import com.wpf.app.quick.base.widgets.recyclerview.QuickItemData;
import com.wpf.app.quick28.databinding.ActivityRecyclerviewTestBinding;
import com.wpf.app.quick28.model.BindDataTestModel;

import java.util.List;
import java.util.function.Function;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
public class RecyclerViewTestViewModel extends BindingViewModel<ActivityRecyclerviewTestBinding> {

    @Override
    public void onBindingCreate(@Nullable ActivityRecyclerviewTestBinding mViewBinding) {

    }

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
