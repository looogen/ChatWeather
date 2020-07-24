package com.llg.chatweather.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * create by loogen on 2020-5-21
 */
public class BaseModel implements IModel {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    public void onClear() {
        compositeDisposable.clear();
    }
}
