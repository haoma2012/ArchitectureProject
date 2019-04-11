package com.mobike.uilibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/14 下午3:05
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //LogUtils.d(TAG,"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //LogUtils.d(TAG,"onCreateView");
        View view = null;

        view = inflater.inflate(getLayout(), null);
        beforeInitView(view);
        initView(view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //LogUtils.d(TAG, "onActivityCreated");
        initLogic(savedInstanceState);
    }

    // 带savedInstanceState 参数
    public void initLogic(Bundle savedInstanceState) {
    }

    @Override
    public void onStart() {
        super.onStart();
        //LogUtils.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        //LogUtils.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        //LogUtils.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //LogUtils.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //LogUtils.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //LogUtils.d(TAG, "onDetach");
    }

    protected void beforeInitView(View view) {
    }

    protected abstract int getLayout();

    protected abstract void initView(View view);
}
