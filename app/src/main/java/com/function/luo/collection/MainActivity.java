package com.function.luo.collection;

import android.view.View;
import android.widget.Button;

import com.function.luo.collection.base.BaseActivity;
import com.function.luo.collection.greenDao.GreenDaoActivity;
import com.function.luo.collection.mvp.MvpDaggerActivity;
import com.function.luo.collection.network.NetWorkActivity;
import com.function.luo.collection.tools.ToolsActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_data_base)
    Button btnDataBase;
    @BindView(R.id.btn_network)
    Button btnNetwork;
    @BindView(R.id.btn_mvp)
    Button btnMvp;
    @BindView(R.id.btn_tools)
    Button btnTools;


    @Override
    public int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.btn_data_base, R.id.btn_network, R.id.btn_mvp,R.id.btn_tools})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_data_base:
                //多页面跳转,一句话搞定
                GreenDaoActivity.launch(this);
                break;
            case R.id.btn_network:
                NetWorkActivity.launch(this);
                break;
            case R.id.btn_mvp:
                MvpDaggerActivity.launch(this);
                break;
            case R.id.btn_tools:
                //所有好用的小工具集合
                ToolsActivity.launch(this);
                break;
            default:
                break;
        }
    }


}
