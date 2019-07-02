package com.mobike.uilibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ViewStubCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 1.实现懒加载
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/14 下午3:05
 */
public abstract class BaseFragment extends Fragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    // Fragment的根View
    private View mRootView;

    // 检测声明周期中，是否已经构建视图
    private boolean mViewCreated = false;

    // 占位图
    private ViewStubCompat mViewStub;

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
        if (mRootView != null) {
            mViewCreated = true;
            return mRootView;
        }

//        final Context context = inflater.getContext();
//
//        FrameLayout root = new FrameLayout(context);
//        mViewStub = new ViewStubCompat(context, null);
//        mViewStub.setLayoutResource(getLayout());
//        root.addView(mViewStub, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
//                FrameLayout.LayoutParams.MATCH_PARENT));
//        root.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.MATCH_PARENT));
//
//        mRootView = root;
//
//        mViewCreated = true;
//        if (mUserVisible) {
//            realLoad();
//        }
//
       View view = inflater.inflate(getLayout(), null);
        beforeInitView(view);
        initView(view);

        return view;
    }

    private boolean mUserVisible = false;
    public static final int STATUS_DEFAULT = 0;
    /**
     * fragment 未加载数据
     */
    public static final int STATUS_INITED = 1;
    /**
     * 已加载数据
     */
    public static final int STATUS_LOADED_DATA = 2;
    // 0 默认状态, 1 不可见，未加载数据，2 可见，未加载数据，3 已加载数据
    private int status = STATUS_DEFAULT;//是否已加载数据
    public int getStatus() {
        return status;
    }
    /**
     * 使用前提条件：该fragment被加入到viewpager中
     * 视图用户是否可见
     *
     * @return
     */
    public boolean isUserVisible() {
        return mUserVisible;
    }

    private void setUserVisible(boolean userVisible) {
        this.mUserVisible = userVisible;
    }

    /**
     * 使用前提条件：该fragment被加入到viewpager中
     * fragment从用户可见变为不可见时会调用
     */
    protected void onUserVisible() {
        if (status == STATUS_INITED) {
            initData();
        }
    }

    /**
     * 初始化数据
     */
    protected void initData() {
        status = STATUS_LOADED_DATA;
        Log.i(TAG, "initData: isUserVisible = " + isUserVisible() + " isVisible = " + isVisible());
//        boolean firstTab = false;
//        if (getArgs() != null) {
//            firstTab = getArgs().getBoolean(NodeEvent.NODO_PARAMS_FIRST_TAB, false);
//        }
//
//        if (!isMainActivityPage() || firstTab) { // 如果是首页MainActivity就不发送 或者是第一个tab就发
//            onInitEnter();
//        }
    }

    @Override
    public final void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        setUserVisible(isVisibleToUser);
        if (isVisibleToUser) {
            onUserVisible();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //LogUtils.d(TAG, "onActivityCreated");
        initLogic(savedInstanceState);
        status = STATUS_INITED;
        if (isUserVisible()) {
            initData();
        }
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
