package ck.itheima.com.takeout.utils;

import android.app.Application;

/**
 * 类名:    TakeoutApp
 * 创建者:  ckqu
 * 创建时间:2017/3/20 0020 下午 8:24
 * 包名:    ck.itheima.com.takeout.utils
 * 更新者:  $Author$ $Date$
 * 描述:    TODO
 */

public class TakeoutApp extends Application {
    public static TakeoutApp sInstace;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstace = this;
    }
}
