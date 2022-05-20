package com.wpf.app.quick.base.widgets.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

import com.wpf.app.quick.base.constant.BRConstant;

import java.util.Map;
import java.util.Set;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class QuickViewBindingHolder<T extends QuickItemDataBinding<VB>, VB extends ViewDataBinding>
extends QuickViewHolder<T> {

    protected @LayoutRes int layoutId;

    protected T mViewData;

    protected Map<Integer, Object> variableBinding;

    QuickViewBindingHolder(ViewGroup mParent, @LayoutRes int layoutId, T mViewData) {
        super(mParent, layoutId);
        this.layoutId = layoutId;
        this.mViewData = mViewData;
    }

    public void setViewData(T viewData) {
        mViewData = viewData;
    }

    protected VB mViewBinding;

    @Override
    public void onCreateViewHolder(View itemView) {
        mViewBinding = DataBindingUtil.bind(itemView);
        mViewData.onCreateHolderEnd(this);
        onCreateHolderEnd(itemView);
    }

    public void onCreateHolderEnd(View itemView) {

    }

    @Override
    public void onBindViewHolder(QuickAdapter adapter, T data, int position) {
        setQuickAdapterListener((QuickAdapterListener<T>) adapter.getQuickAdapterListener());
        if (mViewBinding != null) {
            mViewBinding.setVariable(BRConstant.data, data);
            mViewBinding.setVariable(BRConstant.adapter, adapter);
            mViewBinding.setVariable(BRConstant.position, position);
            if (variableBinding != null) {
                Set<Integer> kes = variableBinding.keySet();
                for (Integer key : kes) {
                    mViewBinding.setVariable(key, variableBinding.get(key));
                }
            }
            mViewBinding.executePendingBindings();
        }
    }
}
