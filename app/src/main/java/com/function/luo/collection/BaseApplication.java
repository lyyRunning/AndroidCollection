package com.function.luo.collection;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.function.luo.collection.base.AppComponent;
import com.function.luo.collection.base.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


/**
 * Created by luo on 2019/4/22.
 * 实现DaggerApplication
 */

public class BaseApplication extends DaggerApplication {

    public static BaseApplication myApplication;
    public AppComponent appComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static BaseApplication getInstance(){
        return myApplication;
    }


    /**
     * 实现 Dagger2 方法
     * @return
     */
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        appComponent = DaggerAppComponent.builder().application(this).build();


        return appComponent;
    }
}
