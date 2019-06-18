package com.function.luo.collection.network;


import java.io.IOException;
import java.nio.charset.Charset;
import okhttp3.Interceptor;
import okhttp3.Response;


/**
 * @author by cyb on 2019/1/2.
 *         okhttp  拦截器，主要功能为添加请求头,统一记录日志等等统一行为
 */

public class HttpInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");


    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
