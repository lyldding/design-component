package com.lyldding.modulemain.arouter_interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.lyldding.commonlib.utils.LogUtils;

import java.util.Random;

/**
 * 登录拦截器，通过arouter跳转的activity都是需要通过拦截器的，
 * 如果不需要通过拦截器，需要设置greenChannel()
 *
 * @author https://github.com/lyldding
 * @date 2019/10/22
 */
@Interceptor(name = "login", priority = 3)
public class LoginInterceptor implements IInterceptor {
    private Context mContext;
    private Random mRandom = new Random();

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        LogUtils.e(postcard.getPath());
        //模拟登录状态
        if (mRandom.nextInt(2) != 1) {
            callback.onContinue(postcard);
        } else {
            callback.onInterrupt(new Throwable("未登录"));
        }
    }

    @Override
    public void init(Context context) {
        mContext = context;
        LogUtils.e("LoginInterceptor init.");
    }
}
