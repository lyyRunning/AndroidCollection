package com.function.luo.collection.baseUI;

/**
 * Created by luo on 2019/4/27.
 * 这是 View 基础显示
 */

public interface BaseView {


    /**
     * 显示加载框
     *
     * @param message
     */
    void showProgressDialog(String message);

    /**
     * 显示加载框
     *
     */
    void showProgressDialog(int resId);

    /**
     * 隐藏加载框
     */
    void dismissProgressDialog();

    /**
     * 弹出吐司
     */
    void toast(String message, int duration);
    /**
     * 弹出吐司
     */
    void toast(int resId, int duration);
}
