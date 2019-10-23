package com.lyldding.modulemain.presenter;

import com.lyldding.commonlib.mvp.presenter.BasePresenter;
import com.lyldding.commonlib.utils.MainLooper;
import com.lyldding.modulemain.contract.ContractMain;

/**
 * @author https://github.com/lyldding
 */
public class PresenterMain extends BasePresenter<ContractMain.View> implements ContractMain.Presenter {
    public PresenterMain(ContractMain.View view) {
        attachView(view);
    }

    @Override
    public void getData() {
        getView().showLoadView();
        MainLooper.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isLive()) {
                    return;
                }
                getView().hideLoadView();
                getView().updateName("test success");
            }
        }, 3000);
    }
}
