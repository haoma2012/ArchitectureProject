package com.mobike.uilibrary.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.widget.UIWidgetFragment;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/9 上午10:35
 */
public class DataGenerator {

    public static final int[] mTabRes = new int[]{R.drawable.tab_item_home, R.drawable.tab_item_cart, R.drawable.tab_item_coupons, R.drawable.tab_item_mine};
    //public static final int[] mTabResPressed = new int[]{R.drawable.ic_tab_strip_icon_feed_selected, R.drawable.ic_tab_strip_icon_category_selected, R.drawable.ic_tab_strip_icon_pgc_selected, R.drawable.ic_tab_strip_icon_profile_selected};
    public static final String[] mTabTitle = new String[]{"首页", "购物车", "优惠券", "我的"};

    public static Fragment[] getFragments() {
        Fragment fragments[] = new Fragment[4];
        // put bundle
        Bundle bundle = new Bundle();

        for (int i = 0; i < 4; i++) {
            bundle.putString("title", mTabTitle[i]);
            fragments[i] = UIWidgetFragment.newInstance(bundle);
        }


        return fragments;
    }

    /**
     * 获取Tab 显示的内容
     *
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.header_sheep_tab_home, null);
//        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
//        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
//        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
//        tabText.setText(mTabTitle[position]);
        return view;
    }
}

