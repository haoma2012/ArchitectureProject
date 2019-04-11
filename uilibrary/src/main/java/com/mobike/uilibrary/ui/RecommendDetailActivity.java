package com.mobike.uilibrary.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.base.BaseActivity;
import com.mobike.uilibrary.widget.NewWidgetUIDetailFragment;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/25 下午8:11
 */
public class RecommendDetailActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_container;
    }

    @Override
    protected void initView() {
        initFragment();
    }

    private void initFragment() {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        Intent i = getIntent();
        Bundle bundle = new Bundle();
        if (i != null) {
            bundle = i.getExtras();
        }
        NewWidgetUIDetailFragment fragment = (NewWidgetUIDetailFragment) this.getSupportFragmentManager().
                findFragmentByTag(NewWidgetUIDetailFragment.TAG);

        if (fragment == null) {
            transaction.add(R.id.container, NewWidgetUIDetailFragment.newInstance(bundle),
                    NewWidgetUIDetailFragment.TAG);
            transaction.commitAllowingStateLoss();
        }
    }
}
