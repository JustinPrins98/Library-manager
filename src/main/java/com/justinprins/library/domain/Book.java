package com.justinprins.library.domain;

import com.justinprins.library.exception.*;

public class Book {

    private final int id;
    private final String title;
    private final String author;
    private int availableCopies;

    public Book(int id, String title, String author, int availableCopies) {

        // Validation
        if (id <= 0) {
            throw new IllegalArgumentException("Id must not be equal or less than zero");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be blank");
        }

        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("author must not be blank");
        }

        if (availableCopies < 0) {
            throw new IllegalArgumentException("availableCopies cannot be negative");
        }

        // Assign
        this.id = id;
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public void borrow() {

        if (this.availableCopies <= 0) {
            throw new NoAvailableCopiesException("Book is not available for borrowing");
        }

        this.availableCopies--;
    }

    public void returnBook() {
        this.availableCopies++;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

}
