package com.function.luo.collection.tools;

import android.content.Intent;

import com.function.luo.collection.R;
import com.function.luo.collection.base.BaseActivity;

/**
 * Created by luo on 2019/4/30.
 * BRVAH 对 RecycleView 的封装
 */

public class BrvahActivity extends BaseActivity {


    /**
     * 显示页面
     * @return
     */
    @Override
    public int provideContentViewId() {
        return R.layout.activity_brvah;
    }


    @Override
    public void initData() {
        super.initData();


    }

    public static void launch(ToolsActivity toolsActivity) {

        Intent intent = new Intent(toolsActivity,BrvahActivity.class);
        toolsActivity.startActivity(intent);

    }
}
