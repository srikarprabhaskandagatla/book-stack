package com.srikarprabhaskandagatla.bookstack.model;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int quantity;

    public Book() {}

    public Book(String title, int quantity) {
        this.title = title;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getQuantity() { return quantity; }
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}