package com.function.luo.collection.mvp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.function.luo.collection.MainActivity;
import com.function.luo.collection.R;
import com.function.luo.collection.base.BaseActivity;
import com.function.luo.collection.base.BaseDaggerActivity;
import com.function.luo.collection.mvp.loginmoudle.LoginActivity;
import com.function.luo.collection.mvp.servicemoudle.ServiceActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luo on 2019/4/22.
 */

public class MvpDaggerActivity extends BaseActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;

    @Override
    public int provideContentViewId() {
        return R.layout.activity_mvp_dagger;
    }


    @Override
    public void initData() {
        super.initData();
    }


    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                LoginActivity.launch(this);
                break;
            case R.id.btn2:
                ServiceActivity.launch(this);
                break;
            default:
                break;
        }
    }

    public static void launch(MainActivity mainActivity) {
        Intent intent = new Intent(mainActivity,MvpDaggerActivity.class);
        mainActivity.startActivity(intent);
    }
}
