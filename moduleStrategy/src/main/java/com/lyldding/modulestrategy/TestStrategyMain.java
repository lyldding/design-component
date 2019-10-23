package com.lyldding.modulestrategy;

import com.lyldding.modulestrategy.strategys.AddStrategy;
import com.lyldding.modulestrategy.strategys.ContextStrategy;
import com.lyldding.modulestrategy.strategys.SubStrategy;

/**
 * 策略模式
 *
 * @author https://github.com/lyldding
 */
public class TestStrategyMain {

    public static void main(String[] args) {
        ContextStrategy contextStrategy = new ContextStrategy();

        //加法策略
        contextStrategy.setComputeStrategy(new AddStrategy());
        executeCompute(contextStrategy);

        //减法策略
        contextStrategy.setComputeStrategy(new SubStrategy());
        executeCompute(contextStrategy);
    }

    private static void executeCompute(ContextStrategy contextStrategy) {
        System.out.println("————————————————————————");
        System.out.println(contextStrategy.getStrategyName() + "的执行结果 ："
                + contextStrategy.executeCompute(10, 5));
    }
}
