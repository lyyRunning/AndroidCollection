package com.function.luo.collection.tools;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.function.luo.collection.R;
import com.function.luo.collection.base.BaseActivity;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luo on 2019/4/25.
 * <p>
 * 上拉刷新,下拉加载更多
 */

public class SwipeRefreshActivity extends BaseActivity {

    @BindView(R.id.recycleList)
    RecyclerView recycleList;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @Override
    public int provideContentViewId() {
        return R.layout.activity_swipe_refresh;
    }

    /**
     * 初始化
     */

    @Override
    public void initData() {
        super.initData();
        List<String> data = new ArrayList<>();
        for (int i=0;i<20;i++){
            data.add("第"+i+"行");
        }

        recycleList.setLayoutManager(new LinearLayoutManager(this));
        recycleList.setAdapter(new SwipeAdapter(data));

        //设置刷新颜色
        refreshLayout.setColorSchemeColors(this.getResources().getColor(R.color.red),this.getResources().getColor(R.color.colorPrimary));
        //refreshLayout.setRefreshng(true);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //模拟网络请求需要3000毫秒，请求完成，设置setRefreshing 为false
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }


    private void init() {



    }


    /**
     * 跳转
     *
     * @param activity
     */
    public static void launch(ToolsActivity activity) {
        Intent intent = new Intent(activity, SwipeRefreshActivity.class);
        activity.startActivity(intent);
    }
}
