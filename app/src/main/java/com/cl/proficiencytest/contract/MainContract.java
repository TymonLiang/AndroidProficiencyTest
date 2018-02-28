package com.cl.proficiencytest.contract;

import com.cl.proficiencytest.base.BasePresenter;
import com.cl.proficiencytest.base.BaseView;
import com.cl.proficiencytest.model.Item;


/**
 * Created by tymon on 28/02/2018.
 */

public interface MainContract {

    interface view extends BaseView {

        void setData(Item dataList);

        void showTitle(String title);

        void hideRefreshingDialog();
    }

    interface presenter extends BasePresenter {

        void getData();

    }
}
