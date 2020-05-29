package com.llg.chatweather.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * create by loogen on 2020-5-21
 */
public class BaseViewModel extends ViewModel {

    public final MutableLiveData<Boolean> refreshing = new MutableLiveData<>();

    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mCompositeDisposable != null){
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
