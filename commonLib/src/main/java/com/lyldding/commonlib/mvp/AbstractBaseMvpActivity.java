package com.lyldding.commonlib.mvp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lyldding.commonlib.mvp.presenter.IBasePresenter;
import com.lyldding.commonlib.mvp.view.ILoadView;
import com.lyldding.commonlib.mvp.view.IToastView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author https://github.com/lyldding
 * @date 2019/9/29
 */
public abstract class AbstractBaseMvpActivity<V, P extends IBasePresenter<V>>
        extends AppCompatActivity implements IToastView, ILoadView {
    private static final String TAG = "AbstractBaseMvpActivity";
    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        mPresenter = createPresenter();
        if (mPresenter == null) {
            Log.e(TAG, "mPresenter 为空");
        }
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }

        super.onDestroy();
    }

    /**
     * ContentView
     *
     * @return id
     */
    public abstract int getContentViewId();

    /**
     * 创建Presenter
     */
    public abstract P createPresenter();

    public P getPresenter() {
        if (mPresenter == null) {
            throw new NullPointerException(" mPresenter 为空");
        }
        return mPresenter;
    }

    public abstract void initView();

    public abstract void initData();

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadView() {
        Toast.makeText(this, "加载中", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoadView() {
        Toast.makeText(this, "加载成功", Toast.LENGTH_SHORT).show();
    }
}
