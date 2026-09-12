package com.app.borrowapp2.models;

import android.os.Build;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Borrow {
    private int id,user_id,book_id;
    private String due_date;

    public Borrow(int id, int userId, int bookId) {
        this.id = id;
        user_id = userId;
        book_id = bookId;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            due_date = LocalTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy - hh:mm a"));
        }
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getBook_id() {
        return book_id;
    }
    public void setDue_date(String dueDate){
        due_date=dueDate;
    }
    public String getDue_date() {
        return due_date;
    }
}
