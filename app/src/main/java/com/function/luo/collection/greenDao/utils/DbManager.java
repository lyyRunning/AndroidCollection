package com.function.luo.collection.greenDao.utils;


import android.database.sqlite.SQLiteDatabase;

import com.function.luo.collection.BaseApplication;
import com.function.luo.collection.bean.DaoMaster;
import com.function.luo.collection.bean.DaoSession;


/**
 * Created by lyy
 * 配置数据库
 */

public class DbManager {
    private static String DBName = "com.function.luo.dataBase";
    private static MysqliteOpenHelper mdevOpenHelper = null;
    private static DaoSession mdaoSession = null;

    private static DaoSession outsideDaoSession = null;

    private static MysqliteOpenHelper getDevOpenHelper() {
        if (mdevOpenHelper == null) {
            //mdevOpenHelper = new DaoMaster.DevOpenHelper(BaseApplication.getInstance(), DBName, null);
            //为了使数据库升级的时候，数据能够保存
            //BaseApplication.getInstance()不能为空,一定要有值啊
            mdevOpenHelper = new MysqliteOpenHelper(BaseApplication.getInstance(), DBName, null);
        }
        return mdevOpenHelper;
    }

    public static DaoSession getMdaoSession() {
        if (mdaoSession == null) {
            DaoMaster daoMaster = new DaoMaster(getDevOpenHelper().getWritableDb());
            mdaoSession = daoMaster.newSession();
        }
        return mdaoSession;
    }

    public static DaoSession getOutsideDaoSession(String outSidePath) {

        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(outSidePath, null);

        DaoMaster daoMaster = new DaoMaster(db);
        outsideDaoSession = daoMaster.newSession();

        return outsideDaoSession;
    }


    /**
     * 获取可写数据库
     */
    private static SQLiteDatabase getWritableDatabase() {
        if (mdevOpenHelper == null) {
            mdevOpenHelper = getDevOpenHelper();
        }
        SQLiteDatabase db = mdevOpenHelper.getWritableDatabase();
        return db;
    }
}
