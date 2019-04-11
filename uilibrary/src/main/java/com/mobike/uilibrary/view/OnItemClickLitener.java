package com.mobike.uilibrary.view;

import android.view.View;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/19 下午6:57
 */
public interface OnItemClickLitener {

    void onItemClick(View view, int position);

    void onItemLongClick(View view, int position);
}
