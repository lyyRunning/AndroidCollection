package com.function.luo.collection.mvp.loginmoudle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.function.luo.collection.R;
import com.function.luo.collection.base.BaseDaggerActivity;
import com.function.luo.collection.constant.PublicConstant;
import com.function.luo.collection.mvp.MvpDaggerActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luo on 2019/4/27.
 */

public class LoginActivity extends BaseDaggerActivity<MyPresenter> implements MyContract.View{


    @BindView(R.id.btn_click)
    Button btnClick;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
    }

    /**
     * 页面布局
     *
     * @return
     */
    @Override
    public int provideContentViewId() {
        return R.layout.activity_login;
    }

    /**
     * 点击事件
     */
    @OnClick(R.id.btn_click)
    public void onViewClicked() {
        mPresenter.initInfo("mvp 请求参数");
    }

    public static void launch(MvpDaggerActivity mvpDaggerActivity) {
        Intent intent = new Intent(mvpDaggerActivity,LoginActivity.class);
        mvpDaggerActivity.startActivity(intent);
    }

    @Override
    public void showInfo(String info) {
        Log.d(PublicConstant.TAG,"result=========="+info);
        tvContent.setText(info);
        Toast.makeText(LoginActivity.this,info,Toast.LENGTH_LONG).show();
    }
}
