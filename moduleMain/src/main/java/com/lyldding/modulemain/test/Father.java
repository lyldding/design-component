package com.lyldding.modulemain.test;

/**
 * @author lyldding
 * @date 2020/1/9
 */
public class Father {

    public static final String TAG = "Father";

    static {
        System.out.println("static Father");
    }

    {
        System.out.println("unStatic Father");
    }

    public Father() {
        System.out.println("constract Father");
        method();
    }

    public void method() {
        System.out.println("method Father");
    }

    @Override
    public String toString() {
        return "toString Father";
    }
}
