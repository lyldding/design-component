package com.lyldding.modulestrategy.strategys;

import com.lyldding.modulestrategy.interfaces.IComputeStrategy;

/**
 * @author https://github.com/lyldding
 */
public class AddStrategy implements IComputeStrategy {
    @Override
    public int doCompute(int num1, int num2) {
        return num1 + num2;
    }

    @Override
    public String getName() {
        return "加法";
    }
}
