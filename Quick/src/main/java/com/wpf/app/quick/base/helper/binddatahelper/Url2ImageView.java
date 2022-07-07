package com.wpf.app.quick.base.helper.binddatahelper;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wpf.app.quick.annotations.BindD2VBaseHelper;

/**
 * Created by 王朋飞 on 2022/7/6.
 */
public class Url2ImageView implements BindD2VBaseHelper<ImageView, String> {

    @Override
    public void initView(@NonNull ImageView view, @NonNull String url) {
        if (view.getContext() == null) return;
        Activity activity = (Activity) view.getContext();
        if (activity.isDestroyed() || activity.isFinishing()) return;
        Glide.with(activity)
                .load(url)
                .into(view);
    }
}
