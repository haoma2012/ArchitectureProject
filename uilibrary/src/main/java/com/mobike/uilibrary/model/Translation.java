package com.mobike.uilibrary.model;

/**
 * 金山词霸API model
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-05 10:30
 */
public class Translation {
    private int status;

    private content content;

    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private String ciba_use;
        private String ciba_out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {
        System.out.println("Rxjava翻译结果：" + status);
        System.out.println("Rxjava翻译结果：" + content.from);
        System.out.println("Rxjava翻译结果：" + content.to);
        System.out.println("Rxjava翻译结果：" + content.vendor);
        System.out.println("Rxjava翻译结果：" + content.out);
        System.out.println("Rxjava翻译结果：" + content.errNo);
    }
}
