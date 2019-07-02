package com.mobike.uilibrary.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.utils.AnimationUtils;


/**
 * @author: jiangwenfei
 * @Date: 2019/4/8
 * @Desc: 进入三方商城加载对话框
 */
public class EnterTpMallDialog extends Dialog {
    private static final String TAG = EnterTpMallDialog.class.getSimpleName();
    public static final int DIALOG_STYLE_TYPE_PDD = 1;
    public static final int DIALOG_STYLE_TYPE_JINGDONG = 2;
    public static final int DIALOG_STYLE_TYPE_VIP = 3;
    private Context mContext;
    private ImageView mIvTmLogo;
    private RelativeLayout mRlLoadingLayout;
    private TextView mTvLoading;
    private int mTpType; // 进入dialog样式
    private int loadWidth;
    private String mRebateStr; // 最高返

    public EnterTpMallDialog(@NonNull Context context, int type, String rebate) {
        super(context);
        this.mContext = context;
        mTpType = type;
        mRebateStr = rebate;
        loadWidth = context.getResources().getDimensionPixelOffset(R.dimen.dp_value_200);
        initView();
    }

    private void initView() {
        configDialog();
        setContentView(R.layout.dialog_enter_tpmall);
        mIvTmLogo = findViewById(R.id.iv_tmall_logo);
        mRlLoadingLayout = findViewById(R.id.rl_loading_layout);
        mTvLoading = findViewById(R.id.tv_loading);
        updateUI();
        findViewById(R.id.rl_dialog_root).setOnClickListener(v -> dismiss());
    }

    private void updateUI() {
        Log.d(TAG, "mRebateStr = " + mRebateStr + " mTpType = " + mTpType);
        switch (mTpType) {
            case DIALOG_STYLE_TYPE_PDD: {
                mIvTmLogo.setImageResource(R.drawable.icon_dialog_pdd_bg);
                mRlLoadingLayout.setBackgroundResource(R.drawable.bg_progress_pdd_shape);
                if (TextUtils.isEmpty(mRebateStr)) {
                    mTvLoading.setText("最高返50%");
                } else {
                    mTvLoading.setText(mRebateStr);
                }
            }
            break;
            case DIALOG_STYLE_TYPE_JINGDONG: {
                mIvTmLogo.setImageResource(R.drawable.icon_dialog_jd_bg);
                mRlLoadingLayout.setBackgroundResource(R.drawable.bg_progress_jd_shape);
                if (TextUtils.isEmpty(mRebateStr)) {
                    mTvLoading.setText("最高返20%");
                } else {
                    mTvLoading.setText(mRebateStr);
                }
            }
            break;
            case DIALOG_STYLE_TYPE_VIP: {
                mIvTmLogo.setImageResource(R.drawable.icon_dialog_vip_bg);
                mRlLoadingLayout.setBackgroundResource(R.drawable.bg_progress_vip_shape);
                if (TextUtils.isEmpty(mRebateStr)) {
                    mTvLoading.setText("最高返5.6%");
                } else {
                    mTvLoading.setText(mRebateStr);
                }
            }
            break;
            default: {
                mIvTmLogo.setImageResource(R.drawable.icon_dialog_pdd_bg);
                mRlLoadingLayout.setBackgroundResource(R.drawable.bg_progress_pdd_shape);
                if (TextUtils.isEmpty(mRebateStr)) {
                    mTvLoading.setText("最高返50%");
                } else {
                    mTvLoading.setText(mRebateStr);
                }
            }
            break;
        }
    }

    private void configDialog() {
        //WIDTH = DeviceUtils.getScreenWidth(getContext()) * 90 / 100;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
//            dialogWindow.setGravity(Gravity.CENTER);
//            WindowManager.LayoutParams pm = dialogWindow.getAttributes();
//            pm.width = WIDTH;
//            pm.height = DeviceUtils.getScreenHeight(getContext()) / 2;
//            dialogWindow.setAttributes(pm);
            dialogWindow.setWindowAnimations(R.style.TmEnterDialogStyle);
            dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
        }
    }

    @Override
    public void show() {
        if (mContext != null && !((Activity) mContext).isFinishing()) {
            AnimationUtils.dialogLoadingAnimation(mRlLoadingLayout, mTvLoading, loadWidth);
            super.show();
        }
    }

    public void updateType(int tmType) {
        mTpType = tmType;
        updateUI();
    }

//    private void loadGif() {
//        Uri uri = new Uri.Builder()
//                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
//                .path(String.valueOf(R.drawable.enter_tpmall_loading))
//                .build();
//        DraweeController draweeController =
//                Fresco.newDraweeControllerBuilder()
//                        .setUri(uri)
//                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
//                        .build();
//        mImgLoading.setController(draweeController);
//
//    }
}
