package com.lyldding.modulemain.test;

/**
 * @author lyldding
 * @date 2020/1/9
 */
public class Son extends Father {
    public static Son instance = new Son();


    static {
        System.out.println("static Son");
    }

    {
        System.out.println("unStatic Son");
    }

    public Son() {
        System.out.println("constract Son");
        method();
    }

    @Override
    public void method() {
        System.out.println("method Son");
    }

    @Override
    public String toString() {
        return "toString Son";
    }
}
