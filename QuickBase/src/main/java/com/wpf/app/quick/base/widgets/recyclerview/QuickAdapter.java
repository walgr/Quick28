package com.wpf.app.quick.base.widgets.recyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class QuickAdapter extends RecyclerView.Adapter<QuickViewHolder<QuickItemData>> {

    private ArrayList<QuickItemData> mDataList;

    private QuickAdapterListener<? extends QuickItemData> mQuickAdapterListener;

    public void setNewData(ArrayList<QuickItemData> mDataList) {
        this.mDataList = mDataList;
        notifyDataSetChanged();
    }

    public void addData(QuickItemData data) {
        if (this.mDataList == null) {
            this.mDataList = new ArrayList<>();
        }
        this.mDataList.add(data);
    }

    public void addData(int pos, QuickItemData data) {
        if (this.mDataList == null) {
            this.mDataList = new ArrayList<>();
        }
        this.mDataList.add(pos, data);
    }

    public void cleanAll() {
        this.mDataList.clear();
        this.mDataList = null;
        notifyDataSetChanged();
    }

    public @Nullable ArrayList<QuickItemData> getData() {
        return this.mDataList;
    }

    @NonNull
    @Override
    public QuickViewHolder<QuickItemData> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        QuickItemData findData = findDataByViewType(i);
        if (findData instanceof QuickItemDataBinding) {
            HolderBindingClass holderAnnotationClass = findData.getClass().getAnnotation(HolderBindingClass.class);
            HolderBindingLayout holderAnnotationLayout = findData.getClass().getAnnotation(HolderBindingLayout.class);
            if (holderAnnotationLayout != null) {
                QuickViewBindingHolder bindingHolder = new QuickViewBindingHolder<>(
                        viewGroup, holderAnnotationLayout.layout(), (QuickItemDataBinding)findData
                );
                bindingHolder.onCreateViewHolder(bindingHolder.itemView);
                return bindingHolder;
            }
            if (holderAnnotationClass != null) {
                try {
                    Constructor<?> bindingHolderCls = holderAnnotationClass.holderClass().getConstructor(ViewGroup.class);
                    QuickViewBindingHolder bindingHolder = (QuickViewBindingHolder) bindingHolderCls.newInstance(viewGroup);
                    bindingHolder.setViewData((QuickItemDataBinding)findData);
                    bindingHolder.onCreateViewHolder(bindingHolder.itemView);
                    return bindingHolder;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
        HolderClass holderAnnotation = findData.getClass().getAnnotation(HolderClass.class);
        if (holderAnnotation == null) {
            throw new RuntimeException("当前数据类未使用HolderClass注解指定ViewHolder");
        }
        Class<?> holderCls = holderAnnotation.holderClass();
        try {
            Constructor<?> holderConstructor = holderCls.getConstructor(ViewGroup.class);
            QuickViewHolder holder = (QuickViewHolder) holderConstructor.newInstance(viewGroup);
            holder.onCreateViewHolder(holder.itemView);
            return holder;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private @Nullable QuickItemData findDataByViewType(int viewType) {
        if (this.mDataList == null) return null;
        for (QuickItemData itemData : this.mDataList) {
            if (itemData.getViewType() == viewType) {
                return itemData;
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull QuickViewHolder<QuickItemData> quickItemDataQuickViewHolder, int i) {
        quickItemDataQuickViewHolder.onBindViewHolder(this, this.mDataList.get(i), i);
    }

    @Override
    public int getItemCount() {
        if (this.mDataList == null) return 0;
        return this.mDataList.size();
    }

    public QuickAdapterListener<? extends QuickItemData> getQuickAdapterListener() {
        return mQuickAdapterListener;
    }

    public void setQuickAdapterListener(QuickAdapterListener<? extends QuickItemData> quickAdapterListener) {
        mQuickAdapterListener = quickAdapterListener;
    }
}
