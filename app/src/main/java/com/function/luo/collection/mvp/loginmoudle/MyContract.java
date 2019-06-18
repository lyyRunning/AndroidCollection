package com.function.luo.collection.mvp.loginmoudle;

import android.app.Activity;

import com.function.luo.collection.base.BasePresenter;
import com.function.luo.collection.baseUI.BaseView;

/**
 * Created by luo on 2019/4/27.
 */

public class MyContract {

    public interface View extends BaseView {
        /**
         * 页面实现
         *
         */

        void showInfo(String info);
    }

    public static abstract class Presenter extends BasePresenter<View> {

        /**
         *
         * @param requestMsg 请求信息
         */
        public abstract void initInfo(String requestMsg);


    }
}
