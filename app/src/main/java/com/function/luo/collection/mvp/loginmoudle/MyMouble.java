package com.function.luo.collection.mvp.loginmoudle;

/**
 * Created by luo on 2019/4/27.
 */

import com.function.luo.collection.bean.LoginBean;

import dagger.Module;
import dagger.Provides;

/**
 * Created by luo on 2019/4/27.
 * 这是登录模块的 moudle(类似箱子)
 */
@Module
public class MyMouble {

    @Provides
    LoginBean provideLoginBean() {

        return new LoginBean();
    }
}
