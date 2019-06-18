package com.function.luo.collection.network;

import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.function.luo.collection.R;
import com.function.luo.collection.base.BaseActivity;


import butterknife.BindView;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import rx.Subscriber;

/**
 * Created by luo on 2019/5/18.
 */

public class RxJavaActivity extends BaseActivity {

    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.bt2)
    Button bt2;
    @BindView(R.id.bt3)
    Button bt3;
    @BindView(R.id.bt4)
    Button bt4;
    @BindView(R.id.bt5)
    Button bt5;
    @BindView(R.id.bt6)
    Button bt6;
    @BindView(R.id.bt7)
    Button bt7;
    @BindView(R.id.bt8)
    Button bt8;

    private String TAG = "LUO";

    @Override
    public int provideContentViewId() {
        return R.layout.activity_rxjava;
    }

    @Override
    public void initData() {
        super.initData();
    }

    public static void launch(NetWorkActivity netWorkActivity) {
        Intent intent = new Intent(netWorkActivity, RxJavaActivity.class);
        netWorkActivity.startActivity(intent);
    }



    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                 bt1();
                break;
            case R.id.bt2:
                bt2();
                break;
            case R.id.bt3:
                bt3();
                break;
            case R.id.bt4:
                break;
            case R.id.bt5:
                break;
            case R.id.bt6:
                break;
            case R.id.bt7:
                break;
            case R.id.bt8:
                break;
            default:
        }
    }

    private void bt3() {

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {
                Log.d(TAG,"=====onCompleted");

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"====="+e);
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG,"====="+s);
            }
        };

    }

    private void bt2() {
        //创建一个下游观察者
        Observer<String> observable =  new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,"=====Subscribe");

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG,"====="+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"====="+e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"=====onComplete");
            }
        };
    }

    private void bt1() {


        Observable.create(new ObservableOnSubscribe<String>() {



            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onComplete();
            }

        }).subscribe(new Observer<String>() {


            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d("LUO","========"+s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
