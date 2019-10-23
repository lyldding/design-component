package com.lyldding.modulestrategy.contract;

import com.lyldding.commonlib.mvp.presenter.IBasePresenter;
import com.lyldding.commonlib.mvp.view.ILoadView;

/**
 * @author https://github.com/lyldding
 */
public interface ContractStrategy {
    interface View extends ILoadView {
        void onSuccess();
    }

    interface Presenter extends IBasePresenter<View> {
        void getData();

        void setAddStrategy();

        void setSubStrategy();

        int executeCompute(int num1, int num2);
    }
}
