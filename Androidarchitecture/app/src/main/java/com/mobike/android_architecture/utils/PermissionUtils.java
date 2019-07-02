package com.mobike.android_architecture.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import com.mobike.uilibrary.view.dialog.XiuAlertDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 权限申请工具类
 * Created by yangdehao@xiaoyouzi.com  on 2017/7/3 上午9:17
 */

public class PermissionUtils {
    private static final String TAG = "PermissionUtils";
    public static final int PERMISSION_REQUEST_CODE = 0x10;
    public static final int PERMISSION_SETTING_REQ_CODE = 0x1000;

    /**
     * 检查是否拥有权限
     *
     * @param context     context
     * @param permissions 权限
     * @return true or false
     */
    public static boolean hasPermission(@NonNull Context context, @NonNull String... permissions) {
        return hasPermission(context, Arrays.asList(permissions));
    }

    //权限是否授权 PERMISSION_GRANTED 授权  PERMISSION_DENIED 拒绝
    public static boolean hasPermission(@NonNull Context context, @NonNull List<String> permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true;
        for (String permission : permissions) {
            String op = AppOpsManagerCompat.permissionToOp(permission);
            if (TextUtils.isEmpty(op)) continue;
            int result = AppOpsManagerCompat.noteProxyOp(context, op, context.getPackageName());
            if (result == AppOpsManagerCompat.MODE_IGNORED) return false;
            result = ContextCompat.checkSelfPermission(context, permission);
            if (result != PackageManager.PERMISSION_GRANTED) return false;
        }
        return true;
    }


    /**
     * 用户拒绝过这个权限了
     *
     * @param cxt
     * @param permission
     * @return 点击禁止授权 返回true
     */
    public static boolean shouldShowRequestPermissionRationaleWrapper(Context cxt, String permission) {
        Activity activity = (Activity) cxt;
        return ActivityCompat.shouldShowRequestPermissionRationale(activity,
                permission);
    }

    /**
     * 用户拒绝过这个权限了
     *
     * @param context
     * @param permissions
     * @return 点击禁止授权 返回true
     */
    public static boolean hasAlwaysDeniedPermission(@NonNull Context context, @NonNull String... permissions) {
        return !shouldShowRationalePermissions(context, Arrays.asList(permissions));
    }

    private static boolean shouldShowRationalePermissions(Context context, List<String> permissions) {
        Activity mActivity = (Activity) context;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return false;
        for (String permission : permissions) {
            boolean rationale = mActivity.shouldShowRequestPermissionRationale(permission);
            if (rationale) return true;
        }
        return false;

    }


    /**
     * 调用权限申请
     *
     * @param context
     * @param permission
     * @param requestCode
     */
    public static void requestPermissionsWrapper(Context context, String[] permission, int requestCode) {
        Activity activity = (Activity) context;
        ActivityCompat.requestPermissions(activity, permission, requestCode);
    }

    /**
     * 添加未授权列表
     *
     * @param context
     * @param permission
     * @return
     */
    private static String[] checkSelfPermissionArray(Context context, String[] permission) {
        ArrayList<String> permissionList = new ArrayList<>();
        for (String p : permission) {
            if (!hasPermission(context, p)) {
                permissionList.add(p);
            }
        }

        return permissionList.toArray(new String[permissionList.size()]);
    }

    //申请权限数组
    public static boolean checkPermissionArray(Context cxt, String[] permission, int requestCode) {
        String[] permissionNo = checkSelfPermissionArray(cxt, permission);
        if (permissionNo.length > 0) {
            requestPermissionsWrapper(cxt, permissionNo, requestCode);
            return false;
        } else {
            return true;
        }
    }


    /**
     * 打开应用设置界面
     */
    private static void doIntentSettingActivity(Activity activity, int req) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent, req);
    }

    /**
     * 去允许权限dialog
     */
    public static void showPermissionCheckDialog(final Activity activity, String content, String buttonOkText, final String permission) {
        final XiuAlertDialog dialog = new XiuAlertDialog(activity, "", content);
        dialog.setButtonOkText(buttonOkText)
                .showOneButton()
                .setOnClickListener(new XiuAlertDialog.onDialogClickListener() {
                    @Override
                    public void onOk() {
                        requestPermissionsWrapper(activity, new String[]{permission}, PERMISSION_REQUEST_CODE);
                        dialog.dismissDialogEx();
                    }

                    @Override
                    public void onCancle() {

                    }
                });
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 去设置界面权限dialog
     */
    public static void showPermissionSettingDialog(final Activity activity, String content, String buttonOkText) {
        final XiuAlertDialog dialog = new XiuAlertDialog(activity, "", content);
        dialog.setButtonOkText(buttonOkText)
                .showOneButton()
                .setOnClickListener(new XiuAlertDialog.onDialogClickListener() {
                    @Override
                    public void onOk() {
                        doIntentSettingActivity(activity, PERMISSION_SETTING_REQ_CODE);
                        dialog.dismissDialogEx();
                    }

                    @Override
                    public void onCancle() {

                    }
                });

        dialog.setCancelable(false);
        dialog.show();
    }
}
