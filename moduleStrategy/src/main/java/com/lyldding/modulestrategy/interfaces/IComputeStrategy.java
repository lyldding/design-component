package com.lyldding.modulestrategy.interfaces;

/**
 * @author https://github.com/lyldding
 */
public interface IComputeStrategy {
    /**
     * 计算
     *
     * @param num1
     * @param num2
     */
    int doCompute(int num1, int num2);

    String getName();
}

