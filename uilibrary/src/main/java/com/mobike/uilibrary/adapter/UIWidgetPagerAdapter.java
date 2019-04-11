package com.mobike.uilibrary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.mobike.uilibrary.model.TabHomeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager的Adapter
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/14 下午2:53
 */
public class UIWidgetPagerAdapter extends FragmentPagerAdapter {

    private List<TabHomeModel> mFragmentList;

    public UIWidgetPagerAdapter(FragmentManager fm, List<TabHomeModel> list) {
        super(fm);
        mFragmentList = list;
    }

    private List<TabHomeModel> getFragmentList() {
        if (mFragmentList == null) {
            mFragmentList = new ArrayList<>();
        }
        return mFragmentList;
    }

    @Override
    public int getCount() {
        return getFragmentList().size();
    }

    @Override
    public Fragment getItem(int position) {
        return getFragmentList().get(position).fragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getFragmentList().get(position).name;
    }
}
