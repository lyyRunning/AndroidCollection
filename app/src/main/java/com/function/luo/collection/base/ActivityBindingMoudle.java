package com.function.luo.collection.base;


/**
 * Created by luo
 * 注入绑定关系,如果使用到了就需要在这里进行注册，默认全局module并且加上各个module之间的联系
 */

import com.function.luo.collection.mvp.MvpBindingMoudle;

import javax.inject.Singleton;

import dagger.Module;

/**
 * 新添加的 moudle 直接加到这里
 */
@Singleton
@Module(includes = {MvpBindingMoudle.class
})
public abstract  class ActivityBindingMoudle {
}
