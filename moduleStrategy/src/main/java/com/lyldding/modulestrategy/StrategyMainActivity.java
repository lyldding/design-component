package com.lyldding.modulestrategy;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lyldding.commonlib.Constants;
import com.lyldding.commonlib.mvp.AbstractBaseMvpActivity;
import com.lyldding.modulestrategy.contract.ContractStrategy;
import com.lyldding.modulestrategy.presenter.PresenterStrategy;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author https://github.com/lyldding
 */
@Route(path = Constants.GROUP_STRATEGY + "main")
public class StrategyMainActivity extends
        AbstractBaseMvpActivity<ContractStrategy.View, ContractStrategy.Presenter>
        implements ContractStrategy.View, View.OnClickListener {

    @BindView(R2.id.mode)
    TextView mMode;
    @BindView(R2.id.add)
    TextView mAdd;
    @BindView(R2.id.sub)
    TextView mSub;
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;


    @Override
    public int getContentViewId() {
        return R.layout.strategy_activity_main;
    }

    @Override
    public PresenterStrategy createPresenter() {
        return new PresenterStrategy(this);
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mAdd.setVisibility(View.GONE);
        mSub.setVisibility(View.GONE);

        mAdd.setOnClickListener(this);
        mSub.setOnClickListener(this);

        //例子
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        Animation animation = new AlphaAnimation(0f, 1f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
    }

    @Override
    public void initData() {
        getPresenter().getData();
    }

    @Override
    public void onSuccess() {
        mAdd.setVisibility(View.VISIBLE);
        mSub.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.add) {
            getPresenter().setAddStrategy();
        } else if (id == R.id.sub) {
            getPresenter().setSubStrategy();
        }
        showToast("计算结果为 ：" + getPresenter().executeCompute(10, 5));
    }
}
