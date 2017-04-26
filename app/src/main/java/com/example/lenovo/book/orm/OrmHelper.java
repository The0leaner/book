package com.example.lenovo.book.orm;

import android.content.Context;

import com.example.lenovo.book.bean.BookDetail;

import java.util.List;



public class OrmHelper {
    private static OrmHelper instance;

    private static DaoMaster daoMaster = null;
    private static DaoSession daoSession = null;

    public OrmHelper(Context context) {
        MySqlOpenHelper openHelper = new MySqlOpenHelper(context , "ORMDB");
        daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static OrmHelper getInstance(Context context) {
        if(instance == null){
            instance = new OrmHelper(context);
        }
        return instance;
    }

    public void insertBook(BookDetail book){
        BookDetailDao bookDao = daoSession.getBookDetailDao();
        bookDao.insert(book);
    }
    public void insertBooks(List<BookDetail> lists){
        BookDetailDao bookDao =  daoSession.getBookDetailDao();
        bookDao.insertInTx(lists);
    }
    public void deleteBook(BookDetail bookDetail){
        BookDetailDao bookDao =  daoSession.getBookDetailDao();
        bookDao.delete(bookDetail);
    }
}
