package com.function.luo.collection.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by luo on 2019/5/6.
 * 网络请求的接口
 */

public interface IServiceApi {

  /*  // @GET注解的作用:采用Get方法发送网络请求
    // getNews(...) = 接收网络请求数据的方法
    // 其中返回类型为Call<News>，News是接收数据的类（即上面定义的News类）
    // 如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>
    @Headers("apikey:81bf9da930c7f9825a3c3383f1d8d766")
    @GET("word/word")
    Call<News> getNews(@Query("num") String num, @Query("page")String page);*/

    //@GET("/html/2017/0817/128429877.shtm")
    @GET
    Call<ResponseBody> getNews();
}
