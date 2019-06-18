package com.function.luo.collection.greenDao.utils;



import com.function.luo.collection.base.DbBean;
import com.function.luo.collection.bean.DaoSession;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;


public abstract class BaseDaoService {

    public abstract DaoSession getDaoSession();

    /**
     * 依据Bean Class查询对应的Dao实例。
     * Dao对象名必须以 Bean Class名 + Dao组成。
     * 例如：CInfoTypeBean 对应的 Dao 为 CInfoTypeBeanDao
     *
     * @param clazz Bean Class
     * @return Bean Class对应的Dao实例
     */
    public AbstractDao getDaoInstance(Class<? extends DbBean> clazz) {
        if (clazz == null) {
            return null;
        }
        DaoSession daoSession = getDaoSession();
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
     * 通过不同Dao的class文件获取Dao的实例
     *
     * @param bean Dao的class
     * @return Dao的实例，例如CInfoTypeBeanDao
     */
    public AbstractDao getDaoInstance(DbBean bean) {
        Class<? extends DbBean> clazz = bean.getClass();
        return getDaoInstance(clazz);
    }


    public List<? extends DbBean> loadAllDbBean(Class<? extends DbBean> clazz) throws ClassCastException {
        List<? extends DbBean> beans = null;
        AbstractDao dao = getDaoInstance(clazz);
        try {
            beans = dao.loadAll();
        } catch (ClassCastException e) {
            throw new ClassCastException();
        }
        return beans;
    }

    public List<? extends DbBean> loadAllDbBeanWithCondition(Class<? extends DbBean> clazz, WhereCondition whereCondition) throws ClassCastException {
        List<? extends DbBean> beanList = null;
        AbstractDao dao = getDaoInstance(clazz);

        QueryBuilder queryBuilder = dao.queryBuilder();
        if (null != whereCondition) {
            queryBuilder.where(whereCondition);
        }
        try {
            beanList = queryBuilder.build().list();
        } catch (ClassCastException e) {
            throw new ClassCastException();
        }
        return beanList;
    }

    public List<? extends DbBean> loadAllDbBeanWithConditionAndOrderByAsc(Class<? extends DbBean> clazz, List<WhereCondition> whereCondition, Property property) throws ClassCastException {
        List<? extends DbBean> beanList = null;
        AbstractDao dao = getDaoInstance(clazz);

        QueryBuilder queryBuilder = dao.queryBuilder();
        queryBuilder.orderAsc(property);
        if (null != whereCondition) {
            for (WhereCondition whereCondition1 : whereCondition) {
                queryBuilder.where(whereCondition1);
            }
        }
        try {
            beanList = queryBuilder.build().list();
        } catch (ClassCastException e) {
            throw new ClassCastException();
        }
        return beanList;
    }

    public List<? extends DbBean> loadAllDbBeanWithConditions(Class<? extends DbBean> clazz, List<WhereCondition> whereCondition) throws ClassCastException {
        List<? extends DbBean> beanList = null;
        AbstractDao dao = getDaoInstance(clazz);
        QueryBuilder queryBuilder = dao.queryBuilder();
        if (null != whereCondition) {
            for (WhereCondition whereCondition1 : whereCondition) {
                queryBuilder.where(whereCondition1);
            }
        }
        try {
            beanList = queryBuilder.build().list();
        } catch (ClassCastException e) {
            throw new ClassCastException();
        }
        return beanList;
    }

    public <T extends DbBean> T loadDbBeanWithCondition(Long rowId, Class<? extends DbBean> clazz, WhereCondition whereCondition) throws ClassCastException {
        T t = null;
        AbstractDao dao = getDaoInstance(clazz);

        QueryBuilder queryBuilder = dao.queryBuilder();
        if (null != whereCondition) {
            queryBuilder.where(whereCondition);
        }
        try {
            t = (T) dao.loadByRowId(rowId);
        } catch (ClassCastException e) {
            throw new ClassCastException();
        }

        return t;
    }

    /**
     * 检查Bean 主键值是否合法
     *
     * @param bean 实体Bean
     * @return 是否合法。True=合法；FALSE=非法
     */
    private boolean checkBeanPrimaryKeyValidate(DbBean bean) {
        Class<?> cls = bean.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] fieldAnnotations = field.getAnnotations();
            for (Annotation annotation : fieldAnnotations) {
                if (annotation.annotationType() == Id.class) {
                    Id idAnnotation = (Id) annotation;
                    if (!idAnnotation.autoincrement()) {
                        Object obj = null;
                        try {
                            obj = field.get(bean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (obj == null) {
                            return false;
                        }
                    }
                    break;
                }
            }
        }
        return true;
    }

  /*  *//**
     * @param parentBean
     * @throws
     *//*
    private void saveSubObject(DbBean parentBean) throws Exception {
        Class<? extends DbBean> parentClass = parentBean.getClass();
        Field[] fields = parentClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] fieldAnnotations = field.getAnnotations();
            for (Annotation anno : fieldAnnotations) {
                if (anno.annotationType() == RestOneToMany.class) {
                    try {
                        Object object = field.get(parentBean);
                        if (object instanceof List) {
                            List<?> subItemList = (List<?>) object;
                            for (Object subObj : subItemList) {
                                if (subObj instanceof DbBean) {
                                    save((DbBean) subObj);
                                    saveSubObject((DbBean) subObj);
                                }
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }*/

    /**
     * 保存Bean对象
     *
     * @param bean bean实体对象
     * @throws
     */
    public void save(DbBean bean) throws Exception {
        boolean checkValid = checkBeanPrimaryKeyValidate(bean);
        if (!checkValid) {
            throw new Exception("主键Id不允许为空,不允许自增长");

        }

        AbstractDao dao = getDaoInstance(bean);
        if (dao != null) {
            dao.insertOrReplace(bean);
           // saveSubObject(bean);
        } else {
            throw new Exception("AbstractDao Is Null");
        }
    }

    /**
     * 保存Bean实体类列表
     * 若保存具体每个Bean实体类时发生错误，则返回FALSE。
     *
     * @param beanList Bean实体类列表
     * @return 是否保存成功
     */
    public boolean save(List<DbBean> beanList) {
        boolean hasError = false;
        for (DbBean bean : beanList) {
            try {
                save(bean);
            } catch (Exception ex) {
                hasError = true;
                ex.printStackTrace();
            }
        }
        return !hasError;
    }

    /**
     * 依据主键值进行查询对象
     *
     * @param clazz        Bean实体类Class
     * @param pkFieldValue 主键值
     * @return 主键对象
     */
    public Object selectByPrimaryKey(Class<? extends DbBean> clazz, Object pkFieldValue) {
        AbstractDao dao = getDaoInstance(clazz);

        Object obj = dao.load(pkFieldValue);
        return obj;
    }

    public boolean deleteAllBean(Class<? extends DbBean> clazz) {
        AbstractDao dao = getDaoInstance(clazz);
        dao.deleteAll();
        return true;
    }

    public boolean deleteBean(DbBean bean) {
        AbstractDao dao = getDaoInstance(bean);
        dao.delete(bean);
        return true;
    }

    public boolean saveInTransaction(Class<? extends DbBean> clazz, List<DbBean> beanList) {
        if (beanList.size() == 0) {
            return true;
        }

//        Class clazz = beanList.get(0).getClass();
        AbstractDao dao = getDaoInstance(clazz);
        dao.insertOrReplaceInTx(beanList);
        return true;
    }

}
