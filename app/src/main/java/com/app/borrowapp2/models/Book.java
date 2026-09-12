package com.app.borrowapp2.models;

public class Book {
    private int id,quantity;
    private String title,description, author;

    public Book(int id, String title, String description, String author,int quantity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.quantity = quantity;
    }
    public int getId() {
        return id;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }
}
