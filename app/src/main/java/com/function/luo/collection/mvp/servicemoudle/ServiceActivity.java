package com.function.luo.collection.mvp.servicemoudle;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.function.luo.collection.R;
import com.function.luo.collection.base.BaseDaggerActivity;
import com.function.luo.collection.constant.PublicConstant;
import com.function.luo.collection.mvp.MvpDaggerActivity;
import com.function.luo.collection.mvp.loginmoudle.LoginActivity;
import com.function.luo.collection.mvp.loginmoudle.MyPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by luo on 2019/4/27.
 */

public class ServiceActivity extends BaseDaggerActivity<ServicePresenter> implements ServiceContract.View {


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
        mPresenter.initInfo("服务页面请求");
    }

    public static void launch(MvpDaggerActivity mvpDaggerActivity) {
        Intent intent = new Intent(mvpDaggerActivity,ServiceActivity.class);
        mvpDaggerActivity.startActivity(intent);
    }


    @Override
    public void showInfo(String info) {
        Log.d(PublicConstant.TAG,"result=========="+info);
        tvContent.setText(info);
        Toast.makeText(ServiceActivity.this,info,Toast.LENGTH_LONG).show();
    }
}
