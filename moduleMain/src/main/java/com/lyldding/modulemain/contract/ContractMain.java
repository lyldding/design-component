package com.lyldding.modulemain.contract;

import com.lyldding.commonlib.mvp.presenter.IBasePresenter;
import com.lyldding.commonlib.mvp.view.ILoadView;

/**
 * @author https://github.com/lyldding
 * @date 2019/9/29
 */
public interface ContractMain {
    interface View extends ILoadView {
        void updateName(String msg);
    }

    interface Presenter extends IBasePresenter<View> {
        void getData();
    }
}
