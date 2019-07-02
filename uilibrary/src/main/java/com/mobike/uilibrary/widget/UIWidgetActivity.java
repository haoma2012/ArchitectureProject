package com.mobike.uilibrary.widget;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.util.Log;
import android.util.LruCache;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.*;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.adapter.UIWidgetPagerAdapter;
import com.mobike.uilibrary.annotation.AspectJAnnotation;
import com.mobike.uilibrary.base.BaseActivity;
import com.mobike.uilibrary.component.MyBroadCastReceived;
import com.mobike.uilibrary.fragment.ClassifyBottomSheetFragment;
import com.mobike.uilibrary.manager.NotifationUtils;
import com.mobike.uilibrary.model.TabHomeModel;
import com.mobike.uilibrary.thread.DownLoadImageUtils;
import com.mobike.uilibrary.thread.MyService;
import com.mobike.uilibrary.utils.AnimationUtils;
import com.mobike.uilibrary.utils.DataGenerator;
import com.mobike.uilibrary.utils.DeviceUtils;
import com.mobike.uilibrary.utils.DialogUtils;
import com.mobike.uilibrary.view.button.CircleCheckBox;
import com.mobike.uilibrary.view.dialog.BaseDialogFragment;
import com.mobike.uilibrary.view.dialog.EnterTpMallDialog;
import com.mobike.uilibrary.widget.video.UIVideoActivity;
import com.mobike.uilibrary.widget.webview.UIWebViewActivity;

import java.lang.ref.WeakReference;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/14 下午2:34
 */
public class UIWidgetActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = UIWidgetActivity.class.getSimpleName();
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
    private ImageView mDownLoadImg;
    private DialogUtils mDialogUtils;
    private BaseDialogFragment dialogFragment;

    private CircleCheckBox checkBox;

    private EnterTpMallDialog tpMallDialog;
    private int mTmType;
    /**
     * Handler消息处理机制
     */

    private Handler mUIHandler = new Handler();


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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "UIWidgetActivity: onCreate ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "UIWidgetActivity: onStart ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "UIWidgetActivity: onRestart ");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "UIWidgetActivity: onResume ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "UIWidgetActivity: onSaveInstanceState ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "UIWidgetActivity: onRestoreInstanceState ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "UIWidgetActivity: onPause ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "UIWidgetActivity: onStop ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "UIWidgetActivity: onDestroy ");
        if (mHandler != null) { // 移除相关callBack&Message
            mHandler.removeCallbacksAndMessages(null);
        }
        if (mUIHandler != null) {
            mUIHandler.removeCallbacksAndMessages(null);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.widget_main_layout;
    }

    @Override
    protected void initView() {

        initCanvas();
        /** DialogFragment**/
        mDialogUtils = new DialogUtils(this);
        dialogFragment = new BaseDialogFragment();
        /** common UI*/
        mTv_common = findViewById(R.id.tv_text);
        mBtn_common = findViewById(R.id.btn_add);
        mIv_common = findViewById(R.id.image_view);
        //mScrollView = new ScrollView(this);

        mDownLoadImg = findViewById(R.id.download_img);

//        mUIHandler.post();
//        mUIHandler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                Log.e("handleMessage", "次数:" + msg.what);
//                Bitmap bitmap = (Bitmap) msg.obj;
//                if (bitmap != null) {
//                    mDownLoadImg.setImageBitmap(bitmap);
//                }
//
//
//            }
//        };

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

        //initUtils();
        //initHandler();
    }

    /**
     * 自定义View合集
     */
    private void initCanvas() {
        Button canvas_btn = findViewById(R.id.canvas_btn);
        canvas_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCanvasActivity.enterActivity(UIWidgetActivity.this);
            }
        });
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


        TextView mTvPrivacy = findViewById(R.id.tv_test_privacy);
        String str1 = "《羊毛省钱隐私政策》";
        String str2 = "《软件使用及服务协议》";

        String str = String.format(getString(R.string.welcome_person_privacy_policy), str1, str2);
        Log.d(TAG, "" + str);

        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(str);


        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                if (widget instanceof TextView) {
                    TextView tv = (TextView) widget;
                    Log.d(TAG, "点击位置：" + tv.getText());
                }
                Toast.makeText(getApplicationContext(), "点击文案", Toast.LENGTH_SHORT).show();
                initUtils();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setColor(getResources().getColor(R.color.red_b));
                ds.setUnderlineText(false);
            }
        };

        int firstLength = str1.length();
        int secondLength = str2.length();

        spannableString.setSpan(new ClickableSpan() {
                                    @Override
                                    public void onClick(@NonNull View widget) {
                                        initUtils();
                                    }

                                    @Override
                                    public void updateDrawState(@NonNull TextPaint ds) {
                                        ds.setColor(getResources().getColor(R.color.red_b));
                                        ds.setUnderlineText(false);
                                    }
                                }, 15, 25,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(clickableSpan, 26, 38,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //设置部分文字颜色
//        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#ff5793"));
//        spannableString.setSpan(foregroundColorSpan, 15, 25,
//                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//
//        spannableString.setSpan(foregroundColorSpan, 26, 38,
//                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);


        // 配置给TextView
        mTvPrivacy.setMovementMethod(LinkMovementMethod.getInstance());
        mTvPrivacy.setText(spannableString);

    }

    int first;

    private void initButton() {
        // 返利弹框
        findViewById(R.id.btn_show_enter_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tpMallDialog == null) {
                    tpMallDialog = new EnterTpMallDialog(UIWidgetActivity.this, EnterTpMallDialog.DIALOG_STYLE_TYPE_PDD, "");
                }
                if (first == 1) {
                    tpMallDialog.updateType(EnterTpMallDialog.DIALOG_STYLE_TYPE_PDD);
                } else if (first == 2) {
                    tpMallDialog.updateType(EnterTpMallDialog.DIALOG_STYLE_TYPE_JINGDONG);
                } else {
                    tpMallDialog.updateType(EnterTpMallDialog.DIALOG_STYLE_TYPE_VIP);
                    first = 0;
                }

                tpMallDialog.show();
                first++;
            }
        });

        checkBox = findViewById(R.id.circle_check_box);
        checkBox.setListener(isChecked -> {
            // do something
            if (isChecked) {
                checkBox.setChecked(false);
            } else {
                checkBox.setChecked(true);
            }
        });

        // goto WebView
        Button mBtnShowWeb = findViewById(R.id.btn_show_web);
        mBtnShowWeb.setOnClickListener(v -> gotoWebViewActivity());

        Button mBtnShowVideo = findViewById(R.id.btn_show_video);
        mBtnShowVideo.setOnClickListener(v -> gotoVideoActivity());

        Button mBtnSendBroadCast = findViewById(R.id.btn_send_broadcast);
        mBtnSendBroadCast.setOnClickListener(v -> sendBroadCastActivity());


        //
        TextView mTvLoading = findViewById(R.id.tv_loading);
        RelativeLayout mRlLoadingLayout = findViewById(R.id.rl_loading_layout);

        int width = DeviceUtils.getScreenWidth(getApplicationContext()) - getResources().getDimensionPixelOffset(R.dimen.dp_value_48);
        Log.i(TAG, "loading width = " + width);
        AnimationUtils.dialogLoadingAnimation(mRlLoadingLayout, mTvLoading, width);
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


    /**
     * goto WebView
     */
    private void gotoWebViewActivity() {
        Intent intent = new Intent();
        intent.putExtra("url", "https://www.taobao.com");
        intent.setClass(UIWidgetActivity.this, UIWebViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * goto WebView
     */
    @AspectJAnnotation(value = Manifest.permission.CAMERA)
    public void gotoVideoActivity() {
        Intent intent = new Intent();
        intent.setClass(UIWidgetActivity.this, UIVideoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    /**
     * goto WebView
     */
    @AspectJAnnotation(value = Manifest.permission.CAMERA)
    public void sendBroadCastActivity() {
        Intent intent = new Intent();
        intent.setAction(MyBroadCastReceived.ACTION_BROADCAST);
        sendBroadcast(intent);
    }


    private void initUtils() {
        boolean isOpen = NotifationUtils.isOpenNotification(getApplicationContext());
        Toast.makeText(getApplicationContext(), " 通知权限是否打开：" + isOpen, Toast.LENGTH_LONG).show();
        if (!isOpen) {
            NotifationUtils.openSettingPage(UIWidgetActivity.this);
        }
    }

    /**
     * Handler消息处理机制
     */

    private MyHandler mHandler;

    private void initHandler() {
        if (mHandler == null) {
            mHandler = new MyHandler(this);
        }

        // 执行MyAsyncTask
        //new MyAsyncTask().execute();

        DownLoadImageUtils.getInstance().downLoadImgWithHandlerThread(mUIHandler);

        LruCache<String, Bitmap> mLruCache;
        Bundle bundle = new Bundle();
        bundle.putSerializable("key", new TabHomeModel());

        // 获取最小滑动距离
        int touchSlop = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();
        Log.e("key", "touchSlop = " + touchSlop);


    }

    // 使用静态内部类
    static class MyHandler extends Handler {
        WeakReference<Activity> mActivityReference;

        MyHandler(Activity activity) {
            mActivityReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity activity = mActivityReference.get();

        }
    }

    private void startService() {
        startService(new Intent());

        //连续启动Service
        Intent intentOne = new Intent(this, MyService.class);
        startService(intentOne);
        Intent intentTwo = new Intent(this, MyService.class);
        startService(intentTwo);
        Intent intentThree = new Intent(this, MyService.class);
        startService(intentThree);

//        bindService(intentOne);

//        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();
//
//        registerReceiver(myBroadCastReceiver, new IntentFilter());

    }

    private void initContentProvide() {
        /**
         * 对user表进行操作
         */

        // 设置URI
        Uri uri_user = Uri.parse("content://cn.scu.myprovider/user");

        // 插入表中数据
        ContentValues values = new ContentValues();
        values.put("_id", 3);
        values.put("name", "Iverson");


        // 获取ContentResolver
        ContentResolver resolver = getContentResolver();
        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        resolver.insert(uri_user, values);

        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor = resolver.query(uri_user, new String[]{"_id", "name"}, null, null, null);
        while (cursor.moveToNext()) {
            System.out.println("query book:" + cursor.getInt(0) + " " + cursor.getString(1));
            // 将表中数据全部输出
        }
        cursor.close();
        // 关闭游标

        /**
         * 对job表进行操作
         */
        // 和上述类似,只是URI需要更改,从而匹配不同的URI CODE,从而找到不同的数据资源
        Uri uri_job = Uri.parse("content://cn.scu.myprovider/job");

        // 插入表中数据
        ContentValues values2 = new ContentValues();
        values2.put("_id", 3);
        values2.put("job", "NBA Player");

        // 获取ContentResolver
        ContentResolver resolver2 = getContentResolver();
        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        resolver2.insert(uri_job, values2);

        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor2 = resolver2.query(uri_job, new String[]{"_id", "job"}, null, null, null);
        while (cursor2.moveToNext()) {
            System.out.println("query job:" + cursor2.getInt(0) + " " + cursor2.getString(1));
            // 将表中数据全部输出
        }
        cursor2.close();
        // 关闭游标
    }
}
