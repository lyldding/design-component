package com.lyldding.commonlib.mvp.presenter;

/**
 * @author https://github.com/lyldding
 * @date 2019/9/30
 */
public interface IBasePresenter<V> {

    void attachView(V view);


    void detachView();


    boolean isLive();


    V getView();
}
