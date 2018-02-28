package com.cl.proficiencytest.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.cl.proficiencytest.R;
import com.cl.proficiencytest.adapter.RecyclerViewAdapter;
import com.cl.proficiencytest.base.BaseActivity;
import com.cl.proficiencytest.contract.MainContract;
import com.cl.proficiencytest.model.Item;
import com.cl.proficiencytest.model.Row;
import com.cl.proficiencytest.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainContract.presenter>
        implements MainContract.view, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ActionBar mActionBar;

    private List<Row> list = new ArrayList<>();
    private RecyclerViewAdapter adapter;

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();

        presenter.getData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void setupLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public MainContract.presenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void setData(Item dataList) {
        list.clear();
        list.addAll(dataList.getRows());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showTitle(String title) {
        mActionBar.setTitle(title);
    }

    @Override
    public void hideRefreshingDialog() {
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.getData();
    }
}
