package com.wpf.app.quick.base.widgets.recyclerview;

import android.databinding.ViewDataBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindHolder {
    Class<? extends QuickViewBindingHolder<? extends QuickViewDataBinding<? extends ViewDataBinding>, ? extends ViewDataBinding>> holderClass();
}