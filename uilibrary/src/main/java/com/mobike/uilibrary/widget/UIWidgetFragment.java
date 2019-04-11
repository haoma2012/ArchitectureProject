package com.mobike.uilibrary.widget;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.base.BaseFragment;
import com.mobike.uilibrary.utils.EcoConstants;

/**
 * UIWidgetFragment 控件集合 Fragment
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/14 下午3:31
 */
public class UIWidgetFragment extends BaseFragment {

    public static final String TAG = UIWidgetFragment.class.getSimpleName();
    private TextView mTvContent;
    private String title;


    public static UIWidgetFragment newInstance(Bundle bundle) {
        UIWidgetFragment fragment = new UIWidgetFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.widget_main_fragment;
    }

    @Override
    protected void initView(View view) {
        mTvContent = view.findViewById(R.id.tv_content);
        setListener();
    }

    @Override
    public void initLogic(Bundle savedInstanceState) {
        super.initLogic(savedInstanceState);
        getIntentData();
        if (savedInstanceState != null) {
            title = savedInstanceState.getString(EcoConstants.TITLE);
        }

        // 更新title
        if (mTvContent != null && !TextUtils.isEmpty(title)) {
            mTvContent.setText(title);
        }

    }

    private void getIntentData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString(EcoConstants.TITLE);
        }
    }

    private void setListener() {

        mTvContent.setOnClickListener(v -> {
            Toast.makeText(getContext(), "click content", Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * 更新内容
     *
     * @param text string
     */
    public void updateContent(String text) {
        if (mTvContent != null && !TextUtils.isEmpty(text)) {
            mTvContent.setText(text);
        }
    }

    private void sendContentResult() {
        Intent intent = new Intent(getActivity(), UIWidgetActivity.class);
        intent.putExtra("title", "返回值");
        startActivityForResult(intent, 101);
    }


    private void testFragment() {

        DialogFragment dialogFragment = new DialogFragment();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {

        }
    }
}
