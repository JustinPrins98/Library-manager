package com.justinprins.library.service;

import com.justinprins.library.repository.BookRepository;
import com.justinprins.library.domain.Book;
import java.util.List;
import java.util.Collection;

public class LibraryServiceImpl implements LibraryService {
    private final BookRepository repository;

    public LibraryServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void borrowBook(int id) {
        Book book = repository.findById(id);
        book.borrow();

        repository.save(book);
    }

    @Override
    public void returnBook(int id) {
        Book book = repository.findById(id);
        book.returnBook();

        repository.save(book);
    }

    @Override
    public List<Book> listAvailableBooks() {

        Collection<Book> books = repository.findAll();
        return books.stream()
                .filter(book -> book.getAvailableCopies() > 0)
                .toList();
    }
}