package com.wpf.app.quick.base.widgets.recyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
public class QuickAdapter extends RecyclerView.Adapter<QuickViewHolder<QuickItemData>> {

    private ArrayList<QuickItemData> mDataList;

    private QuickAdapterListener<? extends QuickItemData> mQuickAdapterListener;

    public void setNewData(@Nullable List<? extends QuickItemData> mDataList) {
        cleanAll();
        appendList(mDataList);
        notifyDataSetChanged();
    }

    public void cleanAll() {
        if (this.mDataList != null) {
            this.mDataList.clear();
        }
        notifyDataSetChanged();
    }

    public void appendList(@Nullable List<? extends QuickItemData> mDataList) {
        if (mDataList == null) return;
        if (this.mDataList == null) {
            this.mDataList = new ArrayList<>();
        }
        this.mDataList.addAll(mDataList);
    }

    public void addData(@NonNull QuickItemData data) {
        if (this.mDataList == null) {
            this.mDataList = new ArrayList<>();
        }
        this.mDataList.add(data);
    }

    public void addData(int pos, @NonNull QuickItemData data) {
        if (this.mDataList == null) {
            this.mDataList = new ArrayList<>();
        }
        this.mDataList.add(pos, data);
    }

    public @Nullable
    ArrayList<QuickItemData> getData() {
        return this.mDataList;
    }

    public @Nullable
    <T extends QuickItemData> ArrayList<T> getRealTypeData() {
        return (ArrayList<T>) this.mDataList;
    }

    @NonNull
    @Override
    public QuickViewHolder<QuickItemData> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        QuickItemData findData = findDataByViewType(i);
        if (findData instanceof QuickViewDataBinding) {
            BindBindingHolder holderAnnotationClass = findData.getClass().getAnnotation(BindBindingHolder.class);
            if (holderAnnotationClass != null) {
                try {
                    Constructor<?> bindingHolderCls = holderAnnotationClass.value().getConstructor(ViewGroup.class);
                    QuickViewBindingHolder bindingHolder = (QuickViewBindingHolder) bindingHolderCls.newInstance(viewGroup);
                    bindingHolder.setViewData((QuickViewDataBinding) findData);
                    bindingHolder.onCreateViewHolder(bindingHolder.itemView);
                    return bindingHolder;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (findData != null) {
            BindHolder holderAnnotation = findData.getClass().getAnnotation(BindHolder.class);
            QuickViewHolder<? extends QuickItemData> holder = null;

            if (holderAnnotation == null) {
                if (findData instanceof QuickBindData) {
                    holder = new QuickViewHolder<>(viewGroup, ((QuickBindData) findData).layoutId);
                } else {
                    throw new RuntimeException("当前数据类未使用HolderClass注解指定ViewHolder");
                }
            } else {
                try {
                    Class<?> holderCls = holderAnnotation.value();
                    Constructor<?> holderConstructor = holderCls.getConstructor(ViewGroup.class);
                    holder = (QuickViewHolder<? extends QuickItemData>) holderConstructor.newInstance(viewGroup);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            holder.onCreateViewHolder(holder.itemView);
//            if (findData instanceof QuickBindData) {
//                ((QuickBindData) findData).onCreateViewHolder((QuickViewHolder<? extends QuickBindData>)holder);
//            }
            return (QuickViewHolder<QuickItemData>) holder;
        }
        return null;
    }

    private @Nullable
    QuickItemData findDataByViewType(int viewType) {
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
