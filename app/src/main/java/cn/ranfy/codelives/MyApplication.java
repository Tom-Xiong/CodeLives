package cn.ranfy.codelives;

import android.app.Application;

/**
 * Created by ransom on 2017/9/26 10:38.
 */

public class MyApplication extends Application {


    public static MyApplication mInstance;

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public MyApplication getmInstance() {
        return mInstance;
    }

}
