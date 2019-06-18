package com.function.luo.collection.greenDao.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.function.luo.collection.bean.DaoMaster;


/**
 * Created by cyb on 2018/04/03.
 * 版本更新需要添加字段
 */

public class MysqliteOpenHelper extends DaoMaster.OpenHelper {

    public MysqliteOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MysqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 需要在实体类加一个字段 或者 改变字段属性等 就需要版本更新来保存以前的数据了
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        if (oldVersion < newVersion) {
            //这里添加要增加的字段
            //MigrationHelper.migrate(db, AUserInfoDao.class);
        }
        //有数据变化的所有Dao.class,新增的字段要以(Double,Integer,Float,Boolean)为类型,否则会导致数据复制不成功
    }
}
