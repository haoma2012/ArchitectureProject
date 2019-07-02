package com.mobike.uilibrary.thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 构建单例模式
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/24 下午7:18
 */
public class DownLoadImageUtils {
    /**
     * 图片地址集合
     */
    private String url[] = {
            "http://img.blog.csdn.net/20160903083245762",
            "http://img.blog.csdn.net/20160903083252184",
            "http://img.blog.csdn.net/20160903083257871",
            "http://img.blog.csdn.net/20160903083257871",
            "http://img.blog.csdn.net/20160903083311972",
            "http://img.blog.csdn.net/20160903083319668",
            "http://img.blog.csdn.net/20160903083326871"
    };

    private static DownLoadImageUtils mInstance;

    private HandlerThread mDownThread;


//    public static DownLoadImageUtils getInstance() {
//        if (mInstance == null) {
//            mInstance = new DownLoadImageUtils();
//        }
//        return mInstance;
//    }

    // 4.DCL单例（双重检查锁定）
    public static DownLoadImageUtils getInstance() {
        if (mInstance == null) {
            synchronized (DownLoadImageUtils.class) {
                if (mInstance == null) {
                    mInstance = new DownLoadImageUtils();
                }
            }
        }
        return mInstance;
    }


    public void downLoadImgWithHandlerThread(Handler uiHandler) {
        if (uiHandler == null) {
            return;
        }
        if (mDownThread == null) {
            mDownThread = new HandlerThread("DownLoadThread");
        }

        mDownThread.start();
        //通过 Looper 初始化 Handler
        Handler handler = new Handler(mDownThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Bitmap bitmap = downloadUrlBitmap(url[msg.what]);
                Message msg1 = new Message();
                msg1.what = msg.what;
                msg1.obj = bitmap;

                uiHandler.sendMessage(msg1);
                return false;
            }
        });

        for (int i = 0; i < 7; i++) {
            handler.sendEmptyMessageDelayed(i, 1000 * i);
        }


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();

    }


    private Bitmap downloadUrlBitmap(String urlString) {
        if (TextUtils.isEmpty(urlString)) {
            return null;
        }
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        Bitmap bitmap = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }


}
