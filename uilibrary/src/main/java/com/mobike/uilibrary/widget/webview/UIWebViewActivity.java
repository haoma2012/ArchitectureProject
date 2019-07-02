package com.mobike.uilibrary.widget.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.base.BaseActivity;
import com.mobike.uilibrary.utils.NetWorkStatusUtils;
import com.mobike.uilibrary.utils.ThirdPartyUtils;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/11 下午2:48
 */
public class UIWebViewActivity extends BaseActivity {
    private static final String TAG = UIWebViewActivity.class.getSimpleName();
    public static final String URL = "url";
    private WebView mWebView;
    private EcoWebChromeClient ecoWebChromeClient;
    private EcoWebViewClient ecoWebViewClient;

    private Context mContext;
    private String mOriginUrl;
    public String mUrl;
    public String mTitle;
    // 是否管理cookie
    private boolean isKeepCookie;
    // 自动播放
    private boolean isAutoPlay;

    private boolean isFirstLoad = false;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_ui_webview;
    }

    @Override
    protected void beforeInitView(@Nullable Bundle savedInstanceState) {
        super.beforeInitView(savedInstanceState);
        if (savedInstanceState != null) {
            mUrl = savedInstanceState.getString(URL);
        }
        mContext = getApplicationContext();
        // Adjust input method mode
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        getIntentData();
        initLogic(mUrl);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!TextUtils.isEmpty(mUrl) && outState != null) {
            outState.putString(URL, mUrl);
        }
    }

    @Override
    protected void initView() {
        mWebView = findViewById(R.id.web_content);
        mWebView.setActivated(true);

        initWebView(mWebView);
        initWebManager(mWebView);

        loadUrl();
    }

    private void getIntentData() {
        //windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        Bundle bundle = null;
        mUrl = intent.getStringExtra(URL);
        if (!TextUtils.isEmpty(mUrl)) {
            if (ThirdPartyUtils.isEmail(mUrl)) {
                finish();
                return;
            }
        } else {//use fragment bundle extra
            bundle = intent.getExtras();
            if (bundle != null) {
                mUrl = bundle.getString(URL);
                if (!TextUtils.isEmpty(mUrl) && ThirdPartyUtils.isEmail(mUrl)) {
                    finish();
                    return;
                }
            }
        }
        mOriginUrl = mUrl;
    }

    public void loadUrl() {
        if (mWebView == null || !mWebView.isActivated()) {
            //如果webview还没初始化好，就返回
            return;
        }
        Log.i(TAG, " mUrl = " + mUrl);
        if (!TextUtils.isEmpty(mUrl)) {
            mWebView.loadUrl(mUrl);
        }

//        boolean res = loadOrignUrl();
//        if (res) {
//            //请求url
////            loadUrl();
//            return;
//        }



//        if(!isFirstLoad) {
        //LogUtils.d(TAG, "访问URL:" + mFinalUrl);
//            if(mFinalUrl != null) {
//                isFirstLoad = true;
//                if(!mUseWebModule) {
//                    //web/pure协议只显示loadingview和打叉按钮，不显示进度条；调用xiyou://stopAnimation来隐藏loadingView和打叉按钮
//
//                    if(pbLoadProgress!=null)
//                        pbLoadProgress.setVisibility(View.VISIBLE);
//
//                    // 对/web/pure做特殊处理:不显示上方进度条，只显示中间的loadingView
//                    if (isFromWebPure) {
//                        pbLoadProgress.setVisibility(View.GONE);
//                        if (loadingView != null) {
//                            loadingView.setStatus(LoadingView.STATUS_LOADING);
//                            loadingView.setVisibility(View.VISIBLE);
//                        }
//                    }
//
//                    mWebView.loadUrl(mFinalUrl, mHashMapHeader);
//                }else{
//                    mWebModule.loadHtml(mWebView);
//                    // 模板加载的时候显示loadview
//                    if (loadingView != null) {
//                        loadingView.setStatus(LoadingView.STATUS_LOADING);
//                        loadingView.setVisibility(View.VISIBLE);
//                    }
//                    if(pbLoadProgress!=null)
//                        pbLoadProgress.setVisibility(View.INVISIBLE);
//                }
//                if(WebViewController.getInstance().isWebPure()&& loadingView!=null && ivLeftClose!=null && pbLoadProgress!=null) {
//                    ivLeftClose.setVisibility(View.VISIBLE);
//                    ivLeftClose.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            getActivity().finish();
//                        }
//                    });
//                    mWebView.setVisibility(View.INVISIBLE);
//                }
//            }
//        }
    }


    public boolean loadOrignUrl() {
        if (mUrl == null)
            return false;
        if ((mUrl.startsWith("http") && mUrl.contains(".apk")) /*|| isDownloadApk*/) {
            // 原先是直接传needFinish = true进去直接关闭的，但是女人通视频广告那边需要特殊处理，不关闭
            //handleWifiDownload(isFinishUIWhenUrlIsApk, mUrl);
            return true;
        }
        //非http请求
        if (!mUrl.startsWith("http") && !mUrl.startsWith("file")) {
            //getWebViewClient().handleSchemaJump(mUrl);
            finish();
            return true;
        }
        //是淘宝和百川的链接不添加参数
        if (mUrl.contains("tmall.com") || mUrl.contains("taobao.com")) {
            mWebView.loadUrl(mUrl);
            return true;
        }
        return false;
    }

    private void initWebManager(WebView webView) {
        if (webView == null) {
            return;
        }
        // 设置接收第三方Cookie
        if (isKeepCookie && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        }

        //禁止自动播放
        if (!isAutoPlay && Build.VERSION.SDK_INT > 16) {
            webView.getSettings().setMediaPlaybackRequiresUserGesture(true);
        }

    }


    private void initWebView(WebView webView) {
        if (webView == null) {
            return;
        }
        WebSettings settings = webView.getSettings();
        // 缓存(cache)
        settings.setAppCacheEnabled(true);      // 默认值 false
        settings.setAppCachePath(getCacheDir().getAbsolutePath());

        // 存储(storage)
        settings.setDomStorageEnabled(true);    // 默认值 false
        settings.setDatabaseEnabled(true);      // 默认值 false

        // 是否支持viewport属性，默认值 false
        // 页面通过`<meta name="viewport" ... />`自适应手机屏幕
        settings.setUseWideViewPort(true);
        // 是否使用overview mode加载页面，默认值 false
        // 当页面宽度大于WebView宽度时，缩小使页面宽度等于WebView宽度
        settings.setLoadWithOverviewMode(true);

        // 是否支持Javascript，默认值false
        settings.setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 5.0以上允许加载http和https混合的页面(5.0以下默认允许，5.0+默认禁止)
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        if (NetWorkStatusUtils.hasNetWork(getApplicationContext())) {
            // 根据cache-control决定是否从网络上取数据
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            // 没网，离线加载，优先加载缓存(即使已经过期)
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        getWebViewChromeClient();
        getWebViewClient();
        webView.setWebViewClient(ecoWebViewClient);
        webView.setWebChromeClient(ecoWebChromeClient);
    }


    //外部可重写
    protected EcoWebViewClient getWebViewClient() {
        if (ecoWebViewClient == null)
            ecoWebViewClient = new EcoWebViewClient();
        return ecoWebViewClient;
    }

    //外部可重写
    protected EcoWebChromeClient getWebViewChromeClient() {
        if (ecoWebChromeClient == null) {
            ecoWebChromeClient = new EcoWebChromeClient();

        }
        return ecoWebChromeClient;
    }

    protected void initLogic(String mUrl) {
        try {
            isFirstLoad = false;
            if (mUrl.startsWith("http") && mUrl.contains(".apk")) {
                return;
            }
            //非http请求
            if (!mUrl.startsWith("http") && !mUrl.startsWith("file")) {
                return;
            }
            //是淘宝和百川的链接不添加参数
            if (mUrl.contains("tmall.com") || mUrl.contains("taobao.com")) {
                return;
            }
            mOriginUrl = mUrl;
//            mUrl = AppHost.getEnvironmentReplaceUrl(mUrl);
//            mUrl = AppHost.switchHost(mUrl);
//            final String replaceUrl = mUrl;
//            LogUtils.d("webmodule", "开启url线程");
//            ThreadUtil.addTask(MeetyouFramework.getContext(), new ThreadUtil.ITasker() {
//                @Override
//                public Object onExcute() {
//                    LogUtils.d("webmodule", "URL线程开始执行");
//                    int mode = BizHelper.getInstance().getMode();
//                    mUrlParams  =  WebViewController.getInstance().getWebUrlParams(replaceUrl, mode);
//                    mHashMapHeader = WebViewController.getInstance().getWebRequestHeader(replaceUrl + mUrlParams);
//                    mFinalUrl  = replaceUrl + mUrlParams;
//                    mWebModule.setCallBack(new WebModuleLoadCallback() {
//                        @Override
//                        public CustomWebView getWebView() {
//                            return mWebView;
//                        }
//                    });
//                    mWebModule.setApiCallBack(new WebModuleApiCallback() {
//                        @Override
//                        public void onCacheFinish(String mH5Url, String data) {
//                            hidePullToRefresh();
//                        }
//
//                        @Override
//                        public void onApiFinish(String mH5Url, String cache, String data) {
//                            hidePullToRefresh();
//                        }
//
//                        @Override
//                        public void onApiFail() {
//                            hidePullToRefresh();
//                        }
//                    });
//
//                    mUseWebModule = mWebModule.preload(mContext, mFinalUrl, replaceUrl);
//                    return null;
//                }
//
//                @Override
//                public void onFinish(Object result) {
//                    if(ConfigManager.from(mContext).isTest() && mUseWebModule){
//                        ToastUtils.showToast(mContext,"使用了模板");
//                    }
//                    loadUrl();
//                }
//            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void reload() {
//        if(mUseWebModule){
//            //模板化刷新
//            mWebModule.refreshApi(mContext);
//        }else {
//            mWebView.reload();
//        }

        mWebView.reload();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            // 传入true表示同时内存与磁盘，false表示仅清除内存
            // 由于内核缓存是全局的因此这个方法不仅仅针对webview而是针对整个应用程序
            mWebView.clearCache(true);

            mWebView.setWebViewClient(null);
            mWebView.setWebChromeClient(null);
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;

        }
    }
}
