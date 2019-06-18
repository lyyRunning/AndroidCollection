package com.function.luo.collection.network;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.function.luo.collection.R;
import com.function.luo.collection.base.BaseActivity;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by luo on 2019/5/6.
 */

public class RetrofitActivity extends BaseActivity {

    @BindView(R.id.bt_get)
    Button btGet;
    @BindView(R.id.bt_post)
    Button btPost;
    private  OkHttpClient okHttpClient;
    private int CONNECT_TIMEOUT = 30;
    private int READ_TIMEOUT = 30;
    private int WRITE_TIMEOUT = 30;
    @Override
    public int provideContentViewId() {
        return R.layout.activity_retrofit;
    }


    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
         HttpInterceptor interceptor = new HttpInterceptor();
         okHttpClient =  new OkHttpClient.Builder().
                connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).
                writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS).
                 //添加拦截器
               // addInterceptor(interceptor).
                retryOnConnectionFailure(true).
                build();

    }



    @OnClick({R.id.bt_get, R.id.bt_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_get:

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URLUtils.SERVICE_URL)
//                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();
                // 创建网络请求接口的实例
                IServiceApi  mApi = retrofit.create(IServiceApi .class);

                /**
                 * 网络请求
                 */
                mApi.getNews().enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("LUO","========="+call);
                        Log.d("LUO","========="+response);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("LUO","========="+call);
                        Log.d("LUO","========="+t);
                    }
                });

                break;
            case R.id.bt_post:
                break;
            default:
                break;
        }
    }

    public static void launch(NetWorkActivity netWorkActivity) {
        Intent intent = new Intent(netWorkActivity,RetrofitActivity.class);
        netWorkActivity.startActivity(intent);

    }
}
