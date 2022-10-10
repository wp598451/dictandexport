package com.example.demo.user.common.note.annocation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dict {

    /**
     * 方法描述:  数据dataSource
     * @return 返回类型： String
     */
    String dicDataSource();

    /**
     * 方法描述:  这是返回后Put到josn中的文本 key 值
     * @return 返回类型： String
     */
    String dicText() default "";
}