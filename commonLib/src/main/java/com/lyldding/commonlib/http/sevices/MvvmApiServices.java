package com.lyldding.commonlib.http.sevices;

import com.lyldding.commonlib.http.base.BaseEntity;
import com.lyldding.commonlib.http.bean.Weather;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author https://github.com/lyldding
 * @date 2019/10/15
 */
public interface MvvmApiServices {

    @GET("weather/city/101030100")
    Observable<BaseEntity<Weather>> getWeatherInfo();
}
