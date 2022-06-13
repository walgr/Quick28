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
public class QuickViewBindingHolder<VM extends QuickBindingData<VB>, VB extends ViewDataBinding> extends QuickViewHolder<VM> {

    protected @LayoutRes
    int layoutId;

    protected VM mViewData;

    protected Map<Integer, Object> variableBinding;

    public QuickViewBindingHolder(ViewGroup mParent, @LayoutRes int layoutId) {
        super(mParent, layoutId);
        this.layoutId = layoutId;
    }

    public QuickViewBindingHolder(ViewGroup mParent, @LayoutRes int layoutId, boolean dealBindView) {
        super(mParent, layoutId, dealBindView);
        this.layoutId = layoutId;
    }

    public void setViewData(VM viewData) {
        mViewData = viewData;
    }

    protected VB mViewBinding;

    @Override
    public void onCreateViewHolder(View itemView) {
        super.onCreateViewHolder(itemView);
        mViewBinding = DataBindingUtil.bind(itemView);
        mViewData.onCreateHolderEnd(this);
        onCreateHolderEnd(itemView);
    }

    public void onCreateHolderEnd(View itemView) {

    }

    @Override
    public void onBindViewHolder(QuickAdapter adapter, VM data, int position) {
        setQuickAdapterListener((QuickAdapterListener<VM>) adapter.getQuickAdapterListener());
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

    public VM getViewData() {
        return mViewData;
    }
}
