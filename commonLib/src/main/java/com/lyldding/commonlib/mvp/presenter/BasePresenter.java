package com.lyldding.commonlib.mvp.presenter;

import java.lang.ref.WeakReference;

/**
 * @author https://github.com/lyldding
 */
public class BasePresenter<V> implements IBasePresenter<V> {

    private WeakReference<V> mView;

    @Override
    public void attachView(V view) {
        mView = new WeakReference<>(view);
    }


    @Override
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    @Override
    public boolean isLive() {
        return mView != null && mView.get() != null;
    }

    @Override
    public V getView() {
        return mView.get();
    }
}
