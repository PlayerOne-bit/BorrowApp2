package com.app.borrowapp2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseRepository extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME="borrow_app.db",
    TABLE_USER="user", TABLE_BOOK="book", TABLE_BORROW="borrow",
    COLUMN_USER_ID="id", COLUMN_BOOK_ID="id", COLUMN_BORROW_ID="id",
    COLUMN_USER_USERNAME="username", COLUMN_USER_PASSWORD="password",
    COLUMN_BOOK_TITLE="title",COLUMN_BOOK_DESCRIPTION="description",COLUMN_BOOK_AUTHOR="author", COLUMN_BOOK_QUANTITY="quantity",
    COLUMN_BORROW_USER_ID="user_id",COLUMN_BORROW_BOOK_ID="book_id", COLUMN_BORROW_DUE_DATE="due_date";

    public DatabaseRepository(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +TABLE_USER +"("+
                COLUMN_USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"+
                COLUMN_USER_USERNAME+" TEXT,"+
                COLUMN_USER_PASSWORD+" TEXT"+
                ");");
        db.execSQL("CREATE TABLE " +TABLE_BOOK +"("+
                COLUMN_BOOK_ID+" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"+
                COLUMN_BOOK_TITLE+" TEXT,"+
                COLUMN_BOOK_DESCRIPTION+" TEXT,"+
                COLUMN_BOOK_AUTHOR+" TEXT,"+
                COLUMN_BOOK_QUANTITY+" TEXT"+
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}