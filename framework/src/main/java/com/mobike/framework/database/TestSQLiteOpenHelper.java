package com.mobike.framework.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-05-07 15:08
 */
public class TestSQLiteOpenHelper extends SQLiteOpenHelper {
    // 数据库版本号
    private static Integer DBVersion = 1;

    public TestSQLiteOpenHelper(Context context, String name) {
        this(context, name, DBVersion);
    }

    //参数说明
    //context:上下文对象
    //name:数据库名称
    //param:factory
    //version:当前数据库的版本，值必须是整数并且是递增的状态
    public TestSQLiteOpenHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    public TestSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("创建数据库和表");
        //创建了数据库并创建一个叫records的表
        //SQLite数据创建支持的数据类型： 整型数据，字符串类型，日期类型，二进制的数据类型
        String sql = "create table user(id int primary key,name varchar(200))";
        //execSQL用于执行SQL语句
        //完成数据库的创建
        db.execSQL(sql);
        //数据库实际上是没有被创建或者打开的，直到getWritableDatabase() 或者 getReadableDatabase() 方法中的一个被调用时才会进行创建或者打开

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 使用 SQL的ALTER语句
        System.out.println("更新数据库版本为:" + newVersion);
        String sql = "alter table person add sex varchar(8)";
        db.execSQL(sql);
    }
}
