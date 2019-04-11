package com.mobike.uilibrary.widget;

import android.webkit.WebView;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.base.BaseActivity;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/11 下午2:48
 */
public class UIWebViewActivity extends BaseActivity {
    private static final String TAG =UIWebViewActivity.class.getSimpleName();
    private WebView mWebView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_ui_webview;

    }

    @Override
    protected void initView() {

        mWebView = findViewById(R.id.web_content);
    }
}
