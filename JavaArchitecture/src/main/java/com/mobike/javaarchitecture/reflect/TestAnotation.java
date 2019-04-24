package com.mobike.javaarchitecture.reflect;

import java.lang.annotation.*;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/23 下午1:06
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TestAnotation {
    String ip();

    int port() default 3306;

    String database();

    String encoding();

    String loginName();

    String password();
}
