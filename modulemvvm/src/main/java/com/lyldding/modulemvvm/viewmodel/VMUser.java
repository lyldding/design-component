package com.lyldding.modulemvvm.viewmodel;


import android.app.Application;

import com.lyldding.commonlib.http.base.BaseEntity;
import com.lyldding.commonlib.http.base.BaseObserver;
import com.lyldding.commonlib.http.bean.Weather;
import com.lyldding.commonlib.http.request.MvvmNetWork;
import com.lyldding.commonlib.mvvm.base.BaseViewModel;
import com.lyldding.commonlib.utils.MainLooper;
import com.lyldding.commonlib.utils.RxjavaUtils;
import com.lyldding.modulemvvm.bean.UserBean;
import com.lyldding.modulemvvm.bean.UserNoLiveBean;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

/**
 * @author https://github.com/lyldding
 * @date 2019/10/14
 */
public class VMUser extends BaseViewModel {
    //live data
    private MutableLiveData<UserBean> mUserInfo;

    private UserNoLiveBean mUserNoLiveBean;

    public VMUser(@NonNull Application application) {
        super(application);
        mUserInfo = new MutableLiveData<>();
        mUserNoLiveBean = new UserNoLiveBean();
    }

    public MutableLiveData<UserBean> getUserInfo() {
        return mUserInfo;
    }

    public UserNoLiveBean getUserNoLiveBean() {
        return mUserNoLiveBean;
    }

    public void loadUserInfo() {
        //模拟网络加载
        MainLooper.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                mUserInfo.setValue(UserBean.UserBeanBuilder.newBuilder().setName("我是live data").setAge("12").build());
                mUserNoLiveBean.name.set("我是 data binding");
                mUserNoLiveBean.age.set("100");
            }
        }, 500);
    }

    public void getWeatherInfo(String city) {
        MvvmNetWork.getInstance().getWeatherInfo(city)
                .compose(RxjavaUtils.<BaseEntity<Weather>>io2main())
                .as(this.<BaseEntity<Weather>>bindLifecycle())
                .subscribe(new BaseObserver<Weather>() {
                    @Override
                    public void onSuccess(BaseEntity<Weather> data) {

                    }

                    @Override
                    public void onFailure(int code, Throwable throwable) {

                    }
                });
    }

}
