package com.example.lenovo.book.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class MySqlOpenHelper extends DaoMaster.OpenHelper{
    public MySqlOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion ==2 && oldVersion ==1){
            //TODO
        }
    }
}
