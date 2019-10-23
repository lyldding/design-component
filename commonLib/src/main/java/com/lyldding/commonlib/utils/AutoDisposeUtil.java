package com.lyldding.commonlib.utils;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/**
 * @author https://github.com/lyldding
 * @date 2019/10/16
 */
public class AutoDisposeUtil {
    public static <T> AutoDisposeConverter<T> bindLifecycle(LifecycleOwner owner) {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(owner, Lifecycle.Event.ON_DESTROY));
    }
}
