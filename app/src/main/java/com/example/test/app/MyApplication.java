package com.example.test.app;

import android.app.Application;
import android.content.Context;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbusperf.MyEventBusIndex;

public class MyApplication extends Application {

    public static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();

        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }

}
