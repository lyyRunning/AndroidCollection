package com.function.luo.collection.greenDao.utils;

import android.database.Cursor;


import com.function.luo.collection.base.DbBean;
import com.function.luo.collection.bean.DaoSession;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.InternalQueryDaoAccess;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;


/**
 * @author lyy
 */
public class DaoService {


    /**
     * 通过不同Dao的class文件获取Dao的实例
     * @param clazz Dao的class
     * @return Dao的实例，例如CInfoTypeBeanDao
     */
    public static AbstractDao getDaoInstance(Class clazz) {
        if (clazz == null) {
            return null;
        }
        DaoSession daoSession = DbManager.getMdaoSession();
        daoSession.clear();
        Class<?> daoClass = daoSession.getClass();
        Field[] fields = daoClass.getDeclaredFields();
        String daoClassName = clazz.getSimpleName() + "Dao";
        for (Field field : fields) {
            String fieldTypeName = field.getType().getSimpleName();

            if (fieldTypeName.equalsIgnoreCase(daoClassName)) {
                try {
                    field.setAccessible(true);
                    AbstractDao dao = (AbstractDao) field.get(daoSession);
                    return dao;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 查询数据库某表下的所有数据
     *
     * @param clazz 需要查询的数据Bean.class
     * @return 表下的所有数据Bean的集合
     * @throws ClassCastException
     */
    public static List<? extends DbBean> loadAllDbBean(Class<? extends DbBean> clazz) {
        List<DbBean> beans = null;
        AbstractDao dao = null;
        try {
            dao = getDaoInstance(clazz);
            beans = dao.loadAll();
        } catch (ClassCastException e) {
            //LogUtils.e(e.getMessage());
            throw new ClassCastException();
        }
        return beans;
    }

    /**
     * 删除数据库某表下的所有数据
     *
     * @param clazz 需要删除的数据Bean.class
     * @throws ClassCastException
     */
    public static boolean deleteAllDbBean(Class<? extends DbBean> clazz) {
        List<DbBean> beans = null;
        AbstractDao dao = null;
        try {
            dao = getDaoInstance(clazz);
            dao.deleteAll();
            return true;
        } catch (ClassCastException e) {
            //LogUtils.e(e.getMessage());
            throw new ClassCastException();
        }
    }


    /**
     * 通过测试   CommonDao.insertOrReplaceDaoBean(？extends DbBean bean);
     * 插入数据到本地数据库
     *
     * @param bean GreenDao数据bean
     * @throws Exception 主键id空
     */
    public static void insertOrReplaceDaoBean(DbBean bean) {
        getDaoInstance(bean.getClass()).insertOrReplace(bean);
    }

    /**
     * 删除by KeyId
     * 从本地数据删除数据
     */
    public static void deleteDaoBeanByKey(Class<? extends DbBean> clazz, Long keyId) {
        try {
            getDaoInstance(clazz).deleteByKey(keyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     * 从本地数据删除数据
     */
    public static void deleteDaoBean(DbBean bean) {
        getDaoInstance(bean.getClass()).delete(bean);
    }

    /**
     * 删除
     * 从本地数据删除数据列表
     */
    public static void deleteDaoBeanListInTx(List<? extends DbBean> beanList) {
        if (beanList != null && beanList.size() != 0) {
            AbstractDao dao = getDaoInstance(beanList.get(0).getClass());
            dao.deleteInTx(beanList);
        }
    }


    /**
     * 删除by KeyId  list
     * 从本地数据删除数据列表
     */
    public static void deleteDaoBeanByKeyList(Class<? extends DbBean> clazz, List<Long> keyList) {
        try {
            for (Long keyId : keyList) {
                getDaoInstance(clazz).deleteByKey(keyId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入数据到本地数据库
     *
     * @param list GreenDao数据bean集合,少量数据可使用
     * @throws Exception 主键id空
     */
    /*public static void insertOrReplaceDaoBeanList(List<? extends DbBean> list) {
        for (DbBean bean : list) {
            insertOrReplaceDaoBean(bean);
        }
    }*/

    /**
     * 插入数据到本地数据库
     *
     * @param list GreenDao数据bean集合
     * @throws Exception 主键id空
     */
    public static void insertOrReplaceDaoBeanList(List<? extends DbBean> list) {
        if (list != null && list.size() != 0) {
            getDaoInstance(list.get(0).getClass()).insertOrReplaceInTx(list);
        }
    }

    /**
     * 调用数据库语句查询
     * 几个参数的拼接要注意
     * TableName =  xxDao.TABLENAME;
     * 各个字段  = xxDao.Properties.XX.columnName
     */
    public static List<? extends DbBean> selectDaoBeanbyConditationSQL(Class<? extends DbBean> clazz, String sql) {
        AbstractDao dao = null;
        try {
            dao = getDaoInstance(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Cursor cursor = dao.getDatabase().rawQuery(sql, null);
        InternalQueryDaoAccess daoAccess = new InternalQueryDaoAccess<>(dao);
        return daoAccess.loadAllAndCloseCursor(cursor);
    }

    /**
     * 查询所有未上传的数据
     *
     * @param clazz 需要查询的数据Bean.class
     * @return 表下的所有数据Bean的集合
     * @throws ClassCastException
     */
    /*public static List<? extends DbBean> loadAllUnUpLoadDbBean(Class<? extends DbBean> clazz) {
        Field fieldisupload = null;
        Field[] filds = clazz.getFields();
        for (Field field : filds) {
            //是否上传字段
            field.setAccessible(true);
            if (field.isAnnotationPresent(NeedUpLoad.class)) {
                fieldisupload = field;
            }
        }

        List<DbBean> beans = null;
        AbstractDao dao = null;
        try {
            dao = getDaoInstance(clazz);
            beans = dao.loadAll();
        } catch (ClassCastException e) {
            LogUtils.e(e.getMessage());
            throw new ClassCastException();
        }
        return beans;
    }*/


    /**
     * 条件查询
     *
     * @return 匹配条件的集合
     */
    public static List<? extends DbBean> selectDaoBeanWhereCondition(Class<? extends DbBean> clazz, WhereCondition... whereConditions) throws Exception {
        try {
            QueryBuilder queryBuilder = getDaoInstance(clazz).queryBuilder();
            for (WhereCondition whereCondition : whereConditions) {
                queryBuilder.where(whereCondition);
            }
            List<DbBean> dbBeans = queryBuilder.list();
            return dbBeans;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 简单的and条件查询,经过简单测试
     *
     * @return 匹配条件的集合
     */
    public static List<? extends DbBean> selectDaoBeanByAndCondition(Class<? extends DbBean> clazz, PropertyParam... params) throws Exception {
        List<Property> list = Arrays.asList(getDaoInstance(clazz).getProperties());
        QueryBuilder queryBuilder = getDaoInstance(clazz).queryBuilder();
        for (PropertyParam param : params) {
            //是他的属性
            if (list.contains(param.getKeyProperty())) {
                queryBuilder.where(param.getKeyProperty().eq(param.getValueObject()));
            } else {
                throw new Exception("兄弟，属性搞错了");
            }
        }
        List<DbBean> cInfoTypeBeanList = queryBuilder.list();
        return cInfoTypeBeanList;
    }

    /**
     * 查询条件
     */
    public static class PropertyParam {
        Property keyProperty;
        Object valueObject;

        public PropertyParam(Property keyProperty, Object valueObject) {
            this.keyProperty = keyProperty;
            this.valueObject = valueObject;
        }

        public Property getKeyProperty() {
            return keyProperty;
        }

        public Object getValueObject() {
            return valueObject;
        }
    }




}
