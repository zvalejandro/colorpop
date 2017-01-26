package com.zaraos.colorpop;

import android.os.Handler;

/**
 * Created by Alex on 26/01/17.
 */

public class Application extends android.app.Application {

    private static Application instance;
    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        instance = this;
    }

    public static Application getInstance() {
        return instance;
    }

    public Handler getHandler() {
        return handler;
    }

}
