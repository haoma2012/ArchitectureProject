package com.mobike.uilibrary.view;

import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/26 下午4:13
 */
public class NoLineClickSpan extends ClickableSpan {

    @Override
    public void onClick(@NonNull View widget) {

    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        ds.setUnderlineText(false);
    }


}
