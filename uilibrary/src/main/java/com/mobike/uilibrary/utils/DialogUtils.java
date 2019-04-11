package com.mobike.uilibrary.utils;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.view.dialog.CustomPopWindow;

/**
 * 弹框工具类
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/19 下午7:15
 */
public class DialogUtils {
    private static final String TAG = DialogUtils.class.getSimpleName();
    private Context mContext;
    private BottomSheetDialog bottomSheetDialog;
    private AlertDialog mNormalDialog;
    private AlertDialog.Builder mBuildDialog;
    private LayoutInflater mInflater;
    private PopupWindow mTabPopupWindow;


    public DialogUtils(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }


    /**
     * 显示底部弹框
     */
    public void ShowBottomSheetDialog() {
        if (mContext == null) {
            return;
        }
        if (bottomSheetDialog == null) {
            bottomSheetDialog = new BottomSheetDialog(mContext);
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
        }
        bottomSheetDialog.show();
    }


    /**
     * 隐藏所有弹框
     */
    public void dismissAllDialog() {
        if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
            bottomSheetDialog.dismiss();
        }

        if (mNormalDialog != null && mNormalDialog.isShowing()) {
            mNormalDialog.dismiss();
        }

        if (mBuildDialog != null) {
            Dialog dialog = mBuildDialog.create();
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }


    /**
     * 弹框大全
     */
    public void showNormalDialog(String msg) {
        if (mNormalDialog == null) {
            mNormalDialog = new AlertDialog.Builder(mContext).create();
        }

        mNormalDialog.setTitle("A Normal Dialog");
        mNormalDialog.setIcon(R.drawable.sheep_switch_one);
        mNormalDialog.setMessage("click the " + msg);
        mNormalDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", (dialog, which) -> {
            Toast.makeText(mContext, "click positive button", Toast.LENGTH_SHORT).show();
        });

        mNormalDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", (dialog, which) -> {
            Toast.makeText(mContext, "click negative button", Toast.LENGTH_SHORT).show();
        });

        mNormalDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "中间按钮", (dialog, which) -> {
            Toast.makeText(mContext, "click Neutral button", Toast.LENGTH_SHORT).show();
        });
        // show dialog
        mNormalDialog.show();

    }


    /**
     * a listView dialog
     *
     * @param type 默认 = list, 1=SingleChoice, 2= MultiChoice 3=输入框弹框
     */
    public void showListDialog(int type) {
//        if (mBuildDialog == null) {
//            mBuildDialog = new AlertDialog.Builder(mContext);
//        }
        mBuildDialog = new AlertDialog.Builder(mContext);
        String[] items = mContext.getResources().getStringArray(R.array.dialog_list_items);


        if (type == 1) {
            // 单选弹框
            mBuildDialog.setTitle("单选弹框");
            mBuildDialog.setSingleChoiceItems(items, 0, (dialog, which) -> {
                Toast.makeText(mContext,
                        "单选弹框：你选中了" + items[which],
                        Toast.LENGTH_SHORT).show();
            });
        } else if (type == 2) {
            // 多选按钮弹框
            mBuildDialog.setTitle("多选按钮弹框");
            boolean[] initChoiceSets = new boolean[items.length];
            for (int i = 0; i < items.length; i++) {
                initChoiceSets[i] = false;
            }
            mBuildDialog.setMultiChoiceItems(items, initChoiceSets, (dialog, which, isChecked) -> {
                Toast.makeText(mContext,
                        "多选弹框：你选中了" + items[which],
                        Toast.LENGTH_SHORT).show();
            });
        } else if (type == 3) { // 带输入框弹框
            mBuildDialog.setTitle("输入框弹框");
            EditText editText = new EditText(mContext);
            mBuildDialog.setView(editText);
            mBuildDialog.setPositiveButton("登录", (dialog, which) -> {
                Toast.makeText(mContext, "click 登录", Toast.LENGTH_SHORT).show();

            });

        } else {
            mBuildDialog.setTitle("列表弹框");
            mBuildDialog.setItems(items, (dialog, which) -> {
                Toast.makeText(mContext, "click list item" + items[which], Toast.LENGTH_SHORT).show();

            });
        }

        mBuildDialog.show();
    }

    /**
     * 显示自定义Dialog
     */
    public void showCustomizeDialog() {
//        if (mBuildDialog == null) {
//            mBuildDialog = new AlertDialog.Builder(mContext);
//        }
        mBuildDialog = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.widget_dialog_activty, null);
        mBuildDialog.setTitle("a customizeDialog");
        mBuildDialog.setView(view);

        AlertDialog alertDialog = mBuildDialog.create();
        mBuildDialog.setPositiveButton("确定", (dialog, which) -> {
            Toast.makeText(mContext, "click 自定义Dialog positive button", Toast.LENGTH_SHORT).show();
            if (alertDialog != null) {
                alertDialog.cancel();
            }
        });

        mBuildDialog.setNegativeButton("取消", (dialog, which) -> {
            Toast.makeText(mContext, "click 自定义Dialog positive button", Toast.LENGTH_SHORT).show();

        });

        alertDialog.show();
    }

    /**
     * show loading dialog
     */
    public void showLoadingDialog() {
        // ProgressDialog 已经过期 使用ProgressBar
        ProgressBar progressBar = new ProgressBar(mContext);
    }


    /**
     * 显示
     */

    public void showPopWindow(View view) {
        View popView = mInflater.inflate(R.layout.widget_dialog_activty, null);
        if (mTabPopupWindow == null) {
            mTabPopupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        mTabPopupWindow.setAnimationStyle(R.style.TabPopAnimTranslate);
        // tab 展开布局
//        mExpandingBgView = popView.findViewById(R.id.view_bg_expanding);
//        mExpandingBgView.getBackground().setAlpha(215);
//        mExpandRecycleView = popView.findViewById(R.id.sticky_top_expand_recycler);
//        mGridLayoutManager = new GridLayoutManager(mContext, MAX_COLS_HEADER_TABS);
//        mExpandRecycleView.setLayoutManager(mGridLayoutManager);
//        mExpandTabAdapter = new SpecialExpandTabAdapter(mContext);
//        mExpandRecycleView.setAdapter(mExpandTabAdapter);
        mTabPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mTabPopupWindow.setOutsideTouchable(true);
        mTabPopupWindow.setFocusable(true);
//        mExpandTabAdapter.setOnTabItemClickListener(this);
        showPopLocation(view);
    }

    /**
     * 兼容处理api 24 7.0机型 PopupWindow弹出位置问题
     *
     * @param view View
     */
    private void showPopLocation(View view) {
        if (view == null) {
            return;
        }
        if (Build.VERSION.SDK_INT == 24) {
            // 适配 android 7.0
            int[] location = new int[2];
            view.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1] + view.getHeight() - 1;
            Log.d(TAG, " x = " + x + " y的值 = " + y);
            mTabPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, x, y);
        } else {
            mTabPopupWindow.showAsDropDown(view, 0, -1);
        }
    }


    public void showCustomPopupWindow(Context context, View view) {
        if (context == null || view == null) {
            return;
        }
        CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(context)
                .setView(R.layout.header_sheep_appbar_layout)//显示的布局，还可以通过设置一个View
                //     .size(600,400) //设置显示的大小，不设置就默认包裹内容
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                .create()//创建PopupWindow
                .showAsDropDown(view,0,10);//显示PopupWindow

        Toast.makeText(context, "show a popupWindow!!!", Toast.LENGTH_SHORT).show();
    }

}
