package com.function.luo.collection.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.function.luo.collection.BaseApplication;
import com.function.luo.collection.R;
import com.function.luo.collection.baseUI.BaseLoadingDialog;
import com.function.luo.collection.baseUI.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.DaggerActivity;

/**
 * Created by luo on 2019/4/25.
 *
 *所有的 Dagger 注解要继承此类
 * 里面默认实现  AndroidInjection.inject(this);
 */

public abstract class BaseDaggerActivity<T extends BasePresenter> extends DaggerActivity implements BaseView {

    BaseLoadingDialog mLoadingDialog = null;

    @Inject
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //隐藏软键盘
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //无头部
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //用于隐藏AppCompatActivity
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //显示 view
        setContentView(provideContentViewId());

        //butterKnife 在这里统一绑定
        initButterKnife();
        //初始化页面
        initView();
        //初始化数据
        initData();
        //常用的点击事件
        initEvent();
    }

    @Nullable
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        if (mPresenter != null) {
            mPresenter.takeView(this);
        }
        return super.onCreateView(name, context, attrs);

    }




    public void initEvent() {
    }

    public void initData() {
    }

    public void initView() {
    }

    /**
     * butterKnife 在这里统一绑定
     */
    public void initButterKnife() {
        ButterKnife.bind(this);
    }

    public int provideContentViewId() {

        return R.layout.activity_common;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.dropView();
            mPresenter = null;
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }


    @Override
    public void dismissProgressDialog() {
        if (null != mLoadingDialog) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    @Override
    public void showProgressDialog(String message) {
        if (null == mLoadingDialog) {
            String loadmessage;
            if (message != null) {
                loadmessage = message;
            } else {
                loadmessage = "加载中...";
            }
            mLoadingDialog = new BaseLoadingDialog(this, loadmessage, R.layout.base_loading_dialog);

            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setCanceledOnTouchOutside(false);
        }
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    @Override
    public void showProgressDialog(int resId) {
        String message = getResources().getString(resId);
        showProgressDialog(message);
    }

    @Override
    public void toast(String message, int toast) {
        Toast.makeText(BaseApplication.getInstance(), message, toast).show();
    }

    @Override
    public void toast(int resId, int toast) {
        Toast.makeText(BaseApplication.getInstance(), resId, toast).show();
    }

}
