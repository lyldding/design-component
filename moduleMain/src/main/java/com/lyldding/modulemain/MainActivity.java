package com.lyldding.modulemain;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lyldding.commonlib.Constants;
import com.lyldding.commonlib.mvp.AbstractBaseMvpActivity;
import com.lyldding.commonlib.utils.ToastUtils;
import com.lyldding.modulemain.contract.ContractMain;
import com.lyldding.modulemain.presenter.PresenterMain;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author https://github.com/lyldding
 */
@Route(path = Constants.GROUP_MAIN + "main")
public class MainActivity extends AbstractBaseMvpActivity<ContractMain.View, ContractMain.Presenter>
        implements ContractMain.View, View.OnClickListener {

    @BindView(R2.id.type)
    TextView mType;
    @BindView(R2.id.button1)
    TextView mButton1;
    @BindView(R2.id.button2)
    TextView mButton2;
    @BindView(R2.id.button3)
    TextView mButton3;
    @BindView(R2.id.button4)
    TextView mButton4;

    private Random mRandom;

    @Override
    public int getContentViewId() {
        return R.layout.main_activity_main;
    }

    @Override
    public ContractMain.Presenter createPresenter() {
        return new PresenterMain(this);
    }


    @Override
    public void initView() {
        ButterKnife.bind(this);
        // ARouter.getInstance().inject(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mType.setOnClickListener(this);

        mButton1.setVisibility(View.GONE);
        mButton2.setVisibility(View.GONE);
        mButton3.setVisibility(View.GONE);
        mButton4.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        mRandom = new Random();
    }

    @Override
    public void updateName(String msg) {
        mButton1.setVisibility(View.VISIBLE);
        mButton2.setVisibility(View.VISIBLE);
        mButton3.setVisibility(View.VISIBLE);
        mButton4.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String path = mRandom.nextInt(2) == 1 ? "main" : "wtf";
        if (id == R.id.button1) {
            //绿色通道不经过拦截器
            ARouter.getInstance().build(Constants.GROUP_STRATEGY + path).greenChannel().navigation();
        } else if (id == R.id.button2) {
            ARouter.getInstance().build(Constants.GROUP_OBSERVE + path).navigation(this, new NavigationCallback() {
                @Override
                public void onFound(Postcard postcard) {
                    ToastUtils.showShort("onFound");
                }

                @Override
                public void onLost(Postcard postcard) {
                    ToastUtils.showShort("没找到：我是局部的策略");
                }

                @Override
                public void onArrival(Postcard postcard) {
                    ToastUtils.showShort("onArrival");
                }

                @Override
                public void onInterrupt(Postcard postcard) {
                    ToastUtils.showShort("onInterrupt");
                }
            });
        } else if (id == R.id.button3) {
            ARouter.getInstance().build(Constants.GROUP_MVVM + path).navigation();
        } else if (id == R.id.button4) {
            ARouter.getInstance().build(Constants.GROUP_KOTLIN + path).navigation();
        } else if (id == R.id.type) {
            getPresenter().getData();
        }
    }
}
