package com.mobike.uilibrary.widget.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * QuickFragmentPageAdapter
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/11 下午6:01
 */
public class QuickFragmentPageAdapter<T extends Fragment> extends FragmentPagerAdapter {
    private List<T> mList;
    private String[] mStrings;

    /**
     * @param fm     FragmentManager
     * @param list   List
     * @param titles PageTitles
     */
    public QuickFragmentPageAdapter(FragmentManager fm, List<T> list, String[] titles) {
        super(fm);
        mList = list;
        mStrings = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (mList != null) {
            return mList.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings == null ? super.getPageTitle(position) : mStrings[position];
    }
}
