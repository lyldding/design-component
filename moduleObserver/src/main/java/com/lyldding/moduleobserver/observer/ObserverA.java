package com.lyldding.moduleobserver.observer;

import com.lyldding.moduleobserver.observer.interfaces.IObserver;

/**
 * @author https://github.com/lyldding
 */
public class ObserverA implements IObserver {
    @Override
    public void notifyChange(String msg) {
        System.out.println(getClass().getSimpleName() + ": " + msg);
    }
}
