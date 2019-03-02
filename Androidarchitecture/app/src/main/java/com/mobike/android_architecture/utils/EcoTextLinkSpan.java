package com.mobike.android_architecture.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.provider.Browser;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;

/**
 * 电商文本链接
 * Created by chenheneng@xiaoyouzi.com on 2017/9/8.
 */

public class EcoTextLinkSpan extends ClickableSpan {
    public static final int URL_SPAN = 11;

    private final String mURL;

    public EcoTextLinkSpan(String url) {
        mURL = url;
    }

    public EcoTextLinkSpan(Parcel src) {
        mURL = src.readString();
    }

    public int getSpanTypeId() {
        return getSpanTypeIdInternal();
    }

    /**
     * @hide
     */
    public int getSpanTypeIdInternal() {
        return URL_SPAN;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        writeToParcelInternal(dest, flags);
    }

    /**
     * @hide
     */
    public void writeToParcelInternal(Parcel dest, int flags) {
        dest.writeString(mURL);
    }

    public String getURL() {
        return mURL;
    }

    private TextPaint ds;

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        this.ds = ds;
    }

    public void updateLinkColor(int linkColor) {
        if (ds != null) {
            ds.linkColor = linkColor;
            ds.setColor(linkColor);
        }
    }

    @Override
    public void onClick(View widget) {
        Uri uri = Uri.parse(getURL());
        Context context = widget.getContext();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (getURL().startsWith("http")) {
            //            String url = EcoActivityCtrl.getInstance().getMeetyouSchemeProxy(context, EcoProxyUtil.PROXY_ECO_TAE_WEB,new TaeWebProxyDo(getURL()));
//            intent.setData(Uri.parse(url));
           // EcoActivityCtrl.getInstance().openMeiyouProxy(context, EcoProxyUtil.PROXY_ECO_TAE_WEB, new TaeWebProxyDo(getURL()));
            return;
        } else if (getURL().startsWith("meiyou")) {
            //EcoUriHelper.parserUri(context, getURL());
            return;
        } else {
            intent.setData(uri);
            intent.putExtra(Browser.EXTRA_APPLICATION_ID, context.getPackageName());
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.w("URLSpan", "Actvity was not found for intent, " + intent.toString());
        }
    }
}