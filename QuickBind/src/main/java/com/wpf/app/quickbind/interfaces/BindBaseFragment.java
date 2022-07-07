package com.wpf.app.quickbind.interfaces;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by 王朋飞 on 2022/7/5.
 */
public abstract class BindBaseFragment extends Fragment {

    /**
     * 从ViewModelActivity获取数据
     */
    public @NonNull
    Bundle getInitBundle(BindViewModel viewModelActivity, int position) {
        return null;
    }

    /**
     * 从Activity获取数据
     */
    public @NonNull Bundle getInitBundle(Activity activity, int position) {
        return null;
    }

    /**
     * 从Fragment获取数据
     */
    public @NonNull Bundle getInitBundle(Fragment fragment, int position) {
        return null;
    }

    @NonNull
    public abstract String getTitle();
}
