package com.mobike.uilibrary.annotation;

import java.lang.annotation.*;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-05-08 13:02
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectJAnnotation {
    String value();
}
