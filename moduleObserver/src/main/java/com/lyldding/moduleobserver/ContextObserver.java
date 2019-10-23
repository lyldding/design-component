package com.lyldding.moduleobserver;

import com.lyldding.moduleobserver.observer.Observable;
import com.lyldding.moduleobserver.observer.ObserverA;
import com.lyldding.moduleobserver.observer.ObserverB;
import com.lyldding.moduleobserver.observer.ObserverC;
import com.lyldding.moduleobserver.observer.interfaces.IObserver;

import java.util.Random;

/**
 * 观察者模式
 *
 * @author https://github.com/lyldding
 */
public class ContextObserver {
    public static void main(String[] args) {
        Random random = new Random();
        IObserver observerA = new ObserverA();
        IObserver observerB = new ObserverB();
        IObserver observerC = new ObserverC();

        Observable observable = new Observable();
        observable.addObserver(observerA);
        observable.addObserver(observerB);
        observable.addObserver(observerC);

        System.out.println("————————————————");
        observable.changeState(random.nextInt(100));

        System.out.println("————————————————");
        observable.removeObserver(observerA);
        observable.changeState(random.nextInt(100));

        System.out.println("————————————————");
        observable.addObserver(observerA);
        observable.removeObserver(observerB);
        observable.changeState(random.nextInt(100));
    }
}
