package com.llg.chatweather.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * create by loogen on 2020-5-21
 */
public abstract class BaseModel implements IModel {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    @Override
    public void onClear() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }
}
