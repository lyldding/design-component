package com.lyldding.modulestrategy.presenter;

import com.lyldding.commonlib.mvp.presenter.BasePresenter;
import com.lyldding.commonlib.utils.MainLooper;
import com.lyldding.modulestrategy.contract.ContractStrategy;
import com.lyldding.modulestrategy.strategys.AddStrategy;
import com.lyldding.modulestrategy.strategys.ContextStrategy;
import com.lyldding.modulestrategy.strategys.SubStrategy;

/**
 * @author https://github.com/lyldding
 */
public class PresenterStrategy extends BasePresenter<ContractStrategy.View> implements ContractStrategy.Presenter {
    private ContextStrategy mContextStrategy;

    public PresenterStrategy(ContractStrategy.View view) {
        attachView(view);
        mContextStrategy = new ContextStrategy();
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
                getView().onSuccess();
            }
        }, 3000);
    }

    @Override
    public void setAddStrategy() {
        mContextStrategy.setComputeStrategy(new AddStrategy());
    }

    @Override
    public void setSubStrategy() {
        mContextStrategy.setComputeStrategy(new SubStrategy());
    }

    @Override
    public int executeCompute(int num1, int num2) {
        return mContextStrategy.executeCompute(num1, num2);
    }
}
