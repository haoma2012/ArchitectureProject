package com.mobike.uilibrary.view.dialog;


import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 基类DialogFragment
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/27 下午5:20
 */
public class BaseDialogFragment extends DialogFragment {
    public static final String TAG = BaseDialogFragment.class.getSimpleName();
    private Dialog dialog;
    private String title;
    private String message;
    private DialogInterface.OnClickListener positiveCallback;
    private DialogInterface.OnClickListener negativeCallback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return getDialog();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void show(String title, String message, DialogInterface.OnClickListener positiveCallback,
                     DialogInterface.OnClickListener negativeCallback,
                     FragmentManager manager, String tag) {
        this.title = title;
        this.message = message;
        this.positiveCallback = positiveCallback;
        this.negativeCallback = negativeCallback;
        show(manager, tag);
    }

    public Dialog getDialog() {
        if (dialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton("确定", positiveCallback);
            builder.setNegativeButton("取消", negativeCallback);
            dialog = builder.create();
        }

        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
}
