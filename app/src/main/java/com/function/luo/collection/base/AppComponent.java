package com.function.luo.collection.base;

import android.app.Application;

import com.function.luo.collection.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 *
 * @author luo
 * @date 2019/4/22
 * 定义全部的注射器(相当于快递员,全局只要一个)
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBindingMoudle.class
})
public interface AppComponent extends AndroidInjector<BaseApplication>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
