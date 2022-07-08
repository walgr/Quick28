package com.wpf.app.quickbind.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 王朋飞 on 2022/6/13.
 * 绑定Sp到TextView上
 * bindSp 2者改变同步
 * setSp view -> sp
 * getSp sp -> view
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindSp2View {
    String bindSp() default "";
    String setSp() default "";
    String getSp() default "";
    String defaultValue() default "";
}
