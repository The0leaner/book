package com.example.lenovo.book.module;

import com.alibaba.fastjson.JSON;
import com.example.lenovo.book.bean.BookBean;
import com.example.lenovo.book.net.HttpMethod;
import com.example.lenovo.book.net.JSONRequest;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by lenovo on 2017/4/25.
 */

public class BookDetailTask extends JSONRequest {
    private String mIsbn;

    public BookDetailTask(String Isbn) {
        super();
        mIsbn = Isbn;
    }

    @Override
    public String getApi() {
        return "/isbn" + mIsbn;
    }

    @Override
    public int getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    public Class getModelClass() {
        return BookBean.class;
    }

    @Override
    public Object parseResponse(ResponseBody body) throws IOException {
        BookBean bookBean = JSON.parseObject(body.string() , BookBean.class);
        return bookBean;
    }
}
