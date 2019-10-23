package com.lyldding.modulestrategy.strategys;

import com.lyldding.modulestrategy.interfaces.IComputeStrategy;

/**
 * @author https://github.com/lyldding
 */
public class ContextStrategy {
    private IComputeStrategy mComputeStrategy = new AddStrategy();

    public void setComputeStrategy(IComputeStrategy computeStrategy) {
        mComputeStrategy = computeStrategy;
    }

    public int executeCompute(int num1, int num2) {
        return mComputeStrategy == null ? 0 : mComputeStrategy.doCompute(num1, num2);
    }

    public String getStrategyName() {
        return mComputeStrategy.getName();
    }
}
