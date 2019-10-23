package com.lyldding.commonlib.mvvm.base;

import android.os.Bundle;

import com.lyldding.commonlib.utils.AutoDisposeUtil;
import com.lyldding.commonlib.utils.LogUtils;
import com.uber.autodispose.AutoDisposeConverter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author https://github.com/lyldding
 * @date 2019/10/15
 */
public abstract class AbstractBaseMvvmActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {
    protected V mBinding;
    protected VM mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getContentViewId());
        if (getVmClass() == null) {
            LogUtils.e("getVmClass() == null");
        } else {
            mViewModel = ViewModelProviders.of(this).get(getVmClass());
            //防止Rxjava使用时的内存泄漏
            mViewModel.setLifecycleOwner(this);
            getLifecycle().addObserver(mViewModel);
        }
        //为livedata添加生命周期
        mBinding.setLifecycleOwner(this);
        init();
    }

    protected abstract int getContentViewId();

    protected abstract Class<VM> getVmClass();

    protected abstract void init();

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return AutoDisposeUtil.bindLifecycle(this);
    }

    public V getBinding() {
        return mBinding;
    }

    public VM getViewModel() {
        if (mViewModel == null) {
            throw new NullPointerException("mViewModel==null");
        }
        return mViewModel;
    }

    @Override
    protected void onDestroy() {
        if (mViewModel != null) {
            getLifecycle().addObserver(mViewModel);
        }
        super.onDestroy();
    }
}
