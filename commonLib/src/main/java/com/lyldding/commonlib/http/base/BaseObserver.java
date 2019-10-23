package com.lyldding.commonlib.http.base;

import com.lyldding.commonlib.utils.GsonUtils;
import com.lyldding.commonlib.utils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author https://github.com/lyldding
 * @date 2019/10/16
 */
public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {
    private static final int DEFAULT_CODE = -1;
    private static final int DEFAULT_SUCCESS = -110;
    private static final String TAG = "net_log@";

    public abstract void onSuccess(BaseEntity<T> data);

    public abstract void onFailure(int code, Throwable throwable);

    @Override
    public void onSubscribe(Disposable d) {

    }

    private boolean isSuccess(int code) {
        return code == DEFAULT_SUCCESS;
    }

    @Override
    public void onNext(BaseEntity<T> tBaseEntity) {
        LogUtils.e(TAG + "code = " + tBaseEntity.getCode() + "\n" + GsonUtils.toJson(tBaseEntity));
        if (isSuccess(tBaseEntity.getCode())) {
            onSuccess(tBaseEntity);
        } else {
            onFailure(tBaseEntity.getCode(), new Throwable(tBaseEntity.getMsg()));
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e(TAG + "code = " + DEFAULT_CODE + "\n" + e.getMessage());
        onFailure(DEFAULT_CODE, e);
    }

    @Override
    public void onComplete() {

    }
}
