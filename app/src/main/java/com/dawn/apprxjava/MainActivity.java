package com.dawn.apprxjava;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import aidl.dawn.com.librxjava.Func1;
import aidl.dawn.com.librxjava.Observable;
import aidl.dawn.com.librxjava.OnSubscrible;
import aidl.dawn.com.librxjava.Subscrible;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_onclick_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxjavaTest();
            }
        });

        findViewById(R.id.tv_onclick_operation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxOperationTest();
            }
        });

        findViewById(R.id.tv_onclick_thread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxThreadTest();
            }
        });
    }

    private void rxjavaTest(){
        Observable.create(new OnSubscrible<String>() {
            @Override
            public void call(Subscrible<? super String> subscrible) {
                subscrible.onNext("dsdhsjdjsh");
            }
        }).subscrible(new Subscrible<String>() {
            @Override
            public void onNext(String s) {
                Log.d("11111111","1111111 : "+s);
            }
        });
    }

    private void rxOperationTest(){
        Observable.create(new OnSubscrible<String>() {
            @Override
            public void call(Subscrible<? super String> subscrible) {
                subscrible.onNext("dsdhsjdjsh");
            }
        }).map(new Func1<String, StudentBean>() {
            @Override
            public StudentBean call(String s) {
                StudentBean studentBean = new StudentBean(12,s);

                return studentBean;
            }
        }).subscrible(new Subscrible<StudentBean>() {
            @Override
            public void onNext(StudentBean studentBean) {
                Log.d("11111111","1111111 : "+studentBean.toString());
            }
        });
    }

    private void rxThreadTest(){
        Observable.create(new OnSubscrible<String>() {
            @Override
            public void call(Subscrible<? super String> subscrible) {
                Log.d("11111111","11111111 ee: "+Thread.currentThread().getName());
                subscrible.onNext("dsdhsjdjsh");
            }
        }).map(new Func1<String, StudentBean>() {
            @Override
            public StudentBean call(String s) {
                StudentBean studentBean = new StudentBean(12,s);
                Log.d("11111111","11111111 dd: "+Thread.currentThread().getName());
                return studentBean;
            }
        }).subscribleOnIO().subscrible(new Subscrible<StudentBean>() {
            @Override
            public void onNext(StudentBean studentBean) {
                Log.d("11111111","1111111 : "+studentBean.toString());
                Log.d("11111111","11111111 vv: "+Thread.currentThread().getName());
            }
        });
    }
}
