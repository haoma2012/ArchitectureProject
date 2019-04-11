package com.mobike.uilibrary.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.widget.UIWidgetFragment;

/**
 * 分类弹框Fragment
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/16 下午4:54
 */
public class ClassifyBottomSheetFragment extends BottomSheetDialogFragment {


    public static ClassifyBottomSheetFragment newInstance() {
        Bundle args = new Bundle();
        ClassifyBottomSheetFragment fragment = new ClassifyBottomSheetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_bottom_classify_layout, container, false);
        initView(view);
        initFragment();
        return view;
    }

    private void initView(View view) {
        ImageView iv_close = view.findViewById(R.id.dialog_classify_close);

        iv_close.setOnClickListener(v -> {
            dismiss();
        });
    }


    private void initFragment() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        UIWidgetFragment fragment = (UIWidgetFragment) getChildFragmentManager().
                findFragmentByTag(UIWidgetFragment.TAG);

        if (fragment == null) {
            fragment = new UIWidgetFragment();
            transaction.add(R.id.container, fragment, UIWidgetFragment.TAG);
            transaction.commitAllowingStateLoss();
        }
    }

}
