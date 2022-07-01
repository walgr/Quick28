package com.wpf.app.quick.base.helper.annotations;

import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 王朋飞 on 2022/6/13.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindSp2View {
    @IdRes int id();
    String bindSp() default "";
    String setSp() default "";
    String getSp() default "";
    String defaultValue() default "";
}
