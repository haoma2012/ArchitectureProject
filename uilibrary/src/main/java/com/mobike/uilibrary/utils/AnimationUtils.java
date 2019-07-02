package com.mobike.uilibrary.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.mobike.uilibrary.R;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/12 下午11:17
 */
public class AnimationUtils {

    private static final int MS_IN_FUTURE = 2000;

    public static void doProgressAnimation(ProgressBar progressBarTop, ValueAnimator.AnimatorUpdateListener listener) {
        ObjectAnimator objectAnimatorTop = ObjectAnimator
                // 这里的animate的目标值是0，所以在updateListener我们获取到剩余的进度
                // 改成progressBar.getMax()，获取到的就是正向的进度了
                .ofInt(progressBarTop, "progress", 0)
                .setDuration(MS_IN_FUTURE);
        // 保证动画的插值器是线性的
        objectAnimatorTop.setInterpolator(new LinearInterpolator());
        objectAnimatorTop.addUpdateListener(listener);
        objectAnimatorTop.start();
    }


    public static void dialogLoadingAnimation(View contentView, View view, int width) {
        if (view == null || contentView == null) {
            return;
        }

        view.setVisibility(View.GONE);
        ValueAnimator widthAnimator = ValueAnimator.ofInt(0, width);
        widthAnimator.addUpdateListener(arg0 -> {
            int value = (int) arg0.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
            layoutParams.width = value;
            contentView.setLayoutParams(layoutParams);
            if (value == width) {
                view.setVisibility(View.VISIBLE);
            }
        });
        widthAnimator.setDuration(800);

        ObjectAnimator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("scaleX", 0.8f, 1.0f),
                PropertyValuesHolder.ofFloat("scaleY", 0.8f, 1.0f));
        scaleAnimator.setInterpolator(new AccelerateInterpolator());
        scaleAnimator.setDuration(300);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(widthAnimator).before(scaleAnimator);
        animatorSet.start();
    }


    /**
     * 逐帧动画
     *
     * @param refreshView ImageView
     */
    public static void animationImageView(ImageView refreshView) {
        if (refreshView == null) {
            return;
        }
        refreshView.setImageResource(R.drawable.refresh_header_animation);
        AnimationDrawable mIconAnimation = (AnimationDrawable) refreshView.getDrawable();
        mIconAnimation.setOneShot(false);

        mIconAnimation.start();

    }
}
