package com.lyldding.modulemvvm;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lyldding.commonlib.Constants;
import com.lyldding.commonlib.mvvm.base.AbstractBaseMvvmActivity;
import com.lyldding.commonlib.utils.LogUtils;
import com.lyldding.modulemvvm.bean.UserBean;
import com.lyldding.modulemvvm.databinding.MvvmActivityMainBinding;
import com.lyldding.modulemvvm.viewmodel.VMUser;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author https://github.com/lyldding
 */
@Route(path = Constants.GROUP_MVVM + "main")
public class MvvmMainActivity extends AbstractBaseMvvmActivity<MvvmActivityMainBinding, VMUser> implements View.OnClickListener {

    @Override
    protected int getContentViewId() {
        return R.layout.mvvm_activity_main;
    }

    @Override
    protected Class<VMUser> getVmClass() {
        return VMUser.class;
    }

    @Override
    protected void init() {
        getViewModel().getUserNoLiveBean().name.set("data binding init");
        getViewModel().getUserNoLiveBean().age.set("0");
        getBinding().setUserNoLive(getViewModel().getUserNoLiveBean());

        getViewModel().getUserInfo().setValue(UserBean.UserBeanBuilder.newBuilder().setName("live data init").setAge("0").build());
        getBinding().setVMUser(getViewModel());


        //添加点击事件
        getBinding().setEventListener(this);

//        //live data 的数据监听
//        getViewModel().getUserInfo().observe(this, new Observer<UserBean>() {
//            @Override
//            public void onChanged(UserBean userBean) {
//                Toast.makeText(MvvmMainActivity.this, userBean.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        ExecutorService executorService = new ThreadPoolExecutor(0, 60,
                10, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "test");
            }
        });
        CountDownLatch countDownLatch = new CountDownLatch(2);
        long start = System.currentTimeMillis();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                LogUtils.d("000000");
                countDownLatch.countDown();
                LogUtils.d("111111111");
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                LogUtils.d("33333333");
                countDownLatch.countDown();
                LogUtils.d("2222222");
            }
        });

        try {
            countDownLatch.await();
            LogUtils.d("55555555555");
            LogUtils.d("time = " + (System.currentTimeMillis() - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        LogUtils.e("haaoaha");
//        if (view.getId() == R.id.title) {
//            getViewModel().loadUserInfo();
//            getViewModel().getWeatherInfo("11");
//        }
    }
}
