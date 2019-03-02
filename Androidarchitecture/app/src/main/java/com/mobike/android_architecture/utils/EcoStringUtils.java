package com.mobike.android_architecture.utils;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/1 下午5:49
 */
public class EcoStringUtils {
    /**
     * 空字符
     */
    public static final String EMPTY = "";
    public static final String COMMA = ",";

    public static final String ECO_HTTPS = "https";
    public static final String ECO_HTTP = "http";
    public static final String ECO_METHOD_GET = "get";
    public static final String ECO_METHOD_POST = "post";

    public static Spanned getHtmlSpanned(String htmlStr) {
        if (TextUtils.isEmpty(htmlStr)) {
            htmlStr = EMPTY;
        } else {
//            htmlStr = htmlStr.replaceAll("<a href", "<ahref href");
//            htmlStr = htmlStr.replaceAll("</a>", "</ahref>");
            htmlStr = htmlStr.replaceAll("<span", "<espan");
            htmlStr = htmlStr.replaceAll("</span", "</espan");
        }
        String html = "<html>" + htmlStr + "</html>";
        return Html.fromHtml(html, null, new HtmlTagHandler());
    }
}
