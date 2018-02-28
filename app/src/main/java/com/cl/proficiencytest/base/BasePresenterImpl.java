package com.cl.proficiencytest.base;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter {
    public BasePresenterImpl(V view) {
        this.view = view;
        start();
    }

    protected V view;


    @Override
    public void detach() {
        this.view = null;
        unDisposable();
    }

    @Override
    public void start() {

    }


    //add Subscription into CompositeSubscription
    private CompositeDisposable mCompositeDisposable;

    /**
     * add Disposable
     *
     * @param subscription
     */
    @Override
    public void addDisposable(Disposable subscription) {
        //if undisposable, require new instance
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    /**
     * avoid memory leak
     */
    @Override
    public void unDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

}
