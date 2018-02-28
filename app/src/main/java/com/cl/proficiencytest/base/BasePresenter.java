package com.cl.proficiencytest.base;

import io.reactivex.disposables.Disposable;


public interface BasePresenter {
    //init
    void start();

    void detach();

    //add disposable into CompositeDisposable
    void addDisposable(Disposable subscription);

    void unDisposable();

}
