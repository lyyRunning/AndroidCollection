package com.function.luo.collection.base;

import com.function.luo.collection.baseUI.BaseView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by cyb on 2018/11/5.
 */

public class BasePresenter<T extends BaseView> {
    public T mView = null;
    protected Reference<T> mViewRef;//view接口类型的弱引用

    /**
     * 绑定view(建立关联)
     */
    public void takeView(T view) {
//        mView = view;
        mViewRef = new WeakReference<T>(view);
        mView = getView();
    }

    /**
     * 解除view
     */
    public void dropView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
        if (mView != null) {
            mView = null;
        }
    }

    protected T getView() {
        return mViewRef.get();
    }

    /**
     * 判断是否为空
     */
    public boolean existView() {
        return mView == null ? false : true;
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }
}
