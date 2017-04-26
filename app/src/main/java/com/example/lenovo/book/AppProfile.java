package com.example.lenovo.book;

import android.content.Context;

import com.example.lenovo.book.net.HttpClientWrapper;


public class AppProfile {
    private static Context sApplicationContext;
    private static HttpClientWrapper sHttpClientWrapper;


    public static HttpClientWrapper getHttpClientWrapper() {
        return sHttpClientWrapper;
    }

    public static Context getContext() {
        return sApplicationContext;
    }

    public static void init(Context context) {
        sApplicationContext = context;
        sHttpClientWrapper = new HttpClientWrapper(context);
    }
}
