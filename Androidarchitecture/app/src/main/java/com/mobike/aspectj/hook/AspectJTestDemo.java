package com.mobike.aspectj.hook;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import com.mobike.android_architecture.utils.PermissionUtils;
import com.mobike.uilibrary.annotation.AspectJAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Aspect AOP hook 所有方法
 * Created by yangdehao@xiaoyouzi.com  on 2019-05-08 10:50
 */
@Aspect
public class AspectJTestDemo {

    private final String TAG = "AspectJTestDemo";
    private static final String ACTIVITY_METHOD_CALL = "execution(* com.mobike.android_architecture.MainActivity.initService(..))";

    /**
     * 调用Activity方法前插入
     */
    @Pointcut(ACTIVITY_METHOD_CALL)
    public void executionActivityMethod() {

    }

    @Before("executionActivityMethod()")
    public void ActivityMethodBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Context context = (Context) joinPoint.getThis();
        Toast.makeText(context.getApplicationContext(), " AOP 执行MainActivity方法：" + methodName, Toast.LENGTH_LONG).show();

        Log.d(TAG, "AspectJTestDemo onActivityMethodBefore: " + methodName);
    }


    /**
     * 调用Activity方法后插入
     */
//    @Before("call(* com.mobike.uilibrary.widget.UIWidgetActivity.onCreate(..)")
//    public void onActivityMethodAfter(JoinPoint joinPoint) {
//        String key = joinPoint.getSignature().toString();
//        Log.d(TAG, "AspectJTestDemo onActivityMethodAfter: " + key);
//        System.out.println("AspectJTestDemo UIWidgetActivity log");
//    }
    @After("execution(* android.view.View.OnClickListener.onClick(..))")
    public void onClickMethodAround(JoinPoint joinPoint) {
        Log.d(TAG, "AspectJTestDemo onClickMethodAround: ");
    }

    /**
     * AspectJAnnotation 注解的任意类任意方法
     */
    @Pointcut("execution(@com.mobike.uilibrary.annotation.AspectJAnnotation  * *(..))")
    public void executionAspectJ() {

    }

    // 检查是否有权限
    @Around("executionAspectJ()")
    public Object aroundAspectJ(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Log.i(TAG, "aroundAspectJ(ProceedingJoinPoint joinPoint)");
        AspectJAnnotation aspectJAnnotation = methodSignature.getMethod().getAnnotation(AspectJAnnotation.class);
        String permission = aspectJAnnotation.value();
        Context context = (Context) joinPoint.getThis();
        Object o = null;
        if (PermissionUtils.hasPermission(context, permission)) {
            o = joinPoint.proceed();
            Log.i(TAG, "有权限");
            Toast.makeText(context, " 有权限CAMERA", Toast.LENGTH_LONG).show();

        } else {
            Log.i(TAG, "没有权限，不给用");
            Toast.makeText(context, " 没有权限CAMERA", Toast.LENGTH_LONG).show();

            // 打开设置页面
            context.startActivity(new Intent(Settings.ACTION_APPLICATION_SETTINGS));
        }
        return o;
    }


}
