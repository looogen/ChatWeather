package com.llg.chatweather;

import android.os.Bundle;
import android.util.Log;

import com.llg.chatweather.bean.BaseBean;
import com.llg.chatweather.bean.NowResultsBean;
import com.llg.chatweather.http.Constant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        //  https://api.seniverse.com/v3/weather/now.json?key=gszdv59kxtnoorb3&location=beijing&language=zh-Hans&unit=c
        //3 获取可观察的对象
//        Observable<BaseBean<NowResultsBean>> observableDaily = service.queryNow(Constant.API_KEY, "zhuhai", "zh-Hans", "c");
//
//        Disposable disposable =  observableDaily.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<BaseBean<NowResultsBean>>() {
//                    @Override
//                    public void accept(BaseBean<NowResultsBean> baseBean) throws Exception {
//                        Log.e(TAG, baseBean.getResults().get(0).toString());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.e(TAG,throwable.toString());
//                    }
//                });
//        mCompositeDisposable.add(disposable);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
