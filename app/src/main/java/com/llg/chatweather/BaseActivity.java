package com.llg.chatweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;

/**
 * create by loogen on 2019-3-18
 */
public abstract class BaseActivity extends AppCompatActivity {
    public final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private static final String  TAG = "BaseActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
       // mCompositeDisposable = new CompositeDisposable();
    }

    //set  Layout resId
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {

        // 若在事件流中有耗时操作，同时事件流中某个事件又持有了Activity的引用，
        // 那么当Activity被关闭时，其引用仍会被未结束的事件所持有，会造成内存泄露；
        // 另外当Activity生命周期走到destroy后（注意：并不是Activity对象被回收），
        // 之后Activity中的view却要根据事件结果设置属性，则可能导致空指针异常（view的引用已被释放）。
        //dispose Observable
        mCompositeDisposable.dispose();
        super.onDestroy();
    }
}
