package com.example.lenovo.book.module;


import com.example.lenovo.book.bean.BookBean;
import com.example.lenovo.book.net.MyHttpCallback;

import okhttp3.Call;

public class BookDetailAction {

    public static void getBookDetail(String result, final IGetBookCB cb){

        final BookDetailTask task = new BookDetailTask(result);
        task.enqueue(new MyHttpCallback() {
            @Override
            public void onResponse(Call call, Object data) {
                if (cb != null) {
                    cb.onConnectSuccess(data);
                }
            }

            @Override
            public void onFailure(Call call, Exception e) {

                if(cb != null) {
                    cb.onConnectFailed(e);
                }
            }
        },true);
    }

    public static interface IGetBookCB{
        public void onConnectSuccess(Object BookBean);
        public void onConnectFailed(Exception e);
    }
}
