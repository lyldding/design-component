package com.lyldding.moduleobserver.observer.interfaces;

/**
 * @author https://github.com/lyldding
 */
public interface IObservable {
    void addObserver(IObserver observer);

    void removeObserver(IObserver observer);

    void notifyObservers();
}
