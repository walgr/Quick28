package com.wpf.app.quick.base.widgets.recyclerview;

/**
 * Created by 王朋飞 on 2022/5/20.
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindHolder {
    Class<? extends QuickViewHolder<? extends QuickItemData>> value();
}
