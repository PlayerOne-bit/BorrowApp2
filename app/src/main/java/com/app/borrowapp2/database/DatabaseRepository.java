package com.app.borrowapp2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.app.borrowapp2.models.Book;
import com.app.borrowapp2.models.Borrow;
import com.app.borrowapp2.models.User;

import java.util.List;

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
        db.execSQL("CREATE TABLE " +TABLE_BORROW +"("+
                COLUMN_BORROW_ID+" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"+
                COLUMN_BORROW_USER_ID+" INTEGER REFERENCES "+TABLE_USER+"("+COLUMN_USER_ID+"),"+
                COLUMN_BORROW_BOOK_ID+" INTEGER REFERENCES "+TABLE_BOOK+"("+COLUMN_BOOK_ID+"),"+
                COLUMN_BORROW_DUE_DATE+" DATE"+
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BOOK);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BORROW);
    }
    public boolean register(User user){
        long result=0;
        try(SQLiteDatabase db = this.getWritableDatabase()){
            ContentValues values = getValues(user);
            result = db.insert(TABLE_USER,null,values);
        }
        return result!=0;
    }
    public boolean login(User user){
        try(SQLiteDatabase db = this.getReadableDatabase()){
            Cursor cursor = db.query(TABLE_USER,new String[]{user.getUsername(), user.getPassword()},"username=? AND password=?",);
        }
    }

    private ContentValues getValues(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_USERNAME,user.getUsername());
        values.put(COLUMN_USER_PASSWORD,user.getPassword());
        return values;
    }
    private ContentValues getValues(Book book){
        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOK_TITLE,book.getTitle());
        values.put(COLUMN_BOOK_DESCRIPTION,book.getDescription());
        values.put(COLUMN_BOOK_AUTHOR,book.getAuthor());
        values.put(COLUMN_BOOK_QUANTITY,book.getQuantity());
        return values;
    }
    private ContentValues getValues(Borrow borrow) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_BORROW_BOOK_ID, borrow.getBook_id());
        values.put(COLUMN_BORROW_USER_ID, borrow.getUser_id());
        values.put(COLUMN_BORROW_DUE_DATE, borrow.getDue_date());
        return values;
    }
}