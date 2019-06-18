package com.function.luo.collection.mvp;

/**
 * Created by luo on 2019/4/27.
 */


import com.function.luo.collection.mvp.loginmoudle.LoginActivity;
import com.function.luo.collection.mvp.loginmoudle.MyMouble;
import com.function.luo.collection.mvp.servicemoudle.ServiceActivity;
import com.function.luo.collection.mvp.servicemoudle.ServiceMouble;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by luo on 2019/4/27.
 * 这是登录模块的 moudle(类似箱子)
 */
@Module
public interface MvpBindingMoudle {
    /**
     * 登录模块导入
     */
    @ActivityScoped
    @ContributesAndroidInjector(modules = {MyMouble.class})
    LoginActivity loginActivity();


    /**
     * 服务模块导入
     */
    @ActivityScoped
    @ContributesAndroidInjector(modules = {ServiceMouble.class})
    ServiceActivity serviceActivity();

}
