package com.lyldding.commonlib.mvvm.base;

import android.app.Application;

import com.lyldding.commonlib.mvvm.interfaces.ILifecycleObserver;
import com.lyldding.commonlib.utils.AutoDisposeUtil;
import com.lyldding.commonlib.utils.LogUtils;
import com.uber.autodispose.AutoDisposeConverter;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;

/**
 * @author https://github.com/lyldding
 * @date 2019/10/16
 */
public class BaseViewModel extends AndroidViewModel implements ILifecycleObserver {
    private LifecycleOwner mLifecycleOwner;

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        LogUtils.e("onCreate");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        LogUtils.e("onDestroy");
    }

    @Override
    public void onLifecycleChange(@NonNull LifecycleOwner owner) {
        LogUtils.e("onLifecycleChange  = " + owner.getLifecycle().getCurrentState());
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        if (mLifecycleOwner == null) {
            throw new NullPointerException("mLifecycleOwner == null");
        }
        return AutoDisposeUtil.bindLifecycle(mLifecycleOwner);
    }
}
