package com.example.lenovo.book.net;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by zw on 16/10/13.
 */
public class HttpClientWrapper {

    public static final String DEFUALT_TOKEN = "netease";

    private OkHttpClient mOkHttpClient;
    private ExecutorDelivery mDelivery;

    public HttpClientWrapper(Context context) {
        Handler handler = new Handler(Looper.getMainLooper());
        mDelivery = new ExecutorDelivery(handler);

        mOkHttpClient = new OkHttpClient.Builder().connectTimeout(3, TimeUnit.SECONDS).readTimeout(3, TimeUnit.SECONDS).writeTimeout(3, TimeUnit.SECONDS).build();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public ExecutorDelivery getDelivery() {
        return mDelivery;
    }


}
