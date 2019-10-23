package com.lyldding.moduleobserver.observer;

import com.lyldding.moduleobserver.observer.interfaces.IObservable;
import com.lyldding.moduleobserver.observer.interfaces.IObserver;

import java.util.HashSet;
import java.util.Set;

/**
 * @author https://github.com/lyldding
 */
public class Observable implements IObservable {
    private Set<IObserver> mObservers;
    private int mState;

    public Observable() {
        mObservers = new HashSet<>();
    }

    public void changeState(int state) {
        mState = state;
        notifyObservers();
    }

    @Override
    public void addObserver(IObserver observer) {
        mObservers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        if (mObservers.contains(observer)) {
            mObservers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : mObservers) {
            observer.notifyChange("新状态为 ：" + mState);
        }
    }
}
