package com.wpf.app.quickbind.annotations;

import com.wpf.app.quickbind.interfaces.BindBaseFragment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 王朋飞 on 2022/6/15.
 * 自动组装Fragments到ViewPager中
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindFragments {
    Class<? extends BindBaseFragment>[] fragments();
    boolean withState() default true;
    int limit() default 0;
}
