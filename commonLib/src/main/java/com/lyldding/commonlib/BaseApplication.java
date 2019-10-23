package com.lyldding.commonlib;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lyldding.commonlib.utils.Utils;

import androidx.multidex.MultiDexApplication;

/**
 * @author https://github.com/lyldding
 * @date 2019/9/27
 */
public class BaseApplication extends MultiDexApplication {
    private static BaseApplication BaseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        BaseApplication = this;
        Utils.init(BaseApplication);
        if (BuildConfig.IS_DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(BaseApplication);
    }

    public static BaseApplication getInstance() {
        return BaseApplication;
    }
}
