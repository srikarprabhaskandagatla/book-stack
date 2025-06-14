package com.srikarprabhaskandagatla.bookstack.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class BorrowedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int bookId;
    private int userId;
    private LocalDate dueDate;

    public BorrowedBook() {}

    public BorrowedBook(int bookId, int userId, LocalDate dueDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.dueDate = dueDate;
    }

    public int getId() { return id; }
    public int getBookId() { return bookId; }
    public int getUserId() { return userId; }
    public LocalDate getDueDate() { return dueDate; }
    public void setId(int id) { this.id = id; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}