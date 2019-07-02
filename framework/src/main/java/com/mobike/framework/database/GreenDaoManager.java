package com.mobike.framework.database;

import android.content.Context;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-05-07 17:00
 */
public class GreenDaoManager {

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private MyBeanDao myBeanDao; // 数据表

    /**
     * 静态内部类，实例化对象使用
     */
    private static class SingleInstanceHolder {
        private static final GreenDaoManager INSTANCE = new GreenDaoManager();
    }

    /**
     * 对外唯一实例的接口
     *
     * @return GreenDaoManager
     */
    public static GreenDaoManager getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }

    /**
     * Application初始化数据
     */
    public void init(Context context) {
        if (context == null) {
            return;
        }
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context,
                "Android_Architecture");
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getmDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    /**
     * 获取数据表
     * @return MyBeanDao
     */
    public MyBeanDao getMyBeanDao() {
        if (myBeanDao == null) {
            myBeanDao = getmDaoSession().getMyBeanDao();
        }

        return myBeanDao;
    }


    public void addMembers() {

    }

}
