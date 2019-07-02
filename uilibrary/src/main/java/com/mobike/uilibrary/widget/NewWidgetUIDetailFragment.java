package com.mobike.uilibrary.widget;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.adapter.BaseTextRecycleAdapter;
import com.mobike.uilibrary.base.BaseFragment;
import com.mobike.uilibrary.model.DetailItemModel;
import com.mobike.uilibrary.utils.DialogUtils;
import com.mobike.uilibrary.view.OnItemClickLitener;

import java.util.ArrayList;
import java.util.List;

/**
 * UI详情页 RecycleView 跳转页
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/25 下午8:17
 */
public class NewWidgetUIDetailFragment extends BaseFragment {
    public static final String TAG = NewWidgetUIDetailFragment.class.getSimpleName();
    private RecyclerView mRecycleView;
    private BaseTextRecycleAdapter mAdapter;
    private DialogUtils mDialogUtils;

    public static NewWidgetUIDetailFragment newInstance(Bundle bundle) {
        NewWidgetUIDetailFragment fragment = new NewWidgetUIDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_recommend_detail;
    }

    @Override
    protected void initView(View view) {
        mRecycleView = view.findViewById(R.id.recycle_view);
        // 设置布局管理器
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 设置分割线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.gray_round_1));
        mRecycleView.addItemDecoration(itemDecoration);
        // 设置数据适配器
        if (mAdapter == null) {
            mAdapter = new BaseTextRecycleAdapter(getActivity(), getDialogListData());
        }
        mRecycleView.setAdapter(mAdapter);

        mDialogUtils = new DialogUtils(getActivity());
        mAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

                showDialogType(position);


            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }


    private List<DetailItemModel> getListData() {
        List<DetailItemModel> list = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            DetailItemModel model = new DetailItemModel();
            model.name = "普通弹框";
            list.add(model);
        }

        return list;

    }


    private List<DetailItemModel> getDialogListData() {
        List<DetailItemModel> list = new ArrayList<>();

        DetailItemModel model = new DetailItemModel();
        model.name = "普通弹框";

        DetailItemModel model1 = new DetailItemModel();
        model.name = "单选弹框";

        DetailItemModel model2 = new DetailItemModel();
        model2.name = "多选弹框";

        DetailItemModel model3 = new DetailItemModel();
        model3.name = "输入框弹框";

        DetailItemModel model4 = new DetailItemModel();
        model4.name = "列表弹框";

        DetailItemModel model5 = new DetailItemModel();
        model5.name = "自定义View弹框";

        DetailItemModel model6 = new DetailItemModel();
        model6.name = "显示PopupWindow";

        DetailItemModel model7 = new DetailItemModel();
        model7.name = "显示自定义CustomPopWindow";

        list.add(model);
        list.add(model1);
        list.add(model2);
        list.add(model3);
        list.add(model4);
        list.add(model5);
        list.add(model6);
        list.add(model7);

        list.addAll(getListData());

        return list;

    }

    /**
     * 显示不同的弹框
     *
     * @param position 位置
     */
    private void showDialogType(int position) {
        switch (position) {
            case 0:
                if (mDialogUtils != null) {
                    mDialogUtils.showNormalDialog("我是普通弹框");
                }
                break;
            case 1:
                if (mDialogUtils != null) {
                    mDialogUtils.showListDialog(1);
                }
                break;
            case 2:
                if (mDialogUtils != null) {
                    mDialogUtils.showListDialog(2);
                }
                break;
            case 3:
                if (mDialogUtils != null) {
                    mDialogUtils.showListDialog(3);
                }
                break;
            case 4:
                if (mDialogUtils != null) {
                    mDialogUtils.showListDialog(4);
                }
                break;
            case 5:
                if (mDialogUtils != null) {
                    mDialogUtils.showCustomizeDialog();
                }
                break;
            case 6:
                if (mDialogUtils != null) {
                    mDialogUtils.showPopWindow(mRecycleView);
                }
                break;
            case 7:
                if (mDialogUtils != null) {
                    mDialogUtils.showCustomPopupWindow(getActivity(), mRecycleView);
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 隐藏所有弹框
        if (mDialogUtils != null) {
            mDialogUtils.dismissAllDialog();
        }
    }
}
