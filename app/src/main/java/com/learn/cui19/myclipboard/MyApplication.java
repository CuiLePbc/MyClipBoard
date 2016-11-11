package com.learn.cui19.myclipboard;

import android.app.Application;

/**
 * Created by cui19 on 2016/11/11.
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static MyApplication getInstance(){
        return myApplication;
    }
}
