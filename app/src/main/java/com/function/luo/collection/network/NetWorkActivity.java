package com.function.luo.collection.network;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.function.luo.collection.MainActivity;
import com.function.luo.collection.R;
import com.function.luo.collection.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by luo on 2019/5/6.
 */

public class NetWorkActivity extends BaseActivity {


    @BindView(R.id.bt_retrofit)
    Button btRetrofit;
    @BindView(R.id.bt_volley)
    Button btVolley;
    @BindView(R.id.bt_okhttp)
    Button btOkhttp;

    /**
     * 页面
     *
     * @return
     */
    @Override
    public int provideContentViewId() {
        return R.layout.activity_network;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
    }


    /**
     * 点击事件
     * @param view
     */

    @OnClick({R.id.bt_retrofit, R.id.bt_volley, R.id.bt_okhttp,R.id.bt_rxjava})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_retrofit:
                //retrofit网络请求
                RetrofitActivity.launch(this);
                break;
            case R.id.bt_volley:
                //volley网络请求
                break;
            case R.id.bt_okhttp:
                //okhttp网络请求
                break;
            case R.id.bt_rxjava:
                //RxJava 操作
                RxJavaActivity.launch(this);
            default:
                break;
        }
    }

    public static void launch(MainActivity mainActivity) {
        Intent intent = new Intent(mainActivity,NetWorkActivity.class);
        mainActivity.startActivity(intent);
    }
}
