package com.function.luo.collection.mvp;

import android.webkit.WebSettings;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by luo on 2019/4/27.
 * 局部单例注解的范围
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScoped {
}
