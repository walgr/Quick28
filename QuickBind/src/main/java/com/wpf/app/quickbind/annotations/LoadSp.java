package com.wpf.app.quickbind.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 王朋飞 on 2022/6/17.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoadSp {
    String fileName() default "QuickViewSpBindFile";
    String key();
    String defaultValue() default "";
}
