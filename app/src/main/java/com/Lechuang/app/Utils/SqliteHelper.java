package com.Lechuang.app.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Android on 2017/8/22.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    //用来保存UserID、Access Token、Access Secret的表名
    public static final String TB_NAME = "users.db";

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    public SqliteHelper(Context context) {
        //这三个参数分别为上下文对象,数据库名称,游标,版本号
        super(context, TB_NAME, null, 6);
    }

    //创建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE friendinfo (_id INTEGER PRIMARY KEY AUTOINCREMENT, userid VARCHAR,name VARCHAR, imagehead VARCHAR)");
    }

    //更新表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS friend");
        onCreate(db);
    }

    //更新列
    public void updateColumn(SQLiteDatabase db, String oldColumn, String newColumn, String typeColumn) {
        try {
            db.execSQL("ALTER TABLE " +
                    TB_NAME + " CHANGE " +
                    oldColumn + " " + newColumn +
                    " " + typeColumn
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
