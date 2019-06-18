package com.function.luo.collection.mvp.loginmoudle;

import android.os.Handler;
import android.util.Log;

import com.function.luo.collection.R;
import com.function.luo.collection.bean.LoginBean;
import com.function.luo.collection.constant.PublicConstant;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

/**
 * Created by luo on 2019/4/27.
 */

public class MyPresenter extends MyContract.Presenter {
    @Inject
    MyPresenter(){

    }
    @Inject
    LoginBean loginBean;
    @Override
    public void initInfo(String filePath) {

        Log.d(PublicConstant.TAG,"请求=========="+filePath);
        mView.showProgressDialog("请求中......");

        Log.d(PublicConstant.TAG,"=========="+mView);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.dismissProgressDialog();
                mView.showInfo("哈哈,登录模块 MVP 成功了!");
            }
        }, 3000);
        //5秒后执行Runnable中的ru


    }
}
