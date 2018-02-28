package com.cl.proficiencytest.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cl.proficiencytest.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity
        implements BaseView {

    protected P presenter;
    public Context context;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupLayout();
        mUnbinder = ButterKnife.bind(this);
        context = this;
        App.getAppInstance().addActivity(this);
        presenter = initPresenter();
        init();
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        App.getAppInstance().removeActivity(this);
        if (presenter != null) {
            presenter.detach();
            presenter = null;
        }
        super.onDestroy();
    }

    /**
     * init presenter for sub class
     *
     * @return responding presenter
     */
    public abstract P initPresenter();

    protected abstract void init();

    protected abstract void setupLayout();

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void showLoadingDialog(String msg) {

    }
}
