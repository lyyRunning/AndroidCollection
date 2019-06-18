package com.function.luo.collection.baseUI;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.function.luo.collection.R;
import com.github.ybq.android.spinkit.style.Circle;


public class BaseLoadingDialog extends TransparentDialog {

    private TextView tips_loading_msg;
    private int layoutResId;
    private String message;
    private Context mContext;
    private ProgressBar mLoading;
    private TextView tv_animation;
    private Circle mCircleDrawable;


    /**
     * 构造方法
     *
     * @param context     上下文
     * @param layoutResId 要传入的dialog布局文件的id
     */
    public BaseLoadingDialog(Context context, @Nullable String message, int layoutResId) {
        super(context);
        this.layoutResId = layoutResId;
        this.message = message;
        this.mContext = context;
        getAttr().gravity = Gravity.CENTER;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layoutResId);
        tips_loading_msg = (TextView) findViewById(R.id.tips_loading_msg);
        tips_loading_msg.setText(this.message);
       // mLoading = (ProgressBar) findViewById(R.id.progressBar);
        //加载框
        tv_animation = findViewById(R.id.tv_animation);
        //修改加载框
        mCircleDrawable = new Circle();
        mCircleDrawable.setBounds(0, 0, 80, 80);
        //加载框动画
        mCircleDrawable.setColor(android.graphics.Color.parseColor("#303F9F"));

        tv_animation.setCompoundDrawables(null, mCircleDrawable, null, null);
        mCircleDrawable.start();
    }

    public void setMessage(String message) {
        this.message = message;
        if (!TextUtils.isEmpty(message) && this.tips_loading_msg != null) {
            this.tips_loading_msg.setText(this.message);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCircleDrawable.stop();
    }
}
