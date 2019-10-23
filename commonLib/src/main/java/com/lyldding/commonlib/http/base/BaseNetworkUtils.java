package com.lyldding.commonlib.http.base;

import android.text.TextUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author https://github.com/lyldding
 * @date 2019/10/15
 */
public abstract class BaseNetworkUtils<S> {
    private static final String DEFAULT_BASE_URL = "http://t.weather.sojson.com/api/";
    protected Retrofit mRetrofit;
    protected S mApiService;

    public BaseNetworkUtils() {
        init();
    }

    private void init() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(TextUtils.isEmpty(getBaseUrl()) ? DEFAULT_BASE_URL : getBaseUrl())
                .client(buildOkhttp())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiService = createApiService();
    }

    private OkHttpClient buildOkhttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.build();
    }

    public abstract String getBaseUrl();

    public abstract S createApiService();
}
