package com.lyldding.modulemain.degradeservice;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.lyldding.commonlib.utils.LogUtils;
import com.lyldding.commonlib.utils.ToastUtils;

/**
 * @author https://github.com/lyldding
 * @date 2019/10/22
 */
@Route(path = "/degrade/app")
public class AppDegradeService implements DegradeService {
    private Context mContext;

    @Override
    public void onLost(Context context, Postcard postcard) {
        ToastUtils.showShort("没找到：我是全局降级策略");
    }

    @Override
    public void init(Context context) {
        mContext = context;
        LogUtils.e("init");
    }
}
