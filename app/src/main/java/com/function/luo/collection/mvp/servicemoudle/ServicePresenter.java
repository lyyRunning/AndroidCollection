package com.function.luo.collection.mvp.servicemoudle;

import android.os.Handler;
import android.util.Log;

import com.function.luo.collection.bean.LoginBean;
import com.function.luo.collection.bean.ServiceBean;
import com.function.luo.collection.constant.PublicConstant;
import com.function.luo.collection.mvp.loginmoudle.MyContract;

import javax.inject.Inject;

/**
 * Created by luo on 2019/4/27.
 */

public class ServicePresenter extends ServiceContract.Presenter {
    @Inject
    ServicePresenter(){

    }
    @Inject
    ServiceBean serviceBean;
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
                mView.showInfo("哈哈,服务模块的 MVP 成功了!");
            }
        }, 3000);
        //3秒后执行Runnable中的ru


    }
}
