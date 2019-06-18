package com.function.luo.collection.tools;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.function.luo.collection.R;
import com.function.luo.collection.base.BaseActivity;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by luo on 2019/4/25.
 */

public class RxViewActivity extends BaseActivity {

    @BindView(R.id.btn_click)
    Button btnClick;
    @BindView(R.id.btn_long)
    Button btnLong;
    @BindView(R.id.btn_scroll)
    Button btnScroll;
    @BindView(R.id.et_edit)
    EditText etEdit;
    @BindView(R.id.btn_delay)
    Button btnDelay;


    @Override
    public int provideContentViewId() {
        return R.layout.activity_rxview;
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
                        Toast.makeText(RxViewActivity.this, "11111111111", Toast.LENGTH_LONG).show();
                    }
                });
        //长按时操作
        RxView.longClicks(btnLong)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Toast.makeText(RxViewActivity.this, "2222222222", Toast.LENGTH_LONG).show();
                    }
                });


        //点击时延迟执行
        btnDelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 延时操作
                Log.d("LUO","延迟开始===================="+System.currentTimeMillis());
                Observable.timer(3, TimeUnit.SECONDS)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                Looper.prepare();
                                Toast.makeText(RxViewActivity.this, "延迟结束", Toast.LENGTH_LONG).show();
                                Looper.loop();
                                Log.d("LUO","延迟结束===================="+System.currentTimeMillis());
                            }
                        });
            }
        });


        //拖拽监听(好像没效果,看你们了兄弟们)
        RxView.drags(btnScroll)
                .subscribe(new Consumer<DragEvent>() {
                    @Override
                    public void accept(DragEvent dragEvent) throws Exception {
                        Toast.makeText(RxViewActivity.this, "被拖拽了", Toast.LENGTH_LONG).show();
                    }
                });


        //EditText 监听
        RxTextView.textChanges(etEdit).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence text) throws Exception {
                Toast.makeText(RxViewActivity.this, "输入监听", Toast.LENGTH_LONG).show();

            }
        });
    }


    /**
     * 页面跳转
     *
     * @param activity
     */
    public static void launch(ToolsActivity activity) {
        Intent intent = new Intent(activity, RxViewActivity.class);
        activity.startActivity(intent);
    }
}
