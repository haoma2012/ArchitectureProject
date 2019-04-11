package com.mobike.uilibrary.widget;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.adapter.UIWidgetPagerAdapter;
import com.mobike.uilibrary.base.BaseActivity;
import com.mobike.uilibrary.fragment.ClassifyBottomSheetFragment;
import com.mobike.uilibrary.utils.DataGenerator;
import com.mobike.uilibrary.utils.DialogUtils;
import com.mobike.uilibrary.view.button.CircleCheckBox;
import com.mobike.uilibrary.view.dialog.BaseDialogFragment;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/14 下午2:34
 */
public class UIWidgetActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLl_content;
    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;
    private Fragment[] mFragments;
    private Button mBtnAddFragment;
    private FrameLayout mContent;

    private UIWidgetPagerAdapter pagerAdapter;

    private UIWidgetFragment widgetFragment;

    private boolean isChange = true;

    /**
     * Common UI
     **/
    private TextView mTv_common;
    private Button mBtn_common;
    private ImageView mIv_common;
    private ScrollView mScrollView;
    private DialogUtils mDialogUtils;
    private BaseDialogFragment dialogFragment;

    private CircleCheckBox checkBox;


    /**
     * Intent跳转
     */
    public static void enterActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, UIWidgetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 5.0新增转场动画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
        } else {
            context.startActivity(intent);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.widget_main_layout;
    }

    @Override
    protected void initView() {


        /** DialogFragment**/
        mDialogUtils = new DialogUtils(this);
        dialogFragment = new BaseDialogFragment();
        /** common UI*/
        mTv_common = findViewById(R.id.tv_text);
        mBtn_common = findViewById(R.id.btn_add);
        mIv_common = findViewById(R.id.image_view);
        //mScrollView = new ScrollView(this);

        initEditText();
        initButton();

        // initFragment
        initFragments();

        mLl_content = findViewById(R.id.ll_content_layout);
        mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        // 由于第一次进来没有回调onNavigationItemSelected，因此需要手动调用一下切换状态的方法
        onTabItemSelected(R.id.tab_menu_home);

        mBtnAddFragment = findViewById(R.id.btn_add_fragment);
        mContent = findViewById(R.id.frame_content);

        setDefaultFragment();

        mBtnAddFragment.setOnClickListener(this);


        findViewById(R.id.btn_show_dialog).setOnClickListener(v -> {
            //initDialog();
            //initFragmentDialog();
            // 进入弹框总页码
            WidgetDialogActivity.enterActivity(UIWidgetActivity.this);
        });

        setListener();
    }

    /**
     * 更新输入框
     */
    private void initEditText() {

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        MultiAutoCompleteTextView multiAutoCompleteTextView = findViewById(R.id.multiAutoCompleteTextView);

        String[] countries2 = getResources().getStringArray(R.array.countries);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, countries2);

        autoCompleteTextView.setAdapter(adapter);
        multiAutoCompleteTextView.setAdapter(adapter);
        // 设置分割符
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

    }

    private void initButton() {
        checkBox = findViewById(R.id.circle_check_box);
        checkBox.setListener(isChecked -> {
            // do something
            if (isChecked) {
                checkBox.setChecked(false);
            } else {
                checkBox.setChecked(true);
            }
        });
    }


    private void setListener() {

        mTv_common.setOnClickListener(v -> {
            mDialogUtils.showNormalDialog(mTv_common.getText().toString());
            mDialogUtils.showListDialog(1);
            mDialogUtils.showListDialog(2);
            mDialogUtils.showListDialog(3);
            mDialogUtils.showListDialog(4);
            mDialogUtils.showCustomizeDialog();

            dialogFragment.show("我是DialogFragment", "show a DialogFragment",
                    (dialog, which) -> {
                        Toast.makeText(getApplicationContext(),
                                "点击确定按钮" + which,
                                Toast.LENGTH_SHORT).show();
                    }, (dialog, which) -> {
                        Toast.makeText(getApplicationContext(),
                                "点击取消按钮" + which,
                                Toast.LENGTH_SHORT).show();
                    }, getFragmentManager(), BaseDialogFragment.TAG);

        });

        mBtn_common.setOnClickListener(v -> Toast.makeText(getApplicationContext(),
                "点击按钮" + mBtn_common.getText().toString(),
                Toast.LENGTH_SHORT).show());

        mIv_common.setOnClickListener(v -> Toast.makeText(getApplicationContext(),
                "点击图片",
                Toast.LENGTH_SHORT).show());

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                onTabItemSelected(item.getItemId());
                return true;
            }
        });

    }


    private void setDefaultFragment() {
        FragmentManager manager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = manager.beginTransaction();
//        if (widgetFragment == null) {
//            widgetFragment = UIWidgetFragment.newInstance(new Bundle());
//        }
        widgetFragment = UIWidgetFragment.newInstance(new Bundle());
        widgetFragment.updateContent("微信");
        transaction.add(R.id.frame_content, widgetFragment, "FIRST");
        transaction.commit();
    }


    private int i = 1;

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        // 开启事务
        //FragmentTransaction transaction = manager.beginTransaction();

        if (v.getId() == R.id.btn_add_fragment) {
            if (widgetFragment == null) {
                widgetFragment = UIWidgetFragment.newInstance(new Bundle());
            }
//            if (isChange) {
//                isChange = false;
//                widgetFragment.updateContent("通讯录");
//            } else {
//                isChange = true;
//                widgetFragment.updateContent("微信");
//            }
            // widgetFragment.updateContent("微信" + i);
            i++;
            addSecondFragment("微信" + i);
            //transaction.replace(R.id.frame_content, widgetFragment);

        }
        //transaction.commit();


    }


    private void addSecondFragment(String text) {
        FragmentManager manager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = manager.beginTransaction();
        UIWidgetFragment widgetFragment = UIWidgetFragment.newInstance(new Bundle());
        widgetFragment.updateContent(text);
        transaction.replace(R.id.frame_content, widgetFragment, "SECOND");
        transaction.addToBackStack(null);
        transaction.commit();
    }


    // 使用TransitionManager 转场动画
    private void transitionAnimation() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(mLl_content, new Fade());
        }
    }


    private BottomSheetDialog bottomSheetDialog;
    private ClassifyBottomSheetFragment bottomSheetFragment;

    private void initDialog() {
        if (bottomSheetDialog == null) {
            bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
        }

        bottomSheetDialog.show();
    }

    private void initFragmentDialog() {
        FragmentManager manager = getSupportFragmentManager();
        if (bottomSheetFragment == null) {
            bottomSheetFragment = new ClassifyBottomSheetFragment();
        }
        bottomSheetFragment.show(manager, "");
    }


    private void testWindow() {

        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    }


    private void initFragments() {
        mFragments = DataGenerator.getFragments();

    }

    private void onTabItemSelected(int id) {
        Fragment fragment = null;
        if (id == R.id.tab_menu_home) {
            fragment = mFragments[0];

        } else if (id == R.id.tab_menu_cart) {
            fragment = mFragments[1];

        } else if (id == R.id.tab_menu_coupon) {
            fragment = mFragments[2];

        } else if (id == R.id.tab_menu_mine) {
            fragment = mFragments[3];

        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, fragment).commit();
        }
    }



}
