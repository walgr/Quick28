package com.wpf.app.quick.base.helper;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wpf.app.quickbind.BindBaseHelper;

/**
 * Created by 王朋飞 on 2022/7/6.
 */
public class GlideDataImage implements BindBaseHelper<ImageView, String> {

    @Override
    public void initView(ImageView view, String url) {
        if (view == null || view.getContext() == null) return;
        Activity activity = (Activity) view.getContext();
        if (activity.isDestroyed() || activity.isFinishing()) return;
        Glide.with(activity)
                .load(url)
                .into(view);
    }
}
