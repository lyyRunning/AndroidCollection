package com.function.luo.collection.tools;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.function.luo.collection.MainActivity;
import com.function.luo.collection.R;
import com.function.luo.collection.base.BaseActivity;
import com.function.luo.collection.constant.PublicConstant;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Created by luo on 2019/4/25.
 * <p>
 * 所有好用的小工具集合
 */

public class ToolsActivity extends BaseActivity {

    @BindView(R.id.btn_click)
    Button btnClick;
    @BindView(R.id.btn_swipe)
    Button btnSwipe;
    @BindView(R.id.btn_dialog)
    Button btnDialog;

    @Override
    public int provideContentViewId() {
        return R.layout.activity_tools;
    }

    /**
     * 点击事件
     */

    @OnClick({R.id.btn_swipe, R.id.btn_dialog,R.id.btn_brvah})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_swipe:
                //下拉刷新
                SwipeRefreshActivity.launch(ToolsActivity.this);
                break;
            case R.id.btn_dialog:
                //dialog 加载框
                showProgressDialog("网络加载中");
                Log.d(PublicConstant.TAG,"==========开始加载...");
                //延迟 5 秒结束
                btnDialog.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgressDialog();
                        Log.d(PublicConstant.TAG,"==========结束加载...");
                    }
                },5000);

                break;
            case R.id.btn_brvah:
                //recycleView的封装
                BrvahActivity.launch(ToolsActivity.this);
            default:
                break;
        }
    }
    @Override
    public void initData() {
        super.initData();

        //两秒内预防多点击事件
        RxView.clicks(btnClick)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        RxViewActivity.launch(ToolsActivity.this);
                    }
                });


    }


    public static void launch(MainActivity mainActivity) {
        Intent intent = new Intent(mainActivity, ToolsActivity.class);
        mainActivity.startActivity(intent);
    }




}
