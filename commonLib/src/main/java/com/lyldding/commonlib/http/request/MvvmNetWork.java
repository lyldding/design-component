package com.lyldding.commonlib.http.request;

import com.lyldding.commonlib.http.base.BaseEntity;
import com.lyldding.commonlib.http.base.BaseNetworkUtils;
import com.lyldding.commonlib.http.bean.Weather;
import com.lyldding.commonlib.http.sevices.MvvmApiServices;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * @author https://github.com/lyldding
 * @date 2019/10/16
 */
public class MvvmNetWork extends BaseNetworkUtils<MvvmApiServices> {

    private MvvmNetWork() {
        super();
    }

    private static final class Holder {
        private static final MvvmNetWork INSTANCE = new MvvmNetWork();
    }

    public static MvvmNetWork getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String getBaseUrl() {
        return null;
    }

    @Override
    public MvvmApiServices createApiService() {
        return mRetrofit.create(MvvmApiServices.class);
    }

    public Observable<BaseEntity<Weather>> getWeatherInfo(String city) {
        Map<String, String> map = new HashMap<>();

        return mApiService.getWeatherInfo();
    }
}
