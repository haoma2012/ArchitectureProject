package com.mobike.javaarchitecture.reflect;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/23 下午1:46
 */
@TestAnotation(ip = "111", port = 8888, database = "test.db",
        encoding = "utf-8", loginName = "root", password = "123456")
public class DBAnatation {
    public static void main(String[] args) {

        // 反射获取注解
        boolean hasAnotation = DBAnatation.class.isAnnotationPresent(TestAnotation.class);
        System.out.println("hasAnotation:" + hasAnotation);
        if (hasAnotation) {
            TestAnotation methodInfo = DBAnatation.class.getAnnotation(TestAnotation.class);

            System.out.println("author:" + methodInfo.ip());
            System.out.println("data:" + methodInfo.database());
        }
    }
}
