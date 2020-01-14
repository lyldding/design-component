package com.lyldding.modulemain;

import com.lyldding.modulemain.test.Father;
import com.lyldding.modulemain.test.Son;

/**
 * @author lyldding
 * @date 2020/1/9
 */
public class TestMain {


    public static void main(String[] args) {
        System.out.println("1.---------------------");
        System.out.println(Son.TAG);
        Son[] sons = new Son[10];
        System.out.println(sons);
        System.out.println("2.---------------------");
        System.out.println(Son.instance);
        System.out.println("3.---------------------");
        Son son = new Son();
        Father father = son;
        father.method();
        System.out.println(son);
    }
}
