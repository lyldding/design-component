package com.lyldding.commonlib.mvvm.interfaces;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author https://github.com/lyldding
 * @date 2019/10/16
 */
public interface ILifecycleObserver extends LifecycleObserver {

    void setLifecycleOwner(LifecycleOwner lifecycleOwner);

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(@NonNull LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(@NonNull LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onLifecycleChange(@NonNull LifecycleOwner owner);
}
