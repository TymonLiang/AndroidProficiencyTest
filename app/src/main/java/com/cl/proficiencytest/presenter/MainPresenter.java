package com.cl.proficiencytest.presenter;

import com.cl.proficiencytest.base.BasePresenterImpl;
import com.cl.proficiencytest.contract.MainContract;
import com.cl.proficiencytest.model.Item;
import com.cl.proficiencytest.model.Row;
import com.cl.proficiencytest.net.ExceptionHelper;
import com.cl.proficiencytest.net.api.Api;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tymon on 28/02/2018.
 */

public class MainPresenter extends BasePresenterImpl<MainContract.view>
        implements MainContract.presenter{

    public MainPresenter(MainContract.view view) {
        super(view);
    }

    @Override
    public void getData() {
        Api.getInstance().getItem()
                .cache()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);
                        view.showLoadingDialog("loading");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Item>() {
                    @Override
                    public void accept(@NonNull Item item ) throws Exception {
                        view.dismissLoadingDialog();
                        view.setData(item);
                        view.showTitle(item.getTitle());
                        view.hideRefreshingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        view.dismissLoadingDialog();
                        view.hideRefreshingDialog();
                        ExceptionHelper.handleException(throwable);
                    }
                });
    }


}
