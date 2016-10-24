package com.example.wkw.weather;

import android.app.Application;

import com.thinkland.sdk.android.JuheSDKInitializer;

/**
 * Created by wkw on 2016/10/24.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JuheSDKInitializer.initialize(getApplicationContext());
    }
}
